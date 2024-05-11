package info.firozansari.android_intent_example.utils

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.Settings

/**
 * Provides factory methods to create intents to do some system tasks such as opening the market app, ...
 *
 */
object SystemIntents {
    /**
     * Intent that should open the app store of the device on the current application page
     *
     * @param context The context associated to the application
     * @return the intent
     */
    fun newGooglePlayIntent(context: Context): Intent? {
        val packageName = context.applicationContext.packageName
        return newGooglePlayIntent(context, packageName)
    }


    /**
     * Intent that should open either the Google Play app or if not available, the web browser on the Google Play website
     *
     * @param context     The context associated to the application
     * @param packageName The package name of the application to find on the market
     * @return the intent for native application or an intent to redirect to the browser if google play is not installed
     */
    fun newGooglePlayIntent(context: Context?, packageName: String): Intent? {
        var intent = Intent(
            Intent.ACTION_VIEW, Uri.parse(
                "https://play.google.com/store/apps/details?id=$packageName"
            )
        )

        if (!IntentUtils.isIntentAvailable(context!!, intent)) {
            intent = MediaIntents.newOpenWebBrowserIntent(
                "https://play.google.com/store/apps/details?id="
                        + packageName
            )
        }

        if (intent != null) {
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY or Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET)
        }

        return intent
    }

    /**
     * Intent that should open either the Amazon store app or if not available, the web browser on the Amazon website
     *
     * @param context     The context associated to the application
     * @param packageName The package name of the application to find on the market
     * @return the intent for native application or an intent to redirect to the browser if google play is not installed
     */
    fun newAmazonStoreIntent(context: Context?, packageName: String): Intent? {
        var intent = Intent(
            Intent.ACTION_VIEW, Uri.parse(
                "amzn://apps/android?p=$packageName"
            )
        )

        if (!IntentUtils.isIntentAvailable(context!!, intent)) {
            intent = MediaIntents.newOpenWebBrowserIntent(
                "http://www.amazon.com/gp/mas/dl/android?p="
                        + packageName
            )
        }

        if (intent != null) {
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY or Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET)
        }

        return intent
    }

    /**
     * Pick file from sdcard with file manager. Chosen file can be obtained from Intent in onActivityResult.
     * See code below for example:
     *
     *
     * <pre>`
     * @Override
     * protected void onActivityResult(int requestCode, int resultCode, Intent data) {
     * Uri file = data.getData();
     * }
    `</pre> *
     */
    fun newPickFileIntent(): Intent {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.setType("file/*")
        return intent
    }

    /**
     * Show Wifi serttings
     */
    fun showWifiSettings(): Intent {
        return Intent(Settings.ACTION_WIFI_SETTINGS)
    }

    /**
     * Show Wifi serttings
     */
    fun showNfcSettings(): Intent {
        return Intent(Settings.ACTION_NFC_SETTINGS)
    }
}
