package dpd.airwallex.calculator.rpncalculator.domain.computers;

import dpd.airwallex.calculator.rpncalculator.domain.Operations;
import dpd.airwallex.calculator.rpncalculator.domain.RpnStack;
import dpd.airwallex.calculator.rpncalculator.domain.exceptions.InsufficientParameterException;

/**
 * An <code>AbstractRpnComputer</code> performs calculation to a set of parameters from a given
 * RPN stack.
 */
public abstract class AbstractRpnComputer implements RpnComputer {

    /**
     * Performs computation from the contents of the stacks
     *
     * @param stack
     * @param operation
     * @return The updated <code>RpnStack</code>
     * @throws InsufficientParameterException if there aren't enough parameters inside the RPN stack for the
     * computation to be performed.
     */
    public RpnStack compute(RpnStack stack, Operations operation) throws InsufficientParameterException {

        if (this.handle(operation)) {
            return this.calculate(stack);
        }

        return stack;
    }

    protected abstract RpnStack calculate(RpnStack stack) throws InsufficientParameterException;

    /**
     * If this instance of <code>AbstractRpnComputer</code> can handle a specific operation
     *
     * @param operation
     * @return <code>true</code> if it can handle the operation. <code>false</code> otherwise
     */
    public abstract boolean handle(Operations operation);
}
