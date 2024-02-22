// BindingAdapters.kt
package com.itverizon.gridlayoutmediastoreimages

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

@BindingAdapter("app:spanCount")
fun setSpanCount(recyclerView: RecyclerView, spanCount: Int) {
    recyclerView.layoutManager = GridLayoutManager(recyclerView.context, spanCount)
}
