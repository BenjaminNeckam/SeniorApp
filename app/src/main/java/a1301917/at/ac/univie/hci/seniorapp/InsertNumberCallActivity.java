package a1301917.at.ac.univie.hci.seniorapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class InsertNumberCallActivity extends AppCompatActivity {
    public final static String EXTRA_MESSAGE = "at.ac.univie.hci.seniorapp.MESSAGE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_call_number);
    }

    public void insertNumber(View view) {
        EditText text = (EditText)findViewById(R.id.insertNumber);
        String number = text.getText().toString();
        doPhoneCall(number);
    }

    private void doPhoneCall(String phoneNumber) {
        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel:"+phoneNumber));

        try {
            startActivity(callIntent);
            finish();
        }
        catch (android.content.ActivityNotFoundException ex) {

        }
    }

    public void BackToLastState(View view){
        Intent intent = new Intent(this, MenuCallsActivity.class);
        startActivity(intent);
    }
}
