package a1301917.at.ac.univie.hci.seniorapp;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CallLog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Anzeige von getätigten Anrufen
 */
public class OutgoingCallsActivity extends AppCompatActivity {
    private ListView listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calls);

        listview = (ListView) findViewById(R.id.callList);

        String[] projection = {CallLog.Calls.CACHED_NAME, CallLog.Calls.CACHED_NUMBER_LABEL, CallLog.Calls.TYPE};
        String where = CallLog.Calls.TYPE + "=" + CallLog.Calls.OUTGOING_TYPE;
        String sortOrder = CallLog.Calls.DATE + " DESC";
        Cursor cursor = this.getContentResolver().query(CallLog.Calls.CONTENT_URI, null, where, null, sortOrder);
        cursor.moveToFirst();

        List<String> outgoingList = new ArrayList<String>();

        while (cursor.moveToNext()) {
            ContactInfo contactInfo = new ContactInfo();

            String callLogID = cursor.getString(cursor.getColumnIndex(CallLog.Calls.CACHED_NAME));
            String callNumber = cursor.getString(cursor.getColumnIndex(CallLog.Calls.NUMBER));
            String callDate = cursor.getString(cursor.getColumnIndex(CallLog.Calls.DATE));

            if (callLogID == null) {
                callLogID = "Unbekannt";
            }

            contactInfo.setName(callLogID);
            contactInfo.setNumber(callNumber);

            outgoingList.add(contactInfo.toString());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, outgoingList);
        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                try {
                    if (listview == null) {
                        return;
                    }

                    int itemPosition = position;
                    String itemValue = (String) listview.getItemAtPosition(position);

                    String[] value = itemValue.split("\n");

                    doPhoneCall(value[1]);
                } catch (Exception e) {

                }

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
     * Kontakt anrufen
     * @param phoneNumber
     */
    private void doPhoneCall(String phoneNumber) {
        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel:" + phoneNumber));

        try {
            startActivity(callIntent);
            finish();
        } catch (android.content.ActivityNotFoundException ex) {

        }
    }

    /**
     * Zurück zur vorherigen Seite
     * @param view
     */
    public void BackToLastState(View view){
        Intent intent = new Intent(this, MenuCallsActivity.class);
        startActivity(intent);
    }
}
