package si.test.dtos;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Data transfer object for TraderEntity
 *
 * @author Zan Schaffer
 * @version %I%, %G%
 * @see si.test.entities.TraderEntity
 */
@Data
public class TraderDto implements Serializable {
    private static final long serialVersionUID = -1431346192645879771L;

    @NotNull
    private String uuid;
    private boolean taxOnWinnings;
    private boolean taxFixedRate;
    private double value;
}
