package a1301917.at.ac.univie.hci.seniorapp;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.BaseColumns;
import android.provider.ContactsContract;
import android.provider.Telephony;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Aktivität zum anzeigen von gesendeten SMS
 */
public class ShowSentMessagesActivity extends AppCompatActivity {
    ListView listView;
    ArrayList<MessageInfo> messageListResults;
    private static final String TAG = "Info: ";
    public final static String EXTRA_MESSAGE = "at.ac.univie.hci.seniorapp.MESSAGE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_sent_messages);
        listView = (ListView) findViewById(R.id.messagesSentList);
        messageListResults = new ArrayList<>();
        showSentSms();
    }

    /**
     * SMS aus SENT laden und anzeigen
     */
    public void showSentSms() {
        String number;
        Cursor cursor = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
            cursor = getContentResolver().query(Telephony.Sms.Sent.CONTENT_URI, null, null, null, null);
        }

        if (cursor.getCount() > 0) {
            while (messageListResults.size() <= 20 && cursor.moveToNext()) {
                String size = String.valueOf(messageListResults.size());
                String message = cursor.getString(cursor.getColumnIndex(Telephony.Sms.Sent.BODY));
                MessageInfo messageInfo = new MessageInfo();
                messageInfo.setMessage(message);
                number = cursor.getString(cursor.getColumnIndex(Telephony.Sms.Sent.ADDRESS));
                messageInfo.setNumber(number);
                String name = getContactByNumber(number);
                messageInfo.setName(name);
                String messagePreview;
                if(message.length()>=20){
                    messagePreview = message.substring(0,20) + "...";
                }else{
                    messagePreview=message;
                }
                messageInfo.setMessagePreview(messagePreview);

                messageListResults.add(messageInfo);
            }
        }

        ArrayAdapter<MessageInfo> adapter = new ArrayAdapter<MessageInfo>(this, R.layout.my_listview_layout, messageListResults);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int itemPosition = position;
                /*String itemValue = (String) listView.getItemAtPosition(position);
                String[] contactDetails = itemValue.split("\n");
                ReadSms(contactDetails[0]);*/
                MessageInfo messageInfo= messageListResults.get(position);
                ReadSms(messageInfo.getMessage());
            }
        });
    }

    /**
     * Gewählte SMS lesen
     * @param message
     */
    public void ReadSms(String message){
        Intent intent = new Intent(this,ReadMessageActivity.class);
        //intent.setData(Uri.parse("tel:"+phoneNumber));
        intent.putExtra(EXTRA_MESSAGE,message);
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
        Intent intent = new Intent(this, MenuMessagesActivity.class);
        startActivity(intent);
    }

    public String getContactByNumber(String number){
        Uri uri = Uri.withAppendedPath(ContactsContract.PhoneLookup.CONTENT_FILTER_URI,Uri.encode(number));
        String name = null;

        ContentResolver contentResolver = getContentResolver();
        Cursor cursor = contentResolver.query(uri,new String[] {BaseColumns._ID,ContactsContract.PhoneLookup.DISPLAY_NAME},null,null,null);

        try{
            if(cursor != null && cursor.getCount()>0){
                cursor.moveToNext();
                name = cursor.getString(cursor.getColumnIndex(ContactsContract.Data.DISPLAY_NAME));
            }
        }finally {
            if(cursor != null){
                cursor.close();
            }
        }

        return name;
    }
}
