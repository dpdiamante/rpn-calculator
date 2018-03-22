package dpd.airwallex.calculator.rpncalculator.domain.computers;

import dpd.airwallex.calculator.rpncalculator.domain.Operations;
import dpd.airwallex.calculator.rpncalculator.domain.RpnStack;
import dpd.airwallex.calculator.rpncalculator.domain.exceptions.InsufficientParameterException;

/**
 * Performs computation on a given RPN stack and a specific operation
 */
public interface RpnComputer {

    /**
     * Performs computation on the given RPN stack for the specific operation
     *
     * @param stack
     * @param operation
     * @return
     * @throws InsufficientParameterException
     */
    RpnStack compute(RpnStack stack, Operations operation) throws InsufficientParameterException;

}
