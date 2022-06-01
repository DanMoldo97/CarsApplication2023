package com.example.carsapplication.ui.manufacturers

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.example.carsapplication.R

class DetailsActivity : AppCompatActivity() {

    private lateinit var mainImageView : ImageView
    private lateinit var title : TextView
    private lateinit var country : TextView
    private lateinit var descriptionText : TextView

    private lateinit var titleString : String
    private lateinit var countryString : String
    private var imageLogo : Int = -1
    private lateinit var descriptionMap : Map<String, String>

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        mainImageView = findViewById(R.id.imageView)
        title = findViewById(R.id.title)
        country = findViewById(R.id.country)
        descriptionText = findViewById(R.id.description)
        initDescriptionMap()
        getData()
        setData()
    }

    private fun initDescriptionMap() {
        descriptionMap = mutableMapOf("Fiat" to resources.getText(R.string.fiat_details_desc).toString(),
            "Alfa Romeo" to resources.getText(R.string.alfa_romeo_details_desc).toString(),
            "Mercedes" to resources.getText(R.string.mercedes_details_desc).toString(),
            "BMW" to resources.getText(R.string.bmw_details_desc).toString(),
            "Audi" to resources.getText(R.string.audi_details_desc).toString(),
            "Mini Cooper" to resources.getText(R.string.mini_cooper_details_desc).toString())

    }

    private fun getData() {
        if (intent.hasExtra("data1") && intent.hasExtra("data2") && intent.hasExtra("myImage")) {
            titleString = intent.getStringExtra("data1").orEmpty()
            countryString = intent.getStringExtra("data2").orEmpty()
            imageLogo = intent.getIntExtra("myImage", 1)
        } else {
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show()
        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun setData() {
        title.text = titleString
        country.text = countryString
        mainImageView.setImageResource(imageLogo)
        descriptionText.text = descriptionMap.getOrDefault(titleString, "")
    }
}