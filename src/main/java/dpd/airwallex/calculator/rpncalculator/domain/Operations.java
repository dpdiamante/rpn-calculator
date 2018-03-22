package dpd.airwallex.calculator.rpncalculator.domain;

public enum Operations {

    ADDITION("+"),
    SUBTRACTION("-"),
    MULTIPLICATION("*"),
    DIVISION("/"),
    SQUARE_ROOT("sqrt");

    private final String symbol;

    private Operations(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }
}
