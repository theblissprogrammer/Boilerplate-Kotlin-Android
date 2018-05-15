package com.example.boilerplateapplication.common.activities

import android.support.v7.app.AppCompatActivity
import com.example.boilerplateapplication.common.controls.SpinnerDialogFragment
import com.example.boilerplateapplication.common.protocols.BaseInterface


/**
 * Created by ahmedsaad on 2017-11-03.
 * Copyright © 2017. All rights reserved.
 */
open class BaseActivity: AppCompatActivity(), BaseInterface {
    override var spinner: SpinnerDialogFragment? = null
}