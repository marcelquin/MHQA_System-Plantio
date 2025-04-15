package App.Infra.UseCase.Area;

import App.Domain.Response.Area;
import App.Infra.Gateway.AreaGateway;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

public class UseCaseAreaPut {

    private final AreaGateway areaGateway;

    public UseCaseAreaPut(AreaGateway areaGateway) {
        this.areaGateway = areaGateway;
    }


    public ResponseEntity<Area> EditarArea(@RequestParam Long id,
                                           @RequestParam String dimensao,
                                           @RequestParam String nomeIdentificador)
    {return areaGateway.EditarArea(id, dimensao, nomeIdentificador);}

    public ResponseEntity<Area> NovaAdubacao(@RequestParam Long id, @RequestParam String relatorio)
    {return areaGateway.NovaAdubacao(id, relatorio);}

    public ResponseEntity<Area> AlterarDimensaoLocalizacoes(@RequestParam Long id,
                                                            @RequestParam int eixoX,
                                                            @RequestParam int eixoY,
                                                            @RequestParam int quantidadeBlocos)
    {return areaGateway.AlterarDimensaoLocalizacoes(id, eixoX, eixoY, quantidadeBlocos);}
}
