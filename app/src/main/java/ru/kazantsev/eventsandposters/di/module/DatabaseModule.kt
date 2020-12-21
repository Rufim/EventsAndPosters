package ru.kazantsev.eventsandposters.di.module

import android.app.Application
import dagger.Module
import dagger.Provides
import ru.kazantsev.eventsandposters.data.database.AppDatabase
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(application: Application) = AppDatabase.getInstance(application)

    @Singleton
    @Provides
    fun providePostsDao(database: AppDatabase) = database.getPostsDao()

    @Singleton
    @Provides
    fun provideEventsDao(database: AppDatabase) = database.getEventsDao()
}