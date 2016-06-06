package a1301917.at.ac.univie.hci.seniorapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class InterruptActivity extends AppCompatActivity {
    private String info,yes,no;
    private String message=null;
    private Intent intent;
    public final static String EXTRA_MESSAGE = "at.ac.univie.hci.seniorapp.MESSAGE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interrupt);

        final TextView menuButton1 = (TextView) findViewById(R.id.InfoButton1);
        final TextView menuButton2 = (TextView) findViewById(R.id.ChooseButton2);
        final TextView menuButton3 = (TextView) findViewById(R.id.ChooseButton3);
        message = getIntent().getStringExtra(SmsReceiver.EXTRA_MESSAGE);
        info = getResources().getString(R.string.sms_received);
        yes = getResources().getString(R.string.yes);
        no = getResources().getString(R.string.no);

        menuButton1.setText(info);
        menuButton2.setText(yes);
        menuButton3.setText(no);
    }

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

    public void ActionMenuButton1(View view){
        Intent intent = new Intent(this,ReadMessageActivity.class);
        intent.putExtra(EXTRA_MESSAGE,getMessage());
        startActivity(intent);
    }

    //// TODO: 06.06.2016 Dahin zurückleiten woher man gekommen ist??? 
    public void ActionMenuButton2(View view){
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }

    public void BackToLastState(View view){
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }

    public String getMessage(){
        return message;
    }
}
