package si.test.entities;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Class TraderEntity represents a registered trader.
 *
 * @author Zan Schaffer
 * @version %I%, %G%
 */
@Data
@Entity
public class TraderEntity {
    @Id
    @GeneratedValue
    private Long id;
    private String uuid;
    @ManyToOne(cascade = CascadeType.PERSIST)
    private TaxBaseEntity taxBase;
    private LocalDateTime dateCreated;
    private LocalDateTime dateUpdated;

    public TraderEntity(TaxBaseEntity taxBase) {
        this.taxBase = taxBase;
    }

    protected TraderEntity() {
    }

    @PrePersist
    private void prePersist() {
        uuid = UUID.randomUUID().toString();
        dateCreated = LocalDateTime.now();
    }

    @PreUpdate
    private void preUpdate() {
        dateUpdated = LocalDateTime.now();
    }
}
