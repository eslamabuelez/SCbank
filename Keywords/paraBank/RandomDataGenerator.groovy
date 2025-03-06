package paraBank

import com.kms.katalon.core.annotation.Keyword

public class RandomDataGenerator {

    @Keyword
    def static String generateRandomData() {
        Random random = new Random()

        // Generate 2 random uppercase letters
        String letterPart = (1..2).collect { (String) ('A' + random.nextInt(26)) }.join('')

        // Generate a 2-digit number (00-99)
        String numberPart = String.format("%02d", random.nextInt(100))

        // Combine both parts
		String randomData = letterPart + numberPart
        return randomData
    }
}
