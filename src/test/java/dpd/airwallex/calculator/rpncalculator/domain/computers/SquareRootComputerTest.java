package dpd.airwallex.calculator.rpncalculator.domain.computers;

import dpd.airwallex.calculator.rpncalculator.domain.Operations;
import dpd.airwallex.calculator.rpncalculator.domain.RpnStack;
import dpd.airwallex.calculator.rpncalculator.domain.exceptions.InsufficientParameterException;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.junit.Assert.assertEquals;

public class SquareRootComputerTest {

    @Test
    public void testOperation() throws InsufficientParameterException {
        RpnStack stack = new RpnStack();
        stack.push(new BigDecimal(4).setScale(15, RoundingMode.DOWN));

        RpnComputer squareRootComputer = new SquareRootComputer();
        RpnStack newStackState = squareRootComputer.compute(stack, Operations.SQUARE_ROOT);

        assertEquals(1, newStackState.size());
        assertEquals("2", newStackState.toString());
        assertEquals(new BigDecimal(2).setScale(15), newStackState.pop());
    }

    @Test
    public void testNegativeOperation() throws InsufficientParameterException {
        RpnStack stack = new RpnStack();
        stack.push(new BigDecimal(1).setScale(15));
        stack.push(new BigDecimal(2).setScale(15));

        RpnComputer squareRootComputer = new SquareRootComputer();

        for(Operations operation : Operations.values()) {

            if (operation == Operations.SQUARE_ROOT) {
                continue;
            }

            RpnStack newStackState = squareRootComputer.compute(stack, operation);
            assertEquals(2, newStackState.size());
            assertEquals("1 2", newStackState.toString());
        }

    }

    @Test(expected = InsufficientParameterException.class)
    public void testInsufficientParameterException() throws InsufficientParameterException {
        RpnStack stack = new RpnStack();

        RpnComputer squareRootComputer = new SquareRootComputer();
        RpnStack newStackState = squareRootComputer.compute(stack, Operations.SQUARE_ROOT);
    }

}
