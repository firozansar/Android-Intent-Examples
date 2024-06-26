package info.firozansari.example.utils

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import info.firozansari.example.R
import info.firozansari.example.ui.MainFragment.OnFragmentInteractionListener

class ItemAdapter(
    private val context: Context,
    private val listener: OnFragmentInteractionListener
) : RecyclerView.Adapter<ItemAdapter.ViewHolder>() {
    private val itemList: MutableList<DemoItem> = ArrayList()


    override fun getItemCount(): Int {
        return if (itemList.size > 0) {
            itemList.size
        } else 0
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(
                context
            ).inflate(R.layout.list_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (itemList.size > 0) {
            val demoItem = itemList[position]
            holder.issueTitle.text = demoItem.description
        }
    }

    inner class ViewHolder(v: View) : RecyclerView.ViewHolder(v), View.OnClickListener {
        var issueTitle: TextView = v.findViewById<View>(R.id.item_tv) as TextView

        init {
            issueTitle.setOnClickListener(this)
        }

        override fun onClick(v: View) {
            val item = getItem(adapterPosition)
            listener.onFragmentInteraction(item?.intent)
        }
    }

    private fun getItem(adapterPosition: Int): DemoItem? {
        if (adapterPosition >= 0) {
            return itemList[adapterPosition]
        }
        return null
    }


    fun swapList(demoItemList: List<DemoItem>?) {
        if (demoItemList != null) {
            itemList.clear()
            itemList.addAll(demoItemList)
            notifyDataSetChanged()
        }
    }
}
