package com.example.streetgame.ui.records

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.streetgame.game.Record

class RecordsAdapter (val recordId: Int) : RecyclerView.Adapter<RecordViewHolder>() {

    private var records = ArrayList<Record>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecordViewHolder {
        return RecordViewHolder.create(parent)
    }

    override fun getItemCount(): Int {
        return records.size
    }

    override fun onBindViewHolder(holder: RecordViewHolder, position: Int) {
        val record = records[position]
        val isMyRecord = (record.id != null && record.id == recordId)
        holder.bind(record, isMyRecord)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }


    fun add(records: List<Record>, refresh: Boolean) {
        if (refresh) {
            this.records = ArrayList(records.size)
        }
        this.records.addAll(records)
        notifyDataSetChanged()
    }
}




