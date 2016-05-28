package a1301917.at.ac.univie.hci.seniorapp;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Anzeigen von Kontakten aus Telefonbuch für Notfälle
 */
public class ShowContactsNotrufActivity extends AppCompatActivity {
    ListView listView;
    //List<ContactInfo> contactListResult;
    ArrayList<String> contactListResult;
    private static final String TAG = "Info: ";
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        intent = new Intent(this, ContactMenuActivity.class);

        setContentView(R.layout.activity_show_contacts);
        listView = (ListView) findViewById(R.id.contactList);
        contactListResult = new ArrayList<>();
        getContacts();
    }

    /**
     * Kontakte in App laden und anzeigen
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

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, contactListResult);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int itemPosition = position;
                String itemValue = (String) listView.getItemAtPosition(position);
                String[]contactDetails = itemValue.split("\n");

                FileOutputStream fos = null;
                try {
                    fos = openFileOutput("notfallKontakt", Context.MODE_PRIVATE);
                    fos.write(contactDetails[1].getBytes());
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (fos != null) {
                        try {
                            fos.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }

                startActivity(intent);
            }
        });
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
        startActivity(intent);
    }

    public void searchContact(String contactName){
         //// TODO: 22.05.2016 Methode implementieren
    }
}
