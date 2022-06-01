package com.example.carsapplication.ui

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.example.carsapplication.R
import android.provider.MediaStore

import android.graphics.Bitmap
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.inputmethod.EditorInfo
import android.widget.SearchView
import android.widget.Toast
import com.example.carsapplication.databinding.ActivityCarCaptureBinding
import com.example.carsapplication.databinding.ActivityViewShareImageBinding

class ViewShareImage : AppCompatActivity() {

    private lateinit var viewBinding: ActivityViewShareImageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityViewShareImageBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        val uri: Uri? = intent.data
        val bitmap = MediaStore.Images.Media.getBitmap(this.contentResolver, uri)
        viewBinding.imageViewShare.setImageBitmap(bitmap)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        var inflater = menuInflater
        inflater.inflate(R.menu.share_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.share -> shareImg()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun shareImg() {
        Toast.makeText(this, "Am ajuns aici!", Toast.LENGTH_SHORT).show()
    }
}