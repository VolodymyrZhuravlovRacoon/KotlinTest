package com.example.kotlintest.di

import android.content.Context
import com.example.kotlintest.KedditApp
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule (val app: KedditApp) {

    @Provides
    @Singleton
    fun provideContext(): Context {
        return app;
    }

    @Provides
    @Singleton
    fun provideApplication(): KedditApp {
        return app;
    }
}