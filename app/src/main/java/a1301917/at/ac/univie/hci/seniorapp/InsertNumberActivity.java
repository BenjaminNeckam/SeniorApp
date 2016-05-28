package a1301917.at.ac.univie.hci.seniorapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

/**
 * Aktivität für Eingabe der Nummber für Nachrichten
 */
public class InsertNumberActivity extends AppCompatActivity {
    public final static String EXTRA_MESSAGE = "at.ac.univie.hci.seniorapp.MESSAGE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_number);
    }

    /**
     * Methode zum Eingeben und Verarbeiten einer Nummer für Sms Versand
     * @param view
     */
    public void insertNumber(View view){
        Intent intent = new Intent(this, WriteMessageActivity.class);
        EditText text = (EditText)findViewById(R.id.insertNumber);
        String number = text.getText().toString();
        intent.putExtra(EXTRA_MESSAGE,number);
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
}
