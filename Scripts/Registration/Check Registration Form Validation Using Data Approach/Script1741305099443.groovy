import internal.GlobalVariable

/*Registration is exceuted using Data Driven Approach 
 * To Ensure consistent behavior Random function is used to generate Username
 * Random Username is set in a global variable to be used in run time at login 
 * 
 * */



CustomKeywords.'paraBank.Registration.registeration'()
CustomKeywords.'paraBank.Login.login'(GlobalVariable.RandomUsername, GlobalVariable.Password)