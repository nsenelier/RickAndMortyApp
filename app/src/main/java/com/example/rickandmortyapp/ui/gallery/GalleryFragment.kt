package com.example.rickandmortyapp.ui.gallery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rickandmortyapp.data.model.CharacterModel
import com.example.rickandmortyapp.data.model.EpisodeModel
import com.example.rickandmortyapp.databinding.FragmentGalleryBinding
import com.example.rickandmortyapp.util.UIState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GalleryFragment : Fragment() {

    private var _binding: FragmentGalleryBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val viewModel: GalleryViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        //val galleryViewModel = ViewModelProvider(this).get(GalleryViewModel::class.java)

        _binding = FragmentGalleryBinding.inflate(inflater, container, false)
//        val root: View = binding.root
//
//        val textView: TextView = binding.textGallery
//        galleryViewModel.text.observe(viewLifecycleOwner) {
//            textView.text = it
//        }
//        return root

        binding.let { _ ->

            viewModel.dataList.observe(viewLifecycleOwner) {
                when (it) {
                    is UIState.Loading -> {
                        Toast.makeText(context, "Loading. . .!", Toast.LENGTH_SHORT).show()
                    }
                    is UIState.Success<*>-> {
                        initView(it.response as EpisodeModel)
                    }
                    is UIState.Failure -> {
                        Toast.makeText(context, "Error occurred..", Toast.LENGTH_SHORT).show()
                    }
                    else -> {}
                }
            }

            viewModel.getEpisodeList()

        }
        return binding.root
    }

    private fun initView(data: EpisodeModel) {
        data.let {
            binding.rmEpisode.layoutManager = LinearLayoutManager(context)
            binding.rmEpisode.adapter = EpisodeAdapter(data.results)
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}