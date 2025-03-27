package App.Domain.Util;

import App.Domain.Response.SubAreaPlantio;
import App.Infra.Persistence.Entity.SubAreaPlantioEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SubareaPlantioMapper {

    SubAreaPlantioEntity DtoToEntity(SubAreaPlantio subAreaPlantio);

    SubAreaPlantio EntityToDto(SubAreaPlantioEntity subAreaPlantioEntity);
}
