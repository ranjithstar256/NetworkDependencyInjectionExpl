package kp.ran.networkinjection

import com.example.jetpackcomposemvvmretrofitandrecyclerview.News
import java.io.IOException
import javax.inject.Inject

class MyRepository @Inject constructor(private val apiService: ApiService,private val apiService2: ApiService2) {

    suspend fun fetchData(): News {
        return apiService.getMovies()
    }
    suspend fun fetchHeros(): List<Hero> {
        return apiService2.getHeros()
    }
}
