package ru.kazantsev.eventsandposters.ui.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.android.support.AndroidSupportInjection
import ru.kazantsev.eventsandposters.ui.navigation.AppNavigator
import ru.kazantsev.eventsandposters.ui.viewmodel.NavigationViewModel
import javax.inject.Inject

abstract class BaseFragment<VM : NavigationViewModel, VB : ViewDataBinding>(@LayoutRes val layoutId: Int) : Fragment(layoutId) {

    protected lateinit var binding: VB
    protected lateinit var viewModel: VM
    @Inject protected lateinit var navigator: AppNavigator
    @Inject protected lateinit var viewModelProvider: ViewModelProvider.Factory

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)?.also {
            binding = DataBindingUtil.bind<VB>(it)!!
            viewModel = initViewModel()
            observeNavigation()
        }
    }

    private fun observeNavigation() {
        viewModel.navigateEvent.observe(viewLifecycleOwner, {
            it.data?.let { arg ->
                navigator.navigateTo(this, arg)
            }
        })
    }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }


    /**
     * It returns [VM] which is assigned to [mViewModel] and initialized in [onCreate]
     */
    abstract fun initViewModel(): VM
}