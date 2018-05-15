package com.example.boilerplateapplication.scenes.home

import android.content.Context
import android.os.Bundle
import android.view.*
import com.example.boilerplateapplication.R
import com.example.boilerplateapplication.common.activities.BaseFragment
import com.example.boilerplateapplication.common.controls.PlayRatingDialog
import com.example.coreandroid.sources.dependencies.HasDependencies
import com.example.coreandroid.sources.preferences.PreferencesWorkerType
import com.example.boilerplateapplication.scenes.home.common.HomeBusinessLogic
import com.example.boilerplateapplication.scenes.home.common.HomeDisplayable
import java.lang.ref.WeakReference

class HomeFragment : BaseFragment(), HomeDisplayable, HasDependencies{

    val interactor: HomeBusinessLogic by lazy {
        HomeInteractor(
                presenter = HomePresenter(
                        fragment = WeakReference(this)
                )
        )
    }

    private val router: HomeRouter by lazy {
        HomeRouter(
                fragment = WeakReference(this)
        )
    }

    private val preferencesWorker: PreferencesWorkerType by lazy {
        dependencies.resolvePreferencesWorker()
    }

    val playRatingDialog by lazy {
        PlayRatingDialog(this.activity as Context)
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configure()
        loadListeners()
    }

    override fun onResume() {
        super.onResume()
        loadData()
        loadUI()
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        loadData()
        loadUI()
    }

    private fun configure() {
        setHasOptionsMenu(true)
        setTitle("")
    }

    private fun loadData() {
        // TODO: Call interactor to load data
    }

    private fun loadUI() {
        // TODO: Configure UI
    }

    private fun loadListeners() {
        // TODO: Configure button listeners
    }
}