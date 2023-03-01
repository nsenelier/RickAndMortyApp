package com.example.rickandmortyapp.ui.slideshow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rickandmortyapp.data.model.EpisodeModel
import com.example.rickandmortyapp.data.model.LocationModel
import com.example.rickandmortyapp.databinding.FragmentSlideshowBinding
import com.example.rickandmortyapp.ui.gallery.EpisodeAdapter
import com.example.rickandmortyapp.util.UIState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SlideshowFragment : Fragment() {

    private var _binding: FragmentSlideshowBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val viewModel =
            ViewModelProvider(this).get(SlideshowViewModel::class.java)

        _binding = FragmentSlideshowBinding.inflate(inflater, container, false)
        binding.let { _ ->

            viewModel.dataList.observe(viewLifecycleOwner) {
                when (it) {
                    is UIState.Loading -> {
                        Toast.makeText(context, "Loading...", Toast.LENGTH_SHORT).show()
                    }
                    is UIState.Success<*> -> {
                        initView(it.response as LocationModel)
                    }
                    is UIState.Failure -> {
                        Toast.makeText(context, "Error occurred..", Toast.LENGTH_SHORT).show()
                    }
                    else -> {}
                }
            }

            viewModel.getLocationList()

        }
        return binding.root
    }

    private fun initView(data: LocationModel) {
        data.let {
            binding.rmLocation.layoutManager = LinearLayoutManager(context)
            binding.rmLocation.adapter = LocationAdapter(data.results)
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}