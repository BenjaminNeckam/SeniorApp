package a1301917.at.ac.univie.hci.seniorapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

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

    public void MenuOn(View view){
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }

    public void MenuOff(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void ActionMenuButton1(View view){
        Intent intent = new Intent(this, ShowContactsActivity.class);
        startActivity(intent);
    }

    public void ActionMenuButton2(View view){
        Intent intent = new Intent(this, InsertNumberActivity.class);
        startActivity(intent);
    }

    public void BackToLastState(View view){
        Intent intent = new Intent(this, MenuMessagesActivity.class);
        startActivity(intent);
    }
}
