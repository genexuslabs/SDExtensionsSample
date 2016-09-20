package com.example.samplemodule;

import android.content.Context;

import com.artech.externalapi.ExternalApiDefinition;
import com.artech.externalapi.ExternalApiFactory;
import com.artech.framework.GenexusModule;
import com.artech.usercontrols.UcFactory;
import com.artech.usercontrols.UserControlDefinition;

public class SampleModule implements GenexusModule {

	@Override
	public void initialize(Context context) {
		ExternalApiDefinition basicExternalObject = new ExternalApiDefinition(
				BasicExternalObject.class.getSimpleName(),
				BasicExternalObject.class
		);
		ExternalApiFactory.addApi(basicExternalObject);

		UserControlDefinition basicUserControl = new UserControlDefinition(
				BasicUserControl.class.getSimpleName(),
				BasicUserControl.class
		);
		UcFactory.addControl(basicUserControl);
	}
}
