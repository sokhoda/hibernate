package exceptions;

public class NotValidCoinNominalValue extends Exception{
    public NotValidCoinNominalValue(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return "\nNotValidCoinNominalValue:" + super.getMessage();
    }
}
