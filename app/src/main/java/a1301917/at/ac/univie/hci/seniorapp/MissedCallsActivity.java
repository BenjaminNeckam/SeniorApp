package a1301917.at.ac.univie.hci.seniorapp;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.CallLog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MissedCallsActivity extends AppCompatActivity {
    private ListView listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calls);

        listview = (ListView) findViewById(R.id.callList);

        String[] projection = {CallLog.Calls.CACHED_NAME, CallLog.Calls.CACHED_NUMBER_LABEL, CallLog.Calls.TYPE};
        String where = CallLog.Calls.TYPE + "=" + CallLog.Calls.MISSED_TYPE;
        String sortOrder = android.provider.CallLog.Calls.DATE + " DESC";
        Cursor cursor = this.getContentResolver().query(CallLog.Calls.CONTENT_URI, null, where, null, sortOrder);
        cursor.moveToFirst();

        List<String> missedList = new ArrayList<String>();

        while (cursor.moveToNext()) {
            ContactInfo contactInfo = new ContactInfo();

            String callLogID = cursor.getString(cursor.getColumnIndex(CallLog.Calls.CACHED_NAME));
            String callNumber = cursor.getString(cursor.getColumnIndex(android.provider.CallLog.Calls.NUMBER));
            String callDate = cursor.getString(cursor.getColumnIndex(android.provider.CallLog.Calls.DATE));

            if (callLogID == null) {
                callLogID = "Unbekannt";
            }
            contactInfo.setName(callLogID);
            contactInfo.setNumber(callNumber);

            missedList.add(contactInfo.toString());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, missedList);
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

    public void MenuOn(View view){
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }

    public void MenuOff(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void doPhoneCall(String phoneNumber) {
        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel:" + phoneNumber));

        try {
            startActivity(callIntent);
            finish();
        } catch (android.content.ActivityNotFoundException ex) {

        }
    }

    public void BackToLastState(View view){
        Intent intent = new Intent(this, MenuCallsActivity.class);
        startActivity(intent);
    }
}
