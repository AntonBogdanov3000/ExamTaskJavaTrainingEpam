package by.bogdanov.dao.pool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.LinkedBlockingQueue;
import by.bogdanov.dao.DaoException;
import com.mysql.cj.jdbc.Driver;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

final public class ConnectionPool {

private final Logger logger = LogManager.getLogger(ConnectionPool.class);

    Driver driver;
	private  String url = "jdbc:mysql://localhost:3306/carshop";
	private  String user = "root";
	private  String password = "Americana22";
	private  String driverClass ="com.mysql.cj.jdbc.Driver";
	private int maxSize = 32;
	private int checkConnectionTimeout = 30;


	private BlockingQueue<PooledConnection> freeConnections = new LinkedBlockingQueue<>();
	private Set<PooledConnection> usedConnections = new ConcurrentSkipListSet<>();

	private ConnectionPool(){}



	public synchronized Connection getConnection() throws DaoException {
		PooledConnection connection = null;
		while(connection == null) {
			try {
				if(!freeConnections.isEmpty()) {
					connection = freeConnections.take();
					if(!connection.isValid(checkConnectionTimeout)) {
						try {
							connection.getConnection().close();
						} catch(SQLException e) {
							logger.debug(e.getMessage());
						}
						connection = null;
					}
				} else if(usedConnections.size() < maxSize) {
					connection = createConnection();
				} else {
					logger.error("The limit of number of database connections is exceeded");
					throw new DaoException();
				}
			} catch(InterruptedException| ClassNotFoundException | SQLException e) {
				logger.error("It is impossible to connect to a database", e);
				throw new DaoException(e);
			}
		}
		usedConnections.add(connection);
		logger.debug(String.format("Connection was received from pool. Current pool size: %d used connections; %d free connection", usedConnections.size(), freeConnections.size()));
		return connection;
	}

	synchronized void freeConnection(PooledConnection connection) {
		try {
			if(connection.isValid(checkConnectionTimeout)) {
				connection.clearWarnings();
				connection.setAutoCommit(true);
				usedConnections.remove(connection);
				freeConnections.put(connection);
				logger.debug(String.format("Connection was returned into pool. Current pool size: %d used connections; %d free connection", usedConnections.size(), freeConnections.size()));
			}
		} catch(SQLException | InterruptedException e1) {
			logger.warn("It is impossible to return database connection into pool", e1);
			try {
				connection.getConnection().close();
			} catch(SQLException e2){
				logger.debug(e2.getMessage());
			}
		}
	}

	public synchronized void init(String driverClass, String url, String user, String password, int startSize, int maxSize, int checkConnectionTimeout) throws DaoException {
		try {
			destroy();
			Class.forName(driverClass);
			this.url = url;
			this.user = user;
			this.password = password;
			System.out.println("the pass" + password);
			this.maxSize = maxSize;
			this.checkConnectionTimeout = checkConnectionTimeout;
			for(int counter = 0; counter < startSize; counter++) {
				freeConnections.put(createConnection());
			}
		} catch(ClassNotFoundException | SQLException | InterruptedException  e) {
			logger.fatal("It is impossible to initialize connection pool", e);
			throw new DaoException(e);
		}
	}

	private static ConnectionPool instance = new ConnectionPool();

	public static ConnectionPool getInstance() {
		return instance;
	}

	private PooledConnection createConnection() throws SQLException ,ClassNotFoundException{
		logger.info("Try to connect");
		driver=new com.mysql.cj.jdbc.Driver();
		DriverManager.registerDriver(driver);
		return new PooledConnection(DriverManager.getConnection(url,user,password));
	}

	public synchronized void destroy() {
		usedConnections.addAll(freeConnections);
		freeConnections.clear();
		for(PooledConnection connection : usedConnections) {
			try {
				connection.getConnection().close();
			} catch(SQLException e) {
				logger.debug(e.getMessage());
			}
		}
		usedConnections.clear();
	}

}
