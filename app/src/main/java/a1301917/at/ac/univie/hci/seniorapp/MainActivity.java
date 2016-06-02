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
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;
import java.util.Vector;

/**
 * Menü mit Sprachsteuerung
 */
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
        infoLinesCount=0;
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

    }

    /**
     * Button zur Befehlsentgegennahme durch Spracherkennung
     * @param view
     */
    public void GiveCommand(View view){
        senior_handy.dialog("Bitte sprechen Sie", new DHRoot());
    }

    class DHRoot implements  DialogHandler {

        @Override
        public void handle(String answer, VoiceAssistant voiceAssistant) {
            Log.i("MainActivity","You said: " + answer);
            MainActivity.this.showInfo(answer);
            for (ContactInfo info : contacts) {
                if (answer.toLowerCase().contains(info.getName().toLowerCase())) {
                    senior_handy.dialog("Wollen Sie " + info.getName() + " anrufen?",new DHCall(info));
                    MainActivity.this.showInfo("Wollen Sie " + info.getName() + " anrufen");
                    break;
                }
            }
        }
    }

    class DHCall implements DialogHandler {

        private ContactInfo contactInfo;

        DHCall(ContactInfo contactInfo) {
            this.contactInfo = contactInfo;
        }

        @Override
        public void handle(String answer, VoiceAssistant voiceAssistant) {
            MainActivity.this.showInfo(answer);
            if (answer.toLowerCase().contains("ja")) {
                MainActivity.this.showInfo("ja");
                Log.i("MainActivity", "Calling " + contactInfo.getName());
                Util.doPhoneCall(MainActivity.this, contactInfo.getNumber());
            }
        }
    }

    private int infoLinesCount;
    public void showInfo(String infoText) {

        TextView textView = (TextView)MainActivity.this.findViewById(R.id.tv_diaLog);
        textView.append(infoText + "\n");
        if (++infoLinesCount > 5) {
            String s = textView.getText().toString();
            textView.setText(s.substring(s.indexOf('\n')+1));
            --infoLinesCount;
        }
    }

    /**
     * Überprüfen der Permissions
     */
    private static final int PERMISSIONS_CORE = 0;
    private void checkAndRequestAllPermissions() {
        Vector<String> permissionRequests = new Vector<>();
        checkAndPushRequests(android.Manifest.permission.RECORD_AUDIO,permissionRequests);
        //checkAndPushRequests(android.Manifest.permission.READ_CONTACTS,permissionRequests);
        //checkAndPushRequests(android.Manifest.permission.CALL_PHONE, permissionRequests);
        if (permissionRequests.size()>0)
            ActivityCompat.requestPermissions(this, permissionRequests.toArray(new String[permissionRequests.size()]), PERMISSIONS_CORE);
    };


    /**
     * Weiterleitung der Anfrage bei Berechtigung
     * @param permission
     * @param requests
     */
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
