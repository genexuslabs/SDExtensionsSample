# Smart Devices Extension Sample
This sample contains:
- A `User Control` definition.
- An `External Object` definition.
- An `Android Library` project of a `GeneXus Module` that implements the two previously defined extensions.
- An `GeneXus Library` definition which declares the `Android Library` which implements the `User Control` and `External Object` in Android and its corresponding `GeneXus Module` name.
- An implementation for iOS for the User Control and External Object.
- An app that lets you try out both extensions.

## Requirements
- GeneXus 16 (latest upgrade is recommended)
- JDK and Android SDK for Android generator
- MAC machine for iOS generator

For more details see:
- [Android Requirements for GeneXus 16](http://wiki.genexus.com/commwiki/servlet/wiki?14449).
- [iOS Requirements for GeneXus 16](https://wiki.genexus.com/commwiki/servlet/wiki?19478).

## Instructions

### Import the GeneXus Library definition to GeneXus
- Copy the `SampleLibrary` directory to the `Libraries` directory in your GeneXus installation.

### Import the User Control definition in GeneXus
- Set the following environment variable:
    - `GENEXUS_REPO` to GeneXus Android Maven repository located in `Android\m2repository` inside your GeneXus installation (e.g. file:///C/path/to/GX/Android/m2Repository).
- Copy the `BasicUserControl` directory to the `UserControls` directory in your GeneXus installation.
- Run `genexus.exe /install`.

### Import the External Object definition in GeneXus
- Open GeneXus and open the KB to work with.
- Import the _BasicExternalObject_ definition from `BasicExternalObject\BasicExternalObject.xpz`.

### Import the sample app (optional).
To try out the _BasicUserControl_ and _BasicExternalObject_ samples you may wish to import the sample app from `SampleApp\BasicSample.xpz`.

## Android specific

### Deploy the GeneXus Android Module to GeneXus
- Set the following environment variable:
    - `ANDROID_HOME` to your Android SDK directory.
- Run the command `gradlew uploadArchives` from the `SampleModule` directory.

## Further reading

### User Controls
- [User Control definition file syntax](http://wiki.genexus.com/commwiki/servlet/wiki?13309)
- [User Control properties file syntax](http://wiki.genexus.com/commwiki/servlet/wiki?27179)
- [External Objects for Smart Devices](https://wiki.genexus.com/commwiki/servlet/wiki?17880)

### External Objects
- [External Object definition](http://wiki.genexus.com/commwiki/servlet/wiki?6148)

## License
This project is licensed under the MIT License. See the [LICENSE](LICENSE.txt) file for more details.
