package com.example.listxml

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.listxml.databinding.ItemFilmBinding

class FilmAdapter (private val filmList: ArrayList<Film>)
    : RecyclerView.Adapter<FilmAdapter.FilmViewHolder>() {

    inner class FilmViewHolder(val binding: ItemFilmBinding) : RecyclerView.ViewHolder(binding.root) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmViewHolder {
        val binding = ItemFilmBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FilmViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FilmViewHolder, position: Int) {
        val film = filmList[position]
        holder.binding.apply {
            filmImage.setImageResource(film.image)
            filmTitle.text = film.title
            filmDesc.text = film.desc
            filmYear.text = film.year
            btnImdb.setOnClickListener {
                // Handle IMDB button click
            }
            btnDetail.setOnClickListener {
                // Handle Detail button click
            }
        }
    }

    override fun getItemCount(): Int {
        return filmList.size
    }
}