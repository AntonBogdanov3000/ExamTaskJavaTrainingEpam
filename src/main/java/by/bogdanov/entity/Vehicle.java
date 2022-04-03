package by.bogdanov.entity;

public class Vehicle extends Entity {

    private int id;
    private int ownerId;
    private String plate;
    private String model;
    private int year;
    private int mileage;

    public Vehicle(){
    }

    public Vehicle(int id, int ownerId, String model,
                   String plate, int year, int mileage ){
        this.id = id;
        this.ownerId = ownerId;
        this.model = model;
        this.plate = plate;
        this.year = year;
        this.mileage = mileage;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setOwnerId(int ownerId){
        this.ownerId = ownerId;
    }
    public long getOwnerId(){
        return ownerId;
    }

    public void setModel(String model){
        this.model = model;
    }

    public String getModel(){
        return model;
    }

    public void setPlate(String plate){
        this.plate = plate;
    }

    public String getPlate(){
        return plate;
    }

    public void setYear(int year){
        this.year = year;
    }

    public int getYear(){
        return year;
    }

    public void setMileage(int mileage){
        this.mileage = mileage;
    }

    public int getMileage(){
        return mileage;
    }

    public String toString(){
        return " " + this.id + "|"+ " " + this.model + " " + this.year +
                "\n" + "Mileage: "+ this.mileage +
                "\n" + "Plate: " + this.plate +
                "\n" + "Client id : "+ this.ownerId + "\n";

    }
    public boolean equals(Object obj){
        if(obj == this){
            return true;
        }
        if(obj == null || obj.getClass() != this.getClass()){
            return false;
        }
        Vehicle vehicle = (Vehicle) obj;

        if(vehicle.ownerId != this.ownerId){
            return false;
        }
        if(!vehicle.model.equals(this.model)){
            return false;
        }
        if(!vehicle.plate.equals(this.plate)){
            return false;
        }
        if(vehicle.year != this.year){
            return false;
        }
        if(vehicle.mileage != this.mileage){
            return false;
        }
        return true;
    }
    public int hashCode(){
        final int number = 31;
        int result = 1;
        result = number * result + id;
        result = number * result + ownerId;
        result = number * result + model.hashCode();
        result = number * result + plate.hashCode();
        result = number * result + year;
        result = number * result + mileage;
        return result;
    }
}
