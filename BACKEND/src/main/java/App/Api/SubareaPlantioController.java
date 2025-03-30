package App.Api;

import App.Domain.Bussness.SubareaPlantioService;
import App.Domain.Response.SubAreaPlantio;
import App.Infra.Persistence.Enum.TAMANHO;
import App.Infra.UseCase.SubareaPlantio.UseCaseSubareaPlantioGet;
import App.Infra.UseCase.SubareaPlantio.UseCaseSubareaPlantioPut;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("subareaPlantio")
@Tag(name = "Subarea Plantio",
        description = "Manipula dados referente a entidade"
)
@CrossOrigin(origins = "*")
public class SubareaPlantioController {

    private final UseCaseSubareaPlantioGet caseSubareaPlantioGet;
    private final UseCaseSubareaPlantioPut caseSubareaPlantioPut;

    public SubareaPlantioController(UseCaseSubareaPlantioGet caseSubareaPlantioGet, UseCaseSubareaPlantioPut caseSubareaPlantioPut) {
        this.caseSubareaPlantioGet = caseSubareaPlantioGet;
        this.caseSubareaPlantioPut = caseSubareaPlantioPut;
    }


    @Operation(summary = "Lista Registros da tabela", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algoo deu errado"),
    })
    @GetMapping("/ListarSubareas")
    public ResponseEntity<List<SubAreaPlantio>> ListarSubareas()
    {return caseSubareaPlantioGet.ListarSubareas();}

    @Operation(summary = "Busca Registro da tabela", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algoo deu errado"),
    })
    @GetMapping("/BuscarSubAreaPorId")
    public ResponseEntity<SubAreaPlantio> BuscarSubAreaPorId(@RequestParam Long id)
    {return caseSubareaPlantioGet.BuscarSubAreaPorId(id);}

    @Operation(summary = "Busca Registro da tabela", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algoo deu errado"),
    })
    @GetMapping("/BuscarSubAreaPorCodigo")
    public ResponseEntity<SubAreaPlantio> BuscarSubAreaPorCodigo(String codigo)
    {return caseSubareaPlantioGet.BuscarSubAreaPorCodigo(codigo);}

    @Operation(summary = "Edita Registro na tabela", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algoo deu errado"),
    })
    @PutMapping("/AdubacaoSubAreaIndividual")
    public ResponseEntity<SubAreaPlantio> AdubacaoSubAreaIndividual(@RequestParam String codigo,
                                                                    @RequestParam String resumoAdubacao)
    {return caseSubareaPlantioPut.AdubacaoSubAreaIndividual(codigo, resumoAdubacao);}
    
}
