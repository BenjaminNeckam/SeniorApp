package a1301917.at.ac.univie.hci.seniorapp;

/**
 * Klasse für Kontaktinformationen (Name, Nummer)
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
