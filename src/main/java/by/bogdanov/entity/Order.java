package by.bogdanov.entity;

import java.util.Date;
import java.util.List;

public class Order extends Entity {

    private int id;
    private int vehicleId;
    private int price;
    private Date date;
    private int userId;
    private int managerId;
    private List<Operation> operationList;
    private Vehicle vehicle;

    public Order(){}
    public Order(int id, int vehicleId, int price, Date date, int userId, int managerId){
        this.id = id;
        this.vehicleId = vehicleId;
        this.price = price;
        this.date = date;
        this.userId = userId;
        this.managerId = managerId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setManagerId(int managerId) {
        this.managerId = managerId;
    }

    public int getManagerId() {
        return managerId;
    }



    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }

    public int getVehicleId() {
        return vehicleId;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getDate() {
        return date;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getUserId() {
        return userId;
    }

    public void setOperationList(List<Operation> operationList) {
        this.operationList = operationList;
    }

    public List<Operation> getOperationList() {
        return operationList;
    }

    public String toString(){
        return "Client id: " + this.userId + "\n" +
                "Order id: " + this.id + "; Price: " + this.price + "\n" +
                     "Car: " + this.vehicle + "\n" +
                "Manager :" + this.managerId + "\n" +
                this.date+"\n" + this.operationList;
    }

    public boolean equals(Object obj){
        if(obj == this){
            return true;
        }
        if(obj == null || obj.getClass() != this.getClass()){
            return false;
        }
        Order order = (Order) obj;
        if(order.id != this.id){
            return false;
        }
        if(order.price != this.price){
            return false;
        }
        if(order.date != this.date){
            return false;
        }
        if(order.userId != this.userId){
            return false;
        }
        if(order.vehicleId != this.vehicleId){
            return false;
        }
        return true;
    }
    public int hashCode(){
        final int number = 31;
        int result = 1;
        result = number * result + id;
        result = number * result + price;
        result = number * result + date.hashCode();
        result = number * result + userId;
        result = number * result + vehicleId;
        return result;
    }
}
