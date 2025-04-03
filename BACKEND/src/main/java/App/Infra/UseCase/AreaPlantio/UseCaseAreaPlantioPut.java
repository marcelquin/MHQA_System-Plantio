package App.Infra.UseCase.AreaPlantio;

import App.Infra.Gateway.AreaPlantioGateway;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

public class UseCaseAreaPlantioPut {

    private final AreaPlantioGateway areaPlantioGateway;

    public UseCaseAreaPlantioPut(AreaPlantioGateway areaPlantioGateway) {
        this.areaPlantioGateway = areaPlantioGateway;
    }

    public ResponseEntity<Void> AdubacaoAreaPlantioGeral(@RequestParam String nomeIdentificador,
                                                         @RequestParam String adubacao)
    { return areaPlantioGateway.AdubacaoAreaPlantioGeral(nomeIdentificador, adubacao);}

    public ResponseEntity<Void> EditarAreaPlantio(@RequestParam Long id,
                                                  @RequestParam String nomeIdentificador,
                                                  @RequestParam String dimensao,
                                                  @RequestParam String gps,
                                                  @RequestParam int eixoX,
                                                  @RequestParam int eixoY)
    { return areaPlantioGateway.EditarAreaPlantio(id, nomeIdentificador, dimensao, gps, eixoX, eixoY);}

}
