package App.Infra.UseCase.Planta;

import App.Infra.Gateway.PlantaGateway;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

public class UseCasePlantaPut {

    private final PlantaGateway plantaGateway;

    public UseCasePlantaPut(PlantaGateway plantaGateway) {
        this.plantaGateway = plantaGateway;
    }

    public ResponseEntity<Void> EditarInformacaoPlanta(@RequestParam Long id,
                                                       @RequestParam String nomeCientifico,
                                                       @RequestParam String nomePopular,
                                                       @RequestParam String instrucoes)
    {return plantaGateway.EditarInformacaoPlanta(id, nomeCientifico, nomePopular, instrucoes);}

    public Boolean AtualizaCiclo(@RequestParam String codigoPlanta,
                                 @RequestParam String codigoSubarea,
                                 @RequestParam String faseatual)
    {return plantaGateway.AtualizaCiclo(codigoPlanta, codigoSubarea, faseatual);}

}
