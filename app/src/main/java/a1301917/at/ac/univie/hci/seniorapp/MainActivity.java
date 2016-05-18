package a1301917.at.ac.univie.hci.seniorapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void MenueOn(View view){
        Intent intent = new Intent(this, MenueActivity.class);
        startActivity(intent);
    }

    //// TODO: 16.05.2016 Wirklich notwendig? 
    public void MenueOff(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void GiveCommand(View view){

    }
}
