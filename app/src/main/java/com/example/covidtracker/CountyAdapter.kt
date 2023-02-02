package com.example.covidtracker
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.covidtracker.CountyData
import com.example.covidtracker.CountyListActivity
import com.example.covidtracker.R

class CountyAdapter(var dataSet: List<CountyData>) : RecyclerView.Adapter<CountyAdapter.ViewHolder>() {
    companion object{
        val TAG = "hi"
        val EXTRA_COUNTY = "county"
    }

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder)
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textViewCounty: TextView
        val textViewLastUpdated: TextView
        val textViewWeeklyCases: TextView
        val layout: ConstraintLayout

        init {
            textViewCounty = view.findViewById(R.id.recycler_county)
            textViewLastUpdated = view.findViewById(R.id.recycler_last_updated)
            textViewWeeklyCases = view.findViewById(R.id.recycler_weekly)
            layout = view.findViewById(R.id.ConstraintLayout)
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_county_data, viewGroup, false)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.textViewCounty.text = dataSet[position].county
        viewHolder.textViewLastUpdated.text = dataSet[position].lastUpdatedDate
        viewHolder.textViewWeeklyCases.text = dataSet[position].metrics.weeklyNewCasesPer100k.toString()
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size

}
