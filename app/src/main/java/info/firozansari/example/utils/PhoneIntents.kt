package info.firozansari.example.utils

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.provider.AlarmClock
import android.provider.ContactsContract
import android.provider.Telephony
import android.text.TextUtils
import com.google.android.gms.actions.NoteIntents

/**
 * Provides factory methods to create intents to send SMS, MMS and call phone numbers
 *
 */
object PhoneIntents {
    /**
     * Creates an intent that will allow to send an SMS without specifying the phone number
     *
     * @return the intent
     */
    fun newEmptySmsIntent(context: Context?): Intent {
        return newSmsIntent(context, null, null as Array<String?>?)
    }

    /**
     * Creates an intent that will allow to send an SMS without specifying the phone number
     *
     * @param phoneNumber The phone number to send the SMS to
     * @return the intent
     */
    fun newEmptySmsIntent(context: Context?, phoneNumber: String?): Intent {
        return newSmsIntent(context, null, arrayOf(phoneNumber))
    }

    /**
     * Creates an intent that will allow to send an SMS without specifying the phone number
     *
     * @param phoneNumbers The phone numbers to send the SMS to
     * @return the intent
     */
    fun newEmptySmsIntent(context: Context?, phoneNumbers: Array<String?>?): Intent {
        return newSmsIntent(context, null, phoneNumbers)
    }

    /**
     * Creates an intent that will allow to send an SMS without specifying the phone number
     *
     * @param body The text to send
     * @param phoneNumber The phone number to send the SMS to
     * @return the intent
     */
    fun newSmsIntent(context: Context?, body: String?, phoneNumber: String?): Intent {
        return newSmsIntent(context, body, arrayOf(phoneNumber))
    }


    /**
     * Creates an intent that will allow to send an SMS to a phone number
     *
     * @param body         The text to send
     * @param phoneNumbers The phone numbers to send the SMS to (or null if you don't want to specify it)
     * @return the intent
     */
    /**
     * Creates an intent that will allow to send an SMS without specifying the phone number
     *
     * @param body The text to send
     * @return the intent
     */
    @JvmOverloads
    fun newSmsIntent(
        context: Context?,
        body: String?,
        phoneNumbers: Array<String?>? = null as Array<String?>?
    ): Intent {
        val smsUri = if (phoneNumbers == null || phoneNumbers.size == 0) {
            Uri.parse("smsto:")
        } else {
            Uri.parse(
                "smsto:" + Uri.encode(
                    TextUtils.join(
                        ",",
                        phoneNumbers
                    )
                )
            )
        }
        val intent = Intent(Intent.ACTION_SENDTO, smsUri)
        intent.setPackage(Telephony.Sms.getDefaultSmsPackage(context))
        if (body != null) {
            intent.putExtra("sms_body", body)
        }
        return intent
    }

    /**
     * Creates an intent that will open the phone app and enter the given number. Unlike
     * [.newCallNumberIntent], this does not actually dispatch the call, so it gives the user a chance to
     * review and edit the number.
     *
     * @param phoneNumber the number to dial
     * @return the intent
     */
    fun newDialNumberIntent(phoneNumber: String?): Intent {
        val intent = if (phoneNumber == null || phoneNumber.trim { it <= ' ' }.length <= 0) {
            Intent(Intent.ACTION_DIAL, Uri.parse("tel:"))
        } else {
            Intent(
                Intent.ACTION_DIAL,
                Uri.parse("tel:" + phoneNumber.replace(" ", ""))
            )
        }
        return intent
    }

    /**
     * Creates an intent that will immediately dispatch a call to the given number. NOTE that unlike
     * [.newDialNumberIntent], this intent requires the [android.Manifest.permission.CALL_PHONE]
     * permission to be set.
     *
     * @param phoneNumber the number to call
     * @return the intent
     */
    fun newCallNumberIntent(phoneNumber: String?): Intent {
        val intent = if (phoneNumber == null || phoneNumber.trim { it <= ' ' }.length <= 0) {
            Intent(Intent.ACTION_CALL, Uri.parse("tel:"))
        } else {
            Intent(
                Intent.ACTION_CALL,
                Uri.parse("tel:" + phoneNumber.replace(" ", ""))
            )
        }
        return intent
    }

    /**
     * Pick contact from phone book
     */
    fun newPickContactIntent(): Intent {
        return Intent(
            Intent.ACTION_VIEW,
            Uri.parse("content://contacts/people/")
        )
    }

    /**
     * Edit a contact from phone book
     */
    fun editContactIntent(): Intent {
        return Intent(
            Intent.ACTION_EDIT,
            Uri.parse("content://contacts/people/1")
        )
    }


    /**
     * Pick contact from phone book
     *
     *
     * Examples:
     *
     *
     * `<pre>
     * // Select only from users with emails
     * IntentUtils.pickContact(ContactsContract.CommonDataKinds.Email.CONTENT_TYPE);
     *
     *
     * // Select only from users with phone numbers on pre Eclair devices
     * IntentUtils.pickContact(Contacts.Phones.CONTENT_TYPE);
     *
     *
     * // Select only from users with phone numbers on devices with Eclair and higher
     * IntentUtils.pickContact(ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE);
    </pre>` *
     *
     * @param scope You can restrict selection by passing required content type.
     */
    @Suppress("deprecation")
    fun newPickContactIntent(scope: String?): Intent {
        val intent =
            Intent(Intent.ACTION_PICK, Uri.parse("content://com.android.contacts/contacts"))

        if (!TextUtils.isEmpty(scope)) {
            intent.setType(scope)
        }

        return intent
    }

    /**
     * Pick contact only from contacts with telephone numbers
     */
    @Suppress("deprecation")
    fun newPickContactWithPhoneIntent(): Intent {
        return newPickContactIntent(ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE)
    }

    /**
     * Edit a contact from phone book
     */
    fun showAllAlarms(): Intent {
        return Intent(AlarmClock.ACTION_SHOW_ALARMS)
    }

    /**
     * Create a note with a subject and text
     */
    fun createNote(subject: String?, text: String?): Intent {
        return Intent(NoteIntents.ACTION_CREATE_NOTE)
            .putExtra(NoteIntents.EXTRA_NAME, subject)
            .putExtra(NoteIntents.EXTRA_TEXT, text)
    }
}
