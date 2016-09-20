package com.example.samplemodule;

import android.content.Context;

import com.artech.externalapi.ExternalApiFactory;
import com.artech.framework.GenexusModule;

public class SampleModule implements GenexusModule {

	@Override
	public void initialize(Context context) {
		ExternalApiFactory.addApi(BasicEO.class);
	}
}
