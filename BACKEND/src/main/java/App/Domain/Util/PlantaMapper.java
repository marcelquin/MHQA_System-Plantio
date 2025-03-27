package App.Domain.Util;


import App.Domain.Response.Planta;
import App.Infra.Persistence.Entity.PlantaEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PlantaMapper {

    Planta EntityToRecord(PlantaEntity plantaEntity);

    PlantaEntity RecordToEntity(Planta planta);
}
