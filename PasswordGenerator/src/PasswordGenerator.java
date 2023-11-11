import java.util.Random;

public class PasswordGenerator {
    private static final String LOWERCASE_CHARACTERS = "abcdefghijklmnopqrstuvwxyz";
    private static final String UPPERCASE_CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String NUMBERS = "0123456789";
    private static final String SYMBOLS = "!@#$%^&*()_+-=[]{}|;:<>,.?/";
    
    public static String generatePassword(int length,  boolean useSymbols, boolean useUppercase, boolean useNumbers){
        StringBuilder password = new StringBuilder();
        Random random = new Random();
        String allCharacters = LOWERCASE_CHARACTERS; 
        
        //Adding more characters for password by users checkbox choises
        if(useSymbols){ allCharacters += SYMBOLS; }
        if(useUppercase){ allCharacters += UPPERCASE_CHARACTERS; }
        if(useNumbers){ allCharacters += NUMBERS; }
        
        // Ensure that at least one uppercase letter is included
        if (useUppercase) {
            int randomIndex = random.nextInt(UPPERCASE_CHARACTERS.length());
            char randomUppercaseChar = UPPERCASE_CHARACTERS.charAt(randomIndex);
            password.append(randomUppercaseChar);
            length--;
        }
        // Ensure that at least one symbol is included
        if (useSymbols) {
            int randomIndex = random.nextInt(SYMBOLS.length());
            char randomSymbolChar = SYMBOLS.charAt(randomIndex);
            password.append(randomSymbolChar);
            length--; 
        }
        // Ensure that at least one number is included
        if (useNumbers) {
            int randomIndex = random.nextInt(NUMBERS.length());
            char randomNumberChar = SYMBOLS.charAt(randomIndex);
            password.append(randomNumberChar);
            length--; 
        }
        
        for(int i = 0; i <= length; i++){
            int randomIndex = random.nextInt(allCharacters.length());
            char randomChar = allCharacters.charAt(randomIndex);
            password.append(randomChar);
        }
        
        shufflePassword(password);
        
        return password.toString();
    }
    
    // Helper method to shuffle the password characters to be more random
    private static void shufflePassword(StringBuilder password) {
        Random random = new Random();
        for (int i = password.length() - 1; i > 0; i--) {
            int index = random.nextInt(i + 1);
            char temp = password.charAt(index);
            password.setCharAt(index, password.charAt(i));
            password.setCharAt(i, temp);
        }
    }
}
