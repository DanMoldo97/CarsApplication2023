package com.example.carsapplication.ui.manufacturers

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.inputmethod.EditorInfo
import android.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.carsapplication.R

class ManufacturersActivity : AppCompatActivity() {

    lateinit var recyclerView : RecyclerView;
    lateinit var myAdapter : MyAdapter;
    lateinit var s1 : Array<String>;
    lateinit var s2 : Array<String>;
    var images : IntArray = intArrayOf(R.drawable.fiat_logo, R.drawable.alfa_romeo_logo, R.drawable.mini_cooper_logo, R.drawable.bmw_logo, R.drawable.mercedes_logo);

    private var manufacturerList : MutableList<ManufacturerExample> = ArrayList();

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_manufacturers)

        s1 = resources.getStringArray(R.array.car_manufacturers)
        s2 = resources.getStringArray(R.array.manufacturer_country)
        recyclerView = findViewById(R.id.recyclerView)
        populateManufacturerList()
        myAdapter = MyAdapter(this, manufacturerList)
        recyclerView.adapter = myAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        var inflater = menuInflater
        inflater.inflate(R.menu.search_manufacturer_menu, menu)

        var searchItem = menu?.findItem(R.id.action_search)
        var searchView = searchItem?.actionView as SearchView
        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{

            override fun onQueryTextSubmit(p0: String?): Boolean {
                return false
            }

            override  fun onQueryTextChange(p0: String?): Boolean {
                myAdapter.filter.filter(p0)
                return false
            }
        })
        return true;
    }

    fun populateManufacturerList() {
        var position = 0;
        while (position < s1.size) {
            var manufacturer = ManufacturerExample()
            manufacturer.title = s1[position]
            manufacturer.country = s2[position]
            manufacturer.imageLogo = images[position]
            manufacturerList.add(position, manufacturer)
            position++
        }
    }
}
