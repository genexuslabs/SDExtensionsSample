package com.example.samplemodule;

import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.widget.TextView;

import com.artech.base.controls.IGxControlRuntime;
import com.artech.base.metadata.layout.LayoutItemDefinition;
import com.artech.controls.IGxEdit;
import com.artech.ui.Coordinator;
import com.example.genexusmodule.R;

public class BasicUserControl extends TextView implements IGxEdit,
														  IGxControlRuntime {
	private final static String METHOD_SET_NAME = "SetName";
	private String mName;

	public BasicUserControl(Context context, Coordinator coordinator, LayoutItemDefinition definition) {
		super(context);
	}

	@Override
	public String getGx_Value() {
		return mName;
	}

	@Override
	public void setGx_Value(String value) {
		setName(value);
	}

	@Override
	public String getGx_Tag() {
		return getTag().toString();
	}

	@Override
	public void setGx_Tag(String tag) {
		setTag(tag);
	}

	@Override
	public void setValueFromIntent(Intent data) {

	}

	@Override
	public boolean isEditable() {
		return false;
	}

	@Override
	public IGxEdit getViewControl() {
		return null;
	}

	@Override
	public IGxEdit getEditControl() {
		return null;
	}

	@Override
	public void setProperty(String name, Object value) {

	}

	@Override
	public Object getProperty(String name) {
		return null;
	}

	@Override
	public void runMethod(String method, List<Object> parameters) {
		if (METHOD_SET_NAME.equals(method)) {
			String name = (String) parameters.get(0);
			setName(name);
		}
	}

	public void setName(String name) {
		mName = name;
		setText(getContext().getString(R.string.welcome_message, name));
	}
}
