package dpd.airwallex.calculator.rpncalculator.application;

import dpd.airwallex.calculator.rpncalculator.domain.RpnStack;
import dpd.airwallex.calculator.rpncalculator.domain.exceptions.InsufficientParameterException;
import dpd.airwallex.calculator.rpncalculator.domain.services.RpnCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * An <code>AirwallexRpnCalculator</code> computes a stream of input using the RPN logic.
 */
@Component
public class AirwallexRpnCalculator {

    @Autowired
    private final RpnCalculator rpnCalculator;

    public AirwallexRpnCalculator(RpnCalculator rpnCalculator) {
        this.rpnCalculator = rpnCalculator;
    }

    /**
     * Computes the stream of input in accordance to RPN logic.
     *
     * @param commandInput
     * @param userInterfacePort
     */
    public void calculate(String commandInput, UserInterfacePort userInterfacePort) {

        String[] commands = commandInput.split(" ");
        RpnStack rpnStack = new RpnStack();

        int position = 0;

        for(String command : commands) {

            position = commandInput.indexOf(command, position);

            try {
                rpnStack = this.rpnCalculator.calculate(command);
            } catch (InsufficientParameterException ipe) {
                userInterfacePort.showErrorAndPrompt(rpnStack,
                        "operator " + command + "(position: " + (position + 1) + "): insufficient parameters");
                return;
            }

            position += command.length();
        }

        userInterfacePort.showStackAndPrompt(rpnStack);
    }
}
