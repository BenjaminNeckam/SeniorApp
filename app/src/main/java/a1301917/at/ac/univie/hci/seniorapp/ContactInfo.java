package a1301917.at.ac.univie.hci.seniorapp;

/**
 * Created by Benni on 22.05.2016.
 */
public class ContactInfo {
    private String name;
    private String number;

    public void setName(String name){
        this.name = name;
    }

    public void setNumber(String number){
        this.number = number;
    }

    public String getName(){
        return name;
    }

    public String getNumber(){
        return number;
    }

    public String toString(){
        return name + "\n" + number;
    }
}
