package si.test.entities;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Class TaxBaseEntity represents a unique tax base.
 * <p>
 * It holds information on how the trade should be executed based on local laws.
 * <p>
 *
 * @author Zan Schaffer
 * @version %I%, %G%
 */
@Data
@Entity
public class TaxBaseEntity {
    @Id
    @GeneratedValue
    private Long id;
    private boolean taxOnWinnings;
    private boolean taxFixedRate;
    private double value;
    private LocalDateTime dateCreated;
    private LocalDateTime dateUpdated;

    public TaxBaseEntity(boolean taxOnWinnings, boolean taxFixedRate, double value) {
        this.taxOnWinnings = taxOnWinnings;
        this.taxFixedRate = taxFixedRate;
        this.value = value;
    }

    protected TaxBaseEntity() {
    }

    @PrePersist
    private void prePersist() {
        dateCreated = LocalDateTime.now();
    }

    @PreUpdate
    private void preUpdate() {
        dateUpdated = LocalDateTime.now();
    }
}
