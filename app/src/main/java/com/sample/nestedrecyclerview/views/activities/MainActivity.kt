package com.sample.nestedrecyclerview.views.activities

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.sample.nestedrecyclerview.R
import com.sample.nestedrecyclerview.data.AirlineInfo
import com.sample.nestedrecyclerview.data.AirlineInfos
import com.sample.nestedrecyclerview.views.adapters.MainListAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val TAG = MainActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.title = "InternTakeHome" // set the top title
        try {
            rv_main.layoutManager = LinearLayoutManager(this)
            val adapter = MainListAdapter(getData(AirlineInfos.unsortedAirlines))
            rv_main.adapter = adapter
        } catch (e: Exception) {
            Log.d(TAG, e.printStackTrace().toString())
        }


    }

    private fun getData(unsortedAirlines: List<AirlineInfo>): HashMap<String, List<AirlineInfo>> {

        var sectionedList: HashMap<String, List<AirlineInfo>> = HashMap()
        for (airlineinfo in unsortedAirlines) {
            val initial = airlineinfo.name.substring(0, 1)
            val temp = sectionedList[initial]
            if (temp == null) {
                sectionedList[initial] = unsortedAirlines.filter { it.name.startsWith(initial) }
            }
        }
        return sectionedList.toList().sortedBy { (k, v) -> k }
            .toMap() as HashMap<String, List<AirlineInfo>>
    }


}
