package a1301917.at.ac.univie.hci.seniorapp;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import java.util.List;
import java.util.Vector;

public class MainActivity extends AppCompatActivity {

    VoiceAssistant senior_handy;
    List<ContactInfo> contacts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //on lollipop and higher builds do runtime permission checking
        if (Build.VERSION.SDK_INT >= 23)
            checkAndRequestAllPermissions();

        senior_handy = new VoiceAssistant(this);
        contacts = Util.getContacts(getContentResolver());
    }

    public void MenuOn(View view){
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }

    public void MenuOff(View view){

    }

    public void GiveCommand(View view){
        senior_handy.dialog("was geht alter", new DialogHandler() {
            @Override
            public void handle(String answer, VoiceAssistant voiceAssistant) {

            }
        });
    }

    private static final int PERMISSIONS_CORE = 0;
    private void checkAndRequestAllPermissions() {
        Vector<String> permissionRequests = new Vector<>();
        checkAndPushRequests(android.Manifest.permission.RECORD_AUDIO,permissionRequests);
        //checkAndPushRequests(android.Manifest.permission.READ_CONTACTS,permissionRequests);
        //checkAndPushRequests(android.Manifest.permission.CALL_PHONE, permissionRequests);
        if (permissionRequests.size()>0)
            ActivityCompat.requestPermissions(this, permissionRequests.toArray(new String[permissionRequests.size()]), PERMISSIONS_CORE);
    };


    private void checkAndPushRequests(String permission, Vector<String> requests) {
        if (ContextCompat.checkSelfPermission(this,permission) != PackageManager.PERMISSION_GRANTED)
            requests.add(permission);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        if (requestCode==PERMISSIONS_CORE) {
            for (int i:grantResults) {
                if (i!=PackageManager.PERMISSION_GRANTED) Log.e("Main Activity","Permission denied: "+i);
            }
        }
    }

}
