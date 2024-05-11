package info.firozansari.android_intent_example.utils

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import info.firozansari.android_intent_example.DemoItem
import info.firozansari.android_intent_example.MainFragment.OnFragmentInteractionListener
import info.firozansari.android_intent_example.R

class ItemAdapter(
    private val context: Context,
    private val listener: OnFragmentInteractionListener
) : RecyclerView.Adapter<ItemAdapter.ViewHolder>() {
    private val itemList: MutableList<DemoItem>? = ArrayList()


    override fun getItemCount(): Int {
        if (itemList != null) {
            return if (itemList.size == 0) {
                0
            } else {
                itemList.size
            }
        }
        return 0
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(
                context
            ).inflate(R.layout.list_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (itemList!!.size > 0) {
            val demoItem = itemList[position]

            if (demoItem != null) {
                holder.issueTitle.text = demoItem.description
            }
        }
    }


    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    inner class ViewHolder(v: View) : RecyclerView.ViewHolder(v), View.OnClickListener {
        var issueTitle: TextView = v.findViewById<View>(R.id.item_tv) as TextView

        init {
            issueTitle.setOnClickListener(this)
        }

        override fun onClick(v: View) {
            val item = getItem(adapterPosition)
            listener.onFragmentInteraction(item!!.intent)
        }
    }

    private fun getItem(adapterPosition: Int): DemoItem? {
        if (adapterPosition >= 0) {
            return itemList!![adapterPosition]
        }

        return null
    }


    fun swapList(demoItemList: List<DemoItem>?) {
        if (demoItemList != null) {
            itemList!!.clear()
            itemList.addAll(demoItemList)
            notifyDataSetChanged()
        }
    }
}
