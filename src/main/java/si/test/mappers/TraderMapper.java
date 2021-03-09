package si.test.mappers;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import si.test.dtos.TraderDto;
import si.test.entities.TraderEntity;

import java.util.List;

@Mapper(componentModel = "cdi")
public interface TraderMapper {

    @Mapping(target = "taxOnWinnings", source = "entity.taxBase.taxOnWinnings")
    @Mapping(target = "taxFixedRate", source = "entity.taxBase.taxFixedRate")
    @Mapping(target = "value", source = "entity.taxBase.value")
    TraderDto toDto(TraderEntity entity);

    @InheritInverseConfiguration
    TraderEntity toEntity(TraderDto dto);

    List<TraderDto> toDtoList(List<TraderEntity> entities);
}
