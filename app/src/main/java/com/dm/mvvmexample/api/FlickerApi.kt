package com.dm.mvvmexample.api

import com.dm.mvvmexample.api.response.RecentResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface FlickerApi {

    @GET("rest/")
    suspend fun getRecentPhoto(@Query("method") method: String,
                               @Query("api_key") api_key: String,
                               @Query("format") format:String,
                               @Query("nojsoncallback") nojsoncallback: Int,
                               @Query("extras") extras: String): RecentResponse
}


val BASE_URL = "https://api.flickr.com/services/"