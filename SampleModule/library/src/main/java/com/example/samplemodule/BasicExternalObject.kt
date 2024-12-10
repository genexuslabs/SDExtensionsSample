package com.example.samplemodule

import android.app.Activity
import android.content.Intent
import com.genexus.android.core.actions.ApiAction
import com.genexus.android.core.base.services.Services
import com.genexus.android.core.externalapi.ExternalApi
import com.genexus.android.core.externalapi.ExternalApiResult
import com.example.genexusmodule.R
import com.genexus.android.core.actions.ExternalObjectEvent

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

	private val methodActivityAddNumbersWithUI = object : IMethodInvokerWithActivityResult {
		override fun invoke(parameters: MutableList<Any>?): ExternalApiResult {
			val intent = Intent(context, ActivityAddNumbers::class.java)
			val number1 = parameters?.get(0).toString().toInt()
			val number2 = parameters?.get(1).toString().toInt()
			intent.putExtra(ActivityAddNumbers.KEY_NUMBER_1, number1)
			intent.putExtra(ActivityAddNumbers.KEY_NUMBER_2, number2)
			startActivityForResult(intent, METHOD_ACTIVITY_REQUEST_CODE)
			return ExternalApiResult.SUCCESS_WAIT
		}

		override fun handleActivityResult(requestCode: Int, resultCode: Int, result: Intent?) : ExternalApiResult {
			if (requestCode != METHOD_ACTIVITY_REQUEST_CODE || resultCode != Activity.RESULT_OK)
				return ExternalApiResult.FAILURE

			val sumResult = result?.getIntExtra(ActivityAddNumbers.KEY_RESULT, -1)
			return if (sumResult == null || sumResult == -1)
				ExternalApiResult.FAILURE
			else
				ExternalApiResult.success(sumResult)
		}
	}

	private val methodAddNumbers = IMethodInvoker { parameters ->
		val addend1 = parameters?.get(0).toString().toInt()
		val addend2 = parameters?.get(1).toString().toInt()
		val result = addend1 + addend2
		ExternalApiResult.success(result)
	}

	private val methodFireMessagePrintEvent = IMethodInvoker { parameters ->
		val message = parameters[0].toString()
		val event = ExternalObjectEvent(NAME, EVENT_NAME_ON_MESSAGE_PRINTED)
		val params = listOf(message)
		event.fire(params)
		ExternalApiResult.SUCCESS_CONTINUE
	}

	companion object {
		const val NAME = "BasicExternalObject"
		private const val METHOD_HELLO = "Hello"
		private const val METHOD_MESSAGE = "Message"
		private const val METHOD_UI_ADD_NUMBERS = "UIAdd"
		private const val METHOD_ADD_NUMBERS = "AddNumbers"
		private const val METHOD_PRINT_MESSAGE = "PrintMessage"

		internal const val EVENT_NAME_ON_MESSAGE_PRINTED = "OnMessagePrinted"

		private const val METHOD_ACTIVITY_REQUEST_CODE = 4364
	}

	init {
		addMethodHandler(METHOD_HELLO, 0, methodHello)
		addMethodHandler(METHOD_MESSAGE, 1, methodMessage)
		addMethodHandler(METHOD_UI_ADD_NUMBERS, 2, methodActivityAddNumbersWithUI)
		addMethodHandler(METHOD_ADD_NUMBERS, 2, methodAddNumbers)
		addMethodHandler(METHOD_PRINT_MESSAGE, 1, methodFireMessagePrintEvent)
	}
}
