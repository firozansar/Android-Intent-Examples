package info.firozansari.android_intent_example

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import info.firozansari.android_intent_example.utils.*
import java.util.*

class MainFragment : Fragment() {
    private var mListener: OnFragmentInteractionListener? = null
    private lateinit var textView: TextView
    private lateinit var ItemRecyclerView: RecyclerView
    private var itemAdapter: ItemAdapter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val mView = inflater.inflate(R.layout.fragment_main, container, false)
        textView = mView.findViewById<View>(R.id.explain_tv) as TextView
        textView.setOnClickListener {
            activity?.let { it1 -> AlertDialog.Builder(it1).setMessage(intentExplanationText).show() }
        }
        ItemRecyclerView = mView.findViewById<View>(R.id.item_recycler) as RecyclerView
        val recyclerLayoutManager = LinearLayoutManager(activity)
        ItemRecyclerView.layoutManager = recyclerLayoutManager
        if (mListener != null) {
            itemAdapter = activity?.let { ItemAdapter(it, mListener!!) }
            ItemRecyclerView.adapter = itemAdapter
        }
        return mView
    }

    override fun onResume() {
        super.onResume()
        itemAdapter?.swapList(demoItems)
    }

    private val demoItems: List<DemoItem>
        private get() {
            val res = resources
            val demoItemList: MutableList<DemoItem> = ArrayList()

            // PhoneIntents
            demoItemList.add(DemoItem(res.getString(R.string.dialer), PhoneIntents.newDialNumberIntent(null)))
            demoItemList.add(DemoItem(res.getString(R.string.call_number), PhoneIntents.newCallNumberIntent("07957941679")))
            demoItemList.add(DemoItem(res.getString(R.string.dial_number), PhoneIntents.newDialNumberIntent("07957941679")))
            demoItemList.add(DemoItem(res.getString(R.string.send_sms_to), PhoneIntents.newSmsIntent(activity, "this is a test SMS", "07957941679")))
            demoItemList.add(DemoItem(res.getString(R.string.send_sms), PhoneIntents.newSmsIntent(activity, "this is a test SMS")))
            demoItemList.add(DemoItem(res.getString(R.string.pick_contact), PhoneIntents.newPickContactIntent()))
            // demoItemList.add(new DemoItem(res.getString(R.string.edit_contact), PhoneIntents.editContactIntent()));
            demoItemList.add(DemoItem(res.getString(R.string.pick_contact_with_phone), PhoneIntents.newPickContactWithPhoneIntent()))
            demoItemList.add(DemoItem(res.getString(R.string.show_alarms), PhoneIntents.showAllAlarms()))
            // demoItemList.add(new DemoItem(res.getString(R.string.create_notes), PhoneIntents.createNote("Test subject", "Some random texts")));

            // GeoIntents
            // demoItemList.add(new DemoItem(res.getString(R.string.map_of), GeoIntents.newMapsIntent("10 Downing Street, London", "Prime Minister's Residence")));
            demoItemList.add(DemoItem(res.getString(R.string.map_at), GeoIntents.newMapsIntent(53.600910f, -2.547780f, "Horwich, Bolton")))
            demoItemList.add(DemoItem(res.getString(R.string.navigate_to_address), GeoIntents.newNavigationIntent("Manchester Piccadilly Garden")))
            demoItemList.add(DemoItem(res.getString(R.string.navigate_to_location), GeoIntents.newNavigationIntent(53.600910f, -2.547780f)))
            demoItemList.add(DemoItem(res.getString(R.string.streetview_at_location), GeoIntents.newStreetViewIntent(53.600910f, -2.547780f)))

            // MediaIntents
            demoItemList.add(DemoItem(res.getString(R.string.play_image), MediaIntents.newPlayImageIntent("http://upload.wikimedia.org/wikipedia/commons/thumb/a/a9/Biarritz-Plage.JPG/1920px-Biarritz-Plage.JPG")))
            demoItemList.add(DemoItem(res.getString(R.string.play_audio), MediaIntents.newPlayAudioIntent("http://www.stephaniequinn.com/Music/Allegro%20from%20Duet%20in%20C%20Major.mp3")))
            demoItemList.add(DemoItem(res.getString(R.string.play_video), MediaIntents.newPlayVideoIntent("http://mirror.bigbuckbunny.de/peach/bigbuckbunny_movies/big_buck_bunny_480p_h264.mov")))
            // demoItemList.add(new DemoItem(res.getString(R.string.play_video_youtube), MediaIntents.newPlayYouTubeVideoIntent("b_yiWIXBI7o")));
            demoItemList.add(DemoItem(res.getString(R.string.browse_web), MediaIntents.newOpenWebBrowserIntent("http://firozansari.info")))
            demoItemList.add(DemoItem(res.getString(R.string.take_pic), MediaIntents.newTakePictureIntent()))
            demoItemList.add(DemoItem(res.getString(R.string.select_pic), MediaIntents.newSelectPictureIntent()))

            // EmailIntents
            demoItemList.add(DemoItem(res.getString(R.string.email_to), EmailIntents.newEmailIntent("test@example.com", "My subject", "My content")))

            // ShareIntents
            demoItemList.add(DemoItem(res.getString(R.string.share), ShareIntents.newShareTextIntent("My subject", "My message", getString(R.string.share_dialog_title))))

            // SystemIntents
            demoItemList.add(DemoItem(res.getString(R.string.show_wifi_settings), SystemIntents.showWifiSettings()))
            demoItemList.add(DemoItem(res.getString(R.string.show_nfc_settings), SystemIntents.showNfcSettings()))
//            demoItemList.add(SystemIntents.newGooglePlayIntent(activity, "uk.co.topcashback.topcashback")
//                ?.let { DemoItem(res.getString(R.string.app_store), it) })
            return demoItemList
        }
    private val intentExplanationText: String
        private get() {
            val stringBuilder = StringBuilder()
            stringBuilder.append("An Intent is a messaging object you can use to request an action from another app component.")
            stringBuilder.append(Constant.LINE_SEPARATOR)
            stringBuilder.append("It's mainly used to start activity or service or deliver a broadcast.")
            stringBuilder.append(Constant.LINE_SEPARATOR)
            stringBuilder.append("Explicit intents specify which app component will satisfy the intent but implicit intents do not name a specific component")
            stringBuilder.append(Constant.LINE_SEPARATOR)
            stringBuilder.append("The primary information contained in an Intent is the following:")
            stringBuilder.append(Constant.LINE_SEPARATOR)
            stringBuilder.append(" 1. Component name")
            stringBuilder.append(Constant.LINE_SEPARATOR)
            stringBuilder.append(" 2. Action (such as view or send) ")
            stringBuilder.append(Constant.LINE_SEPARATOR)
            stringBuilder.append(" 3. Data (URI or MIME type) ")
            stringBuilder.append(Constant.LINE_SEPARATOR)
            stringBuilder.append(" 4. Category (such as browsable or launcher) ")
            stringBuilder.append(Constant.LINE_SEPARATOR)
            stringBuilder.append("Above listed properties needed for most Intent.")
            stringBuilder.append(Constant.LINE_SEPARATOR)
            return stringBuilder.toString()
        }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mListener = try {
            context as OnFragmentInteractionListener
        } catch (e: ClassCastException) {
            throw ClassCastException(context.toString()
                    + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        mListener = null
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     */
    interface OnFragmentInteractionListener {
        fun onFragmentInteraction(intent: Intent?)
    }

    companion object {
        fun newInstance(): MainFragment {
            return MainFragment()
        }
    }
}