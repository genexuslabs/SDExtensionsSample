package com.example.samplemodule

import com.example.samplemodule.BasicExternalObject.Companion.EVENT_NAME_ON_MESSAGE_PRINTED
import com.example.samplemodule.BasicExternalObject.Companion.NAME
import com.genexus.android.core.actions.ExternalObjectEvent
import com.genexus.android.core.base.services.Services

class BasicExternalObjectOffline {
	companion object {
		@JvmStatic
		fun hello() {
			Services.Messages.showMessage("Hello World!")
		}

		@JvmStatic
		fun message(text: String) {
			Services.Messages.showMessage(text + "V1")
		}

		@JvmStatic
		fun addNumbers(firstAddend: Int?, secondAddend: Int?) : Int {
			return (firstAddend?:0) + (secondAddend?:0)
		}

		@JvmStatic
		fun printMessage(message: String) {
			val event = ExternalObjectEvent(NAME, EVENT_NAME_ON_MESSAGE_PRINTED)
			val params = listOf(message)
			event.fire(params)
		}
	}
}
