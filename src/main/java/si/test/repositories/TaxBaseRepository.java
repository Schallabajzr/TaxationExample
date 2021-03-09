package si.test.repositories;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import si.test.entities.TaxBaseEntity;

import javax.enterprise.context.ApplicationScoped;
import java.util.Optional;

@ApplicationScoped
public class TaxBaseRepository implements PanacheRepository<TaxBaseEntity> {

    public Optional<TaxBaseEntity> findByAttributes(boolean taxOnWinnings, boolean taxFixedRate, double value) {
        return find("taxOnWinnings = ?1 and taxFixedRate = ?2 and value = ?3", taxOnWinnings, taxFixedRate, value)
                .firstResultOptional();
    }
}
