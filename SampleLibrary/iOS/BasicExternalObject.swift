
import Foundation
import GXCoreBL
import GXCoreUI
import GXDataLayer
import GXStandardClasses

// MARK: - To be called from a User Event
/// This is an external object with examples of different types of methods
public class BasicExternalObject: GXActionExternalObjectHandler {
	
	/// This flag enables defining the methods using just a prefix (gxActionExObjMethodHandler_<MethodName>)
	override public class func handleActionExecutionUsingMethodHandlerSelectorNamePrefix() -> Bool {
		return true
	}
	
	//MARK: - External object methods: Hello, Message, UIAdd and PrintMessage
	
	/// That's a simple method with no parameters
	@objc public func gxActionExObjMethodHandler_Hello() {
		// Showing the Toast with a hardcoded message
		ToastMessage.showToast(message: "Hello World!",
							   gxModel: self.actionDesc.gxModel,
							   uiContext: self.userInterfaceContext)
		
		// Finishing the execution of the method
		self.onFinishedExecutingWithSuccess()
	}
	
	/// That's a simple method with a parameter
	@objc public func gxActionExObjMethodHandler_Message(_ parameters: [Any]) {
		// Validating if we receive 1 parameter
		if let error = self.validateNumber(ofParametersReceived: UInt(parameters.count), expected: 1) {
			self.onFinishedExecutingWithError(error)
			return
		}
		
		
		// Trying to retrieve the parameter as a string
		guard let message = self.stringParameter(self.actionDescParametersArray![0], fromValue: parameters[0]) else {
			let error = self.invalidParameterErrorValid(forMethod: nil, at: 0, developerDescription: "Message parameter must be a String")
			self.onFinishedExecutingWithError(error)
			return
		}
		
		
		// Showing the Toast with the message
		ToastMessage.showToast(message: message,
							   gxModel: self.actionDesc.gxModel,
							   uiContext: self.userInterfaceContext)
		
		// Finishing the execution of the method
		self.onFinishedExecutingWithSuccess()
	}
	
	/// That's a method with 2 parameters and returns a value
	@objc public func gxActionExObjMethodHandler_AddNumbers(_ parameters: [Any]) {
		// Validating if we receive exactly 2 parameters
		if let error = self.validateNumber(ofParametersReceived: UInt(parameters.count), expected: 2) {
			self.onFinishedExecutingWithError(error)
			return
		}
		
		// Trying to retrieve the parameters as a int
		let number1 = self.integerParameter(self.actionDescParametersArray![0], fromValue: parameters[0])
		let number2 = self.integerParameter(self.actionDescParametersArray![1], fromValue: parameters[1])
		
		// Calculating the sum of the two numbers
		let sum = number1 + number2
		
		// Setting the return value to the sum
		self.setReturnValue(sum)
		
		// Indicating that the operation finished successfully
		self.onFinishedExecutingWithSuccess()
	}
	
