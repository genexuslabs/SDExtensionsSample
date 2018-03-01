# Android Extensions Sample
This sample contains:
- A `User Control` definition.
- An `External Object` definition.
- An `Android Library` project of a `Genexus Module` that implements the two previously defined extensions.
- An `Genexus Library` definition which declares the `Android Library` which implements the `User Control` and `External Object` in Android and its corresponding `Genexus Module` name.
- An app that lets you try out both extensions.

## Requirements
- Genexus 15 (**Upgrade 9** or later)
- JDK
- Android SDK

For more details see [Android Requirements for Genexus 15](http://wiki.genexus.com/commwiki/servlet/wiki?14449).

## Instructions

### Deploy the Genexus Android Module to Genexus
- Set the following environment variables:
    - `GENEXUS_HOME` to your Genexus installation directory.
    - `ANDROID_HOME` to your Android SDK directory.
- Run the command `gradlew uploadArchives` from the `SampleModule` directory.

### Import the User Control definition to Genexus
- Copy the `BasicUserControl` directory to `%GENEXUS_HOME%\UserControls`.
- Run `genexus.exe /install`.

### Import the Genexus Library definition to Genexus
- Copy the `SampleLibrary` directory to `%GENEXUS_HOME%\Libraries`.

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
