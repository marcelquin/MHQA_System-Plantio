package App.Domain.Util;

import App.Domain.Response.AreaPlantio;
import App.Infra.Persistence.Entity.AreaPlantioEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AreaPlantioMapper {

    AreaPlantioEntity DtoToEntity(AreaPlantio areaPlantio);

    AreaPlantio EntityToDto(AreaPlantioEntity areaPlantioEntity);
}
