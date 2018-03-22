package dpd.airwallex.calculator.rpncalculator.domain.exceptions;

import dpd.airwallex.calculator.rpncalculator.domain.Operations;

public class InsufficientParameterException extends Exception {

    public InsufficientParameterException(Operations operation) {
        super("Insufficient parameters for " + operation.getSymbol() + " operation");
    }
}
