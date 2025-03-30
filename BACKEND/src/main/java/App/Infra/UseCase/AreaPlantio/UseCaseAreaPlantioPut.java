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

}
