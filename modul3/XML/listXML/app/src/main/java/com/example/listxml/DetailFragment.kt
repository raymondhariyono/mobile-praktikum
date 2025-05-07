package com.example.listxml

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.listxml.databinding.FragmentDetailBinding

class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    private var title: String? = null
    private var desc: String? = null
    private var year: String? = null
    private var image: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            title = it.getString("EXTRA_TITLE")
            desc = it.getString("EXTRA_DESC")
            year = it.getString("EXTRA_YEAR")
            image = it.getInt("EXTRA_IMAGE")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.detailTitle.text = title
        binding.detailDesc.text = desc
        binding.detailYear.text = year
        binding.detailImage.setImageResource(image)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
