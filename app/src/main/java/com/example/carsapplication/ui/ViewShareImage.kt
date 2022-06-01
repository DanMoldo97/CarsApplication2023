package com.example.carsapplication.ui

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.carsapplication.R
import android.provider.MediaStore

import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.core.content.FileProvider
import com.example.carsapplication.databinding.ActivityViewShareImageBinding
import java.io.File
import java.io.FileOutputStream
import java.lang.Exception
import java.net.URI
import android.content.pm.ResolveInfo

import android.content.pm.PackageManager




class ViewShareImage : AppCompatActivity() {

    private lateinit var viewBinding: ActivityViewShareImageBinding
    private lateinit var imageUri : Uri

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityViewShareImageBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        imageUri = intent.data!!
        viewBinding.imageViewShare.setImageURI(imageUri)
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
        var shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.setType("image/png")
        shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Check out this cool car I have found ")
        val contentUriFromImage = getContentUriFromImage()
        shareIntent.putExtra(Intent.EXTRA_STREAM, contentUriFromImage)
        shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        shareIntent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION)
        val chooser = Intent.createChooser(shareIntent, "Share Via")
        val resInfoList = this.packageManager.queryIntentActivities(chooser, PackageManager.MATCH_DEFAULT_ONLY)
        for (resolveInfo in resInfoList) {
            val packageName = resolveInfo.activityInfo.packageName
            grantUriPermission(packageName, contentUriFromImage,Intent.FLAG_GRANT_WRITE_URI_PERMISSION or Intent.FLAG_GRANT_READ_URI_PERMISSION)
        }
        startActivity(chooser)
    }

    private fun getContentUriFromImage() : Uri {
        var bitmap : Bitmap? = null
        var contentUri : Uri? = null
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                val source = ImageDecoder.createSource(contentResolver, imageUri)
                bitmap = ImageDecoder.decodeBitmap(source)
            } else {
                bitmap = MediaStore.Images.Media.getBitmap(contentResolver, imageUri)
            }
        } catch (e : Exception) {
            Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show()
        }


        var imagesFolder : File = File(cacheDir, "images")
        try {
            imagesFolder.mkdirs()
            val file = File(imagesFolder, "shared_image.png")
            val outputStream = FileOutputStream(file)
            bitmap?.compress(Bitmap.CompressFormat.PNG, 50, outputStream)
            outputStream.flush()
            outputStream.close()
            contentUri  = FileProvider.getUriForFile(this, "com.example.carsapplication.fileprovider", file)
        } catch (e : Exception) {
            Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show()
        }
        return contentUri!!
    }
}