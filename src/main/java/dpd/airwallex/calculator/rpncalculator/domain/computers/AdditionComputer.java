package dpd.airwallex.calculator.rpncalculator.domain.computers;

import dpd.airwallex.calculator.rpncalculator.domain.Operations;
import dpd.airwallex.calculator.rpncalculator.domain.RpnStack;
import dpd.airwallex.calculator.rpncalculator.domain.exceptions.InsufficientParameterException;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * Handles addition computation
 */
@Component
public class AdditionComputer extends AbstractRpnComputer {

    @Override
    public boolean handle(Operations operation) {
        return operation == Operations.ADDITION ? true : false;
    }

    @Override
    protected RpnStack calculate(RpnStack stack) throws InsufficientParameterException {

        if (stack.size() < 2) {
            throw new InsufficientParameterException(Operations.ADDITION);
        }

        BigDecimal computedValue = stack.pop().add(stack.pop());
        stack.push(computedValue);

        return stack;
    }
}
