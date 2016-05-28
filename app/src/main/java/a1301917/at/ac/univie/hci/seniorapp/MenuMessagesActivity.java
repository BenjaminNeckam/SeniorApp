package a1301917.at.ac.univie.hci.seniorapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

/**
 * Untermenü von Nachrichten
 */
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
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }

    /**
     * Button um ChooseNumberContactActivity auszuführen
     * @param view
     */
    public void ActionMenuButton1(View view){
        Intent intent = new Intent(this, ChooseNumberContactActivity.class);
        startActivity(intent);
    }

    /**
     * Button um ShowMessagesActivity auszuführen
     * @param view
     */
    public void ActionMenuButton2(View view){
        Intent intent = new Intent(this, ShowMessagesActivity.class);
        startActivity(intent);
    }

    /**
     * Button um MenuMessagesActivity auszuführen
     * @param view
     */
    public void ActionMenuButton3(View view){
        Intent intent = new Intent(this, MenuMessagesActivity.class);
        startActivity(intent);
    }

    /**
     * Button um ShowSentMessagesActivity auszuführen
     * @param view
     */
    public void ActionMenuButton4(View view){
        Intent intent = new Intent(this, ShowSentMessagesActivity.class);
        startActivity(intent);
    }

    /**
     * Zurück zur vorherigen Seite
     */
    public void goBackToApp(){
        Intent intent = new Intent(this, MenuMessagesActivity.class);
        startActivity(intent);
    }
}
