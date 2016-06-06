package a1301917.at.ac.univie.hci.seniorapp;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Anzeige von Kontakten aus Telefonbuch
 */
public class ShowContactsActivity extends AppCompatActivity {
    ListView listView;
    //List<ContactInfo> contactListResult;
    ArrayList<String> contactListResult;
    private static final String TAG = "Info: ";
    public final static String EXTRA_MESSAGE = "at.ac.univie.hci.seniorapp.MESSAGE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_contacts);
        listView = (ListView) findViewById(R.id.contactList);
        contactListResult = new ArrayList<>();
        getContacts();
    }

    /**
     * Alle Kontakte in App laden und anzeigen
     */
    public void getContacts(){
        String number;

        Uri CONTENT_URI= ContactsContract.Contacts.CONTENT_URI;
        String _ID = ContactsContract.Contacts._ID;
        String DISPLAY_NAME = ContactsContract.Contacts.DISPLAY_NAME;
        String HAS_PHONE_NUMBER = ContactsContract.Contacts.HAS_PHONE_NUMBER;

        Uri Phone_CONTENT_URI = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        String Phone_CONTACT_ID= ContactsContract.CommonDataKinds.Phone.CONTACT_ID;
        String NUMBER = ContactsContract.CommonDataKinds.Phone.NUMBER;

         //StringBuffer contactListResult = new StringBuffer();

        Cursor cursor = getContentResolver().query(CONTENT_URI,null,null,null,null);

        if(cursor.getCount()>0){
            while(cursor.moveToNext()){
                String contact_id = cursor.getString(cursor.getColumnIndex(_ID));
                String name = cursor.getString(cursor.getColumnIndex(DISPLAY_NAME));
                ContactInfo contactInfo = new ContactInfo();

                int hasPhoneNumber = Integer.parseInt(cursor.getString(cursor.getColumnIndex(HAS_PHONE_NUMBER)));

                if(hasPhoneNumber>0){

                    //contactListResult.append("\n Vorname:" + name);
                    contactInfo.setName(name);
                    Cursor phoneCursor = getContentResolver().query(Phone_CONTENT_URI,null,Phone_CONTACT_ID + " = ?", new String[] {contact_id},null);

                    while(phoneCursor.moveToNext()) {
                        number = phoneCursor.getString(phoneCursor.getColumnIndex(NUMBER));
                        contactInfo.setNumber(number);
                    }
                    phoneCursor.close();
                }
                //contactListResult.append("\n");s
                if(contactInfo.getName()!=null && contactInfo.getNumber()!=null) {
                    contactListResult.add(contactInfo.toString());
                }
            }
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.my_listview_layout, contactListResult);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int itemPosition = position;
                String itemValue = (String) listView.getItemAtPosition(position);
                String[]contactDetails = itemValue.split("\n");
                chooseContact(contactDetails[1]);
            }
        });
    }

    /**
     * Ausgewählten Kontakt weitergeben um Sms zu schreiben
     * @param phoneNumber
     */
    public void chooseContact(String phoneNumber){
        Intent intent = new Intent(this,WriteMessageActivity.class);
        //intent.setData(Uri.parse("tel:"+phoneNumber));
        Log.i(TAG,phoneNumber);
        intent.putExtra(EXTRA_MESSAGE,phoneNumber);
        startActivity(intent);
    }

    /**
     * Button um Menü anzuschalten
     * @param view
     */
    public void MenuOn(View view){
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }

    /**
     * Button um Menü auszuschalten
     * @param view
     */
    public void MenuOff(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    /**
     * Zurück zur vorherigen Seite
     * @param view
     */
    public void BackToLastState(View view){
        Intent intent = new Intent(this, ChooseNumberContactActivity.class);
        startActivity(intent);
    }

    /**
     * Soll nur gesuchten Kontatk anzeigen
     * Keine Zeit mehr in dieser Iteration
     * @param contactName
     */
    public void searchContact(String contactName){
         //// TODO: 22.05.2016 Methode implementieren
    }
}
