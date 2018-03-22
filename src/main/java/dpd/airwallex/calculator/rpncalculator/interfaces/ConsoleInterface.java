package dpd.airwallex.calculator.rpncalculator.interfaces;

import dpd.airwallex.calculator.rpncalculator.application.AirwallexRpnCalculator;
import dpd.airwallex.calculator.rpncalculator.application.UserInterfacePort;
import dpd.airwallex.calculator.rpncalculator.domain.RpnStack;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Component
public class ConsoleInterface implements UserInterfacePort{

    @Autowired
    private AirwallexRpnCalculator airwallexRpnCalculator;

    private BufferedReader in;

    @Override
    public void showStackAndPrompt(RpnStack rpnStack) {
        System.out.println("stack: " + rpnStack);
    }

    public ConsoleInterface() {
        this.in = new BufferedReader(new InputStreamReader(System.in));
    }

    @Override
    public void showErrorAndPrompt(RpnStack rpnStack, String showErrorMessage) {
        System.out.println(showErrorMessage);
        System.out.println("stack: " + rpnStack);
    }

    public void runApplication() throws IOException{
        System.out.println();
        System.out.println("----------------------------------------");
        System.out.println("    RPN CALCULATOR (TYPE 'q' TO QUIT)");
        System.out.println("----------------------------------------");
        System.out.println();

        while (1 == 1) {
            this.runCaculator();
        }
    }

    private void runCaculator() throws IOException {
        String line = in.readLine();

        if ("q".equals(line.toLowerCase())) {
            System.exit(0);
        }

        airwallexRpnCalculator.calculate(line, this);
    }

}
