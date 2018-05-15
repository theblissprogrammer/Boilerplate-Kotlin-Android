package com.example.boilerplateapplication.common.activities

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.*
import com.example.boilerplateapplication.R
import com.example.boilerplateapplication.common.protocols.NavigationInterface
import com.example.boilerplateapplication.scenes.home.HomeFragment

/**
 * Created by ahmedsaad on 2018-01-22.
 * Copyright © 2017. All rights reserved.
 */
class BaseHomeFragment : Fragment(), NavigationInterface {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_profile_base, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (savedInstanceState == null) {
            childFragmentManager
                    .beginTransaction()
                    ?.replace(R.id.base_fragment, HomeFragment(),
                            HomeFragment::class.java.simpleName)
                    ?.commit()
        }

    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)

        if (!hidden) {
            childFragmentManager.fragments.firstOrNull { it.isAdded && it.isVisible }
                    ?.onHiddenChanged(hidden)
        }
    }

    override fun onBackPressed() {
        childFragmentManager.popBackStack()
    }
}