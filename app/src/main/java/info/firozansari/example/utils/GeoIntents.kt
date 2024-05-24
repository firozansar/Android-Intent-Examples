package info.firozansari.example.utils

import android.content.Intent
import android.net.Uri
import android.text.TextUtils

/**
 * Provides factory methods to create intents to work with geographical data (search locations for instance)
 *
 */
object GeoIntents {
    /**
     * Intent that should allow opening a map showing the given address (if it exists)
     *
     * @param address    The address to search
     * @param placeTitle The title to show on the marker
     * @return the intent
     */
    fun newMapsIntent(address: String?, placeTitle: String): Intent {
        val sb = StringBuilder()
        sb.append("geo:0,0?q=")

        val addressEncoded = Uri.encode(address)
        sb.append(addressEncoded)

        // pass text for the info window
        val titleEncoded = Uri.encode(" ($placeTitle)")
        sb.append(titleEncoded)

        return Intent(Intent.ACTION_VIEW, Uri.parse(sb.toString()))
    }

    /**
     * Intent that should allow opening a map showing the given location (if it exists)
     *
     * @param latitude  The latitude of the center of the map
     * @param longitude The longitude of the center of the map
     * @param placeName The name to show on the marker
     * @return the intent
     */
    /**
     * Intent that should allow opening a map showing the given location (if it exists)
     *
     * @param latitude  The latitude of the center of the map
     * @param longitude The longitude of the center of the map
     * @return the intent
     */
    @JvmOverloads
    fun newMapsIntent(latitude: Float, longitude: Float, placeName: String? = null): Intent {
        val sb = StringBuilder()
        sb.append("geo:")

        sb.append(latitude)
        sb.append(",")
        sb.append(longitude)

        if (!TextUtils.isEmpty(placeName)) {
            sb.append("?q=")
            sb.append(latitude)
            sb.append(",")
            sb.append(longitude)
            sb.append("(")
            sb.append(Uri.encode(placeName))
            sb.append(")")
        }

        return Intent(Intent.ACTION_VIEW, Uri.parse(sb.toString()))
    }

    /**
     * Intent that should allow opening a map showing the given address (if it exists)
     *
     * @param address The address to search
     * @return the intent
     */
    fun newNavigationIntent(address: String?): Intent {
        val sb = StringBuilder()
        sb.append("google.navigation:q=")

        val addressEncoded = Uri.encode(address)
        sb.append(addressEncoded)

        return Intent(Intent.ACTION_VIEW, Uri.parse(sb.toString()))
    }

    /**
     * Intent that should allow opening a map showing the given location (if it exists)
     *
     * @param latitude  The latitude of the center of the map
     * @param longitude The longitude of the center of the map
     * @return the intent
     */
    fun newNavigationIntent(latitude: Float, longitude: Float): Intent {
        val sb = StringBuilder()
        sb.append("google.navigation:q=")

        sb.append(latitude)
        sb.append(",")
        sb.append(longitude)

        return Intent(Intent.ACTION_VIEW, Uri.parse(sb.toString()))
    }

    /**
     * Opens the Street View application to the given location.
     * The URI scheme is based on the syntax used for Street View panorama information in Google Maps URLs.
     *
     * @param latitude  Latitude
     * @param longitude Longitude
     * @param zoom      Panorama zoom. 1.0 = normal zoom, 2.0 = zoomed in 2x, 3.0 = zoomed in 4x, and so on.
     * A zoom of 1.0 is 90 degree horizontal FOV for a nominal landscape mode 4 x 3 aspect ratio display Android
     * phones in portrait mode will adjust the zoom so that the vertical FOV is approximately the same as the
     * landscape vertical FOV. This means that the horizontal FOV of an Android phone in portrait mode is much
     * narrower than in landscape mode. This is done to minimize the fisheye lens effect that would be present
     * if a 90 degree horizontal FOV was used in portrait mode.
     */
    fun newStreetViewIntent(latitude: Float, longitude: Float, zoom: Float): Intent {
        return newStreetViewIntent(latitude, longitude, null, null, zoom, null)
    }

