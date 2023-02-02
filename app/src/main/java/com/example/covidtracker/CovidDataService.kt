package com.example.covidtracker

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CovidDataService {
    @GET("county/{state}.json")
    fun getCountyDataByState(
        @Path("state") state: String,
        @Query("apiKey") apiKey: String
    ) : Call<List<CountyData>>

}