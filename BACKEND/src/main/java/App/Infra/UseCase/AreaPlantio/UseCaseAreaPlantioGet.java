package App.Infra.UseCase.AreaPlantio;

import App.Domain.Response.AreaPlantio;
import App.Infra.Gateway.AreaPlantioGateway;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public class UseCaseAreaPlantioGet {

    private final AreaPlantioGateway areaPlantioGateway;

    public UseCaseAreaPlantioGet(AreaPlantioGateway areaPlantioGateway) {
        this.areaPlantioGateway = areaPlantioGateway;
    }

    public ResponseEntity<List<AreaPlantio>> ListarAreas()
    {return areaPlantioGateway.ListarAreas();}

    public ResponseEntity<AreaPlantio> BuscarAreaPlantioPorId(@RequestParam Long id)
    {return areaPlantioGateway.BuscarAreaPlantioPorId(id);}

    public ResponseEntity<AreaPlantio> BuscarAreaPlantioPorNome(@RequestParam String nome)
    {return areaPlantioGateway.BuscarAreaPlantioPorNome(nome);}

}
