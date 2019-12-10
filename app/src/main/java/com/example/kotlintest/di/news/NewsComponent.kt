package com.example.kotlintest.di.news

import com.example.kotlintest.di.AppModule
import com.example.kotlintest.di.NetworkModule
import com.example.kotlintest.features.news.NewsFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(
    AppModule::class,
    NewsModule::class,
    NetworkModule::class)
)
interface NewsComponent {

    fun inject(newsFragment: NewsFragment)

}