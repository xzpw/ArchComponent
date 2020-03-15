package com.dm.mvvmexample.ui.main

import android.util.Log
import androidx.lifecycle.*
import com.dm.mvvmexample.api.response.Photo
import com.dm.mvvmexample.repo.NetworkDataSource
import com.dm.mvvmexample.repo.Status
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import org.koin.core.KoinComponent
import org.koin.core.inject

class MainViewModel : ViewModel(), KoinComponent {

    val name =  MutableLiveData<List<Photo>> ()
    val dataSource: NetworkDataSource by inject()

    var someText =  MutableLiveData<String> ()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            dataSource.getRecentPhoto().let {
                when(it.status){
                    Status.SUCCESS -> name.postValue(it.data)
                    Status.ERROR -> someText.postValue(it.massage)
                }
            }
        }

    }


     /*fun goLiveDataFlow(){
         async/await запросили асинхронно подождали получили
        val def = asyncAwaitExample()
         viewModelScope.launch { someText.value = def.await()
                delay(1000)}            * /

          viewModelScope.launch {goFlow().collect{
                  value -> someText.value = value
          }  }

    }

    fun asyncAwaitExample() = CoroutineScope(Dispatchers.IO).async{
        delay(5000)
        return@async "5000 ms"
    }

    fun goFlow(): Flow<String> = flow {
        for (i in 1..10){
            delay(1000)
            emit("Iteration: $i")
        }
    }
*/
     fun goLiveDataFlow(){

      val job1 = viewModelScope.launch {
          delay(1000)
          postText("job1 text")
          cancel()
      }

         val job2 = viewModelScope.launch {
             job1.join()
             delay(1000)
             postText("job2 text")
         }

     }

    val someJob = viewModelScope.async(Dispatchers.IO) {
        val job = launch { delay(3000)
        this.cancel()}
        job.join()
    return@async "some async text" }


    suspend fun postText(text: String){
        someText.postValue(text)
        Log.d("mylog",text)
    }

    fun getResultFromScope():String{
        return runBlocking { someJob.await() }
    }

}
