package a1301917.at.ac.univie.hci.seniorapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;

/**
 * Created by Benni on 06.06.2016.
 */
public class SmsReceiver extends BroadcastReceiver {
    private final static String TAG= "Info: ";
    public final static String EXTRA_MESSAGE = "at.ac.univie.hci.seniorapp.MESSAGE";


    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
        try {
            if (bundle != null) {
                final Object[] pdusObj = (Object[]) bundle.get("pdus");

                for (int i = 0; i < pdusObj.length; i++) {
                    SmsMessage smsMessage = SmsMessage.createFromPdu((byte[]) pdusObj[i]);
                    String number = smsMessage.getOriginatingAddress();
                    String messageBody = smsMessage.getMessageBody();
                    startInterruptActivity(messageBody,context);
                    Log.i(TAG, "Number: " + number + " ;MessageBody: " + messageBody);
                }
            }
        }catch (Exception e){
            Log.e("SmsReceiver", "Exception SmsReceiver" + e);
        }
    }

    public void startInterruptActivity(String message, Context context){
        Intent newIntent = new Intent(context, InterruptActivity.class);
        newIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        newIntent.putExtra(EXTRA_MESSAGE,message);
        context.startActivity(newIntent);
    }
}
