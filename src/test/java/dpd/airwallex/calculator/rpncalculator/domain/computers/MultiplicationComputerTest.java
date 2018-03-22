package dpd.airwallex.calculator.rpncalculator.domain.computers;

import dpd.airwallex.calculator.rpncalculator.domain.Operations;
import dpd.airwallex.calculator.rpncalculator.domain.RpnStack;
import dpd.airwallex.calculator.rpncalculator.domain.exceptions.InsufficientParameterException;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class MultiplicationComputerTest {

    @Test
    public void testOperation() throws InsufficientParameterException {
        RpnStack stack = new RpnStack();
        stack.push(new BigDecimal(2).setScale(15));
        stack.push(new BigDecimal(3).setScale(15));

        RpnComputer multiplicationComputer = new MultiplicationComputer();
        RpnStack newStackState = multiplicationComputer.compute(stack, Operations.MULTIPLICATION);
        assertEquals(1, newStackState.size());
        assertEquals("6", newStackState.toString());
        assertEquals(new BigDecimal(6).setScale(15), newStackState.pop());
    }

    @Test
    public void testNegativeOperation() throws InsufficientParameterException {
        RpnStack stack = new RpnStack();
        stack.push(new BigDecimal(1).setScale(15));
        stack.push(new BigDecimal(2).setScale(15));

        RpnComputer multiplicationComputer = new MultiplicationComputer();

        for(Operations operation : Operations.values()) {

            if (operation == Operations.MULTIPLICATION) {
                continue;
            }

            RpnStack newStackState = multiplicationComputer.compute(stack, operation);
            assertEquals(2, newStackState.size());
            assertEquals("1 2", newStackState.toString());
        }

    }

    @Test(expected = InsufficientParameterException.class)
    public void testInsufficientParameterException() throws InsufficientParameterException {
        RpnStack stack = new RpnStack();
        stack.push(new BigDecimal(1).setScale(15));

        RpnComputer multiplicationComputer = new MultiplicationComputer();
        RpnStack newStackState = multiplicationComputer.compute(stack, Operations.MULTIPLICATION);
    }

}
