package ru.kazantsev.eventsandposters

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import ru.kazantsev.eventsandposters.di.component.DaggerApplicationComponent
import java.util.*
import javax.inject.Inject

class App : Application(), HasAndroidInjector {

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    override fun androidInjector(): AndroidInjector<Any> = androidInjector


    override fun onCreate() {
        super.onCreate()

        // Initialize Dependency Injection
        DaggerApplicationComponent.builder()
            .create(this)
            .build()
            .inject(this)

    }

}