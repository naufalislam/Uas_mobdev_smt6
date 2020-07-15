package com.pale.uas.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.pale.uas.R

import com.pale.uas.model.DataItem
import kotlinx.android.synthetic.main.item_data.view.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import java.util.*

class DataAdapter(val data: List<DataItem>?, private val click: onClickItem) :
    RecyclerView.Adapter<DataAdapter.MyHolder>(), Filterable {
    override fun onCreateViewHolder(
        parent: ViewGroup, viewType:
        Int
    ): MyHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_data, parent, false)
        return MyHolder(view)
    }

    private var dataFilterList: List<DataItem>? = data

    override fun getItemCount() = dataFilterList?.size ?: 0

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                if (charSearch.isEmpty()) {
                    dataFilterList = data
                } else {
                    var resultList: List<DataItem> = emptyList()
                    if (data != null) {
                        for (dt in data) {
                            if (dt.staffName?.toLowerCase(Locale.ROOT)?.contains(
                                    charSearch.toLowerCase(
                                        Locale.ROOT
                                    )
                                )!!
                            ) {
                                resultList += dt
                            }
                        }
                        dataFilterList = resultList
                    }
                }
                val filterResults = FilterResults()
                filterResults.values = dataFilterList
                return filterResults
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                dataFilterList = results?.values as List<DataItem>
                notifyDataSetChanged()
            }
        }
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        holder.onBind(dataFilterList?.get(position))
        holder.itemView.onClick {
            click.clicked(dataFilterList?.get(position))
        }
        holder.itemView.btnHapus.setOnClickListener {
            click.delete(dataFilterList?.get(position))
        }
    }

    class MyHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        fun onBind(get: DataItem?) {
            itemView.tvName.text = get?.staffName
            itemView.tvPhone.text = get?.staffHp
            itemView.tvAddress.text = get?.staffAlamat
        }
    }

    interface onClickItem {
        fun clicked(item: DataItem?)
        fun delete(item: DataItem?)
    }
}
