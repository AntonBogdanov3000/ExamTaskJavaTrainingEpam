package by.bogdanov.entity;

import java.util.List;

public class User extends Entity {

    private int id;
    private String name;
    private String lastName;
    private String password;
    private String login;
    private String telephone;
    private int role;
    private List<Vehicle> carList;
    private List<Order> orderList;

    public User(){}
    public User( String name, String lastName, String password,String login,String telephone,int role){
        this.name = name;
        this.lastName = lastName;
        this.password = password;
        this.login = login;
        this.telephone = telephone;
        this.role = role;
    }
    public void setId(int id){
        this.id = id;
    }
    public int getId(){
        return id;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return name;
    }
    public void setLastName(String lastName){
        this.lastName = lastName;
    }
    public String getLastName(){
        return lastName;
    }
    public void setPassword(String password){
        this.password = password;
    }
    public String getPassword(){
        return password;
    }
    public void setLogin(String login){
        this.login = login;
    }
    public String getLogin(){
        return login;
    }

    public void setTelephone(String telephone){
        this.telephone = telephone;
    }
    public String getTelephone(){
        return telephone;
    }

    public void setRole(int role) {
        this.role = role;
    }
    public int getRole(){
        return role;
    }

    public void setCarList(List<Vehicle> carList){
        this.carList = carList;
    }
    public List<Vehicle> getCarList(){
        return carList;
    }

    public void setOrderList(List<Order> orderList){
        this.orderList = orderList;
    }
    public List<Order> orderList(){
        return orderList;
    }

    public String toString(){
        return "User id: " + this.id + "\n"
                +this.name +" " + this.lastName + "\n" +
                "Login: " + this.login + "  Pass: " + this.password + "\n"
                +"Tel: " + this.telephone + "\n";

    }
    public boolean equals(Object obj){
        if(obj == this){
            return true;
        }
        if(obj == null || obj.getClass() != this.getClass()){
            return false;
        }
        User user = (User) obj;

        if(!user.name.equals(this.name)){
            return false;
        }
        if(!user.lastName.equals(this.lastName)){
            return false;
        }
        if(!user.password.equals(this.password)){
            return false;
        }
        if(!user.login.equals(this.login)){
            return false;
        }
        if(!user.telephone.equals(this.telephone)){
            return false;
        }
        if(user.role != this.role){
            return false;
        }
        if(user.carList != this.carList){
            return false;
        }

        if(user.orderList != this.orderList){
            return false;
        }
        return true;
    }
    public int hashCode(){
        final int number = 31;
        int result = 1;
        result = number * result + id;
        result = number * result + name.hashCode();
        result = number * result + lastName.hashCode();
        result = number * result + password.hashCode();
        result = number * result + login.hashCode();
        result = number * result + carList.hashCode();
        result = number * result + telephone.hashCode();
        result = number * result + orderList.hashCode();

        return result;
    }
}
