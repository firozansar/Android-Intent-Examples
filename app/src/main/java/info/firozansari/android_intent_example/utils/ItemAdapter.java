package info.firozansari.android_intent_example.utils;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import info.firozansari.android_intent_example.DemoItem;
import info.firozansari.android_intent_example.MainFragment;
import info.firozansari.android_intent_example.R;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder>{

    private List<DemoItem> itemList = new ArrayList<>();
    private Context context;
    private MainFragment.OnFragmentInteractionListener listener;


    public ItemAdapter(Context context, MainFragment.OnFragmentInteractionListener listener) {
        this.context = context;
        this.listener = listener;
    }


    @Override
    public int getItemCount() {
        if(itemList != null) {
            if (itemList.size() == 0) {
                return 0;
            } else {
                return itemList.size();
            }
        }
        return 0;
    }

    @Override
    public ItemAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ItemAdapter.ViewHolder(LayoutInflater.from(context).inflate(R.layout.list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(ItemAdapter.ViewHolder holder, int position) {
        if (itemList.size() > 0) {

            DemoItem demoItem = itemList.get(position);

            if(demoItem != null) {
                holder.issueTitle.setText(demoItem.getDescription());
            }
        }

    }


    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView issueTitle;

        public ViewHolder(View v) {
            super(v);
            issueTitle = (TextView) v.findViewById(R.id.item_tv);
            issueTitle.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            DemoItem item = getItem(getAdapterPosition());
            listener.onFragmentInteraction(item.getIntent());
        }
    }

    private DemoItem getItem(int adapterPosition) {

        if (adapterPosition >= 0) {
            return itemList.get(adapterPosition);
        }

        return null;
    }


    public void swapList(List<DemoItem> demoItemList) {
        if(demoItemList != null) {
            this.itemList.clear();
            this.itemList.addAll(demoItemList);
            notifyDataSetChanged();
        }

    }

}
