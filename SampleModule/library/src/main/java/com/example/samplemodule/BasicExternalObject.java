package com.example.samplemodule;

import java.util.List;

import androidx.annotation.NonNull;

import com.artech.actions.ApiAction;
import com.artech.base.services.Services;
import com.artech.externalapi.ExternalApi;
import com.artech.externalapi.ExternalApiResult;

public class BasicExternalObject extends ExternalApi {
	final static String NAME = "BasicExternalObject";
	private final static String METHOD_HELLO = "Hello";
	private final static String METHOD_MESSAGE = "Message";

	public BasicExternalObject(ApiAction action) {
		super(action);
		addMethodHandler(METHOD_HELLO, 0, mMethodHello);
		addMethodHandler(METHOD_MESSAGE, 1, mMethodMessage);
	}

	private final IMethodInvoker mMethodHello = new IMethodInvoker() {
		@Override
		public @NonNull ExternalApiResult invoke(List<Object> parameters) {
			Services.Messages.showMessage(getContext().getString(com.example.genexusmodule.R.string.hello_message));
			return ExternalApiResult.SUCCESS_CONTINUE;
		}
	};

	private final IMethodInvoker mMethodMessage = new IMethodInvoker() {
		@Override
		public @NonNull ExternalApiResult invoke(List<Object> parameters) {
			final String text = (String) parameters.get(0);
			Services.Messages.showMessage(text);
			return ExternalApiResult.SUCCESS_CONTINUE;
		}
	};
}
