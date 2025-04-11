package App.Infra.Mapper;

import App.Domain.Response.Localizacao;
import App.Infra.Persistence.Entity.LocalizacaoEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LocalizacaoMapper {

    LocalizacaoEntity DtoToEntity(Localizacao localizacao);

    Localizacao EntityToDto(LocalizacaoEntity localizacaoEntity);
}
