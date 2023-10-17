package kp.ran.networkinjection


import com.example.example.Articles
import com.example.jetpackcomposemvvmretrofitandrecyclerview.News
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("top-headlines?country=us&category=business&apiKey=684cb893caf7425abeffad82ac1d0f4e")
    suspend fun getMovies() : News

}

interface ApiService2 {

    @GET("marvel")
    suspend fun getHeros() : List<Hero>

}
data class PostModel(
    var userId:Int?=0,
    var id:Int?=0,
    var title:String?="",
    var body:String?=""
)

interface PostsApiService {

    @POST("posts")
    suspend fun postData(@Body postData:PostModel): Response<PostModel>

}
