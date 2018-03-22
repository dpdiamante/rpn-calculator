package dpd.airwallex.calculator.rpncalculator.domain.computers;

import dpd.airwallex.calculator.rpncalculator.domain.Operations;
import dpd.airwallex.calculator.rpncalculator.domain.RpnStack;
import dpd.airwallex.calculator.rpncalculator.domain.exceptions.InsufficientParameterException;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Handles multiplication computation
 */
@Component
public class MultiplicationComputer extends AbstractRpnComputer {

    @Override
    protected RpnStack calculate(RpnStack stack) throws InsufficientParameterException {
        if (stack.size() < 2) {
            throw new InsufficientParameterException(Operations.MULTIPLICATION);
        }

        BigDecimal computedValue = stack.pop().multiply(stack.pop()).setScale(15, RoundingMode.DOWN);
        stack.push(computedValue);

        return stack;
    }

    @Override
    public boolean handle(Operations operation) {
        return operation == Operations.MULTIPLICATION ? true : false;
    }
}
