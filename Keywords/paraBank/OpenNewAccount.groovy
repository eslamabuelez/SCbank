package paraBank

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI




public class OpenNewAccount {
	
	def static accountNumber
	def static opennedAccountVerifyingText= 'Account Opened!'
	def  actualVerifyingText
	
	@Keyword
	def static openNewAccount() {
		
		WebUI.click(findTestObject('Object Repository/OpenNewAccount/OpenNewAccountLink'))
		WebUI.waitForElementClickable(findTestObject('Object Repository/OpenNewAccount/OpenNewAccountButton'), 5)
		WebUI.refresh()
		WebUI.delay(6)
		WebUI.click(findTestObject('Object Repository/OpenNewAccount/OpenNewAccountButton'))
		
		println("Open new account button is clicked")
		WebUI.delay(5)
		WebUI.waitForElementPresent(findTestObject('Object Repository/OpenNewAccount/OpenAccountResult'), 5)
		String actualVerifyingText = WebUI.getText(findTestObject('Object Repository/OpenNewAccount/OpenAccountResult'), FailureHandling.STOP_ON_FAILURE)
		assert  opennedAccountVerifyingText== actualVerifyingText
		
		WebUI.waitForElementPresent(findTestObject('Object Repository/OpenNewAccount/AccountNumber'), 3)
		accountNumber= WebUI.getText(findTestObject('Object Repository/OpenNewAccount/AccountNumber'), FailureHandling.STOP_ON_FAILURE)
		println("Opened Account ID is: "+accountNumber)
		return accountNumber
	}
}
