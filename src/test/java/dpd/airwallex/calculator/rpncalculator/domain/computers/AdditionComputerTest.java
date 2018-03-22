package dpd.airwallex.calculator.rpncalculator.domain.computers;

import dpd.airwallex.calculator.rpncalculator.domain.Operations;
import dpd.airwallex.calculator.rpncalculator.domain.RpnStack;
import dpd.airwallex.calculator.rpncalculator.domain.exceptions.InsufficientParameterException;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class AdditionComputerTest {

    @Test
    public void testOperation() throws InsufficientParameterException {
        RpnStack stack = new RpnStack();
        stack.push(new BigDecimal(1).setScale(15));
        stack.push(new BigDecimal(2).setScale(15));

        RpnComputer additionComputer = new AdditionComputer();
        RpnStack newStackState = additionComputer.compute(stack, Operations.ADDITION);
        assertEquals(1, newStackState.size());
        assertEquals("3", newStackState.toString());
        assertEquals(new BigDecimal(3).setScale(15), newStackState.pop());
    }

    @Test
    public void testNegativeOperation() throws InsufficientParameterException {
        RpnStack stack = new RpnStack();
        stack.push(new BigDecimal(1).setScale(15));
        stack.push(new BigDecimal(2).setScale(15));

        RpnComputer additionComputer = new AdditionComputer();

        for(Operations operation : Operations.values()) {

            if (operation == Operations.ADDITION) {
                continue;
            }

            RpnStack newStackState = additionComputer.compute(stack, operation);
            assertEquals(2, newStackState.size());
            assertEquals("1 2", newStackState.toString());
        }

    }

    @Test(expected = InsufficientParameterException.class)
    public void testInsufficientParameterException() throws InsufficientParameterException {
        RpnStack stack = new RpnStack();
        stack.push(new BigDecimal(1).setScale(15));

        RpnComputer additionComputer = new AdditionComputer();
        RpnStack newStackState = additionComputer.compute(stack, Operations.ADDITION);
    }

}
