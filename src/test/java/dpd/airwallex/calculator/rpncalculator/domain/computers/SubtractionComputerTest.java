package dpd.airwallex.calculator.rpncalculator.domain.computers;

import dpd.airwallex.calculator.rpncalculator.domain.Operations;
import dpd.airwallex.calculator.rpncalculator.domain.RpnStack;
import dpd.airwallex.calculator.rpncalculator.domain.exceptions.InsufficientParameterException;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class SubtractionComputerTest {

    @Test
    public void testOperation() throws InsufficientParameterException {
        RpnStack stack = new RpnStack();
        stack.push(new BigDecimal(1).setScale(15));
        stack.push(new BigDecimal(2).setScale(15));

        RpnComputer subtractionComputer = new SubtractionComputer();
        RpnStack newStackState = subtractionComputer.compute(stack, Operations.SUBTRACTION);
        assertEquals(1, newStackState.size());
        assertEquals("-1", newStackState.toString());
        assertEquals(new BigDecimal(-1).setScale(15), newStackState.pop());

        stack = new RpnStack();
        stack.push(new BigDecimal(2).setScale(15));
        stack.push(new BigDecimal(1).setScale(15));

        newStackState = subtractionComputer.compute(stack, Operations.SUBTRACTION);
        assertEquals(1, newStackState.size());
        assertEquals("1", newStackState.toString());
        assertEquals(new BigDecimal(1).setScale(15), newStackState.pop());
    }

    @Test
    public void testNegativeOperation() throws InsufficientParameterException {
        RpnStack stack = new RpnStack();
        stack.push(new BigDecimal(1).setScale(15));
        stack.push(new BigDecimal(2).setScale(15));

        RpnComputer subtractionComputer = new SubtractionComputer();

        for(Operations operation : Operations.values()) {

            if (operation == Operations.SUBTRACTION) {
                continue;
            }

            RpnStack newStackState = subtractionComputer.compute(stack, operation);
            assertEquals(2, newStackState.size());
            assertEquals("1 2", newStackState.toString());
        }

    }

    @Test(expected = InsufficientParameterException.class)
    public void testInsufficientParameterException() throws InsufficientParameterException {
        RpnStack stack = new RpnStack();
        stack.push(new BigDecimal(1).setScale(15));

        RpnComputer subtractionComputer = new SubtractionComputer();
        RpnStack newStackState = subtractionComputer.compute(stack, Operations.SUBTRACTION);
    }
}
