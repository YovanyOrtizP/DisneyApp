package com.example.disneyapp.ui.characters.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.disneyapp.R
import com.example.disneyapp.data.model.DisneyData
import com.example.disneyapp.databinding.FragmentCharactersDetailsBinding
import com.example.disneyapp.ui.characters.DisneyViewModel
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharactersDetails : Fragment() {

    private var _binding: FragmentCharactersDetailsBinding? = null
    private val binding get() = _binding!!

    private val sharedViewModel: DisneyViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentCharactersDetailsBinding.inflate(inflater,container,false)
        initViews(sharedViewModel.disneyObject)

        return binding.root
    }

    private fun initViews(disneyObject: DisneyData?) {
        disneyObject?.let { it ->
            Picasso.get().load(it.imageUrl).resize(1000,900).into(binding.ivCharacter)
            binding.tvCharacterName.text = it.name
            binding.tvFilmography.text = "Films: ${it.films} \n\n Short Films: ${it.shortFilms} \n\n Tv Shows: ${it.tvShows}"
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}