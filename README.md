# Android Extensions Sample
This repository contains samples of a `User Control` and a `External Object` for the Android generator.

## Instructions

### Deploy the Genexus Android Module to Genexus
- Set the environment variable `GENEXUS_HOME` to your Genexus installation directory.
- Run `gradlew uploadArchives` from the `ModuleSample` directory.
- Modify the following templates from the Android generator:
    - In the file `%GENEXUS_HOME%\Android\Templates\ApplicationProject\build.gradle`, add `compile 'com.example:library:1.0'` to the `dependencies` block.
    - In the file `%GENEXUS_HOME%\Android\Templates\src\main\java\com\genexus\namespace\MainApplication.java`, add `registerModule(new com.example.samplemodule.SampleModule());` before the call to `UserControls.initializeUserControls();`.

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

