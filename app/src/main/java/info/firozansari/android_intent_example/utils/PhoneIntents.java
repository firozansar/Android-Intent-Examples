package info.firozansari.android_intent_example.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.AlarmClock;
import android.provider.Contacts;
import android.provider.ContactsContract;
import android.provider.Telephony;
import android.text.TextUtils;

import com.google.android.gms.actions.NoteIntents;

/**
 * Provides factory methods to create intents to send SMS, MMS and call phone numbers
 *
 */
public class PhoneIntents {

    /**
     * Creates an intent that will allow to send an SMS without specifying the phone number
     *
     * @return the intent
     */
    public static Intent newEmptySmsIntent(Context context) {
        return newSmsIntent(context, null, (String[]) null);
    }

    /**
     * Creates an intent that will allow to send an SMS without specifying the phone number
     *
     * @param phoneNumber The phone number to send the SMS to
     * @return the intent
     */
    public static Intent newEmptySmsIntent(Context context, String phoneNumber) {
        return newSmsIntent(context, null, new String[]{phoneNumber});
    }

    /**
     * Creates an intent that will allow to send an SMS without specifying the phone number
     *
     * @param phoneNumbers The phone numbers to send the SMS to
     * @return the intent
     */
    public static Intent newEmptySmsIntent(Context context, String[] phoneNumbers) {
        return newSmsIntent(context, null, phoneNumbers);
    }

    /**
     * Creates an intent that will allow to send an SMS without specifying the phone number
     *
     * @param body The text to send
     * @return the intent
     */
    public static Intent newSmsIntent(Context context, String body) {
        return newSmsIntent(context, body, (String[]) null);
    }

    /**
     * Creates an intent that will allow to send an SMS without specifying the phone number
     *
     * @param body The text to send
     * @param phoneNumber The phone number to send the SMS to
     * @return the intent
     */
    public static Intent newSmsIntent(Context context, String body, String phoneNumber) {
        return newSmsIntent(context, body, new String[]{phoneNumber});
    }


    /**
     * Creates an intent that will allow to send an SMS to a phone number
     *
     * @param body         The text to send
     * @param phoneNumbers The phone numbers to send the SMS to (or null if you don't want to specify it)
     * @return the intent
     */
    public static Intent newSmsIntent(Context context, String body, String[] phoneNumbers) {
        Uri smsUri;
        if (phoneNumbers == null || phoneNumbers.length==0) {
            smsUri = Uri.parse("smsto:");
        } else {
            smsUri = Uri.parse("smsto:" + Uri.encode(TextUtils.join(",", phoneNumbers)));
        }

        Intent intent;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            intent = new Intent(Intent.ACTION_SENDTO, smsUri);
            intent.setPackage(Telephony.Sms.getDefaultSmsPackage(context));
        } else {
            intent = new Intent(Intent.ACTION_VIEW, smsUri);
        }

        if (body!=null) {
            intent.putExtra("sms_body", body);
        }

        return intent;
    }

    /**
     * Creates an intent that will open the phone app and enter the given number. Unlike
     * {@link #newCallNumberIntent(String)}, this does not actually dispatch the call, so it gives the user a chance to
     * review and edit the number.
     *
     * @param phoneNumber the number to dial
     * @return the intent
     */
    public static Intent newDialNumberIntent(String phoneNumber) {
        final Intent intent;
        if (phoneNumber == null || phoneNumber.trim().length() <= 0) {
            intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"));
        } else {
            intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phoneNumber.replace(" ", "")));
        }
        return intent;
    }

    /**
     * Creates an intent that will immediately dispatch a call to the given number. NOTE that unlike
     * {@link #newDialNumberIntent(String)}, this intent requires the {@link android.Manifest.permission#CALL_PHONE}
     * permission to be set.
     *
     * @param phoneNumber the number to call
     * @return the intent
     */
    public static Intent newCallNumberIntent(String phoneNumber) {
        final Intent intent;
        if (phoneNumber == null || phoneNumber.trim().length() <= 0) {
            intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"));
        } else {
            intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phoneNumber.replace(" ", "")));
        }
        return intent;
    }

    /**
     * Pick contact from phone book
     */
    public static Intent newPickContactIntent() {
        return new Intent(Intent.ACTION_VIEW,
                Uri.parse("content://contacts/people/"));
    }

    /**
     * Edit a contact from phone book
     */
    public static Intent editContactIntent() {
        return new Intent(Intent.ACTION_EDIT,
                Uri.parse("content://contacts/people/1"));
    }


    /**
     * Pick contact from phone book
     * <p/>
     * Examples:
     * <p/>
     * <code><pre>
     *     // Select only from users with emails
     *     IntentUtils.pickContact(ContactsContract.CommonDataKinds.Email.CONTENT_TYPE);
     * <p/>
     *     // Select only from users with phone numbers on pre Eclair devices
     *     IntentUtils.pickContact(Contacts.Phones.CONTENT_TYPE);
     * <p/>
     *     // Select only from users with phone numbers on devices with Eclair and higher
     *     IntentUtils.pickContact(ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE);
     * </pre></code>
     *
     * @param scope You can restrict selection by passing required content type.
     */
    @SuppressWarnings("deprecation")
    public static Intent newPickContactIntent(String scope) {
        Intent intent = new Intent(Intent.ACTION_PICK, Uri.parse("content://com.android.contacts/contacts"));

        if (!TextUtils.isEmpty(scope)) {
            intent.setType(scope);
        }

        return intent;
    }

    /**
     * Pick contact only from contacts with telephone numbers
     */
    @SuppressWarnings("deprecation")
    public static Intent newPickContactWithPhoneIntent() {

        return newPickContactIntent(ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE);
    }

    /**
     * Edit a contact from phone book
     */
    public static Intent showAllAlarms() {
        return new Intent(AlarmClock.ACTION_SHOW_ALARMS);
    }

    /**
     * Create a note with a subject and text
     */
    public static Intent createNote(String subject, String text) {
        return new Intent(NoteIntents.ACTION_CREATE_NOTE)
                .putExtra(NoteIntents.EXTRA_NAME, subject)
                .putExtra(NoteIntents.EXTRA_TEXT, text);
    }

}
