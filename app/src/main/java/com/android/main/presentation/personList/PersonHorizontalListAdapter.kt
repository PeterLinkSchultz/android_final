package com.android.main.presentation.personList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.main.data.staff.StaffDto
import com.android.main.databinding.PersonCardFragmentBinding
import com.bumptech.glide.Glide

class PersonHorizontalListAdapter: RecyclerView.Adapter<PersonHorizontalListAdapter.PersonCardViewHolder>() {
    private var data: List<StaffDto> = listOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonCardViewHolder {
        return PersonCardViewHolder(PersonCardFragmentBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: PersonCardViewHolder, position: Int) {
        val item = data[position]

        holder.binding.personCardName.text = item.nameRu ?: item.nameEn
        holder.binding.personCardRole.text = item.description ?: item.professionText
        Glide.with(holder.binding.root)
            .load(item.posterUrl)
            .into(holder.binding.personCardPoster);
    }

    fun setData(newData: List<StaffDto>) {
        data = newData

        notifyDataSetChanged()
    }

    class PersonCardViewHolder(val binding: PersonCardFragmentBinding): RecyclerView.ViewHolder(binding.root)
}