package a1301917.at.ac.univie.hci.seniorapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

//// TODO: 16.05.2016 Größe der Grids so ändern das sie ganzes layout füllen
public class MenueActivity extends AppCompatActivity {
    private String[] menue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menue);

        menue = getResources().getStringArray(R.array.menue_button_names);
        ArrayAdapter adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, menue);
        GridView gridView = (GridView) findViewById(R.id.MenuGrid);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MenueActivity.this, "" + position,
                        Toast.LENGTH_SHORT).show();

            }
        });
    }
}
