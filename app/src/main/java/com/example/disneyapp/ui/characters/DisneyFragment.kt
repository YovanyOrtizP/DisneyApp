package com.example.disneyapp.ui.characters

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.disneyapp.R
import com.example.disneyapp.data.model.DisneyData
import com.example.disneyapp.data.model.DisneyResponse
import com.example.disneyapp.databinding.FragmentDisneyBinding
import com.example.disneyapp.util.ResponseType
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DisneyFragment : Fragment() {
    private var _binding: FragmentDisneyBinding? = null
    private val binding get() = _binding!!

    private val viewModel: DisneyViewModel by activityViewModels()

    private val mAdapter by lazy {
        DisneyAdapter {
            //Toast.makeText(context, "${it.name} clicked!", Toast.LENGTH_SHORT).show()
            viewModel.disneyObject = it
            // todo move to the details screen
            findNavController().navigate(R.id.action_charactersFragment_to_detailsFragment)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDisneyBinding.inflate(inflater, container, false)

        binding.rvDisney.apply {
            layoutManager = GridLayoutManager(context, 3)
            adapter = mAdapter
        }

        viewModel.result.observe(viewLifecycleOwner) {
            when (it) {
                is ResponseType.LOADING -> {
                    //Toast.makeText(context, "Loading. . .!", Toast.LENGTH_SHORT).show()
                }
                is ResponseType.SUCCESS<List<DisneyData>> -> {
                    initViews(it.response)
                }
                is ResponseType.ERROR -> {
                    //Toast.makeText(context, "Loading. . .!", Toast.LENGTH_SHORT).show()
                }
                else -> {
                    //Toast.makeText(context, "Loading. . .!", Toast.LENGTH_SHORT).show()
                }
            }
        }

        viewModel.flowCharacters()

        return binding.root
    }

    private fun initViews(data: List<DisneyData>?){
        data?.let {
            mAdapter.updateDisneyAdapter(it)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}