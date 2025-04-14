package App.Infra.Mapper;

import App.Domain.Response.Bloco;
import App.Domain.Response.Localizacao;
import App.Infra.Persistence.Entity.BlocoEntity;
import App.Infra.Persistence.Entity.LocalizacaoEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BlocoMapper {

    BlocoEntity DtoToEntity(Bloco bloco);

    Bloco EntityToDto(BlocoEntity blocoEntity);
}
