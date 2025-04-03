package App.Infra.UseCase.AreaPlantio;

import App.Domain.Response.AreaPlantio;
import App.Infra.Gateway.AreaPlantioGateway;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

public class UseCaseAreaPlantioPost {

    private final AreaPlantioGateway areaPlantioGateway;

    public UseCaseAreaPlantioPost(AreaPlantioGateway areaPlantioGateway) {
        this.areaPlantioGateway = areaPlantioGateway;
    }

    public ResponseEntity<AreaPlantio> NovaAreaPlantio(@RequestParam String nomeIdentificador,
                                                       @RequestParam String dimensao,
                                                       @RequestParam String gps,
                                                       @RequestParam int tamanhoEixoX,
                                                       @RequestParam int tamanhoEixoY)
    { return areaPlantioGateway.NovaAreaPlantio(nomeIdentificador, dimensao, gps, tamanhoEixoX, tamanhoEixoY);}

}
