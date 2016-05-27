package a1301917.at.ac.univie.hci.seniorapp;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MenuMessagesActivity extends AppCompatActivity {
    private String[] menuButtonNames;
    private static final String TAG = "Message: ";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_messages);

        menuButtonNames = getResources().getStringArray(R.array.messages_button_names);

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

    public void BackToLastState(View view){
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }

    public void ActionMenuButton1(View view){
        Intent intent = new Intent(this, ChooseNumberContactActivity.class);
        startActivity(intent);

        /*Intent smsIntent = new Intent(Intent.ACTION_VIEW);
        smsIntent.setData(Uri.parse("smsto:"));
        smsIntent.setType("vnd.android-dir/mms-sms");
        smsIntent.putExtra("address"  ,"");
        smsIntent.putExtra("sms_body"  , "");

        try {
            Log.i(TAG,"Inside try-block");
            startActivity(smsIntent);
            finish();
            Log.i(TAG,"After finish()");

        }
        catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(MenuMessagesActivity.this,
                    "SMS faild, please try again later.", Toast.LENGTH_SHORT).show();
        }*/

    }

    public void ActionMenuButton2(View view){
        Intent intent = new Intent(this, ShowMessagesActivity.class);
        startActivity(intent);
    }

    public void goBackToApp(){
        Intent intent = new Intent(this, MenuMessagesActivity.class);
        startActivity(intent);
    }
}
