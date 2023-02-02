package com.example.covidtracker

import android.net.wifi.rtt.CivicLocationKeys.STATE
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.covidtracker.databinding.ActivityCountyListBinding
import com.example.covidtracker.CountyAdapter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
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

}