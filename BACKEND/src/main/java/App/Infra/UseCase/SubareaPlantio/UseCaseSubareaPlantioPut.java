package App.Infra.UseCase.SubareaPlantio;

import App.Domain.Response.SubAreaPlantio;
import App.Infra.Gateway.SubareaGateway;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

public class UseCaseSubareaPlantioPut {

    private final SubareaGateway subareaGateway;

    public UseCaseSubareaPlantioPut(SubareaGateway subareaGateway) {
        this.subareaGateway = subareaGateway;
    }

    public ResponseEntity<SubAreaPlantio> AdubacaoSubAreaIndividual(@RequestParam String codigo,
                                                                    @RequestParam String resumoAdubacao)
    {return subareaGateway.AdubacaoSubAreaIndividual(codigo, resumoAdubacao);}

}
