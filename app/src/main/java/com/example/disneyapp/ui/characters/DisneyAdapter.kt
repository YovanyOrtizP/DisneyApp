package com.example.disneyapp.ui.characters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.disneyapp.data.model.DisneyData
import com.example.disneyapp.databinding.ItemDisneyBinding
import com.squareup.picasso.Picasso

class DisneyAdapter(
    private val disneyList: MutableList<DisneyData> = mutableListOf(),
    // high order function to handle the click on the item in recycler view
    private val clickListener: (DisneyData) -> Unit
) : RecyclerView.Adapter<DisneyAdapter.ViewHolder>() {

    fun updateDisneyAdapter(newDisney: List<DisneyData>) {
        disneyList.clear()
        disneyList.addAll(newDisney)
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val view: ItemDisneyBinding) :
        RecyclerView.ViewHolder(view.root) {
        fun setup(disneyResponse: DisneyData) {
                Picasso.get().load(disneyResponse.imageUrl).resize(600,500).into(view.disneyCharacter)
                view.characterName.text = disneyResponse.name

            // setting a click to each card view in the recycler view
            itemView.setOnClickListener { clickListener(disneyResponse) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        ItemDisneyBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: DisneyAdapter.ViewHolder, position: Int) {
        holder.setup(disneyList[position])
    }

    override fun getItemCount(): Int = disneyList.size
}
