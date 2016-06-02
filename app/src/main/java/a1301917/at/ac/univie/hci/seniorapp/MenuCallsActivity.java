package a1301917.at.ac.univie.hci.seniorapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

/**
 * Untermenü von Anrufe
 */
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

    /**
     * Button um Menü anzuschalten
     * @param view
     */
    public void MenuOn(View view){
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }

    /**
     * Button um Menü aususchalten
     * @param view
     */
    public void MenuOff(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    /**
     * Button um ShowContactsCallActivity auszuführen
     * @param view
     */
    public void ActionMenuButton1(View view){
        Intent intent = new Intent(this, ShowContactsCallActivity.class);
        startActivity(intent);
    }

    /**
     * Button um InsertNumberCallActivity auszuführen
     * @param view
     */
    public void ActionMenuButton2(View view){
        Intent intent = new Intent(this, InsertNumberCallActivity.class);
        startActivity(intent);
    }

    /**
     * Button um OutgoinCallsActivity auszuführen
     * @param view
     */
    public void ActionMenuButton3(View view){
        Intent intent = new Intent(this, OutgoingCallsActivity.class);
        startActivity(intent);
    }

    /**
     * Button um MissedCallsActivity auszuführen
     * @param view
     */
    public void ActionMenuButton4(View view){
        Intent intent = new Intent(this, MissedCallsActivity.class);
        startActivity(intent);
    }

    /**
     * Zurück zur vorherigen Seite
     * @param view
     */
    public void BackToLastState(View view){
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }
}
