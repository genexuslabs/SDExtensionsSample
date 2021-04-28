package com.example.samplemodule

import com.artech.base.services.Services

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
