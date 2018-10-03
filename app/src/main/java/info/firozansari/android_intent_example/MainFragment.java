package info.firozansari.android_intent_example;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Environment;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import info.firozansari.android_intent_example.utils.Constant;
import info.firozansari.android_intent_example.utils.EmailIntents;
import info.firozansari.android_intent_example.utils.GeoIntents;
import info.firozansari.android_intent_example.utils.ItemAdapter;
import info.firozansari.android_intent_example.utils.MediaIntents;
import info.firozansari.android_intent_example.utils.PhoneIntents;
import info.firozansari.android_intent_example.utils.ShareIntents;
import info.firozansari.android_intent_example.utils.SystemIntents;


public class MainFragment extends Fragment {

    private OnFragmentInteractionListener mListener;

    private TextView textView;

    public RecyclerView ItemRecyclerView;

    public ItemAdapter itemAdapter;

    AlertDialog.Builder builder;

    public MainFragment() {
    }

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View mView =  inflater.inflate(R.layout.fragment_main, container, false);

        textView = (TextView) mView.findViewById(R.id.explain_tv);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builder = new AlertDialog.Builder(getActivity()).setMessage(getIntentExplanationText());
                builder.show();
            }
        });
        ItemRecyclerView = (RecyclerView) mView.findViewById(R.id.item_recycler);
        LinearLayoutManager recyclerLayoutManager = new LinearLayoutManager(getActivity());
        ItemRecyclerView.setLayoutManager(recyclerLayoutManager);
        itemAdapter = new ItemAdapter(getActivity(), mListener);
        ItemRecyclerView.setAdapter(itemAdapter);

        return mView;

    }

    @Override
    public void onResume() {
        super.onResume();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                itemAdapter.swapList(getDemoItems());
            }
        }, 500);
    }


    @NonNull
    private List<DemoItem> getDemoItems() {
        final Resources res = getResources();
        final List<DemoItem> demoItemList = new ArrayList<DemoItem>();

        // PhoneIntents
        demoItemList.add(new DemoItem(res.getString(R.string.dialer), PhoneIntents.newDialNumberIntent(null)));
        demoItemList.add(new DemoItem(res.getString(R.string.call_number), PhoneIntents.newCallNumberIntent("07957941679")));
        demoItemList.add(new DemoItem(res.getString(R.string.dial_number), PhoneIntents.newDialNumberIntent("+07957941679")));
        demoItemList.add(new DemoItem(res.getString(R.string.send_sms_to), PhoneIntents.newSmsIntent(getActivity(), "this is a test SMS", "07957941679")));
        demoItemList.add(new DemoItem(res.getString(R.string.send_sms), PhoneIntents.newSmsIntent(getActivity(), "this is a test SMS")));
        demoItemList.add(new DemoItem(res.getString(R.string.pick_contact), PhoneIntents.newPickContactIntent()));
        // demoItemList.add(new DemoItem(res.getString(R.string.edit_contact), PhoneIntents.editContactIntent()));
        demoItemList.add(new DemoItem(res.getString(R.string.pick_contact_with_phone), PhoneIntents.newPickContactWithPhoneIntent()));

        // GeoIntents
        // demoItemList.add(new DemoItem(res.getString(R.string.map_of), GeoIntents.newMapsIntent("10 Downing Street, London", "Prime Minister's Residence")));
        demoItemList.add(new DemoItem(res.getString(R.string.map_at), GeoIntents.newMapsIntent(53.600910f, -2.547780f, "Horwich, Bolton")));
        demoItemList.add(new DemoItem(res.getString(R.string.navigate_to_address), GeoIntents.newNavigationIntent("Manchester Piccadilly Garden")));
        demoItemList.add(new DemoItem(res.getString(R.string.navigate_to_location), GeoIntents.newNavigationIntent(53.600910f, -2.547780f)));
        demoItemList.add(new DemoItem(res.getString(R.string.streetview_at_location), GeoIntents.newStreetViewIntent(53.600910f, -2.547780f)));

        // MediaIntents
        demoItemList.add(new DemoItem(res.getString(R.string.play_image), MediaIntents.newPlayImageIntent("http://upload.wikimedia.org/wikipedia/commons/thumb/a/a9/Biarritz-Plage.JPG/1920px-Biarritz-Plage.JPG")));
        demoItemList.add(new DemoItem(res.getString(R.string.play_audio), MediaIntents.newPlayAudioIntent("http://www.stephaniequinn.com/Music/Allegro%20from%20Duet%20in%20C%20Major.mp3")));
        demoItemList.add(new DemoItem(res.getString(R.string.play_video), MediaIntents.newPlayVideoIntent("http://mirror.bigbuckbunny.de/peach/bigbuckbunny_movies/big_buck_bunny_480p_h264.mov")));
        // demoItemList.add(new DemoItem(res.getString(R.string.play_video_youtube), MediaIntents.newPlayYouTubeVideoIntent("b_yiWIXBI7o")));
        demoItemList.add(new DemoItem(res.getString(R.string.browse_web), MediaIntents.newOpenWebBrowserIntent("http://firozansari.info")));
        demoItemList.add(new DemoItem(res.getString(R.string.take_pic), MediaIntents.newTakePictureIntent()));
        demoItemList.add(new DemoItem(res.getString(R.string.select_pic), MediaIntents.newSelectPictureIntent()));

        // EmailIntents
        demoItemList.add(new DemoItem(res.getString(R.string.email_to), EmailIntents.newEmailIntent("test@example.com", "My subject", "My content")));

        // ShareIntents
        demoItemList.add(new DemoItem(res.getString(R.string.share), ShareIntents.newShareTextIntent("My subject", "My message", getString(R.string.share_dialog_title))));

        // SystemIntents
        demoItemList.add(new DemoItem(res.getString(R.string.app_store), SystemIntents.newGooglePlayIntent(getActivity(), "uk.co.topcashback.topcashback")));
        return demoItemList;
    }

    @NonNull
    private String getIntentExplanationText() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("An Intent is a messaging object you can use to request an action from another app component.");
        stringBuilder.append(Constant.LINE_SEPARATOR);
        stringBuilder.append("It's mainly used to start activity or service or deliver a broadcast.");
        stringBuilder.append(Constant.LINE_SEPARATOR);
        stringBuilder.append("Explicit intents specify which app component will satisfy the intent but implicit intents do not name a specific component");
        stringBuilder.append(Constant.LINE_SEPARATOR);
        stringBuilder.append("The primary information contained in an Intent is the following:");
        stringBuilder.append(Constant.LINE_SEPARATOR);
        stringBuilder.append("1. Component name");
        stringBuilder.append(Constant.LINE_SEPARATOR);
        stringBuilder.append("2. Action (such as view or send");
        stringBuilder.append(Constant.LINE_SEPARATOR);
        stringBuilder.append("3. Data (URI or MIME type");
        stringBuilder.append(Constant.LINE_SEPARATOR);
        stringBuilder.append("4. Category (such as browsable or launcher");
        stringBuilder.append(Constant.LINE_SEPARATOR);
        stringBuilder.append("Above listed properties needed for most Intent.");
        stringBuilder.append(Constant.LINE_SEPARATOR);
        return stringBuilder.toString();
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mListener = (OnFragmentInteractionListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     */
    public interface OnFragmentInteractionListener {
        public void onFragmentInteraction(Intent intent);
    }
}
