package com.example.samplemodule

import android.app.Activity
import android.content.Intent
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

	private val methodActivity = object : IMethodInvokerWithActivityResult {
		override fun invoke(parameters: MutableList<Any>?): ExternalApiResult {
			val intent = Intent(context, ActivitySumSample::class.java)
			intent.putExtra(ActivitySumSample.KEY_NUMBER_1, (parameters?.get(0).toString().toInt()))
			intent.putExtra(ActivitySumSample.KEY_NUMBER_2, (parameters?.get(1).toString().toInt()))
			startActivityForResult(intent, METHOD_ACTIVITY_REQUEST_CODE)
			return ExternalApiResult.SUCCESS_WAIT
		}

		override fun handleActivityResult(requestCode: Int, resultCode: Int, result: Intent?): ExternalApiResult {
			return if (requestCode == METHOD_ACTIVITY_REQUEST_CODE) {
				when (resultCode) {
					Activity.RESULT_OK -> {
						val sumResult = result?.getIntExtra(ActivitySumSample.KEY_RESULT, -1)
						ExternalApiResult.success(sumResult)
					}

					else -> {
						ExternalApiResult.FAILURE
					}
				}
			} else {
				ExternalApiResult.FAILURE
			}
		}
	}

	companion object {
		const val NAME = "BasicExternalObject"
		private const val METHOD_HELLO = "Hello"
		private const val METHOD_MESSAGE = "Message"
		private const val METHOD_UI_SUM = "UISum"

		private const val METHOD_ACTIVITY_REQUEST_CODE = 4364
	}

	init {
		addMethodHandler(METHOD_HELLO, 0, methodHello)
		addMethodHandler(METHOD_MESSAGE, 1, methodMessage)
		addMethodHandler(METHOD_UI_SUM, 2, methodActivity)
	}
}