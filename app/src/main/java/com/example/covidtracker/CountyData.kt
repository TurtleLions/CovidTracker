package com.example.covidtracker

data class CountyData(
    val state: String?,
    val county: String?,
    val actuals: Actuals,
    val metrics: Metrics,
    val cdcTransmissionLevel: Int,
    val lastUpdatedDate: String?
){
    data class Actuals(
        val cases: Int?,
        val newCases: Int?
    )
    data class Metrics(
        val testPositivityRatio: Double?,
        val weeklyNewCasesPer100k: Double?
    )
}
