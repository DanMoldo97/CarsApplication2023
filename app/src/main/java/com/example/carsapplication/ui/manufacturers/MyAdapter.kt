package com.example.carsapplication.ui.manufacturers

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.carsapplication.R
import java.util.*

class MyAdapter(): RecyclerView.Adapter<MyAdapter.MyViewHolder>(), Filterable {

    var context: Context? = null
    var manufacturersList: MutableList<ManufacturerExample>? = null
    var manufacturerExampleListFull: List<ManufacturerExample>? = null

    constructor(context: Context?, manufacturersList: MutableList<ManufacturerExample>?) : this() {
        this.context = context
        this.manufacturersList = manufacturersList
        manufacturerExampleListFull = ArrayList(manufacturersList)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(context)
        val view: View = inflater.inflate(R.layout.my_row_layout, parent, false)
        return MyViewHolder(view)
    }

     override fun onBindViewHolder(holder: MyViewHolder, @SuppressLint("RecyclerView") position: Int) {
        holder.manufacturerName.text = manufacturersList!![position].title
        holder.country.text = manufacturersList!![position].country
        holder.manufacturerLogo.setImageResource(manufacturersList!![position].imageLogo)
        holder.mainLayout.setOnClickListener {
            val intent = Intent(context, DetailsActivity::class.java)
            intent.putExtra("data1", manufacturersList!![position].title)
            intent.putExtra("data2", manufacturersList!![position].country)
            intent.putExtra("myImage", manufacturersList!![position].imageLogo)
            context!!.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return manufacturersList!!.size
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var manufacturerName: TextView
        var country: TextView
        var manufacturerLogo: ImageView
        var mainLayout: ConstraintLayout

        init {
            manufacturerName = itemView.findViewById(R.id.manufacturerText)
            country = itemView.findViewById(R.id.countryText)
            manufacturerLogo = itemView.findViewById(R.id.myLogoImage)
            mainLayout = itemView.findViewById(R.id.mainLayout)
        }
    }

    override fun getFilter(): Filter {
        return exampleFilter
    }

    private val exampleFilter: Filter = object : Filter() {
        @RequiresApi(api = Build.VERSION_CODES.N)
        override fun performFiltering(constraint: CharSequence): FilterResults {
            val filteredList: MutableList<ManufacturerExample> = ArrayList()
            if (constraint.isNotEmpty()) {
                val filterPattern = constraint.toString().lowercase(Locale.getDefault()).trim { it <= ' ' }
                manufacturerExampleListFull!!.stream().filter { el -> el.title.lowercase(Locale.getDefault()).startsWith(filterPattern) }
                    .forEach (filteredList::add)
            } else {
                filteredList.addAll(manufacturerExampleListFull!!)
            }
            val filterResults = FilterResults()
            filterResults.values = filteredList
            return filterResults
        }

        @SuppressLint("NotifyDataSetChanged")
        override fun publishResults(charSequence: CharSequence, filterResults: FilterResults) {
            manufacturersList?.clear()
            manufacturersList?.addAll(filterResults?.values as Collection<ManufacturerExample>)
            notifyDataSetChanged()
        }
    }
}