package dpd.airwallex.calculator.rpncalculator.domain.services;

import dpd.airwallex.calculator.rpncalculator.domain.Operations;
import dpd.airwallex.calculator.rpncalculator.domain.RpnStack;
import dpd.airwallex.calculator.rpncalculator.domain.computers.AbstractRpnComputer;
import dpd.airwallex.calculator.rpncalculator.domain.exceptions.InsufficientParameterException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RpnCalculatorTest {

    private RpnCalculator rpnCalculator;

    @Before
    public void setUp() {
        this.rpnCalculator = new RpnCalculator();
    }

    @After
    public void tearDown() {
        this.rpnCalculator = null;
    }

    @Test
    public void testNumericInput() throws InsufficientParameterException {
        RpnStack stack = rpnCalculator.calculate("1");

        assertEquals(1, stack.size());
        assertEquals("1", stack.toString());
        assertEquals(new BigDecimal(1).setScale(15, RoundingMode.HALF_UP), stack.pop());
        assertEquals(1, rpnCalculator.stackHistory().size());

        stack = rpnCalculator.calculate("2");
        assertEquals(2, stack.size());
        assertEquals("1 2", stack.toString());
        assertEquals(new BigDecimal(2).setScale(15, RoundingMode.HALF_UP), stack.pop());
        assertEquals(2, rpnCalculator.stackHistory().size());
        assertEquals("1", rpnCalculator.stackHistory().get(0).toString());
        assertEquals("1 2", rpnCalculator.stackHistory().get(1).toString());
    }

    @Test
    public void testPositiveComputation() throws InsufficientParameterException {
        AbstractRpnComputer rpnComputer = mock(AbstractRpnComputer.class);

        RpnStack stack = new RpnStack();
        stack.push(new BigDecimal(3).setScale(15));

        when(rpnComputer.handle(any(Operations.class))).thenReturn(true);
        when(rpnComputer.compute(any(RpnStack.class), any(Operations.class))).thenReturn(stack);

        List<AbstractRpnComputer> rpnComputerList = new ArrayList<>();
        rpnComputerList.add(rpnComputer);

        rpnCalculator.setRpnComputers(rpnComputerList);
        rpnCalculator.calculate("1");
        rpnCalculator.calculate("2");

        RpnStack updatedStack = rpnCalculator.calculate("+");

        assertEquals(1, updatedStack.size());
        assertEquals("3", updatedStack.toString());
        assertEquals(new BigDecimal(3).setScale(15, RoundingMode.HALF_UP), stack.pop());
        assertEquals(3, rpnCalculator.stackHistory().size());
        assertEquals("1", rpnCalculator.stackHistory().get(0).toString());
        assertEquals("1 2", rpnCalculator.stackHistory().get(1).toString());
        assertEquals("3", rpnCalculator.stackHistory().get(2).toString());
    }

    @Test
    public void testInsufficientParameterComputation() throws InsufficientParameterException {

        AbstractRpnComputer rpnComputer = mock(AbstractRpnComputer.class);

        RpnStack stack = new RpnStack();
        stack.push(new BigDecimal(3).setScale(15));

        when(rpnComputer.handle(any(Operations.class))).thenReturn(true);
        when(rpnComputer.compute(any(RpnStack.class), any(Operations.class))).thenThrow(InsufficientParameterException.class);

        List<AbstractRpnComputer> rpnComputerList = new ArrayList<>();
        rpnComputerList.add(rpnComputer);

        rpnCalculator.setRpnComputers(rpnComputerList);
        rpnCalculator.calculate("1");
        rpnCalculator.calculate("2");
        try {
            rpnCalculator.calculate("+");
        } catch (InsufficientParameterException ipe) {

        }

        assertEquals(2, rpnCalculator.stackHistory().size());
        assertEquals("1 2", rpnCalculator.stackHistory().get(1).toString());
        assertEquals("1", rpnCalculator.stackHistory().get(0).toString());
    }

}
