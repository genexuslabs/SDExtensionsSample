package com.example.samplemodule;

import java.util.List;

import com.artech.application.MyApplication;
import com.artech.externalapi.ExternalApi;

public class BasicExternalObject extends ExternalApi {
	private static final String METHOD_HELLO = "Hello";
	private static final String METHOD_MESSAGE = "Message";

	public BasicExternalObject() {
		addSimpleMethodHandler(METHOD_HELLO, 0, mMethodHello);
		addSimpleMethodHandler(METHOD_MESSAGE, 1, mMethodMessage);
	}

	private final ISimpleMethodInvoker mMethodHello = new ISimpleMethodInvoker() {
		@Override
		public Object invoke(List<Object> parameters) {
			MyApplication.getInstance().showMessage(getContext().getString(com.example.genexusmodule.R.string.hello_message));
			return null;
		}
	};

	private final ISimpleMethodInvoker mMethodMessage = new ISimpleMethodInvoker() {
		@Override
		public Object invoke(List<Object> parameters) {
			final String text = (String) parameters.get(0);
			MyApplication.getInstance().showMessage(text);
			return null;
		}
	};
}
