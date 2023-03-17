
import Foundation
import GXCoreBL
import GXCoreUI
import GXDataLayer
import GXStandardClasses

// MARK: - To be called from a User Event

@objc(BasicExternalObject)
public class BasicExternalObject: GXActionExternalObjectHandler {

	public static let classIdentifier = "BasicExternalObject"

	override public class func handleActionExecutionUsingMethodHandlerSelectorNamePrefix() -> Bool {
		return true
	}

	//MARK: - External object methods: Message and Hello

	@objc public func gxActionExObjMethodHandler_Message(_ parameters: [Any]) {
		if let error = self.validateNumber(ofParametersReceived: UInt(parameters.count), expected: 1) {
			self.onFinishedExecutingWithError(error)
			return
		}
		
		guard let message = self.stringParameter(self.actionDescParametersArray![0], fromValue: parameters[0]) else {
			self.onFinishedExecutingWithDefaultError()
			return
		}
		
		ToastMessage.showToast(message: message)
		self.onFinishedExecutingWithSuccess()
    }

	@objc public func gxActionExObjMethodHandler_Hello() {
		let hello = "Hello World!"
		ToastMessage.showToast(message: hello)
		self.onFinishedExecutingWithSuccess()
	}
	
}

// MARK: - Offline support

@objc(BasicExternalObjectOffline)
public class BasicExternalObjectOffline: GXExternalObjectBase {
	
	// MARK: Overrides
	
	override public var externalObjectName: String {
		return "BasicExternalObject"
	}
	
	// MARK: External object methods
	
	@objc(hello)
	public class func hello() {
		ToastMessage.showToast(message: "Hello World!")
	}
	
	@objc(message:)
	public class func message(_ text: String) {
		ToastMessage.showToast(message: text)
	}
	
}

// MARK: - Toast implementation

fileprivate class ToastMessage {
	static func showToast(message : String) {
		// Ideas from https://stackoverflow.com/questions/31540375/how-to-toast-message-in-swift
		DispatchQueue.main.async {
			// First get the controller and it's view
			if let rootController = GXExecutionEnvironmentHelper.keyWindow?.rootViewController {
				// Build the Toast sample
				let size = rootController.view.frame.size
				let toastLabel = UILabel(frame: CGRect(x: (size.width)/2 - 75, y: (size.height) - 100, width: 150, height: 35))
				toastLabel.backgroundColor = UIColor.black.withAlphaComponent(0.6)
				toastLabel.textColor = UIColor.white
				toastLabel.textAlignment = .center;
				toastLabel.font = UIFont(name: "Montserrat-Light", size: 12.0)
				toastLabel.text = message
				toastLabel.alpha = 1.0
				toastLabel.layer.cornerRadius = 10;
				toastLabel.clipsToBounds  =  true
				rootController.view.addSubview(toastLabel)
				UIView.animate(withDuration: 4.0, delay: 0.1, options: .curveEaseOut, animations: {
					toastLabel.alpha = 0.0
				}, completion: {(isCompleted) in
					toastLabel.removeFromSuperview()
				})
			}
		}
	}
}
