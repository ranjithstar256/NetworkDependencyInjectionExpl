package kp.ran.networkinjection

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.example.Articles
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: MyRepository) : ViewModel() {


    var movieListResponse: List<Articles> by mutableStateOf(listOf())
    var heroListResponse: List<Hero> by mutableStateOf(listOf())


    fun getMovieList() {
        viewModelScope.launch {
            try {
                movieListResponse = repository.fetchData().articles
            } catch (e: Exception) {
                Log.d("abc123TAG", "getMovieList: ${e.message})")
            }
        }
    }
    fun getHeroList() {
        viewModelScope.launch {
            try {
                heroListResponse = repository.fetchHeros()
            } catch (e: Exception) {
                Log.d("abc123TAG", "getMovieList: ${e.message})")
            }
        }
    }
}