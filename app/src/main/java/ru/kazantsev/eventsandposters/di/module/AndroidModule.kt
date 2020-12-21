package ru.kazantsev.eventsandposters.di.module

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import ru.kazantsev.eventsandposters.App
import ru.kazantsev.eventsandposters.ui.navigation.AppNavigator
import javax.inject.Singleton

@Module
class AndroidModule {

    @Provides
    @Singleton
    internal fun provideApplicationContext(context: Application) = context.applicationContext

    @Provides
    @Singleton
    internal fun provideSharedPreferences(context: Application) = context.getSharedPreferences(
        DEFAULT_PREFERENCES,
        Context.MODE_PRIVATE
    )

    @Provides
    @Singleton
    internal fun provideAppNavigator() = AppNavigator()


    companion object {
       const val DEFAULT_PREFERENCES = "default_preferences"
    }
}