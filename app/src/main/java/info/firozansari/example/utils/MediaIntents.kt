package info.firozansari.example.utils

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.provider.MediaStore
import java.io.File

/**
 * Provides factory methods to create intents to view / open / ... medias
 *
 */
object MediaIntents {
    const val AUDIO_TYPE: String = "audio/*"
    const val VIDEO_TYPE: String = "video/*"
    const val IMAGE_TYPE: String = "image/*"

    /**
     * Open the media player to play the given media
     *
     * @param file The file path of the media to play.
     * @return the intent
     */
    fun newPlayAudioFileIntent(file: File?): Intent {
        return newPlayMediaFileIntent(file, AUDIO_TYPE)
    }

    /**
     * Open the media player to play the given media
     *
     * @param path The file path of the media to play.
     * @return the intent
     */
    fun newPlayAudioFileIntent(path: String?): Intent {
        return newPlayMediaFileIntent(path, AUDIO_TYPE)
    }

    /**
     * Open the media player to play the given media
     *
     * @param url The URL of the media to play.
     * @return the intent
     */
    fun newPlayAudioIntent(url: String?): Intent {
        return newPlayMediaIntent(url, AUDIO_TYPE)
    }

    /**
     * Open the media player to play the given media
     *
     * @param file The file path of the media to play.
     * @return the intent
     */
    fun newPlayImageFileIntent(file: File?): Intent {
        return newPlayMediaFileIntent(file, IMAGE_TYPE)
    }

    /**
     * Open the media player to play the given media
     *
     * @param path The file path of the media to play.
     * @return the intent
     */
    fun newPlayImageFileIntent(path: String?): Intent {
        return newPlayMediaFileIntent(path, IMAGE_TYPE)
    }

    /**
     * Open the media player to play the given media
     *
     * @param url The URL of the media to play.
     * @return the intent
     */
    fun newPlayImageIntent(url: String?): Intent {
        return newPlayMediaIntent(url, IMAGE_TYPE)
    }

    /**
     * Open the media player to play the given media
     *
     * @param file The file path of the media to play.
     * @return the intent
     */
    fun newPlayVideoFileIntent(file: File?): Intent {
        return newPlayMediaFileIntent(file, VIDEO_TYPE)
    }

    /**
     * Open the media player to play the given media
     *
     * @param path The file path of the media to play.
     * @return the intent
     */
    fun newPlayVideoFileIntent(path: String?): Intent {
        return newPlayMediaFileIntent(path, VIDEO_TYPE)
    }

    /**
     * Open the media player to play the given media
     *
     * @param url The URL of the media to play.
     * @return the intent
     */
    fun newPlayVideoIntent(url: String?): Intent {
        return newPlayMediaIntent(url, VIDEO_TYPE)
    }

    /**
     * Open a YouTube video. If the app is not installed, it opens it in the browser
     *
     * @param videoId The video ID
     *
     * @return the intent
     */
    fun newPlayYouTubeVideoIntent(videoId: String): Intent {
        return try {
            Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:$videoId"))
        } catch (ex: ActivityNotFoundException) {
            Intent(Intent.ACTION_VIEW, Uri.parse("http://www.youtube.com/watch?v=$videoId"))
        }
    }

    /**
     * Open the media player to play the given media
     *
     * @param url  The URL of the media to play.
     * @param type The mime type
     * @return the intent
     */
    fun newPlayMediaIntent(url: String?, type: String?): Intent {
        return newPlayMediaIntent(Uri.parse(url), type)
    }

    /**
     * Open the media player to play the given media
     *
     * @param file The file path of the media to play.
     * @param type The mime type
     * @return the intent
     */
    fun newPlayMediaFileIntent(file: File?, type: String?): Intent {
        return newPlayMediaIntent(Uri.fromFile(file), type)
    }

    /**
     * Open the media player to play the given media
     *
     * @param path The file path of the media to play.
     * @param type The mime type
     * @return the intent
     */
    fun newPlayMediaFileIntent(path: String?, type: String?): Intent {
        return newPlayMediaIntent(Uri.fromFile(File(path)), type)
    }

    /**
     * Open the media player to play the given media Uri
     *
     * @param uri  The Uri of the media to play.
     * @param type The mime type
     * @return the intent
     */
    fun newPlayMediaIntent(uri: Uri?, type: String?): Intent {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.setDataAndType(uri, type)
        return intent
    }

    /**
     * Creates an intent that will launch a browser (most probably as other apps may handle specific URLs, e.g. YouTube)
     * to view the provided URL.
     *
     * @param url the URL to open
     * @return the intent
     */
    @JvmStatic
    fun newOpenWebBrowserIntent(url: String): Intent {
        var url = url
        if (!url.startsWith("https://") && !url.startsWith("http://")) {
            url = "http://$url"
        }
        return Intent(Intent.ACTION_VIEW, Uri.parse(url))
    }

    /**
     * Creates an intent that will launch the camera to take a picture that's saved to a temporary file so you can use
     * it directly without going through the gallery.
     *
     * @param tempFile the file that should be used to temporarily store the picture
     * @return the intent
     */
    fun newTakePictureIntent(tempFile: File?): Intent {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(tempFile))
        return intent
    }

    /**
     * Creates an intent that will launch the camera to take a picture that's saved to a temporary file so you can use
     * it directly without going through the gallery.
     *
     * @return the intent
     */
    fun newTakePictureIntent(): Intent {
        val intent = Intent("android.media.action.IMAGE_CAPTURE")
        return intent
    }

    /**
     * Creates an intent that will launch the phone's picture gallery to select a picture from it.
     *
     * @return the intent
     */
    fun newSelectPictureIntent(): Intent {
        val intent = Intent(Intent.ACTION_PICK)
        intent.setType("image/*")
        return intent
    }
}
