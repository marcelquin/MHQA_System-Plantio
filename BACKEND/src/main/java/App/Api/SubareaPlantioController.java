package App.Api;

import App.Domain.Bussness.SubareaPlantioService;
import App.Domain.Response.SubAreaPlantio;
import App.Infra.Persistence.Enum.TAMANHO;
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

    private final SubareaPlantioService subareaPlantioService;

    public SubareaPlantioController(SubareaPlantioService subareaPlantioService) {
        this.subareaPlantioService = subareaPlantioService;
    }

    @Operation(summary = "Busca Registro da tabela", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algoo deu errado"),
    })
    @GetMapping("/ListarSubareas")
    public ResponseEntity<List<SubAreaPlantio>> ListarSubareas()
    {return subareaPlantioService.ListarSubareas();}

    @Operation(summary = "Busca Registro da tabela", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algoo deu errado"),
    })
    @GetMapping("/BuscarSubAreaPorId")
    public ResponseEntity<SubAreaPlantio> BuscarSubAreaPorId(@RequestParam Long id)
    {return subareaPlantioService.BuscarSubAreaPorId(id);}

    @Operation(summary = "Busca Registro da tabela", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algoo deu errado"),
    })
    @GetMapping("/BuscarSubAreaPorNumero")
    public ResponseEntity<SubAreaPlantio> BuscarSubAreaPorNumero(@RequestParam int numero)
    {return subareaPlantioService.BuscarSubAreaPorNumero(numero);}

    @Operation(summary = "Salva novo Registro na tabela", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algoo deu errado"),
    })
    @PostMapping("/AdicionarNovaSubArea")
    public ResponseEntity<SubAreaPlantio> AdicionarNovaSubArea(@RequestParam String cor,
                                                               @RequestParam TAMANHO tamanho,
                                                               @RequestParam String nomeAreaPlantio)
    {return subareaPlantioService.AdicionarNovaSubArea(cor, tamanho, nomeAreaPlantio);}

    @Operation(summary = "Edita Registro na tabela", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algoo deu errado"),
    })
    @PutMapping("/AdubacaoSubAreaIndividual")
    public ResponseEntity<SubAreaPlantio> AdubacaoSubAreaIndividual(@RequestParam String nomeAreaPlantio ,
                                                                    @RequestParam int numeroSubarea,
                                                                    @RequestParam String resumoAdubacao)
    {return subareaPlantioService.AdubacaoSubAreaIndividual(nomeAreaPlantio, numeroSubarea, resumoAdubacao);}
    
}
