package com.example.disneyapp.ui.characters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.disneyapp.data.model.DisneyResponse
import com.example.disneyapp.databinding.ItemDisneyBinding
import com.squareup.picasso.Picasso

class DisneyAdapter(
    val disneyList: MutableList<DisneyResponse> = mutableListOf(),
    val clickListener: (DisneyResponse) -> Unit
) : RecyclerView.Adapter<DisneyAdapter.ViewHolder>() {

    fun updateDisneyAdapter(newDisney: DisneyResponse) {
        disneyList.clear()
        disneyList.addAll(listOf(newDisney))
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val view: ItemDisneyBinding) :
        RecyclerView.ViewHolder(view.root) {
        fun setup(disneyResponse: DisneyResponse) {
            for (i in 0..disneyList.size) {
                Picasso.get().load(disneyResponse.data?.get(i)?.url).into(view.disneyCharacter)
                view.characterName.text = disneyResponse.data?.get(i)?.name
            }
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