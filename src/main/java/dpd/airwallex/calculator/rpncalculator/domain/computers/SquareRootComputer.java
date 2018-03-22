package dpd.airwallex.calculator.rpncalculator.domain.computers;

import dpd.airwallex.calculator.rpncalculator.domain.Operations;
import dpd.airwallex.calculator.rpncalculator.domain.RpnStack;
import dpd.airwallex.calculator.rpncalculator.domain.exceptions.InsufficientParameterException;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Component
public class SquareRootComputer extends AbstractRpnComputer {

    @Override
    protected RpnStack calculate(RpnStack stack) throws InsufficientParameterException {
        if (stack.size() < 1) {
            throw new InsufficientParameterException(Operations.SQUARE_ROOT);
        }

        BigDecimal computedValue = new BigDecimal(Math.sqrt(stack.pop().doubleValue())).setScale(15, RoundingMode.DOWN);
        stack.push(computedValue);

        return stack;
    }

    @Override
    public boolean handle(Operations operation) {
        return operation == Operations.SQUARE_ROOT ? true : false;
    }

}
