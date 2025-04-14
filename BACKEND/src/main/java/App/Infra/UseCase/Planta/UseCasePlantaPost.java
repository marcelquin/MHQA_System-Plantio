package App.Infra.UseCase.Planta;

import App.Domain.Response.Planta;
import App.Infra.Gateway.PlantaGateway;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

public class UseCasePlantaPost {

    private final PlantaGateway plantaGateway;

    public UseCasePlantaPost(PlantaGateway plantaGateway) {
        this.plantaGateway = plantaGateway;
    }

    public ResponseEntity<Planta> NovaPlanta(@RequestParam Long plantioId,
                                             @RequestParam Long localizacaoId,
                                             @RequestParam Long blocoId,
                                             @RequestParam String nomeCientifico,
                                             @RequestParam String nomePopular,
                                             @RequestParam String instrucoes)
    {return plantaGateway.NovaPlanta(plantioId, localizacaoId, blocoId, nomeCientifico, nomePopular, instrucoes);}

}
