package info.firozansari.example.utils

import android.content.Intent

/**
 * Provides factory methods to create intents to share stuff
 *
 */
object ShareIntents {
    /**
     * Creates a chooser to share some data.
     *
     * @param subject            The subject to share (might be discarded, for instance if the user picks an SMS app)
     * @param message            The message to share
     * @param chooserDialogTitle The title for the chooser dialog
     * @return the intent
     */
    fun newShareTextIntent(
        subject: String?,
        message: String?,
        chooserDialogTitle: String?
    ): Intent {
        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.putExtra(Intent.EXTRA_TEXT, message)
        shareIntent.putExtra(Intent.EXTRA_SUBJECT, subject)
        shareIntent.setType(MIME_TYPE_TEXT)
        return Intent.createChooser(shareIntent, chooserDialogTitle)
    }

    private const val MIME_TYPE_TEXT = "text/*"
}
