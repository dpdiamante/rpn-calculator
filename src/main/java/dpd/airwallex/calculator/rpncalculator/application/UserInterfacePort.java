package dpd.airwallex.calculator.rpncalculator.application;

import dpd.airwallex.calculator.rpncalculator.domain.RpnStack;

/**
 * A UserInterfacePort represents a user interface (e.g. screen, console, mobile, etc..) that the
 * application interacts with.
 */
public interface UserInterfacePort {

    /**
     * Show the contents of the RPN stack
     * @param rpnStack
     */
    void showStackAndPrompt(RpnStack rpnStack);

    /**
     * Shows the error of the last computation and the current content of the stack when the erronoeus
     * computation occured.
     * @param rpnStack
     * @param showErrorMessage
     */
    void showErrorAndPrompt(RpnStack rpnStack, String showErrorMessage);
}
