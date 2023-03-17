
import Foundation
import GXCoreBL
import GXDataLayer

@objc(BasicUserControl)
public class BasicUserControl: GXControlBaseWithLayout {

	public static let classIdentifier = "BasicUserControl"

	private var label: UILabel!
	private var tapCount: Int = 0

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
		label = UILabel(frame: contentFrame)
		label.textAlignment = .center
		label.text = Constants.HELLO_MESSAGE
		label.textColor = #colorLiteral(red: 0.203922, green: 0.2, blue: 0.305882, alpha: 1)
		
		// Recognize the tap event
		let tapGesture: UITapGestureRecognizer = UITapGestureRecognizer(target: self, action: #selector(self.tapResponse(recognizer:)))
		tapGesture.numberOfTapsRequired = 1
		label.addGestureRecognizer(tapGesture)
		label.isUserInteractionEnabled = true

		containerView.addSubview(label)
	}

	override public func layoutContentViews(withContentFrame contentFrame: CGRect) {
		label.frame = contentFrame
	}

	override public func hasAction(forControlEvent eventName: String) -> Bool {
		return (eventName == Constants.METHOD_SET_NAME)
			|| (eventName == Constants.METHOD_SET_ONTAP)
			|| super.hasAction(forControlEvent: eventName)
	}
	
	override public func executeMethod(_ methodName: String, withParameters parameters: [Any]) -> Any? {
		switch methodName {
		case Constants.METHOD_SET_NAME:
			if (parameters.count == 1) {
				if let firstParameter = GXUtilities.string(from: parameters[0]) {
					setName(firstParameter)
				}
			}
			return nil
		// If it responds to other methods, add here
		default:
			return super.executeMethod(methodName, withParameters: parameters)
		}
	}
	
	// MARK: - Internal

	private func setName(_ name: String) {
		label.text = String(format: Constants.WELCOME_MESSAGE, name)
	}

	@objc func tapResponse(recognizer: UITapGestureRecognizer) {
		// Update local variable
		tapCount += 1
		
		// Dispatch the User control OnTap event
		let parms = [ NSNumber(value: tapCount) ]
		self.fireControlEvent(Constants.METHOD_SET_ONTAP, userInterfaceContext: nil, withEntityData: nil, parameters: parms)
	}
}
