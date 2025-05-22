package com.example.listxml
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class FilmViewModel(application: Application) : AndroidViewModel(application) {

    private val _filmList = MutableLiveData<List<Film>>()
    val filmList: LiveData<List<Film>> get() = _filmList

    fun loadFilmsFromResources() {
        val context = getApplication<Application>()
        val resources = context.resources

        val dataTitle = resources.getStringArray(R.array.data_filmTitle)
        val dataDesc = resources.getStringArray(R.array.data_filmDesc)
        val dataImage = resources.obtainTypedArray(R.array.data_filmImage)
        val dataYear = resources.getStringArray(R.array.data_filmYear)
        val dataImdb = resources.getStringArray(R.array.data_filmImdb)

        val listFilm = ArrayList<Film>()
        for (i in dataTitle.indices) {
            val film = Film(
                title = dataTitle[i],
                year = dataYear[i],
                desc = dataDesc[i],
                image = dataImage.getResourceId(i, -1),
                imdb = dataImdb[i]
            )
            listFilm.add(film)
        }

        dataImage.recycle()

        _filmList.value = listFilm
    }
}
