package paraBank

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows

import internal.GlobalVariable

public class ValidRegistration {

	String registerationHeader = 'Signing up is easy!'

	@Keyword
	def registeration(String firstName,String lastName,String address,String city,String state,String zipCode,String phone,String ssn,String username,String password,String confirmPassword,String verifyingText,String scenario) {
		WebUI.openBrowser('')
		WebUI.navigateToUrl(GlobalVariable.BaseURL)
		WebUI.maximizeWindow()
		WebUI.waitForPageLoad(5)





		WebUI.click(findTestObject('Object Repository/HomePage/RegisterLink'))

		WebUI.verifyElementPresent(findTestObject('Object Repository/HomePage/RegisterationPanel'), 3, FailureHandling.STOP_ON_FAILURE)
		WebUI.verifyTextPresent(registerationHeader, false, FailureHandling.STOP_ON_FAILURE)



		WebUI.setText(findTestObject('Object Repository/HomePage/FirstName'), firstName)
		WebUI.setText(findTestObject('Object Repository/HomePage/LastName'), lastName)
		WebUI.setText(findTestObject('Object Repository/HomePage/Address'), address)
		WebUI.setText(findTestObject('Object Repository/HomePage/City'), city)
		WebUI.setText(findTestObject('Object Repository/HomePage/State'), state)
		WebUI.setText(findTestObject('Object Repository/HomePage/ZipCode'), zipCode)
		WebUI.setText(findTestObject('Object Repository/HomePage/PhoneNumber'), phone)
		WebUI.setText(findTestObject('Object Repository/HomePage/SSN'), ssn)

		String randomData = RandomDataGenerator.generateRandomData()
		GlobalVariable.RandomUsername = randomData+username

		WebUI.setText(findTestObject('Object Repository/HomePage/Username'), GlobalVariable.RandomUsername)


		WebUI.setText(findTestObject('Object Repository/HomePage/Password'), password)
		WebUI.setText(findTestObject('Object Repository/HomePage/ConfirmPassword'), confirmPassword)



		WebUI.click(findTestObject('Object Repository/HomePage/RegisterButton'))




		WebUI.comment("All fields inserted. Checking success message.")

		String expectedSuccessMessage = "Welcome " + GlobalVariable.RandomUsername + "\nYour account was created successfully. You are now logged in."
		boolean isSuccess = WebUI.verifyTextPresent(expectedSuccessMessage, false, FailureHandling.CONTINUE_ON_FAILURE)

		if (isSuccess) {
			WebUI.comment("Test Passed: Registration successful.")
		} else {
			WebUI.takeScreenshot()
			WebUI.comment("Test Failed: Expected success message not found.")
		}



		WebUI.closeBrowser()
	}
}





