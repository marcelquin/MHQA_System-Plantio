package App.Api;

import App.Domain.Bussness.PlantaService;
import App.Domain.Response.Planta;
import App.Infra.Persistence.Enum.FASEATUAL;
import App.Infra.UseCase.Planta.UseCasePlantaGet;
import App.Infra.UseCase.Planta.UseCasePlantaPost;
import App.Infra.UseCase.Planta.UseCasePlantaPut;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("planta")
@Tag(name = "planta",
        description = "Manipula dados referente a entidade"
)
@CrossOrigin(origins = "*")
public class PlantaController {

    private final UseCasePlantaGet casePlantaGet;
    private final UseCasePlantaPost casePlantaPost;
    private final UseCasePlantaPut casePlantaPut;

    public PlantaController(UseCasePlantaGet casePlantaGet, UseCasePlantaPost casePlantaPost, UseCasePlantaPut casePlantaPut) {
        this.casePlantaGet = casePlantaGet;
        this.casePlantaPost = casePlantaPost;
        this.casePlantaPut = casePlantaPut;
    }

    @Operation(summary = "Lista Registros da tabela", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algoo deu errado"),
    })
    @GetMapping("/ListarPlantas")
    public ResponseEntity<List<Planta>>ListarPlantas()
    {return casePlantaGet.ListarPlantas();}

    @Operation(summary = "Lista Registros da tabela", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algoo deu errado"),
    })
    @GetMapping("/ListarPlantasGerminacao")
    public ResponseEntity<List<Planta>>ListarPlantasGerminacao()
    {return casePlantaGet.ListarPlantasGerminacao();}

    @Operation(summary = "Lista Registros da tabela", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algoo deu errado"),
    })
    @GetMapping("/ListarPlantasMudas")
    public ResponseEntity<List<Planta>>ListarPlantasMudas()
    {return casePlantaGet.ListarPlantasMudas();}

    @Operation(summary = "Lista Registros da tabela", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algoo deu errado"),
    })
    @GetMapping("/ListarPlantasProducao")
    public ResponseEntity<List<Planta>>ListarPlantasProducao()
    {return casePlantaGet.ListarPlantasProducao();}

    @Operation(summary = "Lista Registros da tabela", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algoo deu errado"),
    })
    @GetMapping("/ListarPlantasFimCiclo")
    public ResponseEntity<List<Planta>>ListarPlantasFimCiclo()
    {return casePlantaGet.ListarPlantasFimCiclo();}

    @Operation(summary = "Lista Registros da tabela", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algoo deu errado"),
    })
    @GetMapping("/ListarPlantasCrescimento")
    public ResponseEntity<List<Planta>>ListarPlantasCrescimento()
    {return casePlantaGet.ListarPlantasCrescimento();}

    @Operation(summary = "Lista Registros da tabela", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algoo deu errado"),
    })
    @GetMapping("/ListarPlantasMaturacao")
    public ResponseEntity<List<Planta>>ListarPlantasMaturacao()
    {return casePlantaGet.ListarPlantasMaturacao();}

    @Operation(summary = "Lista Registros da tabela", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algoo deu errado"),
    })
    @GetMapping("/ListarPlantasFloracao")
    public ResponseEntity<List<Planta>>ListarPlantasFloracao()
    {return casePlantaGet.ListarPlantasFloracao();}

    @Operation(summary = "Lista Registros da tabela", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algoo deu errado"),
    })
    @GetMapping("/ListarPlantasFrutificacao")
    public ResponseEntity<List<Planta>>ListarPlantasFrutificacao()
    {return casePlantaGet.ListarPlantasFrutificacao();}


    @Operation(summary = "Busca Registro da tabela", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algoo deu errado"),
    })
    @GetMapping("/BuscarPlantaPorId")
    public ResponseEntity<Planta>BuscarPlantaPorId(@RequestParam Long id)
    {return casePlantaGet.BuscarPlantaPorId(id);}


    @Operation(summary = "Busca Registro da tabela", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algoo deu errado"),
    })
    @GetMapping("/BuscarPlantaPorCodigo")
    public ResponseEntity<Planta>BuscarPlantaPorCodigo(@RequestParam String codigo)
    {return casePlantaGet.BuscarPlantaPorCodigo(codigo);}


    @Operation(summary = "Salva novo Registro na tabela", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algoo deu errado"),
    })
    @PostMapping("/AdicionarNovaPlanta")
    public ResponseEntity<Planta> AdicionarNovaPlanta(@RequestParam String nomeCientifico,
                                            @RequestParam String nomePopular,
                                            @RequestParam String instrucoes,
                                            @RequestParam String codigoSubarea)
    {return casePlantaPost.AdicionarNovaPlanta(nomeCientifico, nomePopular, instrucoes,codigoSubarea);}

    @Operation(summary = "Edita Registro na tabela", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algoo deu errado"),
    })
    @PutMapping("/EditarInformacaoPlanta")
    public ResponseEntity<Void> EditarInformacaoPlanta(@RequestParam Long id,
                                                       @RequestParam String nomeCientifico,
                                                       @RequestParam String nomePopular,
                                                       @RequestParam String instrucoes)
    {return casePlantaPut.EditarInformacaoPlanta(id, nomeCientifico, nomePopular, instrucoes);}

    @Operation(summary = "Edita Registro na tabela", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algoo deu errado"),
    })
    @PutMapping("/AtualizaCiclo")
    public Boolean AtualizaCiclo(@RequestParam String codigoPlanta,
                                 @RequestParam String codigoSubarea,
                                 @RequestParam String faseatual)
    { return casePlantaPut.AtualizaCiclo(codigoPlanta,codigoSubarea, faseatual);}


}
