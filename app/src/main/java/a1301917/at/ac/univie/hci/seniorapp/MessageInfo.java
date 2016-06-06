package a1301917.at.ac.univie.hci.seniorapp;

/**
 * Klasse für Sms Informationen (Name, Nummer, Nachrichte)
 */
public class MessageInfo {
    private String number;
    private String message;
    private String messagePreview;
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

    public void setMessagePreview(String name){
        this.messagePreview = name;
    }

    public void setNumber(String number){
        this.number = number;
    }

    public String getMessage(){
        return message;
    }

    public String getMessagePreview(){
        return messagePreview;
    }

    public String getNumber(){
        return number;
    }

    public String toString(){
        return messagePreview + "\n" + number;
    }
}
