package com.example.disneyapp.ui.characters

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
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
            Toast.makeText(context, "${it.data?.get(1)?.name} clicked!", Toast.LENGTH_SHORT).show()
            viewModel.disneyObject = it
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
                    Toast.makeText(context, "Loading. . .!", Toast.LENGTH_SHORT).show()
                }
                is ResponseType.SUCCESS -> {
                    initViews(it.response)
                }
                is ResponseType.ERROR -> {
                    Toast.makeText(context, "Loading. . .!", Toast.LENGTH_SHORT).show()
                }
                else -> {
                    Toast.makeText(context, "Loading. . .!", Toast.LENGTH_SHORT).show()
                }
            }
        }

        viewModel.getCharacter()

        return binding.root
    }

    private fun initViews(data: DisneyResponse?){
        data?.let {
            mAdapter.updateDisneyAdapter(it)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}