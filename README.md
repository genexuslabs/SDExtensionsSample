# Native Mobile Extension Sample
This sample contains:
- A `User Control` definition.
- An `External Object` definition.
- An `Android Library` project of a `GeneXus Module` that implements the two previously defined extensions.
- An `GeneXus Library` definition which declares the `Android Library` which implements the `User Control` and `External Object` in Android and its corresponding `GeneXus Module` name.
- An implementation for iOS for the User Control and External Object.
- An app that lets you try out both extensions.

## Requirements
- GeneXus (latest upgrade is recommended)
- JDK and Android SDK for Android generator
- MAC machine for iOS generator

For more details see:
- [Android Requirements for GeneXus](http://wiki.genexus.com/commwiki/servlet/wiki?14449).
- [iOS Requirements for GeneXus](https://wiki.genexus.com/commwiki/servlet/wiki?19478).

## Instructions

### Import the GeneXus Library definition to GeneXus
- Copy the `SampleLibrary` directory to the `Libraries` directory in your GeneXus installation.

### Import the User Control definition in GeneXus
- Copy the `BasicUserControl` directory to the `UserControls` directory in your GeneXus installation.
- Run `genexus.exe /install` in a command line in the Genexus installation directory.

### Import the External Object definition in GeneXus
- Open GeneXus and open the KB to work with.
- Import the _BasicExternalObject_ definition from `BasicExternalObject\BasicExternalObject.xpz`.

### Import the sample app (optional).
To try out the _BasicUserControl_ and _BasicExternalObject_ samples you may wish to import the sample app from `SampleApp\BasicSample.xpz`.

## Android specific

### Build and Deploy the GeneXus Android Module to GeneXus
- Set the following system environment variables:
    - `GENEXUS_REPO` to GeneXus Android Maven repository located in `Android\m2repository` inside your GeneXus installation (e.g. `file:///C:/path/to/GX/Android/m2Repository`, making sure it starts with the `file` scheme).
    - `ANDROID_HOME` to your Android SDK directory.
- Run the command `gradlew publishDebugPublicationToInternalRepository` from the `SampleModule` directory.

### Considerations
- Take into account that this project uses the latest `FlexibleClient` version installed at `GENEXUS_REPO`. If you need to work with a specific one, you will have to replace it in [library/build.gradle](https://github.com/genexuslabs/SDExtensionsSample/blob/master/SampleModule/library/build.gradle) file.

## Further reading

### User Controls
- [User Control definition file syntax](http://wiki.genexus.com/commwiki/servlet/wiki?13309)
- [User Control properties file syntax](http://wiki.genexus.com/commwiki/servlet/wiki?27179)
- [External Objects for Smart Devices](https://wiki.genexus.com/commwiki/servlet/wiki?17880)

### External Objects
- [External Object definition](http://wiki.genexus.com/commwiki/servlet/wiki?6148)

## License
This project is licensed under the MIT License. See the [LICENSE](LICENSE.txt) file for more details.
