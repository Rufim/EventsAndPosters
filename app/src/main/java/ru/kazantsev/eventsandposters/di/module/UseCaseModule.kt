package ru.kazantsev.eventsandposters.di.module


import dagger.Module
import dagger.Provides
import kotlinx.coroutines.ExperimentalCoroutinesApi
import ru.kazantsev.eventsandposters.data.repository.EventsRepository
import ru.kazantsev.eventsandposters.data.repository.PostsRepository
import ru.kazantsev.eventsandposters.data.usecase.GetEventUseCase
import ru.kazantsev.eventsandposters.data.usecase.GetEventsUseCase
import ru.kazantsev.eventsandposters.data.usecase.GetPostsUseCase

@ExperimentalCoroutinesApi
@Module
class UseCaseModule {

    @Provides
    fun provideEventsUseCase(eventsRepository: EventsRepository) = GetEventsUseCase(eventsRepository)

    @Provides
    fun provideEventUseCase(eventsRepository: EventsRepository) = GetEventUseCase(eventsRepository)

    @Provides
    fun providePostersUseCase(postsRepository: PostsRepository) = GetPostsUseCase(postsRepository)

}