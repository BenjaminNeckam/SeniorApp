package a1301917.at.ac.univie.hci.seniorapp;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;

import java.util.ArrayList;
import java.util.List;

/**
 * Aktivität zum Anzeigen der Kontakte
 */
public class Util {
    public static List<ContactInfo> getContacts(ContentResolver resolver) {
        List<ContactInfo> result = new ArrayList<>();
        String number;

        Uri CONTENT_URI = ContactsContract.Contacts.CONTENT_URI;
        String _ID = ContactsContract.Contacts._ID;
        String DISPLAY_NAME = ContactsContract.Contacts.DISPLAY_NAME;
        String HAS_PHONE_NUMBER = ContactsContract.Contacts.HAS_PHONE_NUMBER;

        Uri Phone_CONTENT_URI = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        String Phone_CONTACT_ID = ContactsContract.CommonDataKinds.Phone.CONTACT_ID;
        String NUMBER = ContactsContract.CommonDataKinds.Phone.NUMBER;

        //StringBuffer contactListResult = new StringBuffer();

        Cursor cursor = resolver.query(CONTENT_URI, null, null, null, null);

        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                String contact_id = cursor.getString(cursor.getColumnIndex(_ID));
                String name = cursor.getString(cursor.getColumnIndex(DISPLAY_NAME));
                ContactInfo contactInfo = new ContactInfo();

                int hasPhoneNumber = Integer.parseInt(cursor.getString(cursor.getColumnIndex(HAS_PHONE_NUMBER)));

                if (hasPhoneNumber > 0) {

                    //contactListResult.append("\n Vorname:" + name);
                    contactInfo.setName(name);
                    Cursor phoneCursor = resolver.query(Phone_CONTENT_URI, null, Phone_CONTACT_ID + " = ?", new String[]{contact_id}, null);

                    while (phoneCursor.moveToNext()) {
                        number = phoneCursor.getString(phoneCursor.getColumnIndex(NUMBER));
                        contactInfo.setNumber(number);
                    }
                    phoneCursor.close();
                }
                //contactListResult.append("\n");s
                if (contactInfo.getName() != null && contactInfo.getNumber() != null) {
                    result.add(contactInfo);
                }
            }
        }
        return result;
    }



}

