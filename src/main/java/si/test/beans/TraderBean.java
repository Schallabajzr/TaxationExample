package si.test.beans;

import si.test.entities.TaxBaseEntity;
import si.test.entities.TraderEntity;
import si.test.repositories.TraderRepository;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.NotFoundException;
import java.io.Serializable;
import java.util.List;

/**
 * Business logic for TraderResource
 *
 * @author Zan Schaffer
 * @version %I%, %G%
 */
@RequestScoped
public class TraderBean implements Serializable {

    private static final long serialVersionUID = -7964259201941959385L;

    @Inject
    TraderRepository traderRepository;

    /**
     * Saves a Trader entity
     *
     * @param trader entity to persist
     * @return persisted entity
     */
    public TraderEntity save(TraderEntity trader) {
        TaxBaseEntity taxBase = trader.getTaxBase();
        return traderRepository.createTrader(taxBase.isTaxOnWinnings(), taxBase.isTaxFixedRate(), taxBase.getValue());
    }

    /**
     * Gets all Trader entities
     *
     * @return list of all traders
     */
    public List<TraderEntity> getAllTraders() {
        return traderRepository.findAll().list();
    }

    /**
     * Finds a trader by its UUID
     *
     * @param uuid unique identifier of the entity
     * @return trader with UUID
     */
    public TraderEntity getTraderByUUID(String uuid) {
        return traderRepository.findByUuid(uuid).orElseThrow(() ->
                new NotFoundException("No entity was found for UUID!"));
    }
}
