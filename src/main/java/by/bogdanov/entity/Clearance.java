package by.bogdanov.entity;

import java.util.Date;
import java.util.List;

public class Clearance extends Entity{

    private int id;
    private String name;
    private Date startDate;
    private Date endDate;
    private int discount;
    private int operation_id;
    private Operation operation;

    public Clearance(){}
    public Clearance(int id, String name, Date startDate, Date endDate, int discount){
        this.id = id;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.discount = discount;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setDiscount(int discont) {
        this.discount = discont;
    }

    public int getDiscount() {
        return discount;
    }

    public void setOperation_id(int operation_id) {
        this.operation_id = operation_id;
    }

    public int getOperation_id() {
        return operation_id;
    }

    public void setOperation(Operation operation) {
        this.operation = operation;
    }

    public Operation getOperation() {
        return operation;
    }

    public String toString(){
        return "Id: " + this.id + "\n"
                +"Clearance name: " + this.name + "\n"
                +"Works: " + this.operation + "\n"
                +"Start - End: " + this.startDate + " -- " + this.endDate + "\n"
                +"Discount " + this.discount + "%";
    }
    public boolean equals(Object obj){
        if(obj == this){
            return true;
        }
        if(obj == null || obj.getClass() != this.getClass()){
            return false;
        }
        Clearance clearance = (Clearance) obj;
        if(clearance.id != this.id){
            return false;
        }
        if(clearance.name != this.name){
            return false;
        }
        if(clearance.startDate != this.startDate){
            return false;
        }
        if(clearance.endDate != this.endDate){
            return false;
        }
        if(clearance.discount != this.discount){
            return false;
        }
        if(clearance.operation != this.operation){
            return false;
        }
        return true;
    }

    public int hashCode(){
        final int number = 31;
        int result = 1;
        result = number * result + id;
        result = number * result + name.hashCode();
        result = number * result + startDate.hashCode();
        result = number * result + endDate.hashCode();
        result = number * result + discount;
        result = number * result + operation.hashCode();
        return result;
    }
}
