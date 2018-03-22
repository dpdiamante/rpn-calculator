package dpd.airwallex.calculator.rpncalculator.application;

import dpd.airwallex.calculator.rpncalculator.domain.Operations;
import dpd.airwallex.calculator.rpncalculator.domain.RpnStack;
import dpd.airwallex.calculator.rpncalculator.domain.computers.AbstractRpnComputer;
import dpd.airwallex.calculator.rpncalculator.domain.exceptions.InsufficientParameterException;
import dpd.airwallex.calculator.rpncalculator.domain.services.RpnCalculator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AirwallexRpnCalculatorTest {

    private RpnCalculator rpnCalculator;

    @Before
    public void setUp() {
        rpnCalculator = new RpnCalculator();
    }

    @After
    public void tearDown() {
        rpnCalculator = null;
    }

    @Test
    public void testPositiveCalculation() {
        UserInterfacePort interfacePort = new UserInterfacePortStub();

        AirwallexRpnCalculator airwallexRpnCalculator = new AirwallexRpnCalculator(this.rpnCalculator);
        airwallexRpnCalculator.calculate("1 2 3 5 8 13 21", interfacePort);
        assertEquals("1 2 3 5 8 13 21", ((UserInterfacePortStub) interfacePort).getRpnStack().toString());
    }

    @Test
    public void testInsufficientParameterException() throws InsufficientParameterException {
        UserInterfacePort interfacePort = new UserInterfacePortStub();

        AbstractRpnComputer rpnComputer = mock(AbstractRpnComputer.class);
        when(rpnComputer.handle(any(Operations.class))).thenReturn(true);
        when(rpnComputer.compute(any(RpnStack.class), any(Operations.class))).thenThrow(InsufficientParameterException.class);

        List<AbstractRpnComputer> rpnComputerList = new ArrayList<>();
        rpnComputerList.add(rpnComputer);

        rpnCalculator.setRpnComputers(rpnComputerList);

        AirwallexRpnCalculator airwallexRpnCalculator = new AirwallexRpnCalculator(this.rpnCalculator);
        airwallexRpnCalculator.calculate("1 2 3 5 8 13 21 /", interfacePort);
        assertEquals("1 2 3 5 8 13 21", ((UserInterfacePortStub) interfacePort).getRpnStack().toString());
    }

}
