    package com.example.listxml
    import android.view.LayoutInflater
    import android.view.ViewGroup
    import androidx.recyclerview.widget.RecyclerView
    import com.example.listxml.databinding.ItemFilmBinding

    interface OnFilmClickListener {
        fun onDetailClicked(film: Film)
        fun onImdbClicked(imdbUrl: String)
    }
    class FilmAdapter (private val filmList: ArrayList<Film>,
    private val listener: OnFilmClickListener)
        : RecyclerView.Adapter<FilmAdapter.FilmViewHolder>() {

        inner class FilmViewHolder(val binding: ItemFilmBinding) : RecyclerView.ViewHolder(binding.root)

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmViewHolder {
            val binding = ItemFilmBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return FilmViewHolder(binding)
        }

        override fun onBindViewHolder(holder: FilmViewHolder, position: Int) {
            val film = filmList[position]
            holder.binding.apply {
                filmImage.setImageResource(film.image)
                filmTitle.text = film.title
                filmYear.text = film.year
                filmDesc.text = film.desc
                btnImdb.setOnClickListener {
                    listener.onImdbClicked(film.imdb)
                }
                btnDetail.setOnClickListener {
                    listener.onDetailClicked(film)
                }
            }
        }

        override fun getItemCount(): Int {
            return filmList.size
        }
    }