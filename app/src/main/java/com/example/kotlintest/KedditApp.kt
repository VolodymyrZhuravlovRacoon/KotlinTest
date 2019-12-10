package com.example.kotlintest

import android.app.Application
import com.example.kotlintest.di.AppModule
import com.example.kotlintest.di.news.DaggerNewsComponent
import com.example.kotlintest.di.news.NewsComponent

class KedditApp : Application() {

    companion object {
        lateinit var newsComponent: NewsComponent
    }


    override fun onCreate() {
        super.onCreate()
        newsComponent = DaggerNewsComponent.builder()
            .appModule(AppModule(this))
            //.newsModule(NewsModule()) Module with empty constructor is implicitly created by dagger.
            .build()
    }
}