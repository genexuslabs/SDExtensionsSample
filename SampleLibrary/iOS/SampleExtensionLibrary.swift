
import GXCoreBL

@objc(SampleExtensionLibrary)
public class SampleExtensionLibrary: NSObject, GXExtensionLibraryProtocol {

	public func initializeExtensionLibrary(withContext context: GXExtensionLibraryContext) {

		GXActionExternalObjectHandler.register(BasicExternalObject.self, forExternalObjectName:BasicExternalObject.classIdentifier)
		GXControlFactory.register(controlClass: BasicUserControl.self, forUserControlType: "BasicUserControl")

	}
}