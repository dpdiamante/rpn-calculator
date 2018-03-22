package dpd.airwallex.calculator.rpncalculator.application;

import dpd.airwallex.calculator.rpncalculator.domain.RpnStack;

public interface UserInterfacePort {

    void showStackAndPrompt(RpnStack rpnStack);

    void showErrorAndPrompt(RpnStack rpnStack, String showErrorMessage);
}
