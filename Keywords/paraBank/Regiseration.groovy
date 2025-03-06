package paraBank

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.testdata.TestDataFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable

public class Regiseration {
    def expectedURL
    def actualURL
    def registerationHeader = 'Signing up is easy!'

    @Keyword
    def registeration() {
        WebUI.openBrowser(GlobalVariable.BaseURL)
        WebUI.navigateToUrl(GlobalVariable.BaseURL)

        // Validate user is redirected correctly to Home Page
        expectedURL = GlobalVariable.BaseURL
        actualURL = WebUI.getUrl()
        assert actualURL == expectedURL
        println("Actual URL is: " + actualURL)

        WebUI.maximizeWindow()
        WebUI.waitForPageLoad(10)

        // Click Register
        WebUI.click(findTestObject('Object Repository/HomePage/RegisterLink'))

        // Verify Registration panel is present
        WebUI.verifyElementPresent(findTestObject('Object Repository/HomePage/RegisterationPanel'), 3)
        WebUI.verifyTextPresent(registerationHeader, false)

        // Load test data
        def testData = TestDataFactory.findTestData('Registration')
        int rowCount = testData.getRowNumbers()

        for (int row = 1; row <= rowCount; row++) {
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
            String scenario = testData.getValue('scenario', row) ?: ''
            String verifyingText = testData.getValue('verifyingText', row) ?: ''

            // Generate a unique random string
            String randomData = RandomDataGenerator.generateRandomData()

            // Ensure proper form input handling
            if (!firstName.trim().isEmpty()) WebUI.setText(findTestObject('Object Repository/HomePage/FirstName'), firstName)
            else WebUI.comment('Skipping First Name as it is empty')

            if (!lastName.trim().isEmpty()) WebUI.setText(findTestObject('Object Repository/HomePage/LastName'), lastName)
            else WebUI.comment('Skipping Last Name as it is empty')

            if (!address.trim().isEmpty()) WebUI.setText(findTestObject('Object Repository/HomePage/Address'), address)
            else WebUI.comment('Skipping Address as it is empty')

            if (!city.trim().isEmpty()) WebUI.setText(findTestObject('Object Repository/HomePage/City'), city)
            else WebUI.comment('Skipping City as it is empty')

            if (!state.trim().isEmpty()) WebUI.setText(findTestObject('Object Repository/HomePage/State'), state)
            else WebUI.comment('Skipping State as it is empty')

            if (!zipCode.trim().isEmpty()) WebUI.setText(findTestObject('Object Repository/HomePage/ZipCode'), zipCode)
            else WebUI.comment('Skipping Zip Code as it is empty')

            if (!phone.trim().isEmpty()) WebUI.setText(findTestObject('Object Repository/HomePage/PhoneNumber'), phone)
            else WebUI.comment('Skipping Phone as it is empty')

            if (!ssn.trim().isEmpty()) WebUI.setText(findTestObject('Object Repository/HomePage/SSN'), ssn)
            else WebUI.comment('Skipping SSN as it is empty')

            // Ensure username is correctly formatted
            if (!username.trim().isEmpty()) {
                WebUI.setText(findTestObject('Object Repository/HomePage/Username'), username + randomData)
            } else {
                WebUI.comment('Skipping Username as it is empty')
            }

            if (!password.trim().isEmpty()) WebUI.setText(findTestObject('Object Repository/HomePage/Password'), password)
            else WebUI.comment('Skipping Password as it is empty')

            if (!confirmPassword.trim().isEmpty()) WebUI.setText(findTestObject('Object Repository/HomePage/ConfirmPassword'), confirmPassword)
            else WebUI.comment('Skipping Confirm Password as it is empty')

            // Optional: Add delay to observe actions
            WebUI.delay(2)
            println("Scenario is: " + scenario)

            WebUI.click(findTestObject('Object Repository/HomePage/RegisterButton'))
            WebUI.verifyTextPresent(verifyingText, false)

            WebUI.refresh()
            WebUI.delay(2)
            WebUI.click(findTestObject('Object Repository/HomePage/RegisterLink'))
        }
    }
}
