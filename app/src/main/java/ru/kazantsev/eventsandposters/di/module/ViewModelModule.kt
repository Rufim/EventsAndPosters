package ru.kazantsev.eventsandposters.di.module

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.MapKey
import dagger.Module
import dagger.multibindings.IntoMap
import kotlinx.coroutines.ExperimentalCoroutinesApi
import ru.kazantsev.eventsandposters.ui.viewmodel.EventDetailsViewModel
import ru.kazantsev.eventsandposters.ui.viewmodel.EventsViewModel
import ru.kazantsev.eventsandposters.ui.viewmodel.PostsViewModel
import ru.kazantsev.eventsandposters.ui.viewmodel.TabsViewModel
import kotlin.reflect.KClass

@Target(
    AnnotationTarget.FUNCTION,
    AnnotationTarget.PROPERTY_GETTER,
    AnnotationTarget.PROPERTY_SETTER
)
@MapKey
annotation class ViewModelKey(val value: KClass<out ViewModel>)

@ExperimentalCoroutinesApi
@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(EventsViewModel::class)
    abstract fun bindEventsViewModel(viewModel: EventsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(PostsViewModel::class)
    abstract fun bindPostsViewModel(viewModel: PostsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(TabsViewModel::class)
    abstract fun bindTabsViewModel(viewModel: TabsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(EventDetailsViewModel::class)
    abstract fun bindEventDetailsViewModel(viewModel: EventDetailsViewModel): ViewModel
}