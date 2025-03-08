package paraBank

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testdata.TestDataFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable

public class Registration {
	def registerationHeader = 'Signing up is easy!'

	@Keyword
	def registeration() {
		WebUI.openBrowser('')
		WebUI.navigateToUrl(GlobalVariable.BaseURL)
		WebUI.maximizeWindow()
		WebUI.waitForPageLoad(5)

		def testData = TestDataFactory.findTestData('Registration')
		int rowCount = testData.getRowNumbers()

		for (int row = 1; row <= rowCount; row++) {
			WebUI.comment("Executing test case #${row}")

			WebUI.navigateToUrl(GlobalVariable.BaseURL)
			WebUI.waitForPageLoad(5)
			WebUI.click(findTestObject('Object Repository/HomePage/RegisterLink'))

			WebUI.verifyElementPresent(findTestObject('Object Repository/HomePage/RegisterationPanel'), 3, FailureHandling.STOP_ON_FAILURE)
			WebUI.verifyTextPresent(registerationHeader, false, FailureHandling.STOP_ON_FAILURE)

			String firstName = testData.getValue('firstName', row) ?: ''
			String lastName = testData.getValue('lastName', row) ?: ''
			String address = testData.getValue('address', row) ?: ''
			String city = testData.getValue('city', row) ?: ''
			String state = testData.getValue('state', row) ?: ''
			String zipCode = testData.getValue('zipCode', row) ?: ''
			String phone = testData.getValue('phone', row) ?: ''
			String ssn = testData.getValue('ssn', row) ?: ''
			String username = testData.getValue('username', row) ?: ''
			String password = testData.getValue('password', row) ?: ''
			String confirmPassword = testData.getValue('confirmPassword', row) ?: ''
			String verifyingText = testData.getValue('verifyingText', row) ?: ''

			if (!firstName.isEmpty()) WebUI.setText(findTestObject('Object Repository/HomePage/FirstName'), firstName)
			if (!lastName.isEmpty()) WebUI.setText(findTestObject('Object Repository/HomePage/LastName'), lastName)
			if (!address.isEmpty()) WebUI.setText(findTestObject('Object Repository/HomePage/Address'), address)
			if (!city.isEmpty()) WebUI.setText(findTestObject('Object Repository/HomePage/City'), city)
			if (!state.isEmpty()) WebUI.setText(findTestObject('Object Repository/HomePage/State'), state)
			if (!zipCode.isEmpty()) WebUI.setText(findTestObject('Object Repository/HomePage/ZipCode'), zipCode)
			if (!phone.isEmpty()) WebUI.setText(findTestObject('Object Repository/HomePage/PhoneNumber'), phone)
			if (!ssn.isEmpty()) WebUI.setText(findTestObject('Object Repository/HomePage/SSN'), ssn)

			String randomData = RandomDataGenerator.generateRandomData()
			GlobalVariable.RandomUsername = randomData+username
			if (!username.isEmpty()) {
				WebUI.setText(findTestObject('Object Repository/HomePage/Username'), GlobalVariable.RandomUsername)
			}

			if (!password.isEmpty()) WebUI.setText(findTestObject('Object Repository/HomePage/Password'), password)
			if (!confirmPassword.isEmpty()) WebUI.setText(findTestObject('Object Repository/HomePage/ConfirmPassword'), confirmPassword)

			WebUI.comment("Form filled for test case #${row}")

			WebUI.click(findTestObject('Object Repository/HomePage/RegisterButton'))

			boolean allFieldsInserted = !firstName.isEmpty() && !lastName.isEmpty() &&
					!address.isEmpty() && !city.isEmpty() &&
					!state.isEmpty() && !zipCode.isEmpty() &&
					!phone.isEmpty() && !ssn.isEmpty() &&
					!username.isEmpty() && !password.isEmpty() &&
					!confirmPassword.isEmpty()

			if (allFieldsInserted) {
				WebUI.comment("All fields inserted. Checking success message.")

				String expectedSuccessMessage = "Welcome " + GlobalVariable.RandomUsername + "\nYour account was created successfully. You are now logged in."
				boolean isSuccess = WebUI.verifyTextPresent(expectedSuccessMessage, false, FailureHandling.CONTINUE_ON_FAILURE)

				if (isSuccess) {
					WebUI.comment("Test Passed: Registration successful.")
				} else {
					WebUI.takeScreenshot()
					WebUI.comment("Test Failed: Expected success message not found.")
				}
			} else {
				WebUI.comment("Some fields are missing. Verifying expected error message.")

				boolean isError = WebUI.verifyTextPresent(verifyingText, false, FailureHandling.CONTINUE_ON_FAILURE)
				if (isError) {
					WebUI.comment("Error message displayed: " + verifyingText)
					WebUI.click(findTestObject('Object Repository/HomePage/RegisterLink')) // Click register link again only if needed
				} else {
					WebUI.comment("Unexpected issue occurred, error message not found.")
				}
			}

			// **Force stop execution after scenario 12**
			if (row == 12) {
				WebUI.comment("Test execution completed after scenario 12. Stopping test.")
				WebUI.closeBrowser()

				// System.exit(0)  // **Forcefully stops execution**
			}
		}

		WebUI.comment("All test cases executed. Closing browser.")
	}
}
