package kp.ran.networkinjection

import com.example.jetpackcomposemvvmretrofitandrecyclerview.News
import retrofit2.Response
import retrofit2.http.Body
import java.io.IOException
import javax.inject.Inject

class MyRepository @Inject constructor(private val apiService: ApiService
,private val apiService2: ApiService2
,private val postsApiService: PostsApiService) {

    suspend fun fetchData(): News {
        return apiService.getMovies()
    }
    suspend fun fetchHeros(): List<Hero> {
        return apiService2.getHeros()
    }

    suspend fun postData(@Body postData:PostModel): Response<PostModel> {
        return postsApiService.postData(postData)
    }
}
