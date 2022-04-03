package by.bogdanov.entity;

public class Operation extends Entity{

    private int id;
    private String operationName;
    private int operationPrice;

    public Operation(){}
    public Operation(int id, String name, int price){
        this.id = id;
        this.operationName = name;
        this.operationPrice = price;
    }
    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setOperationName(String operationName) {
        this.operationName = operationName;
    }

    public String getOperationName() {
        return operationName;
    }

    public void setOperationPrice(int operationPrice) {
        this.operationPrice = operationPrice;
    }

    public int getOperationPrice() {
        return operationPrice;
    }

    public String toString(){
        return "id: " + this.id + "|" + " " + this.operationName+"\n"
                +"Price: " +this.operationPrice;
    }
    public boolean equals(Object obj){
        if(obj == this){
            return true;
        }
        if(obj == null || obj.getClass() != this.getClass()){
            return false;
        }
        Operation operation = (Operation) obj;
        if(operation.id != this.id){
            return false;
        }
        if(operation.operationPrice != this.operationPrice){
            return false;
        }
        if(!operation.operationName.equals(this.operationName)){
            return false;
        }
        return true;
    }
    public int hashCode(){
      final int number = 31;
      int result = 1;
      result = number * result + operationPrice;
      result = number * result + operationName.hashCode();
      return result;
    }
}
