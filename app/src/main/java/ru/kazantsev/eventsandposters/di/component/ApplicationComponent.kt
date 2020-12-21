package ru.kazantsev.eventsandposters.di.component

import android.app.Application
import android.content.Context
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import ru.kazantsev.eventsandposters.App
import ru.kazantsev.eventsandposters.di.builder.ActivityBuilder
import ru.kazantsev.eventsandposters.di.builder.FragmentBuilder
import ru.kazantsev.eventsandposters.di.module.*
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        AndroidModule::class,
        DatabaseModule::class,
        ApiModule::class,
        ActivityBuilder::class,
        FragmentBuilder::class,
        UseCaseModule::class,
        ViewModelFactoryModule::class,
        ViewModelModule::class]
)
interface ApplicationComponent : AndroidInjector<App> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun create(application: Application): Builder

        fun build(): ApplicationComponent
    }

    override fun inject(app: App)
}