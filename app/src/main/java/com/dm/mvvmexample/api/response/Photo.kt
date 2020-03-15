package com.dm.mvvmexample.api.response

data class Photo(
    val farm: Int,
    val height_z: Int,
    val id: String,
    val isfamily: Int,
    val isfriend: Int,
    val ispublic: Int,
    val owner: String,
    val secret: String,
    val server: String,
    val title: String,
    val url_z: String,
    val width_z: Int
)