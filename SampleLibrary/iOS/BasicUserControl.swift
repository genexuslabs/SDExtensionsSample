
import Foundation
import GXCoreBL
import GXDataLayer

public class BasicUserControl: GXControlBaseWithLayout {

	private var label: UILabel? = nil
	private var tapCount: Int = 0

	struct Constants {
		// Methods
		static let METHOD_SET_NAME  = "setname"
		static let EVENT_ONTAP = "ontap"
		// Sample labels
		static let HELLO_MESSAGE = "Hello!"
		static let WELCOME_MESSAGE = "Hello %@!"
	}

	// MARK: - Overrides
	
	override public func loadContentViews(withContentFrame contentFrame: CGRect, intoContainerView containerView: UIView) {
		// Create a Label
		let label = UILabel(frame: contentFrame)
		self.label = label
		label.textAlignment = .center
		label.text = currentLabelText
		label.textColor = .gxLabel
		
		// Recognize the tap event
		let tapGesture: UITapGestureRecognizer = UITapGestureRecognizer(target: self, action: #selector(Self.tapResponse(recognizer:)))
		tapGesture.numberOfTapsRequired = 1
		label.addGestureRecognizer(tapGesture)
		label.isUserInteractionEnabled = true

		containerView.addSubview(label)
	}
	
	public override func unloadView() {
		super.unloadView()
		label = nil
	}

	override public func layoutContentViews(withContentFrame contentFrame: CGRect) {
		label?.frame = contentFrame
	}
	
	override public func executeMethod(_ methodName: String, withParameters parameters: [Any]) -> Any? {
		switch methodName {
		case Constants.METHOD_SET_NAME:
			guard parameters.count == 1 else {
				GXFoundationServices.loggerService()?.logMessage("Method \(methodName) expects 1 parameter",
																 for: .general,
																 with: .error,
																 logToConsole: true)
				return nil
			}
			guard let firstParameter = GXUtilities.string(from: parameters[0]) else {
				GXFoundationServices.loggerService()?.logMessage("Method \(methodName) expects first parameter to be a String",
																 for: .general,
																 with: .error,
																 logToConsole: true)
				return nil
			}
			setName(firstParameter)
			return nil
			// If it responds to other methods, add here
		default:
			return super.executeMethod(methodName, withParameters: parameters)
		}
	}
	
	// MARK: - Internal
	
	private var name: String? = nil {
		didSet {
			label?.text = currentLabelText
		}
	}
	
	private var currentLabelText: String {
		if let name = name {
			return String(format: Constants.WELCOME_MESSAGE, name)
		}
		else {
			return Constants.HELLO_MESSAGE
		}
	}

	private func setName(_ name: String) {
		self.name = name
		label?.text = String(format: Constants.WELCOME_MESSAGE, name)
	}

	@objc func tapResponse(recognizer: UITapGestureRecognizer) {
		// Update local variable
		tapCount += 1
		
		// Dispatch the User control OnTap event
		let parms = [ NSNumber(value: tapCount) ]
		self.fireControlEvent(Constants.EVENT_ONTAP, userInterfaceContext: nil, withEntityData: nil, parameters: parms)
	}
}
