package si.test.resources.impl;

import si.test.beans.TraderBean;
import si.test.dtos.TraderDto;
import si.test.mappers.TraderMapper;
import si.test.resources.TraderResource;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.List;

@RequestScoped
public class TraderResourceImpl implements TraderResource {

    @Inject
    TraderBean traderBean;

    @Inject
    TraderMapper traderMapper;

    @Override
    public List<TraderDto> getAllTraders() {
        return traderMapper.toDtoList(traderBean.getAllTraders());
    }

    @Override
    public TraderDto getTraderByUUID(String uuid) {
        return traderMapper.toDto(traderBean.getTraderByUUID(uuid));
    }

    @Override
    public TraderDto addTrader(TraderDto trader) {
        return traderMapper.toDto(traderBean.save(traderMapper.toEntity(trader)));
    }
}
