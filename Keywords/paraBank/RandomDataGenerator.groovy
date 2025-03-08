package paraBank

import com.kms.katalon.core.annotation.Keyword
import java.util.Random

public class RandomDataGenerator {

	@Keyword
	def static String generateRandomData() {
		String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
		String numbers = "0123456789"
		Random random = new Random()

		// Generate 2 random letters (A-Z)
		String letterPart = (1..1).collect { letters.charAt(random.nextInt(letters.length())) }.join('')

		// Generate 2 random digits (0-9)
		String numberPart = (1..1).collect { numbers.charAt(random.nextInt(numbers.length())) }.join('')

		// Combine both parts
		String randomData= letterPart + numberPart
		println("Random Data is :"+randomData)
		return randomData
	}
}