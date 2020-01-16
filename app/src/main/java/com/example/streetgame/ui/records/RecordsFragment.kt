package com.example.streetgame.ui.records

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.streetgame.R
import com.example.streetgame.game.Record
import com.example.streetgame.network.createStreetGameService
import com.example.streetgame.ui.utils.LinearLayoutManagerWithSmoothScroller
import kotlinx.android.synthetic.main.fr_records.*
import kotlinx.coroutines.*

class RecordsFragment : Fragment() {

    var job : Job? = null

    private var recordId: Int = -1

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fr_records, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.intent?.extras?.let{
            recordId =  RecordsActivity.getRecordId(it)
            initAdapter(recordId)
        }
    }

    private fun initAdapter(recordId: Int){
        records_rv.layoutManager = LinearLayoutManagerWithSmoothScroller(activity!!)
        records_rv.adapter = RecordsAdapter(recordId)
    }

    override fun onStart() {
        super.onStart()

        val service = createStreetGameService()
        job = CoroutineScope(Dispatchers.IO).launch {
            val res = service.getResults("street", 1)
            if(res.isSuccessful){
                var n = 1
                val records = res.body()?.map {
                    it.order = n
                    n += 1
                    it
                }
                withContext(Dispatchers.Main){
                    showRecords(records ?: listOf())
                }
            }
        }
    }

    private fun showRecords(records: List<Record>){
        (records_rv.adapter as RecordsAdapter)
            .add(records , true)
        records_rv.smoothScrollToPosition(records.indexOfFirst{
                record -> record.id == recordId})

    }

    override fun onDestroy() {
        job?.cancel()
        super.onDestroy()
    }

}