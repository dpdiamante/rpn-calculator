package dpd.airwallex.calculator.rpncalculator.domain;

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
