package com.example.samplemodule

import android.content.Context

import com.artech.externalapi.ExternalApiDefinition
import com.artech.externalapi.ExternalApiFactory
import com.artech.framework.GenexusModule
import com.artech.usercontrols.UcFactory
import com.artech.usercontrols.UserControlDefinition

class SampleModule : GenexusModule {

    override fun initialize(context: Context) {
        val basicExternalObject = ExternalApiDefinition(
                BasicExternalObject.NAME,
                BasicExternalObject::class.java
        )
        ExternalApiFactory.addApi(basicExternalObject)

        val basicUserControl = UserControlDefinition(
                BasicUserControl.NAME,
                BasicUserControl::class.java
        )
        UcFactory.addControl(basicUserControl)
    }
}
