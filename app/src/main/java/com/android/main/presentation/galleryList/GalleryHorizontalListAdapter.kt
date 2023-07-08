package com.android.main.presentation.galleryList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.main.data.image.GalleryImageDto
import com.android.main.databinding.GalleryHorizontalCardFragmentBinding
import com.bumptech.glide.Glide

class GalleryHorizontalListAdapter: RecyclerView.Adapter<GalleryHorizontalListAdapter.GalleryHorizontalListHolder>() {
    private var data: List<GalleryImageDto> = listOf()
    class GalleryHorizontalListHolder(val binding: GalleryHorizontalCardFragmentBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GalleryHorizontalListHolder {
        return GalleryHorizontalListHolder(GalleryHorizontalCardFragmentBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: GalleryHorizontalListHolder, position: Int) {
        Glide.with(holder.binding.root)
            .load(data[position].previewUrl)
            .into(holder.binding.preview);
    }

    fun setData(newList: List<GalleryImageDto>): GalleryHorizontalListAdapter {
        data = newList

        notifyDataSetChanged()

        return this
    }
}