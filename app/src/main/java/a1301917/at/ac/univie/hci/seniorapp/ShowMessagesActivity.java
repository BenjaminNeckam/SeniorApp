package a1301917.at.ac.univie.hci.seniorapp;

import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.provider.Telephony;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.provider.Telephony.Sms;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

public class ShowMessagesActivity extends AppCompatActivity  {
    ListView listView;
    ArrayList<String> messageListResults;
    private static final String TAG = "Info: ";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_messages);
        listView = (ListView) findViewById(R.id.messagesList);
        messageListResults = new ArrayList<>();
        showSms();
    }

    //// TODO: 26.05.2016 Nummern stimmen nicht mit Nachricht überein 
    public void showSms() {
        String number;
        Cursor cursor = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
            cursor = getContentResolver().query(Sms.Inbox.CONTENT_URI, null,null,null,null);
        }

        if(cursor.getCount()>0){
            while(messageListResults.size()<=10 && cursor.moveToNext()){
                String size = String.valueOf(messageListResults.size());
                String message = cursor.getString(cursor.getColumnIndex(Sms.Inbox.BODY));
                MessageInfo messageInfo = new MessageInfo();
               messageInfo.setMessage(message);
                number = cursor.getString(cursor.getColumnIndex(Sms.Inbox.ADDRESS));
                messageInfo.setNumber(number);

                    messageListResults.add(messageInfo.toString());
            }
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, messageListResults);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                /*int itemPosition = position;
                String itemValue = (String) listView.getItemAtPosition(position);
                String[]contactDetails = itemValue.split("\n");
                chooseContact(contactDetails[1]);*/
            }
        });
    }

    public void MenuOn(View view){
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }

    public void MenuOff(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void BackToLastState(View view){
        Intent intent = new Intent(this, MenuMessagesActivity.class);
        startActivity(intent);
    }

}
