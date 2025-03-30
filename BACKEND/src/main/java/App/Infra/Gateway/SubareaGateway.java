package App.Infra.Gateway;

import App.Domain.Response.SubAreaPlantio;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface SubareaGateway {

    public ResponseEntity<List<SubAreaPlantio>> ListarSubareas();

    public ResponseEntity<SubAreaPlantio> BuscarSubAreaPorId(@RequestParam Long id);

    public ResponseEntity<SubAreaPlantio> BuscarSubAreaPorCodigo(String codigo);


    public ResponseEntity<SubAreaPlantio> AdubacaoSubAreaIndividual(@RequestParam String codigo,
                                                                    @RequestParam String resumoAdubacao);
}
