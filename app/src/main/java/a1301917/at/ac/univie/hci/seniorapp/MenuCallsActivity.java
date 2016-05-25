package a1301917.at.ac.univie.hci.seniorapp;

import android.content.Context;
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
import android.widget.TextView;

import java.util.ArrayList;

public class MenuCallsActivity extends AppCompatActivity {
    private String[] menuButtonNames;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_calls);

        menuButtonNames = getResources().getStringArray(R.array.calls_button_names);

        final TextView menuButton1 = (TextView) findViewById(R.id.menuButton1);
        final TextView menuButton2 = (TextView) findViewById(R.id.menuButton2);
        final TextView menuButton3 = (TextView) findViewById(R.id.menuButton3);
        final TextView menuButton4 = (TextView) findViewById(R.id.menuButton4);

        menuButton1.setText(menuButtonNames[0]);
        menuButton2.setText(menuButtonNames[1]);
        menuButton3.setText(menuButtonNames[2]);
        menuButton4.setText(menuButtonNames[3]);
    }

    public void MenuOn(View view){
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }

    public void MenuOff(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void ActionMenuButton1(View view){
        Intent intent = new Intent(this, ChooseNumberContactActivity.class);
        startActivity(intent);
    }

    public void ActionMenuButton3(View view){
        Intent intent = new Intent(this, OutgoingCallsActivity.class);
        startActivity(intent);
    }

    public void ActionMenuButton4(View view){
        Intent intent = new Intent(this, MissedCallsActivity.class);
        startActivity(intent);
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
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }
}
