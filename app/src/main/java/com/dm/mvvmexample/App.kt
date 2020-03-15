package com.dm.mvvmexample

import android.app.Application
import com.dm.mvvmexample.di.dataSourceModule
import com.dm.mvvmexample.di.netWorkModule
import org.koin.core.context.startKoin

class App:Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            this@App
            modules(listOf(netWorkModule, dataSourceModule) )
            applicationContext
        }
    }
}