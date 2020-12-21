package ru.kazantsev.eventsandposters.ui

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.findNavController
import dagger.android.AndroidInjection
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import ru.kazantsev.eventsandposters.MainGraphDirections
import ru.kazantsev.eventsandposters.R
import ru.kazantsev.eventsandposters.databinding.MainActivityBinding
import javax.inject.Inject

class MainActivity : AppCompatActivity(), HasAndroidInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    private lateinit var binding: MainActivityBinding
    private lateinit var navigator: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.main_activity)
        navigator = findNavController(R.id.tabsNavHostFragment)
        onNewIntent(intent)
    }

    override fun androidInjector() = dispatchingAndroidInjector

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        intent?.data?.let {
            when (it.host) {
                EVENTS -> {
                    val path = it.path?.replace("/", "")
                    if (!path.isNullOrBlank())
                        MainGraphDirections.navActionEventDetail(path.toInt())
                    else
                        getPostsAction(1)
                }
                POSTS -> {
                    getPostsAction()
                }
                else -> null
            }
        }?.let {
            navigator.navigate(it)
        }

    }

    private fun getPostsAction(page: Int = 0) = MainGraphDirections.navActionTabsFragment(page)


    companion object {
        const val EVENTS = "events"
        const val POSTS = "possts"

        fun getDeepLinkIntent(deepLink: String): Intent {
            return Intent(Intent.ACTION_VIEW).apply {
                data = Uri.parse(deepLink)
            }
        }
    }
}