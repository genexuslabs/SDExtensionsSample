import UIKit

/// This UIViewController receives 2 numbers and allows editing them before returning the sum with a completion block
class UIAddViewController: UIViewController {
	
	// The information that we receive in the init
	private var firstNumber: Int
	private var secondNumber: Int
	var completionBlock: ((Result<Int, NSError>) -> Void)?
	
	// The UI components
	private var firstAddends = UITextField()
	private var secondAddends = UITextField()
	private let sumButton = UIButton(type: .system)
	
	private let padding: CGFloat = 40
	
	/// In the initialization, we require the two numbers and the block of code that we are executing to communicate this to the parent class
	init(firstNumber: Int, secondNumber: Int) {
		self.firstNumber = firstNumber
		self.secondNumber = secondNumber
		super.init(nibName: nil, bundle: nil)
	}
	
	required init?(coder: NSCoder) {
		fatalError("init(coder:) has not been implemented")
	}
	
	deinit {
		// During deserialization, we invoke the completionBlock if it's not nil (If it's nil, it means it has been called previously)
		completionBlock?(.failure(NSError.userCancelledError()))
	}
	
	override func viewDidLoad() {
		setUpView()
	}
	
	/// This function sets the UI components' properties, such as constraints, colors, texts, etc.
	private func setUpView(){
		func setUpTextField(textField: UITextField, placeholder placeholderText: String, defautValue value: String){
			textField.backgroundColor = .systemPurple.withAlphaComponent(0.2)
			textField.layer.cornerRadius = 10
			textField.layer.masksToBounds = true
			textField.textAlignment = .center
			textField.keyboardType = .numberPad
			textField.leftViewMode = .always
			let placeholder = UILabel()
			placeholder.text = placeholderText
			textField.leftView = placeholder
			textField.text = value
		}
		
		view.backgroundColor = .black
		
		setUpTextField(textField: firstAddends, placeholder:" Number 1:", defautValue: String(firstNumber))
		view.addSubview(firstAddends)
		firstAddends.translatesAutoresizingMaskIntoConstraints = false
		firstAddends.topAnchor.constraint(equalTo: view.safeAreaLayoutGuide.topAnchor, constant: padding).isActive = true
		firstAddends.leadingAnchor.constraint(equalTo: view.leadingAnchor, constant: padding).isActive = true
		firstAddends.trailingAnchor.constraint(equalTo: view.trailingAnchor, constant: -padding).isActive = true
		firstAddends.heightAnchor.constraint(equalToConstant: 50).isActive = true
		
		setUpTextField(textField: secondAddends, placeholder:" Number 2:", defautValue: String(secondNumber))
		view.addSubview(secondAddends)
		secondAddends.translatesAutoresizingMaskIntoConstraints = false
		secondAddends.topAnchor.constraint(equalTo: firstAddends.bottomAnchor, constant: padding).isActive = true
		secondAddends.leadingAnchor.constraint(equalTo: firstAddends.leadingAnchor).isActive = true
		secondAddends.trailingAnchor.constraint(equalTo: firstAddends.trailingAnchor).isActive = true
		secondAddends.heightAnchor.constraint(equalToConstant: 50).isActive = true
		
		view.addSubview(sumButton)
		sumButton.translatesAutoresizingMaskIntoConstraints = false
		sumButton.topAnchor.constraint(equalTo: secondAddends.bottomAnchor, constant: padding).isActive = true
		sumButton.centerXAnchor.constraint(equalTo: view.centerXAnchor).isActive = true
		sumButton.widthAnchor.constraint(equalToConstant: 100).isActive = true
		sumButton.heightAnchor.constraint(equalToConstant: 40).isActive = true
		
		sumButton.setTitle("Sum", for: .normal)
		sumButton.backgroundColor = .systemPurple
		sumButton.setTitleColor(.white, for: .normal)
		sumButton.layer.cornerRadius = 20
		sumButton.addTarget(self, action: #selector(didPressSumButton(_:)), for: .primaryActionTriggered)
	}
	
	@objc func didPressSumButton(_ sender: UIButton) {
		// Validating if the firstAddends is text and if the text is an integer
		guard let number1Text = self.firstAddends.text,
			  let number1 = Int(number1Text) else {
			self.completionBlock?(.failure(NSError.defaultGXError(withLocalizedDescription: "The first number you entered was invalid")))
			self.completionBlock = nil
			return
		}
		
		// Validating if the secondAddends is text and if the text is an integer
		guard let number2Text = self.secondAddends.text,
			  let number2 = Int(number2Text) else {
			self.completionBlock?(.failure(NSError.defaultGXError(withLocalizedDescription: "The second number you entered was invalid")))
			self.completionBlock = nil
			return
		}
		
		// If both firstAddends and secondAddends are integers, then make the sum and execute the completionBlock with success
		let result = number1 + number2
		self.completionBlock?(.success(result))
		self.completionBlock = nil
	}
}
