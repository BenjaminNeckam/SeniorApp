package a1301917.at.ac.univie.hci.seniorapp;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CallLog;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class ShowContactsDeleteActivity extends AppCompatActivity {
    ListView listView;
    //List<ContactInfo> contactListResult;
    private ArrayList<String> contactListResult;
    private static final String TAG = "Info: ";
    private ArrayAdapter<String> adapter;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_show_contacts);
        listView = (ListView) findViewById(R.id.contactList);
        contactListResult = new ArrayList<>();
        getContacts();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    public void getContacts() {

        fillContactList();
        setAdapter();
        setListener();
    }

    private void fillContactList() {
        String number;

        Uri CONTENT_URI = ContactsContract.Contacts.CONTENT_URI;
        String _ID = ContactsContract.Contacts._ID;
        String DISPLAY_NAME = ContactsContract.Contacts.DISPLAY_NAME;
        String HAS_PHONE_NUMBER = ContactsContract.Contacts.HAS_PHONE_NUMBER;

        Uri Phone_CONTENT_URI = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        String Phone_CONTACT_ID = ContactsContract.CommonDataKinds.Phone.CONTACT_ID;
        String NUMBER = ContactsContract.CommonDataKinds.Phone.NUMBER;

        //StringBuffer contactListResult = new StringBuffer();

        Cursor cursor = getContentResolver().query(CONTENT_URI, null, null, null, null);

        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                String contact_id = cursor.getString(cursor.getColumnIndex(_ID));
                String name = cursor.getString(cursor.getColumnIndex(DISPLAY_NAME));
                ContactInfo contactInfo = new ContactInfo();

                int hasPhoneNumber = Integer.parseInt(cursor.getString(cursor.getColumnIndex(HAS_PHONE_NUMBER)));

                if (hasPhoneNumber > 0) {

                    //contactListResult.append("\n Vorname:" + name);
                    contactInfo.setName(name);
                    Cursor phoneCursor = getContentResolver().query(Phone_CONTENT_URI, null, Phone_CONTACT_ID + " = ?", new String[]{contact_id}, null);

                    while (phoneCursor.moveToNext()) {
                        number = phoneCursor.getString(phoneCursor.getColumnIndex(NUMBER));
                        contactInfo.setNumber(number);
                    }
                    phoneCursor.close();
                }
                //contactListResult.append("\n");s
                if (contactInfo.getName() != null && contactInfo.getNumber() != null) {
                    contactListResult.add(contactInfo.toString());
                }
            }
        }
    }

    public void setAdapter() {
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, contactListResult);
        listView.setAdapter(adapter);
    }

    public void setListener() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int itemPosition = position;
                String itemValue = (String) listView.getItemAtPosition(position);
                String[] contactDetails = itemValue.split("\n");

                String nameText = contactDetails[0];
                String numberText = contactDetails[1];

                String _ID = ContactsContract.Contacts._ID;
                String DISPLAY_NAME = ContactsContract.Contacts.DISPLAY_NAME;
                String HAS_PHONE_NUMBER = ContactsContract.Contacts.HAS_PHONE_NUMBER;

                Uri Phone_CONTENT_URI = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
                String Phone_CONTACT_ID = ContactsContract.CommonDataKinds.Phone.CONTACT_ID;
                String NUMBER = ContactsContract.CommonDataKinds.Phone.NUMBER;
                Uri CONTENT_URI = ContactsContract.Contacts.CONTENT_URI;
                Cursor cursor = getContentResolver().query(CONTENT_URI, null, null, null, null);

                if (cursor.getCount() > 0) {
                    while (cursor.moveToNext()) {
                        String contact_id = cursor.getString(cursor.getColumnIndex(_ID));
                        String name = cursor.getString(cursor.getColumnIndex(DISPLAY_NAME));

                        int hasPhoneNumber = Integer.parseInt(cursor.getString(cursor.getColumnIndex(HAS_PHONE_NUMBER)));

                        if (hasPhoneNumber > 0) {
                            Cursor phoneCursor = getContentResolver().query(Phone_CONTENT_URI, null, Phone_CONTACT_ID + " = ?", new String[]{contact_id}, null);

                            while (phoneCursor.moveToNext()) {
                                if (phoneCursor.getString(phoneCursor.getColumnIndex(ContactsContract.PhoneLookup.DISPLAY_NAME)).equalsIgnoreCase(name)) {
                                    String number = phoneCursor.getString(phoneCursor.getColumnIndex(NUMBER));

                                    if (numberText.equals(number)) {
                                        String lookupKey = phoneCursor.getString(phoneCursor.getColumnIndex(ContactsContract.Contacts.LOOKUP_KEY));

                                        Uri uri = Uri.withAppendedPath(ContactsContract.Contacts.CONTENT_LOOKUP_URI, lookupKey);
                                        getContentResolver().delete(uri, null, null);
                                    }
                                }
                            }
                            phoneCursor.close();
                        }
                    }
                }

                //listView.setAdapter(adapter);
                contactListResult.remove(position);
                adapter.notifyDataSetChanged();
            }
        });
    }

    public void BackToLastState(View view) {
        Intent intent = new Intent(this, ContactMenuActivity.class);
        startActivity(intent);
    }

    public void searchContact(String contactName) {
        //// TODO: 22.05.2016 Methode implementieren
    }
}
