package ru.kazantsev.eventsandposters.ui.fragment

import android.os.Bundle
import android.os.Parcelable
import android.util.SparseArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.coroutines.ExperimentalCoroutinesApi
import ru.kazantsev.eventsandposters.R
import ru.kazantsev.eventsandposters.databinding.TabsFragmentBinding
import ru.kazantsev.eventsandposters.ui.adapter.MainPageAdapter
import ru.kazantsev.eventsandposters.ui.viewModelOf
import ru.kazantsev.eventsandposters.ui.viewmodel.TabsViewModel

@ExperimentalCoroutinesApi
class TabsFragment : BaseFragment<TabsViewModel, TabsFragmentBinding>(R.layout.tabs_fragment) {

    private val args: TabsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState).also {
            binding.apply {
                viewpager.adapter = MainPageAdapter(this@TabsFragment)
                TabLayoutMediator(slidingTabs, viewpager) { tab, position ->
                    tab.text = resources.getTextArray(R.array.tabs)[position]
                }.attach()
                viewModel.savedAdapterState.value?.let {
                    viewpager.saveHierarchyState(it)
                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        if (args.page >= 0) {
            binding.viewpager.post {
                binding.viewpager.currentItem = args.page
            }
            arguments?.putInt("page", -1)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        val container = SparseArray<Parcelable>()
        binding.viewpager.saveHierarchyState(container)
        viewModel.savedAdapterState.value = container
    }

    override fun initViewModel() = viewModelOf<TabsViewModel>(viewModelProvider)


    companion object {
        fun newInstance() = TabsFragment()
    }
}