package com.example.samplemodule

import com.genexus.android.core.base.services.Services

class BasicExternalObjectOffline {
	companion object {
		@JvmStatic
		fun hello() {
			Services.Messages.showMessage("Hello World!")
		}

		@JvmStatic
		fun message(text: String) {
			Services.Messages.showMessage(text)
		}
	}
}