    /**
     * Opens the Street View application to the given location.
     * The URI scheme is based on the syntax used for Street View panorama information in Google Maps URLs.
     *
     * @param latitude  Latitude
     * @param longitude Longitude
     * @param zoom      Panorama zoom. 1.0 = normal zoom, 2.0 = zoomed in 2x, 3.0 = zoomed in 4x, and so on.
     * A zoom of 1.0 is 90 degree horizontal FOV for a nominal landscape mode 4 x 3 aspect ratio display Android
     * phones in portrait mode will adjust the zoom so that the vertical FOV is approximately the same as the
     * landscape vertical FOV. This means that the horizontal FOV of an Android phone in portrait mode is much
     * narrower than in landscape mode. This is done to minimize the fisheye lens effect that would be present
     * if a 90 degree horizontal FOV was used in portrait mode.
     * @param mapZoom   The map zoom of the map location associated with this panorama.
     * This value is passed on to the Maps activity when the Street View "Go to Maps" menu item is chosen.
     * It corresponds to the zoomLevel parameter in [.showLocation]
     */
    fun newStreetViewIntent(latitude: Float, longitude: Float, zoom: Float, mapZoom: Int): Intent {
        return newStreetViewIntent(latitude, longitude, null, null, zoom, mapZoom)
    }

    /**
     * Opens the Street View application to the given location.
     * The URI scheme is based on the syntax used for Street View panorama information in Google Maps URLs.
     *
     * @param latitude  Latitude
     * @param longitude Longitude
     * @param yaw       Panorama center-of-view in degrees clockwise from North.
     *
     *
     * Note: The two commas after the yaw parameter are required.
     * They are present for backwards-compatibility reasons.
     * @param pitch     Panorama center-of-view in degrees from -90 (look straight up) to 90 (look straight down.)
     * @param zoom      Panorama zoom. 1.0 = normal zoom, 2.0 = zoomed in 2x, 3.0 = zoomed in 4x, and so on.
     * A zoom of 1.0 is 90 degree horizontal FOV for a nominal landscape mode 4 x 3 aspect ratio display Android
     * phones in portrait mode will adjust the zoom so that the vertical FOV is approximately the same as the
     * landscape vertical FOV. This means that the horizontal FOV of an Android phone in portrait mode is much
     * narrower than in landscape mode. This is done to minimize the fisheye lens effect that would be present
     * if a 90 degree horizontal FOV was used in portrait mode.
     * @param mapZoom   The map zoom of the map location associated with this panorama.
     * This value is passed on to the Maps activity when the Street View "Go to Maps" menu item is chosen.
     * It corresponds to the zoomLevel parameter in [.showLocation]
     */
    /**
     * Opens the Street View application to the given location.
     * The URI scheme is based on the syntax used for Street View panorama information in Google Maps URLs.
     *
     * @param latitude  Latitude
     * @param longitude Longitude
     */
    @JvmOverloads
    fun newStreetViewIntent(
        latitude: Float,
        longitude: Float,
        yaw: Float? = null,
        pitch: Int? = null,
        zoom: Float? = null,
        mapZoom: Int? = null
    ): Intent {
        val builder =
            StringBuilder("google.streetview:cbll=").append(latitude).append(",").append(longitude)

        if (yaw != null || pitch != null || zoom != null) {
            val cbpParam = String.format(
                "%s,,%s,%s",
                yaw ?: "",
                pitch ?: "",
                zoom ?: ""
            )

            builder.append("&cbp=1,").append(cbpParam)
        }
        if (mapZoom != null) {
            builder.append("&mz=").append(mapZoom)
        }

        val intent = Intent()
        intent.setAction(Intent.ACTION_VIEW)
        intent.setData(Uri.parse(builder.toString()))

        return intent
    }
}
