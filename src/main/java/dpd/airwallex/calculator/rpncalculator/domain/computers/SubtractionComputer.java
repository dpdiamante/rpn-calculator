package dpd.airwallex.calculator.rpncalculator.domain.computers;

import dpd.airwallex.calculator.rpncalculator.domain.Operations;
import dpd.airwallex.calculator.rpncalculator.domain.RpnStack;
import dpd.airwallex.calculator.rpncalculator.domain.exceptions.InsufficientParameterException;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * Handles subtraction computation
 */
@Component
public class SubtractionComputer extends AbstractRpnComputer {

    @Override
    protected RpnStack calculate(RpnStack stack) throws InsufficientParameterException {

        if (stack.size() < 2) {
            throw new InsufficientParameterException(Operations.SUBTRACTION);
        }

        BigDecimal secondTerm = stack.pop();
        BigDecimal firstTerm = stack.pop();

        BigDecimal computedValue = firstTerm.subtract(secondTerm);
        stack.push(computedValue);

        return stack;
    }

    @Override
    public boolean handle(Operations operation) {
        return operation == Operations.SUBTRACTION ? true : false;
    }
}
