package com.itverizon.gridlayoutmediastoreimages

import android.content.ContentUris
import android.os.Bundle
import android.provider.MediaStore
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.itverizon.gridlayoutmediastoreimages.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        val recyclerView = binding.recyclerView
        recyclerView.layoutManager = GridLayoutManager(this, viewModel.spanCount.value ?: 3)
        recyclerView.adapter = ImageAdapter(this, loadImagesFromMediaStore())
    }

    private fun loadImagesFromMediaStore(): List<Image> {
        val images = mutableListOf<Image>()
        val projection = arrayOf(MediaStore.Images.Media._ID)

        contentResolver.query(
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            projection,
            null,
            null,
            null
        )?.use {
            val idColumn = it.getColumnIndexOrThrow(MediaStore.Images.Media._ID)

            while (it.moveToNext()) {
                val id = it.getLong(idColumn)
                val contentUri = ContentUris.withAppendedId(
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                    id
                )
                images.add(Image(id, contentUri))
            }
        }

        return images
    }
}