package com.dm.mvvmexample.di

import com.dm.mvvmexample.repo.NetworkDataSource
import org.koin.dsl.module

val dataSourceModule = module {
    factory { NetworkDataSource() }
}

