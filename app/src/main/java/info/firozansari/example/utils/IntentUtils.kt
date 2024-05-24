package info.firozansari.example.utils

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri

/**
 * Provides utility functions to work with intents
 *
 */
object IntentUtils {
    /**
     * Checks whether there are applications installed which are able to handle the given action/data.
     *
     * @param context  the current context
     * @param action   the action to check
     * @param uri      that data URI to check (may be null)
     * @param mimeType the MIME type of the content (may be null)
     * @return true if there are apps which will respond to this action/data
     */
    fun isIntentAvailable(
        context: Context,
        action: String?,
        uri: Uri?,
        mimeType: String?
    ): Boolean {
        val intent = if ((uri != null)) Intent(action, uri) else Intent(action)
        if (mimeType != null) {
            intent.setType(mimeType)
        }
        val list = context.packageManager.queryIntentActivities(
            intent,
            PackageManager.MATCH_DEFAULT_ONLY
        )
        return !list.isEmpty()
    }

    /**
     * Checks whether there are applications installed which are able to handle the given action/type.
     *
     * @param context  the current context
     * @param action   the action to check
     * @param mimeType the MIME type of the content (may be null)
     * @return true if there are apps which will respond to this action/type
     */
    fun isIntentAvailable(context: Context, action: String?, mimeType: String?): Boolean {
        val intent = Intent(action)
        if (mimeType != null) {
            intent.setType(mimeType)
        }
        val list = context.packageManager.queryIntentActivities(
            intent,
            PackageManager.MATCH_DEFAULT_ONLY
        )
        return !list.isEmpty()
    }

    /**
     * Checks whether there are applications installed which are able to handle the given intent.
     *
     * @param context the current context
     * @param intent  the intent to check
     * @return true if there are apps which will respond to this intent
     */
    @JvmStatic
    fun isIntentAvailable(context: Context, intent: Intent?): Boolean {
        val list = context.packageManager.queryIntentActivities(
            intent!!,
            PackageManager.MATCH_DEFAULT_ONLY
        )
        return list.isNotEmpty()
    }
}
