package ru.kazantsev.eventsandposters.ui.datasource

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.ItemKeyedDataSource
import androidx.paging.PageKeyedDataSource
import androidx.paging.PositionalDataSource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import ru.kazantsev.eventsandposters.data.usecase.BaseSingleUseCase
import ru.kazantsev.eventsandposters.data.usecase.GetPostsUseCase
import ru.kazantsev.eventsandposters.model.Post
import ru.kazantsev.eventsandposters.ui.viewmodel.PostsViewModel

@ExperimentalCoroutinesApi
abstract class SimpleDataSource<Param, Value>(
    val viewModel: ViewModel,
    val postsUseCase: BaseSingleUseCase<Param, List<Value>>,
    val progressNotifier: ProgressNotifierCallback?
) : PositionalDataSource<Value>() where Value : Any {

    private var loadInitCallback: LoadInitialCallback<Value>? = null


    abstract fun getParamByKey(key: Int?) : Param

    override fun loadInitial(params: LoadInitialParams, callback: LoadInitialCallback<Value>) {
        loadInitCallback = callback
        viewModel.viewModelScope.launch {
            postsUseCase(getParamByKey(params.requestedStartPosition)).collect { state ->
                if (!isInvalid) {
                    when (state) {
                        is BaseSingleUseCase.State.Loading -> progressNotifier?.onStartLoad()
                        is BaseSingleUseCase.State.Success -> {
                            loadInitCallback?.let {
                                loadInitCallback?.onResult(state.data, 0)
                                progressNotifier?.onFinishLoad()
                            }
                        }
                        is BaseSingleUseCase.State.Error -> progressNotifier?.onError()
                    }
                }
            }
        }
    }

    override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<Value>) {
        callback.onResult(emptyList())
    }


}