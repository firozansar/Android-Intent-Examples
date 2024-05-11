package info.firozansari.android_intent_example.utils

import android.content.Intent
import android.net.Uri

/**
 * Provides factory methods to create intents to send emails
 *
 */
object EmailIntents {
    private const val MIME_TYPE_EMAIL = "message/rfc822"

    /**
     * Create an intent to send an email with an attachment to a single recipient
     *
     * @param address    The recipient address (or null if not specified)
     * @param subject    The subject of the email (or null if not specified)
     * @param body       The body of the email (or null if not specified)
     * @param attachment The URI of a file to attach to the email. Note that the URI must point to a location the email
     * application is allowed to read and has permissions to access.
     * @return the intent
     */
    /**
     * Create an intent to send an email to a single recipient
     *
     * @param address The recipient address (or null if not specified)
     * @param subject The subject of the email (or null if not specified)
     * @param body    The body of the email (or null if not specified)
     * @return the intent
     */
    @JvmOverloads
    fun newEmailIntent(
        address: String?,
        subject: String?,
        body: String?,
        attachment: Uri? = null
    ): Intent {
        return newEmailIntent(
            if (address == null) null else arrayOf<String>(address),
            subject,
            body,
            attachment
        )
    }

    /**
     * Create an intent to send an email with an attachment
     *
     * @param addresses  The recipients addresses (or null if not specified)
     * @param subject    The subject of the email (or null if not specified)
     * @param body       The body of the email (or null if not specified)
     * @param attachment The URI of a file to attach to the email. Note that the URI must point to a location the email
     * application is allowed to read and has permissions to access.
     * @return the intent
     */
    fun newEmailIntent(
        addresses: Array<String>?,
        subject: String?,
        body: String?,
        attachment: Uri?
    ): Intent {
        val intent = Intent(Intent.ACTION_SEND)
        if (addresses != null) intent.putExtra(Intent.EXTRA_EMAIL, addresses)
        if (body != null) intent.putExtra(Intent.EXTRA_TEXT, body)
        if (subject != null) intent.putExtra(Intent.EXTRA_SUBJECT, subject)
        if (attachment != null) intent.putExtra(Intent.EXTRA_STREAM, attachment)
        intent.setType(MIME_TYPE_EMAIL)

        return intent
    }
}
