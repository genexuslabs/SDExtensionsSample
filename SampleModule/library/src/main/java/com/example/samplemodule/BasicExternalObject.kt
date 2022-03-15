package com.example.samplemodule

import com.genexus.android.core.actions.ApiAction
import com.genexus.android.core.base.services.Services
import com.genexus.android.core.externalapi.ExternalApi
import com.genexus.android.core.externalapi.ExternalApiResult
import com.example.genexusmodule.R

class BasicExternalObject(action: ApiAction) : ExternalApi(action) {

	private val methodHello = IMethodInvoker {
		Services.Messages.showMessage(context.getString(R.string.hello_message))
		ExternalApiResult.SUCCESS_CONTINUE
	}
	private val methodMessage = IMethodInvoker { parameters ->
		val text = parameters[0] as String
		Services.Messages.showMessage(text)
		ExternalApiResult.SUCCESS_CONTINUE
	}

	companion object {
		const val NAME = "BasicExternalObject"
		private const val METHOD_HELLO = "Hello"
		private const val METHOD_MESSAGE = "Message"
	}

	init {
		addMethodHandler(METHOD_HELLO, 0, methodHello)
		addMethodHandler(METHOD_MESSAGE, 1, methodMessage)
	}
}