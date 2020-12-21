package ru.kazantsev.eventsandposters.di.builder

import dagger.Module
import dagger.android.ContributesAndroidInjector
import kotlinx.coroutines.ExperimentalCoroutinesApi
import ru.kazantsev.eventsandposters.ui.fragment.EventDetailsFragment
import ru.kazantsev.eventsandposters.ui.fragment.EventsFragment
import ru.kazantsev.eventsandposters.ui.fragment.PostsFragment
import ru.kazantsev.eventsandposters.ui.fragment.TabsFragment


@Module
abstract class FragmentBuilder {

    @ExperimentalCoroutinesApi
    @ContributesAndroidInjector
    abstract fun provideTabsFragment(): TabsFragment

    @ExperimentalCoroutinesApi
    @ContributesAndroidInjector
    abstract fun provideEventsFragment(): EventsFragment

    @ExperimentalCoroutinesApi
    @ContributesAndroidInjector
    abstract fun providePostsFragment(): PostsFragment

    @ExperimentalCoroutinesApi
    @ContributesAndroidInjector
    abstract fun provideEventDetailsFragment(): EventDetailsFragment
}