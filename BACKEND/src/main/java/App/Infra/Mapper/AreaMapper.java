package App.Infra.Mapper;

import App.Domain.Response.Area;
import App.Infra.Persistence.Entity.AreaEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AreaMapper {

    AreaEntity toToEntity(Area area);

    Area EntityToDto(AreaEntity areaEntity);
}
