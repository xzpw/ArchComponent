package com.dm.mvvmexample.repo

import com.dm.mvvmexample.api.FlickerApi
import com.dm.mvvmexample.api.response.Photo
import org.koin.core.KoinComponent
import org.koin.core.inject
import java.lang.Exception

class NetworkDataSource: KoinComponent {

    val api: FlickerApi by inject()
    val resourceHandler:ResourceHandler by inject()

    suspend fun getRecentPhoto():Resource<List<Photo>> {
        return try {
            resourceHandler.handleSuccess(api.getRecentPhoto(
                "flickr.photos.getRecent",
                "585b5e89d5602e8f0db4c9cc5d840dde",
                "json",
                1,
                "url_z"
            ).photos.photo)

        } catch (e: Exception){
            resourceHandler.handleError(e)
        }
    }

}