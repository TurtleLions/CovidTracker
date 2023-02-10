package com.example.covidtracker

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.covidtracker.databinding.ActivityCountyListBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class CountyListActivity : AppCompatActivity() {
    companion object{
        const val TAG = "CountyListActivity"
        const val STATE = "CA"
    }
    private lateinit var binding: ActivityCountyListBinding
    private lateinit var adapter: CountyAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCountyListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getCovidDataByCountyApiCall(STATE)
    }

    private fun getCovidDataByCountyApiCall(state: String) {
        val covidDataService = RetrofitHelper.getInstance().create(CovidDataService::class.java)
        val countyDataCall = covidDataService.getCountyDataByState(state,Constants.API_KEY)
        countyDataCall.enqueue(object: Callback<List<CountyData>>{
            override fun onResponse(
                call: Call<List<CountyData>>,
                response: Response<List<CountyData>>
            ) {
                Log.d(TAG, "onResponse: ${response.body()}")
                if(response.body()!=null)
                    adapter = CountyAdapter(response.body()!!)
                else{
                    Log.d(TAG, "response is null")
                }
                binding.recyclerviewCountyList.adapter = adapter
                binding.recyclerviewCountyList.layoutManager = LinearLayoutManager(this@CountyListActivity)
            }

            override fun onFailure(call: Call<List<CountyData>>, t: Throwable) {
                Log.d(TAG, "onFailure: ${t.message}")
            }
        })
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.county_list_menu, menu)

        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle item selection
        return when (item.itemId) {

            R.id.menu_Transmission -> {
                adapter.dataSet = adapter.dataSet.sortedWith(compareByDescending<CountyData> {
                    it.cdcTransmissionLevel
                }.thenBy { it.county })
                adapter.notifyDataSetChanged()
                true
            }
            R.id.menu_countryName -> {
                adapter.dataSet = adapter.dataSet.sortedBy{
                    it.county
                }
                adapter.notifyDataSetChanged()
                true
            }
            R.id.menu_info -> {
                AlertDialog.Builder(this)
                    .setTitle("CovidTracker")
                    .setMessage("Red: High Transmission\nOrange:Substantial Transmission\nYellow: " +
                            "Moderate Transmission\nBlue: Low Transmission\n\nThe number represents " +
                            "the weekly case count per 100k people in the county.")
                    .setNegativeButton(android.R.string.no, null)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}