package me.meenagopal24.googlenow.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import me.meenagopal24.googlenow.R
import me.meenagopal24.googlenow.databinding.RowTextViewsBinding

class TextListAdapter(val click : (TextData , Int) -> Unit ) : RecyclerView.Adapter<TextListAdapter.Holder>(){

    private var list: List<TextData> ? = null

    class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = RowTextViewsBinding.bind(itemView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(
            LayoutInflater.from(parent.context).inflate(R.layout.row_text_views, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return list?.size ?: 0
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: Holder, position: Int) {
        with(holder.binding){
            textViewItem.text = list?.get(position)?.text.orEmpty()
            headingViewItem.text = "VoiceName "+list?.get(position)?.voice?.name
            root.setOnClickListener {
                list?.get(position)?.let { it1 -> click(it1, position) }
            }
        }
    }

    fun setData(list: List<TextData>){
        this.list = list
        notifyDataSetChanged()
    }
}