package a1301917.at.ac.univie.hci.seniorapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MenueActivity extends AppCompatActivity {
    private String[] menue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menue);

        menue = getResources().getStringArray(R.array.menue_button_names);

    }

    public void MenueOn(View view){
        Intent intent = new Intent(this, MenueActivity.class);
        startActivity(intent);
    }

    public void MenueOff(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
