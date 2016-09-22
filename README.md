# Android Extensions Sample
This repository contains:
- `\BasicUserControl`: A sample of a `User Control` definition.
- `\BasicExternalObject`: A sample of a `External Object` definition.
- `\SampleModule`: A sample Android Library project of a `Genexus Module` that implements the two extensions previously defined.
- `\SampleApp`: A sample app that lets you try out both extensions.

## Instructions

### Deploy the Genexus Android Module to Genexus
- Set the following environment variables:
    - `GENEXUS_HOME` to your Genexus installation directory.
    - `ANDROID_HOME` to your Android SDK directory. 
- Run the command `gradlew uploadArchives` from the `SampleModule` directory.
- Modify the following templates from the Android generator:
    - Add the line `compile 'com.example:library:1.0'` inside the `dependencies` block in the file:  `%GENEXUS_HOME%\Android\Templates\ApplicationProject\build.gradle`
    - Add the line `registerModule(new com.example.samplemodule.SampleModule());` right before the call to `UserControls.initializeUserControls();` in the file:  `%GENEXUS_HOME%\Android\Templates\ApplicationProject\src\main\java\com\genexus\namespace\MainApplication.java`

### Import the User Control definition to Genexus
- Copy the `BasicUserControl` directory to `%GENEXUS_HOME%\UserControls`.
- Run `genexus.exe /install`.

### Import the External Object definition to Genexus
- Open Genexus.
- Import the _BasicExternalObject_ definition from `BasicExternalObject\BasicExternalObject.xpz`.

### Import the sample app (optional).
To try out the _BasicUserControl_ and _BasicExternalObject_ samples you may wish to import the sample app from `SampleApp\BasicSample.xpz`.

## Further reading

### User Controls
- [User Control definition file syntax](http://wiki.genexus.com/commwiki/servlet/wiki?13309)
- [User Control properties file syntax](http://wiki.genexus.com/commwiki/servlet/wiki?27179)

### External Objects
- [External Object definition](http://wiki.genexus.com/commwiki/servlet/wiki?6148)

## License
This project is licensed under the MIT License. See the [LICENSE](LICENSE.txt) file for more details.
