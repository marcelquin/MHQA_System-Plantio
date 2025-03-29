package App.Api;


import App.Domain.Bussness.ProcessoService;
import App.Infra.Persistence.Enum.FASEATUAL;
import App.Infra.Persistence.Enum.TAMANHO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("processo")
@Tag(name = "Processo",  description = "Manipula dados referente a entidade"
)
@CrossOrigin(origins = "*")
public class ProcessoController {

    private final ProcessoService service;


    public ProcessoController(ProcessoService service) {
        this.service = service;
    }


    @Operation(summary = "Salva novo Registro na tabela", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algoo deu errado"),
    })
    @PostMapping("/NovaPlanta")
    public ResponseEntity<Void> NovaPlanta(@RequestParam String nomeCientifico,
                                           @RequestParam String nomePopular,
                                           @RequestParam String instrucoes,
                                           @RequestParam int quantidade)
    { return service.NovaPlanta(nomeCientifico, nomePopular, instrucoes, quantidade);}


}
