package com.android.main.presentation.gallery

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.android.main.data.image.GalleryImageDto
import com.android.main.databinding.GalleryDetailLargeItemFragmentBinding
import com.android.main.databinding.GalleryDetailSmallItemFragmentBinding
import com.bumptech.glide.Glide

class GalleryDetailListAdapter: PagingDataAdapter<GalleryImageDto, RecyclerView.ViewHolder>(DiffUtilCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        return if (viewType == TYPE_SMALL) {
            SmallViewHolder(GalleryDetailSmallItemFragmentBinding.inflate(inflater))
        } else {
            LargeViewHolder(GalleryDetailLargeItemFragmentBinding.inflate(inflater))
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)

        when (holder) {
            is SmallViewHolder -> {
                Glide.with(holder.binding.root)
                    .load(item?.previewUrl)
                    .into(holder.binding.image)
            }
            is LargeViewHolder -> {
                Glide.with(holder.binding.root)
                    .load(item?.previewUrl)
                    .into(holder.binding.image)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if ((position + 1) % 3 == 0) return TYPE_LARGE else TYPE_SMALL
    }

    companion object {
        private const val TYPE_SMALL: Int = 1
        private const val TYPE_LARGE: Int = 2
    }

    class SmallViewHolder(val binding: GalleryDetailSmallItemFragmentBinding): RecyclerView.ViewHolder(binding.root)
    class LargeViewHolder(val binding: GalleryDetailLargeItemFragmentBinding): RecyclerView.ViewHolder(binding.root)

    class DiffUtilCallback: DiffUtil.ItemCallback<GalleryImageDto>(){
        override fun areItemsTheSame(oldItem: GalleryImageDto, newItem: GalleryImageDto): Boolean {
            return oldItem.imageUrl == newItem.imageUrl
        }

        override fun areContentsTheSame(
            oldItem: GalleryImageDto,
            newItem: GalleryImageDto
        ): Boolean {
            return oldItem == newItem
        }
    }
}