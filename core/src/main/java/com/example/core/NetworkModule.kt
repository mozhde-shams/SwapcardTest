package com.example.core

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import javax.inject.Named

@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {

    private const val BASE_URL = "https://randomuser.me/"

    @Provides
    @Named("baseUrl")
    internal fun provideBaseUrl(): String = BASE_URL

    @Provides
    internal fun httpLoggingInterceptor(): HttpLoggingInterceptor {
        val logInterceptor = HttpLoggingInterceptor()
        logInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return logInterceptor
    }

    @Provides
    internal fun httpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        return OkHttpClient()
            .newBuilder()
            .addInterceptor(httpLoggingInterceptor)
            .build()
    }

    @Provides
    internal fun provideRetrofitBuilder(
        @Named("baseUrl") baseUrl: String,
        httpClient: OkHttpClient,
    ): Retrofit {
        val contentType = "application/json".toMediaType()
        val json = Json {
            ignoreUnknownKeys = true
        }
        return Retrofit
            .Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(json.asConverterFactory(contentType))
            .client(httpClient)
            .build()
    }
}