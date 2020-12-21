package ru.kazantsev.eventsandposters.data.usecase

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import timber.log.Timber

abstract class BaseSingleUseCase<Param, Result> where Result : Any? {

    abstract fun run(param: Param) : Flow<Result>

    operator fun invoke(param: Param) = flow<State<Result>> {

        emit(State.loading())

        val result = run(param)

        return@flow result
            .catch {
                Timber.e(it)
                emit(State.error(it.localizedMessage?:""))
            }
            .collect { emit(State.success(it)) }
    }.flowOn(Dispatchers.IO)


    sealed class State<T> {
        class Loading<T> : State<T>()

        data class Success<T>(val data: T) : State<T>()

        data class Error<T>(val message: String) : State<T>()

        companion object {

            fun <T> loading() = Loading<T>()
            fun <T> success(data: T) =
                Success(data)
            fun <T> error(message: String) =
                Error<T>(message)
        }

    }
}


