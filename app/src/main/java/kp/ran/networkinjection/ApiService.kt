package kp.ran.networkinjection


import com.example.jetpackcomposemvvmretrofitandrecyclerview.News
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ApiService {

    @GET("top-headlines?country=us&category=business&apiKey=684cb893caf7425abeffad82ac1d0f4e")
    suspend fun getMovies() : News

}