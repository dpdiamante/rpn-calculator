package dpd.airwallex.calculator.rpncalculator;

import dpd.airwallex.calculator.rpncalculator.domain.services.RpnCalculator;
import dpd.airwallex.calculator.rpncalculator.interfaces.ConsoleInterface;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * The Rpn Calculator Application
 */
@SpringBootApplication
public class RpnCalculatorApplication {

	public static void main(String[] args) throws IOException {
		ApplicationContext context = SpringApplication.run(RpnCalculatorApplication.class, args);

		ConsoleInterface consoleInterface = context.getBean(ConsoleInterface.class);
		consoleInterface.runApplication();
	}
}
