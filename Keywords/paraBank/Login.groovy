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

public class Login {
	@Keyword
	def login(String username, String password) {
		WebUI.openBrowser(GlobalVariable.BaseURL)
		WebUI.navigateToUrl(GlobalVariable.BaseURL)

		// Validate user is redirected correctly to Home Page
		assert WebUI.getUrl() == GlobalVariable.BaseURL
		WebUI.maximizeWindow()
		WebUI.waitForPageLoad(10)
		//Insert Username captured from latest registration
		WebUI.setText(findTestObject('Object Repository/HomePage/Login/Login_Username'), username)
		//Insert Password From Global Variables
		WebUI.setText(findTestObject('Object Repository/HomePage/Login/Login_Password'), password)
		WebUI.click(findTestObject('Object Repository/HomePage/Login/Login_Button'))
		def expectedUrl= 'https://parabank.parasoft.com/parabank/overview.htm'
		def actualUrl= WebUI.getUrl()
		
		assert expectedUrl==expectedUrl
		println("User is redirected Correctly")
	}
}
