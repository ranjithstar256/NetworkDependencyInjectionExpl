package kp.ran.networkinjection

import com.example.jetpackcomposemvvmretrofitandrecyclerview.News
import retrofit2.http.GET

interface ApiService2 {

    @GET("marvel")
    suspend fun getHeros() : List<Hero>

}
