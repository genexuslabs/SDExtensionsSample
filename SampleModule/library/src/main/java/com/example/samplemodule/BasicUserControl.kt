package com.example.samplemodule

import android.content.Context
import android.content.Intent
import android.view.View.OnClickListener
import androidx.appcompat.widget.AppCompatTextView
import com.genexus.android.core.base.controls.IGxControlRuntime
import com.genexus.android.core.base.metadata.expressions.Expression
import com.genexus.android.core.base.metadata.layout.LayoutItemDefinition
import com.genexus.android.core.controls.IGxEdit
import com.genexus.android.core.ui.Coordinator
import com.example.genexusmodule.R

@Suppress("ViewConstructor", "UNUSED_PARAMETER")
class BasicUserControl(
		context: Context,
		private val coordinator: Coordinator,
		definition: LayoutItemDefinition?
) : AppCompatTextView(context), IGxEdit, IGxControlRuntime {

	private var name: String? = null
	private var tapCount = 0

	override fun callMethod(methodName: String, parameters: List<Expression.Value>): Expression.Value? {
		if (METHOD_SET_NAME == methodName) {
			val name = parameters[0].coerceToString()
			setName(name)
		}

		return null
	}

	private val onClickListener = OnClickListener {
		tapCount++
		runOnTapEvent()
	}

	private fun runOnTapEvent() {
		val actionDef = coordinator.getControlEventHandler(this, EVENT_ON_TAP)

		for (param in actionDef.eventParameters) {
			val paramName = param.valueDefinition.name
			coordinator.setValue(paramName, tapCount)
		}

		coordinator.runControlEvent(this, EVENT_ON_TAP)
	}

	private fun setName(name: String?) {
		this.name = name
		text = context.getString(R.string.welcome_message, name)
	}

	override fun getGxValue(): String? {
		return name
	}

	override fun setGxValue(value: String) {
		setName(value)
	}

	override fun getGxTag(): String? {
		return tag?.toString()
	}

	override fun setGxTag(tag: String) {
		setTag(tag)
	}

	override fun isEditable(): Boolean {
		return false
	}

	override fun getViewControl(): IGxEdit {
		return this
	}

	override fun getEditControl(): IGxEdit? {
		return null
	}

	override fun setValueFromIntent(data: Intent) {}

	companion object {
		const val NAME = "BasicUserControl"
		private const val METHOD_SET_NAME = "SetName"
		private const val EVENT_ON_TAP = "OnTap"
	}

	init {
		setOnClickListener(onClickListener)
	}
}