package dpd.airwallex.calculator.rpncalculator.domain.services;

import dpd.airwallex.calculator.rpncalculator.domain.Operations;
import dpd.airwallex.calculator.rpncalculator.domain.RpnStack;
import dpd.airwallex.calculator.rpncalculator.domain.RpnStackOperations;
import dpd.airwallex.calculator.rpncalculator.domain.computers.AbstractRpnComputer;
import dpd.airwallex.calculator.rpncalculator.domain.computers.RpnComputer;
import dpd.airwallex.calculator.rpncalculator.domain.exceptions.InsufficientParameterException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class RpnCalculator {

    @Autowired
    private List<AbstractRpnComputer> rpnComputers;

    private RpnStack stack;

    private RpnStackHistory stackHistory;

    public RpnCalculator() {
        this.stack = new RpnStack();
        this.stackHistory = new RpnStackHistory();
    }

    public void setRpnComputers(List<AbstractRpnComputer> rpnComputers) {
        this.rpnComputers = rpnComputers;
    }

    public RpnStack calculate(String command) throws InsufficientParameterException {

        boolean processed = false;

        if (isNumeric(command)) {
            this.stack.push(new BigDecimal(command).setScale(15, RoundingMode.HALF_UP));
            processed = true;
        } else {

            if (command.trim().equals(RpnStackOperations.CLEAR.getCode())) {
                while (!this.stackHistory.isEmpty()) {
                    this.stackHistory.pop();
                }

                this.stack = new RpnStack();
                return this.stack.clone();
            } else if (command.trim().equals(RpnStackOperations.UNDO.getCode())) {
                this.stackHistory.pop();
                this.stack = this.stackHistory.pop();
                processed = true;
            } else {

                for (Operations operation : Operations.values()) {

                    if (operation.getSymbol().equals(command.trim())) {
                        RpnComputer operationComputer = this.rpnComputers.stream()
                                .filter(computer -> computer.handle(operation))
                                .collect(Collectors.toList())
                                .get(0);

                        RpnStack newStack = operationComputer.compute(this.stack, operation);

                        this.stack = newStack;
                        processed = true;
                    }
                }
            }

        }

        if (processed) {
            this.stackHistory.push(this.stack.clone());
        }

        return this.stack.clone();
    }

    public List<RpnStack> stackHistory() {
        List<RpnStack> stackHistoryList = new ArrayList<>();
        this.stackHistory.stackHistory.descendingIterator().forEachRemaining(stackHistoryList::add);

        return stackHistoryList;
    }

    private boolean isNumeric(String input) {

        try {
            double value = Double.parseDouble(input);
        } catch (NumberFormatException nfe) {
            return false;
        }

        return true;
    }

    private class RpnStackHistory {

        private Deque<RpnStack> stackHistory = new ArrayDeque<>();

        public void push(RpnStack stack) {
            stackHistory.addFirst(stack);
        }

        public RpnStack pop() {
            if (!isEmpty()) {
                return stackHistory.removeFirst();
            } else {
                return new RpnStack();
            }
        }

        public boolean isEmpty() {
            return stackHistory.isEmpty();
        }
    }

}
