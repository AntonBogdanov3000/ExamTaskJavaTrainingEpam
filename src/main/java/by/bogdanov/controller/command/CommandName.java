package by.bogdanov.controller.command;

public enum CommandName {
    REGISTRATION,
    LOGIN,
    READ_USER_CAR,
    READ_USER_ORDER,
    ADD_CAR,
    DELETE_CAR,
    LOGOUT,
    SHOW_PRICES,
    SHOW_ORDERS,
    CREATE_ORDER,
    UPDATE_ORDER,
    SHOW_CLIENTS,
    CREATE_MANAGER,
    SHOW_ALL_CLEARANCE,
    SHOW_ALL_CARS,
    DELETE_USER,
    UPDATE_USER,
    DELETE_ORDER,
    CHANGE_LANG,
    CREATE_CLEARANCE,
    DELETE_CLEARANCE,
    GET_CLIENT_CARS;

    public String getName(){
        return name();
    }
}
