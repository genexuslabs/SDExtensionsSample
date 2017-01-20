package com.example.samplemodule;

import java.util.List;

import android.support.annotation.NonNull;

import com.artech.actions.ActionResult;
import com.artech.application.MyApplication;
import com.artech.externalapi.ExternalApi;
import com.artech.externalapi.ExternalApiResult;

public class BasicExternalObject extends ExternalApi {
	final static String NAME = "BasicExternalObject";
	private final static String METHOD_HELLO = "Hello";
	private final static String METHOD_MESSAGE = "Message";

	public BasicExternalObject() {
		addMethodHandler(METHOD_HELLO, 0, mMethodHello);
		addMethodHandler(METHOD_MESSAGE, 1, mMethodMessage);
	}

	private final IMethodInvoker mMethodHello = new IMethodInvoker() {
		@Override
		public @NonNull ExternalApiResult invoke(List<Object> parameters) {
			MyApplication.getInstance().showMessage(getContext().getString(com.example.genexusmodule.R.string.hello_message));
			return new ExternalApiResult(ActionResult.SUCCESS_CONTINUE);
		}
	};

	private final IMethodInvoker mMethodMessage = new IMethodInvoker() {
		@Override
		public @NonNull ExternalApiResult invoke(List<Object> parameters) {
			final String text = (String) parameters.get(0);
			MyApplication.getInstance().showMessage(text);
			return new ExternalApiResult(ActionResult.SUCCESS_CONTINUE);
		}
	};
}
