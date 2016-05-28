package a1301917.at.ac.univie.hci.seniorapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

/**
 * Aktivität zur Eingabe der Nummer für Anruf
 */
public class InsertNumberCallActivity extends AppCompatActivity {
    public final static String EXTRA_MESSAGE = "at.ac.univie.hci.seniorapp.MESSAGE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_call_number);
    }

    /**
     * Methode zum Eingeben und Verarbeiten einer Nummer für Anruf
     * @param view
     */
    public void insertNumber(View view) {
        EditText text = (EditText)findViewById(R.id.insertNumber);
        String number = text.getText().toString();
        doPhoneCall(number);
    }

    /**
     * Eingegebene Nummer anrufen
     * @param phoneNumber
     */
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
        Intent intent = new Intent(this, MenuCallsActivity.class);
        startActivity(intent);
    }
}
