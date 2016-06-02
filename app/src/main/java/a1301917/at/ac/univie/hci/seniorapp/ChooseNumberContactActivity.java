package a1301917.at.ac.univie.hci.seniorapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

/**
 * Menü zur Auswahl ob man Kontakt aussucht oder Nummer eingibt
 */
public class ChooseNumberContactActivity extends AppCompatActivity {
    private String[] menuButtonNames;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_number_contact);

        final TextView menuButton1 = (TextView) findViewById(R.id.chooseNumberContactButton1);
        final TextView menuButton2 = (TextView) findViewById(R.id.chooseNumberContactButton2);

        menuButtonNames = getResources().getStringArray(R.array.choose_number_contact);

        menuButton1.setText(menuButtonNames[0]);
        menuButton2.setText(menuButtonNames[1]);

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
     * Menübutton um ShowContactsActivity aufzurufen
     * @param view
     */
    public void ActionMenuButton1(View view){
        Intent intent = new Intent(this, ShowContactsActivity.class);
        startActivity(intent);
    }

    /**
     * Menübutton um InserNumberActivity aufzurufen
     * @param view
     */
    public void ActionMenuButton2(View view){
        Intent intent = new Intent(this, InsertNumberActivity.class);
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
