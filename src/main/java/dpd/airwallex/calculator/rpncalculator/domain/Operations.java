package dpd.airwallex.calculator.rpncalculator.domain;

/**
 * The set of operations that the application can handle on a given RPN stack
 */
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
