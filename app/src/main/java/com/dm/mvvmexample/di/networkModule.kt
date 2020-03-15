package com.dm.mvvmexample.di

import com.dm.mvvmexample.api.BASE_URL
import com.dm.mvvmexample.api.FlickerApi
import com.dm.mvvmexample.repo.ResourceHandler
import okhttp3.Call
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


    val netWorkModule = module {

            single<Call.Factory> { okHttp() }
            single { retrofit(get()) }
            single { flickerApiProvider(get()) }
            single { resourceHandler() }
    }

    fun okHttp() = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY })
        .build()

    fun retrofit(callFactory:Call.Factory) = Retrofit.Builder()
        .callFactory(callFactory)
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun flickerApiProvider(retrofit: Retrofit) = retrofit.create(FlickerApi::class.java)

    fun resourceHandler() = ResourceHandler()