	/// That's a method that presents another UIViewController, sends different types of errors, and returns a value
	@objc public func gxActionExObjMethodHandler_UIAdd(_ parameters: [Any]) {
		// Validating if we receive 2 parameters
		if let error = self.validateNumber(ofParametersReceived: UInt(parameters.count), expected: 2) {
			self.onFinishedExecutingWithError(error)
			return
		}
		
		// Trying to retrieve the parameters as a int
		let number1 = self.integerParameter(self.actionDescParametersArray![0], fromValue: parameters[0])
		let number2 = self.integerParameter(self.actionDescParametersArray![1], fromValue: parameters[1])
		
		// Getting a presentation handler
		guard let presentationHandler = gxActionHandlerControllerPresentationHandler else {
			// Handle presentation handler not available error
			let error = NSError.defaultGXError(withDeveloperDescription: "No valid presentation handler.")
			onFinishedExecutingWithError(error)
			return
		}
		
		// Initializing UIAddViewController
		let uIAddViewController = UIAddViewController(firstNumber: number1, secondNumber: number2)
		
		// Setting the handler for success or failure cases
		uIAddViewController.completionBlock = { [weak self, weak uIAddViewController] result in
			guard let self = self else { return }
			
			// Since we no longer need the modal, we will dismiss it
			uIAddViewController?.dismiss(animated: true, completion: nil)
			
			switch result {
			case .success(let sum):
				// In the success case, returning the sum using the setReturnValue function and finishing the method execution
				self.setReturnValue(sum)
				self.onFinishedExecutingWithSuccess()
			case .failure(let error):
				// In the failure case, evaluating if it's an error to close the UIAddViewController (isUserCancelledError())
				if error.isUserCancelledError() {
					// If the user closes the UIAddViewController, we finish with success because it's an expected behavior
					self.onFinishedExecutingWithSuccess()
				} else {
					// In any other case, we finish with an error by sending the error type
					self.onFinishedExecutingWithError(error)
				}
			}
		}
		
		// Setup the preferred presentation context
		let pContext = GXPresentationContext(userInterfaceContext: userInterfaceContext)
		pContext.modal = true
		
		// Presenting the view controller
		let presented = presentationHandler.gxPresentViewController(uIAddViewController, context: pContext, completion: nil)
		if !presented {
			// Handle presentation error
			let error = NSError.defaultGXError(withDeveloperDescription: "Could not present view controller.")
			onFinishedExecutingWithError(error)
		}
	}
	
	
	/// That's a simple method that triggers an event of an external object
	@objc public func gxActionExObjMethodHandler_PrintMessage(_ parameters: [Any]) {
		// Validating if we receive 1 parameter
		if let error = self.validateNumber(ofParametersReceived: UInt(parameters.count), expected: 1) {
			self.onFinishedExecutingWithError(error)
			return
		}
		
		// Trying to retrieve the parameter as a string
		guard let message = self.stringParameter(self.actionDescParametersArray![0], fromValue: parameters[0]) else {
			let error = self.invalidParameterErrorValid(forMethod: nil, at: 0, developerDescription: "messageText parameter must be a String")
			self.onFinishedExecutingWithError(error)
			return
		}
		
		// Triggering the BasicExternalObject.OnMessagePrinted event (<ExternalObjectName>.<EventName>)
		GXActionExObjEventsHelper.dispatchExternalObjectEvent("BasicExternalObject.OnMessagePrinted", withParameters: [message])
		
		// Finishing the execution of the method
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
		DispatchQueue.gxOnMain {
			ToastMessage.showToast(message: "Hello World!")
		}
	}
	
	@objc(message:)
	public class func message(_ text: String) {
		DispatchQueue.gxOnMain {
			ToastMessage.showToast(message: text)
		}
	}
	
	@objc(addNumbers::)
	public class func addNumbers(_ fisrtAddend: Int,_ secondAddend: Int) -> Int {
		return fisrtAddend + secondAddend
	}
	
	@objc(printMessage:)
	public class func printMessage(_ message: String) {
		GXActionExObjEventsHelper.dispatchExternalObjectEvent("BasicExternalObject.OnMessagePrinted", withParameters: [message])
	}
	
}

// MARK: - Toast implementation

fileprivate class ToastMessage {
	static func showToast(message : String, gxModel: GXModel? = nil, uiContext: GXUserInterfaceContext? = nil) {
		assert(Thread.isMainThread)
		// Ideas from https://stackoverflow.com/questions/31540375/how-to-toast-message-in-swift
		// First get the controller and it's view
		let controller: UIViewController? = {
			if let controller = uiContext?.userInterfaceController {
				return controller
			}
			if let gxModel = gxModel {
				let fistModelRootController = GXExecutionEnvironmentHelper.allConnectedWindows(for: gxModel).compactMapFirst { $0.rootViewController }
				if let fistModelRootController = fistModelRootController {
					return fistModelRootController
				}
			}
			return GXExecutionEnvironmentHelper.allConnectedWindows.compactMapFirst { $0.rootViewController }
		}()
		if let controller = controller {
			// Build the Toast sample
			let size = controller.view.frame.size
			let toastLabel = UILabel(frame: CGRect(x: (size.width)/2 - 75, y: (size.height) - 100, width: 150, height: 35))
			toastLabel.backgroundColor = UIColor.black.withAlphaComponent(0.6)
			toastLabel.textColor = UIColor.white
			toastLabel.textAlignment = .center;
			toastLabel.font = UIFont(name: "Montserrat-Light", size: 12.0)
			toastLabel.text = message
			toastLabel.alpha = 1.0
			toastLabel.layer.cornerRadius = 10;
			toastLabel.clipsToBounds  =  true
			controller.view.addSubview(toastLabel)
			UIView.animate(withDuration: 4.0, delay: 0.1, options: .curveEaseOut, animations: {
				toastLabel.alpha = 0.0
			}, completion: {(isCompleted) in
				toastLabel.removeFromSuperview()
			})
		}
	}
}

