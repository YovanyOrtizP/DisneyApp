package com.example.disneyapp.ui.characters.paging

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.disneyapp.data.model.DisneyData
import com.example.disneyapp.databinding.ItemDisneyBinding
import com.squareup.picasso.Picasso

class PagingAdapter(
    private val disneyList: MutableList<DisneyData> = mutableListOf(),
) : PagingDataAdapter<DisneyData, PagingAdapter.ViewHolder>(differCallback){

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        if (item!= null){
            holder.setup(disneyList[position])
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        TODO("Not yet implemented")
    }

    inner class ViewHolder(private val view: ItemDisneyBinding) :
        RecyclerView.ViewHolder(view.root) {
        fun setup(disneyResponse: DisneyData) {
            Picasso.get().load(disneyResponse.imageUrl).resize(600,500).into(view.disneyCharacter)
            view.characterName.text = disneyResponse.name
        }
    }

    companion object {
        val differCallback = object : DiffUtil.ItemCallback<DisneyData>() {
            override fun areItemsTheSame(oldItem: DisneyData, newItem: DisneyData): Boolean {
                return oldItem.id == oldItem.id
            }

            override fun areContentsTheSame(oldItem: DisneyData, newItem: DisneyData): Boolean {
                return oldItem == newItem
            }
        }
    }

}