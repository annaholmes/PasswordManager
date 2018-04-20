package OtherStuff;

public class PasswordGenerator {

    private int maxLength;
    private boolean hasSpecialChar;
    private boolean hasUpperCase;
    private boolean hasLowerCase;
    private boolean hasNumber;

    // all parameters changed by text box in GUI
    public PasswordGenerator() {
        this.maxLength = 20;
        this.hasSpecialChar = true;
        this.hasUpperCase = true;
        this.hasLowerCase = true;
        this.hasNumber = true;
    }

    public String generate() {
        // TODO: generate passwords based on internal parameters
        return null;
    }

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
}
