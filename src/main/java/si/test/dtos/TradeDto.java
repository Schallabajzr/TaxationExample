package si.test.dtos;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Data transfer object for trade request
 *
 * @author Zan Schaffer
 * @version %I%, %G%
 */
@Data
public class TradeDto implements Serializable {
    private static final long serialVersionUID = 4258253006400126219L;

    @NotNull
    private String traderId;
    private int playedAmount;
    private double odd;
}
