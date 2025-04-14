package App.Api;

import App.Domain.Response.Bloco;
import App.Infra.UseCase.Bloco.UseCaseBlocoGet;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("Bloco")
@Tag(name = "Bloco", description = "Manipula dados relacioados a entidade")
@CrossOrigin(origins = "*")
public class BlocoController {

    private final UseCaseBlocoGet caseBlocoGet;

    public BlocoController(UseCaseBlocoGet caseBlocoGet) {
        this.caseBlocoGet = caseBlocoGet;
    }

    @Operation(summary = "Lista Registros da tabela", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algoo deu errado"),
    })
    @GetMapping("ListarBlocos")
    public ResponseEntity<List<Bloco>> ListarBlocos()
    { return caseBlocoGet.ListarBlocos();}

    @Operation(summary = "Lista Registros da tabela", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algoo deu errado"),
    })
    @GetMapping("ListarBlocosDisponiveis")
    public ResponseEntity<List<Bloco>> ListarBlocosDisponiveis()
    { return caseBlocoGet.ListarBlocosDisponiveis();}

    @Operation(summary = "Lista Registros da tabela", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algoo deu errado"),
    })
    @GetMapping("ListarBlocosUtilizados")
    public ResponseEntity<List<Bloco>> ListarBlocosUtilizados()
    { return caseBlocoGet.ListarBlocosUtilizados();}

}
