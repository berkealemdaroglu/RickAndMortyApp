package com.ersinberkealemdaroglu.rickandmortyapp.di

import com.ersinberkealemdaroglu.rickandmortyapp.data.ApiService
import com.ersinberkealemdaroglu.rickandmortyapp.data.repository.CharacterRepositoryImp
import com.ersinberkealemdaroglu.rickandmortyapp.domain.repository.CharacterRepository
import com.ersinberkealemdaroglu.rickandmortyapp.utils.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideGsonConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    @Provides
    @Singleton
    fun provideRetrofit(gsonConverterFactory: GsonConverterFactory): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(gsonConverterFactory)
            .build()
    }

    @Provides
    @Singleton
    fun provideService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    fun providesOkhttpClient(interceptor : HttpLoggingInterceptor) : OkHttpClient{
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .callTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
        return okHttpClient.build()
    }

    @Provides
    @Singleton
    fun provideCharacter(apiService: ApiService): CharacterRepository = CharacterRepositoryImp(apiService)

}