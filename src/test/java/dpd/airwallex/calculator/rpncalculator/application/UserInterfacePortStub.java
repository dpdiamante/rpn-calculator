package dpd.airwallex.calculator.rpncalculator.application;

import dpd.airwallex.calculator.rpncalculator.domain.RpnStack;

public class UserInterfacePortStub implements UserInterfacePort {

    private RpnStack rpnStack;

    @Override
    public void showStackAndPrompt(RpnStack rpnStack) {
        System.out.println(rpnStack);
        this.rpnStack = rpnStack;
    }

    @Override
    public void showErrorAndPrompt(RpnStack rpnStack, String showErrorMessage) {
        System.out.println(rpnStack);
        System.out.println(showErrorMessage);
        this.rpnStack = rpnStack;
    }

    public RpnStack getRpnStack() {
        return rpnStack;
    }

}
