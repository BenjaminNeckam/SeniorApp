package a1301917.at.ac.univie.hci.seniorapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

/**
 * Untermenü von Kontakte
 */
public class ContactMenuActivity extends AppCompatActivity {
    private String[] menuButtonNames;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_contact);

        menuButtonNames = getResources().getStringArray(R.array.contact_button_names);

        final TextView menuButton1 = (TextView) findViewById(R.id.menuButton1);
        final TextView menuButton2 = (TextView) findViewById(R.id.menuButton2);
        final TextView menuButton3 = (TextView) findViewById(R.id.menuButton3);
        final TextView menuButton4 = (TextView) findViewById(R.id.menuButton4);

        menuButton1.setText(menuButtonNames[0]);
        menuButton2.setText(menuButtonNames[1]);
        menuButton3.setText(menuButtonNames[2]);
        menuButton4.setText(menuButtonNames[3]);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    /**
     * Button für Menü zum anschalten
     * @param view
     */
    public void MenuOn(View view) {
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }

    /**
     * Button für Menü zum ausschalten
     * @param view
     */
    public void MenuOff(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    /**
     * Button zum Aufrufen von AddNumberActivity
     * @param view
     */
    public void ActionMenuButton1(View view) {
        Intent intent = new Intent(this, AddNumberActivity.class);
        startActivity(intent);
    }

    /**
     * Button zum Aufrufen von ShowContactsDeleteActivity
     * @param view
     */
    public void ActionMenuButton2(View view) {
        Intent intent = new Intent(this, ShowContactsDeleteActivity.class);
        startActivity(intent);
    }

    /**
     * Button zum Aufrufen von ShowContactsNotrufActivity
     * @param view
     */
    public void ActionMenuButton3(View view) {
        Intent intent = new Intent(this, ShowContactsNotrufActivity.class);
        startActivity(intent);
    }

    public void ActionMenuButton4(View view) {
    }

    /**
     * Zurück zur vorherigen Seite
     * @param view
     */
    public void BackToLastState(View view) {
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }

}
