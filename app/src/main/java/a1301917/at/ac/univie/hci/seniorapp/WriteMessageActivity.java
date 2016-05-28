package a1301917.at.ac.univie.hci.seniorapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Aktivität um SMS zu schreigen
 */
public class WriteMessageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_message);
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
     * SMS senden
     * @param view
     */
    public void sendMessage(View view){
        String number = getIntent().getStringExtra(InsertNumberActivity.EXTRA_MESSAGE);
        EditText text = (EditText) findViewById(R.id.message);
        String message = text.getText().toString();

        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(number, null, message, null, null);
            Toast.makeText(getApplicationContext(), "SMS gesendet.", Toast.LENGTH_LONG).show();
        }

        catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Fehlgeschlagen, bitte erneut versuchen", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }

        Intent intentBackToMenu = new Intent(this, MenuActivity.class);
        startActivity(intentBackToMenu);
    }
}
