package a1301917.at.ac.univie.hci.seniorapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class InsertNumberActivity extends AppCompatActivity {
    public final static String EXTRA_MESSAGE = "at.ac.univie.hci.seniorapp.MESSAGE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_number);
    }

    public void insertNumber(View view){
        Intent intent = new Intent(this, WriteMessageActivity.class);
        EditText text = (EditText)findViewById(R.id.insertNumber);
        String number = text.getText().toString();
        intent.putExtra(EXTRA_MESSAGE,number);
        startActivity(intent);
    }
}