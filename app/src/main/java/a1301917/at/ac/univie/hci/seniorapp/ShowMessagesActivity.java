package a1301917.at.ac.univie.hci.seniorapp;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.Telephony.Sms;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Aktivität zum anzeigen der empfangenen Sms
 */
public class ShowMessagesActivity extends AppCompatActivity  {
    ListView listView;
    ArrayList<MessageInfo> messageListResults;
    private static final String TAG = "Info: ";
    public final static String EXTRA_MESSAGE = "at.ac.univie.hci.seniorapp.MESSAGE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_messages);
        listView = (ListView) findViewById(R.id.messagesList);
        messageListResults = new ArrayList<>();
        showSms();
    }

    /**
     * Sms aus Inbox laden und anzeigen
     */
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
                String messagePreview;
                if(message.length()>=20){
                    messagePreview = message.substring(0,20) + "...";
                }else{
                    messagePreview=message;
                }

                MessageInfo messageInfo = new MessageInfo();
               messageInfo.setMessage(message);
                messageInfo.setMessagePreview(messagePreview);
                number = cursor.getString(cursor.getColumnIndex(Sms.Inbox.ADDRESS));
                messageInfo.setNumber(number);

                    //messageListResults.add(messageInfo.toString());
                messageListResults.add(messageInfo);
            }
        }

        ArrayAdapter<MessageInfo> adapter = new ArrayAdapter<MessageInfo>(this, R.layout.my_listview_layout, messageListResults);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int itemPosition = position;
                MessageInfo messageInfo= messageListResults.get(position);
                //String itemValue = (String) listView.getItemAtPosition(position);
                ReadSms(messageInfo.getMessage());
                //String[]contactDetails = itemValue.split("\n");
                //ReadSms(contactDetails[0]);
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
        Intent intent = new Intent(this, MenuMessagesActivity.class);
        startActivity(intent);
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

}
