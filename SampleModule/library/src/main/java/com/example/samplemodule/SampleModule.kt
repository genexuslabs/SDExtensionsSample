package com.example.samplemodule

import android.content.Context
import com.genexus.android.core.externalapi.ExternalApiDefinition
import com.genexus.android.core.externalapi.ExternalApiFactory
import com.genexus.android.core.framework.GenexusModule
import com.genexus.android.core.usercontrols.UcFactory
import com.genexus.android.core.usercontrols.UserControlDefinition

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