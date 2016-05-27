package a1301917.at.ac.univie.hci.seniorapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class AddNumberActivity extends AppCompatActivity {
    public final static String EXTRA_MESSAGE = "at.ac.univie.hci.seniorapp.MESSAGE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_new_contact);
    }

    public void addContact(View view) {
        EditText name = (EditText)findViewById(R.id.insertName);
        EditText number = (EditText)findViewById(R.id.insertNumber);

        String nameText = name.getText().toString();
        String numberText = number.getText().toString();

        Intent intent = new Intent(Intent.ACTION_INSERT);
        intent.setType(ContactsContract.Contacts.CONTENT_TYPE);

        intent.putExtra(ContactsContract.Intents.Insert.NAME, nameText);
        intent.putExtra(ContactsContract.Intents.Insert.PHONE, numberText);

        startActivity(intent);
    }

    public void BackToLastState(View view){
        Intent intent = new Intent(this, ContactMenuActivity.class);
        startActivity(intent);
    }
}
