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

    var errorMessage: String by mutableStateOf("")

    fun getMovieList() {
        viewModelScope.launch {
            try {
                movieListResponse = repository.fetchData().articles
            } catch (e: Exception) {
                errorMessage = e.message.toString()
            }
        }
    }
}