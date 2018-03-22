package dpd.airwallex.calculator.rpncalculator.domain;

import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.junit.Assert.assertEquals;

public class RpnStackTest {

    @Test
    public void testPushMethod() {

        RpnStack stack = new RpnStack();
        stack.push(new BigDecimal(1).setScale(15));
        stack.push(new BigDecimal(2).setScale(15));
        stack.push(new BigDecimal(3).setScale(15));
        stack.push(new BigDecimal(5).setScale(15));
        stack.push(new BigDecimal(8).setScale(15));
        stack.push(new BigDecimal(13).setScale(15));
        stack.push(new BigDecimal(21).setScale(15));

        assertEquals(7, stack.size());
        assertEquals(new BigDecimal(21).setScale(15), stack.pop());

        for(int i = 0; i < 5; i++) {
            stack.pop();
        }

        assertEquals(new BigDecimal(1).setScale(15), stack.pop());
    }

    @Test
    public void testPopMethod() {

        RpnStack stack = new RpnStack();
        stack.push(new BigDecimal(1).setScale(15));
        stack.push(new BigDecimal(2).setScale(15));
        stack.push(new BigDecimal(3).setScale(15));
        stack.push(new BigDecimal(5).setScale(15));
        stack.push(new BigDecimal(8).setScale(15));
        stack.push(new BigDecimal(13).setScale(15));
        stack.push(new BigDecimal(21).setScale(15));

        assertEquals(new BigDecimal(21).setScale(15), stack.pop());
        assertEquals(6, stack.size());
    }

    @Test
    public void testToString() {
        RpnStack stack = new RpnStack();
        stack.push(new BigDecimal(3).setScale(15));
        stack.push(new BigDecimal(3.14).setScale(15, RoundingMode.HALF_UP));
        stack.push(new BigDecimal(3.14159).setScale(15, RoundingMode.HALF_UP));
        stack.push(new BigDecimal(3.1415926535).setScale(15, RoundingMode.HALF_UP));
        stack.push(new BigDecimal(3.141592653589793).setScale(15, RoundingMode.HALF_UP));
        stack.push(new BigDecimal(3.14159265358979323846).setScale(15, RoundingMode.HALF_UP));

        assertEquals("3 3.14 3.14159 3.1415926535 3.1415926535 3.1415926535", stack.toString());
    }

    @Test
    public void testCloneMethod() {
        RpnStack stack = new RpnStack();
        stack.push(new BigDecimal(1).setScale(15));
        stack.push(new BigDecimal(2).setScale(15));
        stack.push(new BigDecimal(3).setScale(15));
        stack.push(new BigDecimal(5).setScale(15));
        stack.push(new BigDecimal(8).setScale(15));
        stack.push(new BigDecimal(13).setScale(15));
        stack.push(new BigDecimal(21).setScale(15));

        RpnStack clonedStack = stack.clone();

        stack.pop();

        assertEquals(7, clonedStack.size());
        assertEquals(6, stack.size());
        assertEquals(new BigDecimal(21).setScale(15), clonedStack.pop());
        assertEquals(new BigDecimal(13).setScale(15), stack.pop());
    }

}
