package si.test.repositories;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import si.test.entities.TaxBaseEntity;
import si.test.entities.TraderEntity;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.Optional;

/**
 * Repository for Trader Entity
 *
 * @author Zan Schaffer
 * @version %I%, %G%
 * @see TraderEntity
 */
@ApplicationScoped
public class TraderRepository implements PanacheRepository<TraderEntity> {

    @Inject
    TaxBaseRepository taxBaseRepository;

    @Transactional
    public TraderEntity createTrader(boolean taxOnWinnings, boolean taxFixedRate, double value) {

        Optional<TaxBaseEntity> byAttributes = taxBaseRepository.findByAttributes(taxOnWinnings, taxFixedRate, value);
        if (byAttributes.isEmpty()) {
            byAttributes = Optional.of(new TaxBaseEntity(taxOnWinnings, taxFixedRate, value));
            taxBaseRepository.persist(byAttributes.get());
        }

        TraderEntity traderEntity = new TraderEntity(byAttributes.get());
        persist(traderEntity);

        return traderEntity;
    }

    public Optional<TraderEntity> findByUuid(String uuid) {
        return find("uuid", uuid).firstResultOptional();
    }
}
