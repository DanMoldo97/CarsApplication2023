package com.example.carsapplication.ui

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.example.carsapplication.R
import android.provider.MediaStore

import android.graphics.Bitmap

class ViewShareImage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_share_image)
        val uri: Uri? = intent.data
        val bitmap = MediaStore.Images.Media.getBitmap(this.contentResolver, uri)
        var imgView : ImageView = findViewById(R.id.imageViewShare)
        imgView.setImageBitmap(bitmap)
    }
}