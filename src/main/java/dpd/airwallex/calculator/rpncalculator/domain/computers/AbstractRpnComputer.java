package dpd.airwallex.calculator.rpncalculator.domain.computers;

import dpd.airwallex.calculator.rpncalculator.domain.Operations;
import dpd.airwallex.calculator.rpncalculator.domain.RpnStack;
import dpd.airwallex.calculator.rpncalculator.domain.exceptions.InsufficientParameterException;

public abstract class AbstractRpnComputer implements RpnComputer {

    public RpnStack compute(RpnStack stack, Operations operation) throws InsufficientParameterException {

        if (this.handle(operation)) {
            return this.calculate(stack);
        }

        return stack;
    }

    protected abstract RpnStack calculate(RpnStack stack) throws InsufficientParameterException;

    public abstract boolean handle(Operations operation);
}
