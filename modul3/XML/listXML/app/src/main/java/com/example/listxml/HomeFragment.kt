package com.example.listxml

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.listxml.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var filmAdapter: FilmAdapter
    private val list = ArrayList<Film>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        list.clear()
        list.addAll(getListFilm())
        setupRecyclerView()
        return binding.root
    }

    private fun setupRecyclerView() {
        filmAdapter = FilmAdapter(
            list,
            object : OnFilmClickListener {
                override fun onDetailClicked(film: Film) {
                    val detailFragment = DetailFragment().apply {
                        arguments = Bundle().apply {
                            putString("EXTRA_TITLE", film.title)
                            putString("EXTRA_DESC", film.desc)
                            putInt("EXTRA_IMAGE", film.image)
                        }
                    }
                    parentFragmentManager.beginTransaction()
                        .replace(R.id.frame_container, detailFragment)
                        .addToBackStack(null)
                        .commit()
                }

                override fun onImdbClicked(imdbUrl: String) {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(imdbUrl))
                    startActivity(intent)
                }
            },
        )
        binding.rvFilm.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = filmAdapter
        }
    }

    private fun getListFilm(): ArrayList<Film> {
        val dataTitle = resources.getStringArray(R.array.data_filmTitle)
        val dataDesc = resources.getStringArray(R.array.data_filmDesc)
        val dataImage = resources.obtainTypedArray(R.array.data_filmImage)
        val dataYear = resources.getStringArray(R.array.data_filmYear)
        val dataImdb = resources.getStringArray(R.array.data_filmImdb)
        val listFilm = ArrayList<Film>()
        for (i in dataTitle.indices) {
            val film = Film(
                dataTitle[i],
                dataYear[i],
                dataDesc[i],
                dataImage.getResourceId(i, -1),
                dataImdb[i]
            )
            listFilm.add(film)
        }
        dataImage.recycle()
        return listFilm
    }
}

