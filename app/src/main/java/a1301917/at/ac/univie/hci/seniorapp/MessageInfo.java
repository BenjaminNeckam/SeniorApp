package a1301917.at.ac.univie.hci.seniorapp;

/**
 * Created by Benni on 26.05.2016.
 */
public class MessageInfo {
    private String number;
    private String message;
    private String name;

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public void setMessage(String name){
        this.message = name;
    }

    public void setNumber(String number){
        this.number = number;
    }

    public String getMessage(){
        return message;
    }

    public String getNumber(){
        return number;
    }

    public String toString(){
        return message + "\n" + number;
    }
}
