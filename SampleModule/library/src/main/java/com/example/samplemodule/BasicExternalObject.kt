package com.example.samplemodule

import com.artech.actions.ActionResult
import com.artech.actions.ApiAction
import com.artech.application.MyApplication
import com.artech.base.services.Services
import com.artech.externalapi.ExternalApi
import com.artech.externalapi.ExternalApiResult

class BasicExternalObject(action: ApiAction) : ExternalApi(action) {

    private val mMethodHello = IMethodInvoker {
        Services.Messages.showMessage(context.getString(com.example.genexusmodule.R.string.hello_message))
        ExternalApiResult(ActionResult.SUCCESS_CONTINUE)
    }

    private val mMethodMessage = IMethodInvoker { parameters ->
        val text = parameters[0] as String
        Services.Messages.showMessage(text)
        ExternalApiResult(ActionResult.SUCCESS_CONTINUE)
    }

    init {
        addMethodHandler(METHOD_HELLO, 0, mMethodHello)
        addMethodHandler(METHOD_MESSAGE, 1, mMethodMessage)
    }

    companion object {
        internal val NAME = "BasicExternalObject"
        private val METHOD_HELLO = "Hello"
        private val METHOD_MESSAGE = "Message"
    }
}
