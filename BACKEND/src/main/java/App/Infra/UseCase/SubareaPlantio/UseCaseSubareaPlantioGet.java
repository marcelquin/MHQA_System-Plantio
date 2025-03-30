package App.Infra.UseCase.SubareaPlantio;

import App.Domain.Response.SubAreaPlantio;
import App.Infra.Gateway.SubareaGateway;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public class UseCaseSubareaPlantioGet {

    private final SubareaGateway subareaGateway;

    public UseCaseSubareaPlantioGet(SubareaGateway subareaGateway) {
        this.subareaGateway = subareaGateway;
    }

    public ResponseEntity<List<SubAreaPlantio>> ListarSubareas()
    {return subareaGateway.ListarSubareas();}

    public ResponseEntity<SubAreaPlantio> BuscarSubAreaPorId(@RequestParam Long id)
    {return subareaGateway.BuscarSubAreaPorId(id);}

    public ResponseEntity<SubAreaPlantio> BuscarSubAreaPorCodigo(@RequestParam String codigo)
    {return subareaGateway.BuscarSubAreaPorCodigo(codigo);}
}
