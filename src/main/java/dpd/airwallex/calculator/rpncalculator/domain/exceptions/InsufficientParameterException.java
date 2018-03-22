package dpd.airwallex.calculator.rpncalculator.domain.exceptions;

import dpd.airwallex.calculator.rpncalculator.domain.Operations;

/**
 * An exception when there aren't enough parameters for a given operation
 */
public class InsufficientParameterException extends Exception {

    public InsufficientParameterException(Operations operation) {
        super("Insufficient parameters for " + operation.getSymbol() + " operation");
    }
}
