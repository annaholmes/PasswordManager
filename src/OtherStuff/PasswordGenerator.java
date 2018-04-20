package OtherStuff;
import java.util.Random;
import java.util.stream.IntStream;

public class PasswordGenerator {

    private int maxLength;
    private boolean hasSpecialChar;
    private boolean hasUpperCase;
    private boolean hasLowerCase;
    private boolean hasNumber;

    private final static int[] upperAlphabet = IntStream.rangeClosed(65,90).toArray();
    private final static int[] lowerAlphabet = IntStream.rangeClosed(97,122).toArray();
    private final static int[] specialChars = IntStream.rangeClosed(33,64).toArray();
    private final static int[] numbers = IntStream.rangeClosed(48,57).toArray();

    public static char getRandomChar(int[] charSet) {
        Random rand = new Random();
        int selection = charSet[rand.nextInt(charSet.length)];
        return (char) selection;
    }


    // all parameters changed by text box in GUI
    public PasswordGenerator() {
        this.maxLength = 20;
        this.hasSpecialChar = true;
        this.hasUpperCase = true;
        this.hasLowerCase = true;
        this.hasNumber = true;
    }

    public String generate() {
        Random rand = new Random();
        char[] password = new char[maxLength];
        int[] valid = new int[maxLength];
        for (int i = 0; i < valid.length; i++) {
            valid[i] = i;
        }
        if (hasSpecialChar) {
            int index = valid[rand.nextInt(valid.length)];
            password[index] = getRandomChar(specialChars);
        } if (hasUpperCase) {
            //password[index] = getRandomChar(upperAlphabet);
        }
        return "";
    }

    //public int[] setChar(int index, int[] )

    public void setMaxLength(int maxLength) {
        this.maxLength = maxLength;
    }

    public void setHasSpecialChar(boolean hasSpecialChar) {
        this.hasSpecialChar = hasSpecialChar;
    }

    public void setHasUpperCase(boolean hasUpperCase) {
        this.hasUpperCase = hasUpperCase;
    }

    public void setHasLowerCase(boolean hasLowerCase) {
        this.hasLowerCase = hasLowerCase;
    }

    public void setHasNumber(boolean hasNumber) {
        this.hasNumber = hasNumber;
    }

    public static void main(String[] args) {
        System.out.println(getRandomChar(upperAlphabet) + getRandomChar(lowerAlphabet) + getRandomChar(specialChars) + getRandomChar(numbers));
    }
}
