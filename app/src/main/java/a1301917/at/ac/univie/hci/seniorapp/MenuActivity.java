package a1301917.at.ac.univie.hci.seniorapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

/**
 * Backup Menü, um Menü auch optisch darstellen zu können
 */
public class MenuActivity extends AppCompatActivity {
    private String[] menuButtonNames;
    private static final String TAG = "MenuButtonNames";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        final TextView menuButton1 = (TextView) findViewById(R.id.menuButton1);
        final TextView menuButton2 = (TextView) findViewById(R.id.menuButton2);
        final TextView menuButton3 = (TextView) findViewById(R.id.menuButton3);
        final TextView menuButton4 = (TextView) findViewById(R.id.menuButton4);

        menuButtonNames = getResources().getStringArray(R.array.menue_button_names);


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
     * Button um Menü auszuschalten
     * @param view
     */
    public void MenuOff(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    /**
     * Button um MenuMessagesActivity auszuführen
     * @param view
     */
    public void ActionMenuButton1(View view){
        Intent intent = new Intent(this, MenuMessagesActivity.class);
        startActivity(intent);
    }

    /**
     * Button um MenuCalssActivity auszuführen
     * @param view
     */
    public void ActionMenuButton2(View view){
        Intent intent = new Intent(this, MenuCallsActivity.class);
        startActivity(intent);
    }

    /**
     * Button um ContactMenuActivity auszuführen
     * @param view
     */
    public void ActionMenuButton3(View view){
        Intent intent = new Intent(this, ContactMenuActivity.class);
        startActivity(intent);
    }

    /**
     * Button um NotrufActivity auszuführen
     * @param view
     */
    public void ActionMenuButton4(View view){
        Intent intent = new Intent(this, NotrufActivity.class);
        startActivity(intent);
    }

    /**
     * Zurück zur vorherigen Seite
     * @param view
     */
    public void BackToLastState(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
