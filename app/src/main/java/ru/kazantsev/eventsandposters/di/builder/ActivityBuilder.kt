package ru.kazantsev.eventsandposters.di.builder

import dagger.Module
import dagger.android.ContributesAndroidInjector
import kotlinx.coroutines.ExperimentalCoroutinesApi
import ru.kazantsev.eventsandposters.ui.MainActivity
import ru.kazantsev.eventsandposters.ui.fragment.TabsFragment

@Module()
abstract class ActivityBuilder {

    @ExperimentalCoroutinesApi
    @ContributesAndroidInjector()
    abstract fun bindMainActivity(): MainActivity

}