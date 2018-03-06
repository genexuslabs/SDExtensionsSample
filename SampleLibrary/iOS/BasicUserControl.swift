
import Foundation
import GXCoreBL
import GXDataLayer

@objc(BasicUserControl)
public class BasicUserControl: GXControlBaseWithLayout {

	public static let classIdentifier = "BasicUserControl"

	private var _label: UILabel!
	private var _tapCount: Int = 0

	struct Constants {
		// Methods
		static let METHOD_SET_NAME  = "setname"
		static let METHOD_SET_ONTAP = "ontap"
		// Sample labels
		static let HELLO_MESSAGE = "Hello!"
		static let WELCOME_MESSAGE = "Hello %@!"
	}

	// MARK: - Overrides
	
	override public func loadContentViews(withContentFrame contentFrame: CGRect, intoContainerView containerView: UIView) {
		// Create a Label
		_label = UILabel(frame: contentFrame)
		_label.textAlignment = .center
		_label.text = Constants.HELLO_MESSAGE

		// Recognize the tap event
		let tapGesture: UITapGestureRecognizer = UITapGestureRecognizer(target: self, action: #selector(self.tapResponse(recognizer:)))
		tapGesture.numberOfTapsRequired = 1
		_label.addGestureRecognizer(tapGesture)
		_label.isUserInteractionEnabled = true

		containerView.addSubview(_label)
	}

	override public func layoutContentViews(withContentFrame contentFrame: CGRect) {
		_label.frame = contentFrame
	}

	override public func hasAction(forControlEvent eventName: String) -> Bool {
		return (eventName == Constants.METHOD_SET_NAME)
			|| (eventName == Constants.METHOD_SET_ONTAP)
			|| super.hasAction(forControlEvent: eventName)
	}

	override public func executeMethod(_ methodName: String, withParameters parameters: [Any]) {
		switch methodName {
		case Constants.METHOD_SET_NAME:
			if (parameters.count == 1) {
				if let firstParameter = GXUtilities.string(from: parameters[0]) {
					self.setName(firstParameter)
				}
			}
		// If it responds to other methods, add here
		default:
			super.executeMethod(methodName, withParameters: parameters)
		}
	}

	// MARK: - Internal

	private func setName(_ name: String) {
		_label.text = String(format: Constants.WELCOME_MESSAGE, name)
	}

	@objc func tapResponse(recognizer: UITapGestureRecognizer) {
		// Update local variable
		_tapCount += 1
		
		// Dispatch the User control OnTap event
		let parms = [ NSNumber(value: _tapCount) ]
		self.fireControlEvent(Constants.METHOD_SET_ONTAP, userInterfaceContext: nil, withEntityData: nil, parameters: parms)
	}
}
