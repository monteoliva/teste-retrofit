package br.monteoliva.testeretrofit.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import androidx.core.content.ContextCompat

import androidx.recyclerview.widget.RecyclerView

import br.monteoliva.testeretrofit.R
import br.monteoliva.testeretrofit.repository.core.extensions.balanceFormat
import br.monteoliva.testeretrofit.repository.core.extensions.dateFormat
import br.monteoliva.testeretrofit.repository.core.extensions.gone
import br.monteoliva.testeretrofit.repository.core.extensions.visible
import br.monteoliva.testeretrofit.repository.model.contracts.statements.Item
import br.monteoliva.testeretrofit.ui.feature.main.MainActivity

class ItemAdapter(private val activity: MainActivity) : RecyclerView.Adapter<ItemAdapter.ViewHolder>()  {
    private var list: MutableList<Item> = emptyList<Item>().toMutableList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.statements_item, parent, false)
        return ViewHolder(this, view)
    }

    fun updateList(items: MutableList<Item>?) {
        items?.let {
            if (it.isNotEmpty()) {
                list = it
                notifyDataSetChanged()
                activity.setLoading(false)
            }
        }
    }

    override fun getItemCount(): Int = list.size
    override fun getItemId(position: Int): Long = 0

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item: Item = getItem(position)
        var name = "-----"

        val backColor = ContextCompat.getColor(activity.baseContext, R.color.white_100)
        val origColor = ContextCompat.getColor(activity.baseContext, R.color.white)

        holder.apply {
            statementTitle.text   = item.description
            statementBalance.text = item.amount?.balanceFormat()
            statementDate.text    = item.createdAt?.dateFormat()

            item.to?.let { name = it }
            statementName.text = name

            if (item.tType.toString().contains("PIX")) {
                itemCard.setBackgroundColor(backColor)
                statementPix.visible()
                statementDate.setPadding(0, 0, 0, 0)
            }
            else {
                itemCard.setBackgroundColor(origColor)
                statementPix.gone()
                statementDate.setPadding(5, 5, 5, 5)
            }
        }
    }

    private fun getItem(position: Int): Item = list[position]

    fun detail(id: String) { activity.detail(id) }

    class ViewHolder(itemAdapter: ItemAdapter, itemView: View): RecyclerView.ViewHolder(itemView) {
        val itemCard: FrameLayout      = itemView.findViewById(R.id.itemCard)
        val statementTitle: TextView   = itemView.findViewById(R.id.statementTitle)
        val statementName: TextView    = itemView.findViewById(R.id.statementName)
        val statementBalance: TextView = itemView.findViewById(R.id.statementBalance)
        val statementPix: TextView     = itemView.findViewById(R.id.statementPix)
        val statementDate: TextView    = itemView.findViewById(R.id.statementDate)

        init {
            itemCard.setOnClickListener {
                val item: Item = itemAdapter.getItem(adapterPosition)
                item.id?.let { id -> itemAdapter.detail(id) }
            }
        }
    }
}