package dpd.airwallex.calculator.rpncalculator.domain;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.*;

/**
 * An <code>RpnStack</code> represents the LIFO (last-in-first-out) stack data structure where the terms for this
 * calculator will be stored. Instead of using the {@see java.util.Stack} class, using {@see java.util.Deque}
 * is recommended.
 */
public class RpnStack {

    private Deque<BigDecimal> dequeAsStack = new ArrayDeque<>();


    /**
     * Pushes an element into the stack data structure
     * @param element
     */
    public void push(BigDecimal element) {
        dequeAsStack.addFirst(element);
    }

    /**
     * Pops the last element that was inserted into the stack
     * @return
     */
    public BigDecimal pop() {
        return dequeAsStack.removeFirst();
    }

    /**
     *
     * @return the contents of the stack
     */
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        dequeAsStack.descendingIterator().forEachRemaining((element) -> {
            DecimalFormat numFormat = new DecimalFormat("#.##########");
            stringBuilder.append(numFormat.format(element.setScale(10, RoundingMode.DOWN)) + " ");
        });

        return stringBuilder.toString().trim();
    }

    /**
     *
     * @return the size of the stack
     */
    public int size() {
        return dequeAsStack.size();
    }

    @Override
    public RpnStack clone() {
        Deque<BigDecimal> clonedDeque = ((ArrayDeque<BigDecimal>)this.dequeAsStack).clone();

        RpnStack clonedStack = new RpnStack();
        clonedStack.dequeAsStack = clonedDeque;

        return clonedStack;
    }

}
