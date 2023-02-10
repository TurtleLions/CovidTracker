package com.example.covidtracker

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.covidtracker.databinding.ActivityCountyDetailBinding

class CountyDetailActivity :AppCompatActivity() {
    companion object{
        val EXTRA_COUNTY = "county"
    }
    private lateinit var binding: ActivityCountyDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCountyDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val county = intent.getParcelableExtra<CountyData>(CountyAdapter.EXTRA_COUNTY)
        binding.detailCounty.setText(county!!.county)
        binding.detailNewcases.setText(county.metrics.weeklyNewCasesPer100k.toString()+" thousand cases")

    }
}