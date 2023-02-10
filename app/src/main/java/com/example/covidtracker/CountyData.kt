package com.example.covidtracker

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CountyData(
    val state: String?,
    val county: String?,
    val actuals: Actuals,
    val metrics: Metrics,
    val cdcTransmissionLevel: Int,
    val lastUpdatedDate: String?
):Parcelable{
    @Parcelize
    data class Actuals(
        val cases: Int?,
        val newCases: Int?
    ):Parcelable
    @Parcelize
    data class Metrics(
        val testPositivityRatio: Double?,
        val weeklyNewCasesPer100k: Double?
    ):Parcelable
}
