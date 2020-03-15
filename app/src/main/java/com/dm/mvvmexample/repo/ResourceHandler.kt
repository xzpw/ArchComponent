package com.dm.mvvmexample.repo

open class ResourceHandler {
    fun<T: Any> handleSuccess(data: T):Resource<T>{
        return Resource.success(data)
    }

    fun<T: Any> handleError(e: Exception):Resource<T>{
        return Resource.error(e.toString(),null)
    }
}