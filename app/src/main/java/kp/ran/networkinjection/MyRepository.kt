package kp.ran.networkinjection

import com.example.jetpackcomposemvvmretrofitandrecyclerview.News
import java.io.IOException
import javax.inject.Inject

class MyRepository @Inject constructor(private val apiService: ApiService) {

    suspend fun fetchData(): News {
        return apiService.getMovies()
    }
}
