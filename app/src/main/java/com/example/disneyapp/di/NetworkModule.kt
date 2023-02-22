package com.example.disneyapp.di

import com.example.disneyapp.data.remote.DisneyApi
import com.example.disneyapp.data.repository.DisneyRepository
import com.example.disneyapp.data.repository.DisneyRepositoryImp
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    @Provides
    fun provideGson(): Gson = Gson()

    @Provides
    fun provideHttpInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

    @Provides
    fun provideOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()

    @Provides
    fun provideRetrofit(
        gson: Gson,
        okHttpClient: OkHttpClient
    ): Retrofit =
        Retrofit.Builder()
            .baseUrl(DisneyApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .build()

    @Provides
    fun provideAnimeApi(
        retrofit: Retrofit
    ): DisneyApi = retrofit.create(DisneyApi::class.java)

    @Provides
    fun provideRepository(
        disneyApi: DisneyApi
    ): DisneyRepository = DisneyRepositoryImp(disneyApi)
}