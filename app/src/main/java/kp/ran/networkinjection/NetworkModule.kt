package kp.ran.networkinjection

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofitService(): ApiService {
        return Retrofit.Builder()
            .baseUrl("https://newsapi.org/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    @Singleton
    @Provides
    fun examplprovideRetrofitServiceB(): ApiService2 {
        return Retrofit.Builder()
             .baseUrl("https://simplifiedcoding.net/demos/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService2::class.java)
    }

}
