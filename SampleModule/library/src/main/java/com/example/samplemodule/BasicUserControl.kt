package com.example.samplemodule

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.support.v7.widget.AppCompatTextView
import android.view.View
import android.widget.TextView

import com.artech.base.controls.IGxControlRuntime
import com.artech.base.metadata.ActionDefinition
import com.artech.base.metadata.ActionParameter
import com.artech.base.metadata.layout.LayoutItemDefinition
import com.artech.controls.IGxEdit
import com.artech.ui.Coordinator
import com.example.genexusmodule.R

@SuppressLint("ViewConstructor")
class BasicUserControl(context: Context, private val mCoordinator: Coordinator, @Suppress("UNUSED_PARAMETER") definition: LayoutItemDefinition) : AppCompatTextView(context), IGxEdit, IGxControlRuntime {

    private var mName: String? = null
    private var tapCount: Int = 0

    private val mOnClickListener = OnClickListener {
        tapCount++
        runOnTapEvent()
    }

    init {
        tapCount = 0
        setOnClickListener(mOnClickListener)
    }

    override fun getGx_Value(): String? {
        return mName
    }

    override fun setGx_Value(value: String) {
        setName(value)
    }

    override fun getGx_Tag(): String {
        return tag.toString()
    }

    override fun setGx_Tag(tag: String) {
        setTag(tag)
    }

    override fun setValueFromIntent(data: Intent) {

    }

    override fun isEditable(): Boolean {
        return false
    }

    override fun getViewControl(): IGxEdit? {
        return null
    }

    override fun getEditControl(): IGxEdit? {
        return null
    }

    override fun setProperty(name: String, value: Any) {

    }

    override fun getProperty(name: String): Any? {
        return null
    }

    override fun runMethod(method: String, parameters: List<Any>) {
        if (METHOD_SET_NAME == method) {
            val name = parameters[0] as String
            setName(name)
        }
    }

    fun setName(name: String) {
        mName = name
        text = context.getString(R.string.welcome_message, name)
    }

    fun runOnTapEvent() {
        val actionDef = mCoordinator.getControlEventHandler(this, EVENT_ON_TAP)

        for (param in actionDef.eventParameters) {
            val paramName = param.valueDefinition.name
            mCoordinator.setValue(paramName, tapCount)
        }

        mCoordinator.runControlEvent(this, EVENT_ON_TAP)
    }

    companion object {
        internal val NAME = "BasicUserControl"
        private val METHOD_SET_NAME = "SetName"
        private val EVENT_ON_TAP = "OnTap"
    }
}
