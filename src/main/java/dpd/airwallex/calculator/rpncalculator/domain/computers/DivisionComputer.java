package dpd.airwallex.calculator.rpncalculator.domain.computers;

import dpd.airwallex.calculator.rpncalculator.domain.Operations;
import dpd.airwallex.calculator.rpncalculator.domain.RpnStack;
import dpd.airwallex.calculator.rpncalculator.domain.exceptions.InsufficientParameterException;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Handles division computation
 */
@Component
public class DivisionComputer extends AbstractRpnComputer {

    @Override
    protected RpnStack calculate(RpnStack stack) throws InsufficientParameterException {

        if (stack.size() < 2) {
            throw new InsufficientParameterException(Operations.DIVISION);
        }

        BigDecimal secondTerm = stack.pop();
        BigDecimal firstTerm = stack.pop();

        BigDecimal computedValue = firstTerm.divide(secondTerm, 15, RoundingMode.DOWN);
        stack.push(computedValue);

        return stack;
    }

    @Override
    public boolean handle(Operations operation) {
        return operation == Operations.DIVISION ? true : false;
    }
}
