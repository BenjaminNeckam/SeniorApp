package a1301917.at.ac.univie.hci.seniorapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.TextView;

/**
 * Aktivität zum lesen einer Nachricht
 */
public class ReadMessageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_message);
        TextView textView = (TextView)findViewById(R.id.DisplayMessageArea);
        textView.setMovementMethod(new ScrollingMovementMethod());
        String message = getIntent().getStringExtra(ShowMessagesActivity.EXTRA_MESSAGE);
        textView.setText(message);
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
        Intent intent = new Intent(this, ShowMessagesActivity.class);
        startActivity(intent);
    }

}
