package si.test.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * Data transfer object for trade response
 *
 * @author Zan Schaffer
 * @version %I%, %G%
 */
@Data
@AllArgsConstructor
public class TradeResponseDto implements Serializable {

    private static final long serialVersionUID = 922498145623162351L;

    private double possibleReturnAmount;
    private double possibleReturnAmountBefTax;
    private double possibleReturnAmountAfterTax;
    private double taxRate;
    private double taxAmount;
}
