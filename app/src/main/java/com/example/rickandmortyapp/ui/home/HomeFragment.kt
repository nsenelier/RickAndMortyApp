package com.example.rickandmortyapp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rickandmortyapp.data.model.CharacterModel
import com.example.rickandmortyapp.data.model.EpisodeModel
import com.example.rickandmortyapp.databinding.FragmentHomeBinding
import com.example.rickandmortyapp.ui.gallery.EpisodeAdapter
import com.example.rickandmortyapp.util.UIState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment: Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val viewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.let { _ ->

            viewModel.dataList.observe(viewLifecycleOwner) {
                when (it) {
                    is UIState.Loading -> {
                        Toast.makeText(context, "Loading. . .!", Toast.LENGTH_SHORT).show()
                    }
                    is UIState.Success<*> -> {
                        initView(it.response as CharacterModel)
                    }
                    is UIState.Failure -> {
                        Toast.makeText(context, "Error occurred..", Toast.LENGTH_SHORT).show()
                    }
                    else -> {}
                }
            }

            viewModel.getCharacterList()

        }
        return binding.root
    }

    private fun initView(data: CharacterModel) {
        data.let {
            binding.rmCharacter.layoutManager = LinearLayoutManager(context)
            binding.rmCharacter.adapter = CharacterAdapter(data.results)
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}