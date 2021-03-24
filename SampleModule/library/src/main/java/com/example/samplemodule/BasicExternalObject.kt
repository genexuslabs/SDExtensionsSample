package com.example.samplemodule

import com.artech.actions.ApiAction
import com.artech.base.services.Services
import com.artech.externalapi.ExternalApi
import com.artech.externalapi.ExternalApi.IMethodInvoker
import com.artech.externalapi.ExternalApiResult
import com.example.genexusmodule.R

class BasicExternalObject(action: ApiAction) : ExternalApi(action) {

	private val mMethodHello = IMethodInvoker {
		Services.Messages.showMessage(context.getString(R.string.hello_message))
		ExternalApiResult.SUCCESS_CONTINUE
	}
	private val mMethodMessage = IMethodInvoker { parameters ->
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
		addMethodHandler(METHOD_HELLO, 0, mMethodHello)
		addMethodHandler(METHOD_MESSAGE, 1, mMethodMessage)
	}
}