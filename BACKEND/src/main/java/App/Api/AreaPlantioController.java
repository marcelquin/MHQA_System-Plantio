package App.Api;

import App.Domain.Bussness.AreaPlantioService;
import App.Domain.Response.AreaPlantio;
import App.Infra.UseCase.AreaPlantio.UseCaseAreaPlantioGet;
import App.Infra.UseCase.AreaPlantio.UseCaseAreaPlantioPost;
import App.Infra.UseCase.AreaPlantio.UseCaseAreaPlantioPut;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("areaPlantio")
@Tag(name = "Area Plantio",
        description = "Manipula dados referente a entidade"
)
@CrossOrigin(origins = "*")
public class AreaPlantioController {

    private final UseCaseAreaPlantioGet caseAreaPlantioGet;
    private final UseCaseAreaPlantioPost caseAreaPlantioPost;
    private final UseCaseAreaPlantioPut caseAreaPlantioPut;

    public AreaPlantioController(UseCaseAreaPlantioGet caseAreaPlantioGet, UseCaseAreaPlantioPost caseAreaPlantioPost, UseCaseAreaPlantioPut caseAreaPlantioPut) {
        this.caseAreaPlantioGet = caseAreaPlantioGet;
        this.caseAreaPlantioPost = caseAreaPlantioPost;
        this.caseAreaPlantioPut = caseAreaPlantioPut;
    }


    @Operation(summary = "Lista Registros da tabela", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algoo deu errado"),
    })
    @GetMapping("/ListarAreas")
    public ResponseEntity<List<AreaPlantio>> ListarAreas()
    {return caseAreaPlantioGet.ListarAreas();}

    @Operation(summary = "Busca Registro da tabela", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algoo deu errado"),
    })
    @GetMapping("/BuscarAreaPlantioPorId")
    public ResponseEntity<AreaPlantio> BuscarAreaPlantioPorId(Long id)
    {return caseAreaPlantioGet.BuscarAreaPlantioPorId(id);}

    @Operation(summary = "Busca Registro da tabela", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algoo deu errado"),
    })
    @GetMapping("/BuscarAreaPlantioPorNome")
    public ResponseEntity<AreaPlantio> BuscarAreaPlantioPorNome(String nome)
    {return caseAreaPlantioGet.BuscarAreaPlantioPorNome(nome);}

    @Operation(summary = "Salva novo Registro na tabela", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algoo deu errado"),
    })
    @PostMapping("/NovaAreaPlantio")
    public ResponseEntity<AreaPlantio> NovaAreaPlantio(@RequestParam String nomeIdentificador,
                                                       @RequestParam String dimensao,
                                                       @RequestParam String gps,
                                                       @RequestParam int tamanhoEixoX,
                                                       @RequestParam int tamanhoEixoY)
    {return caseAreaPlantioPost.NovaAreaPlantio(nomeIdentificador, dimensao, gps,tamanhoEixoX,tamanhoEixoY);}

    @Operation(summary = "Edita Registro na tabela", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algoo deu errado"),
    })
    @PutMapping("/AdubacaoAreaPlantioGeral")
    public ResponseEntity<Void> AdubacaoAreaPlantioGeral(@RequestParam String nomeIdentificador,
                                                         @RequestParam String adubacao)
    {return caseAreaPlantioPut.AdubacaoAreaPlantioGeral(nomeIdentificador, adubacao);}


    @Operation(summary = "Edita Registro na tabela", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algoo deu errado"),
    })
    @PutMapping("/EditarAreaPlantio")
    public ResponseEntity<Void> EditarAreaPlantio(@RequestParam Long id,
                                                  @RequestParam String nomeIdentificador,
                                                  @RequestParam String dimensao,
                                                  @RequestParam String gps,
                                                  @RequestParam int eixoX,
                                                  @RequestParam int eixoY)
    { return caseAreaPlantioPut.EditarAreaPlantio(id, nomeIdentificador, dimensao, gps, eixoX, eixoY);}

    @Operation(summary = "Edita Registro na tabela", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algoo deu errado"),
    })
    @PutMapping("/teste")
    public  void teste(@RequestParam int rows, @RequestParam int cols)
    {
        int basex = 3;
        int[][] matrix = new int[rows][cols];
        for (int i = 0; i < cols; i++) {
            for (int j = 0; j < rows; j++) {
                if(basex > i)
                {
                    System.out.println("menor:");
                    int eixoX = i+1;
                    int eixoY = j+1;
                    System.out.println("eixo x: "+eixoX);
                    System.out.println("eixo y: "+eixoY);
                }
                else
                {
                    System.out.println("maior:");
                    int eixoX = i+1;
                    int eixoY = j+1;
                    System.out.println("eixo x: "+eixoX);
                    System.out.println("eixo y: "+eixoY);
                }
                //System.out.println("padrão: "+i+"---"+j);
            }
        }
    }
}
