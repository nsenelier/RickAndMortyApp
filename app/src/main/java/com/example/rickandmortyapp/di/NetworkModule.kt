package com.example.rickandmortyapp.di


import com.example.rickandmortyapp.data.remote.Repository
import com.example.rickandmortyapp.data.remote.RepositoryImpl
import com.example.rickandmortyapp.data.remote.RickMortyApi
import com.example.rickandmortyapp.util.BASE_URL
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

    @Module
    @InstallIn(SingletonComponent::class)
    class NetworkModule {

        @Provides
        fun providesMoshi(): Moshi =
            Moshi.Builder().add(KotlinJsonAdapterFactory()).build()


        @Provides
        fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
            return HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
        }

        @Provides
        fun provideOkHttpClient(
            loggingInterceptor: HttpLoggingInterceptor
        ): OkHttpClient {
            return OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .build()
        }

        @Provides
        fun provideRetrofit(
            moshi: Moshi,
            client: OkHttpClient
        ): RickMortyApi {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .client(client)
                .build()
                .create(RickMortyApi::class.java)
        }

        @Provides
        fun provideRepository(
            rickMortyApi: RickMortyApi
        ): Repository {
            return RepositoryImpl(rickMortyApi)
        }

    }