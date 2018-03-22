package dpd.airwallex.calculator.rpncalculator.domain;

/**
 * A set of special commands that performs on the entirety of the stack
 */
public enum RpnStackOperations {

    CLEAR("clear"), UNDO("undo");

    private final String code;

    private RpnStackOperations(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
