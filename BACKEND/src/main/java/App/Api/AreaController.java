package App.Api;

import App.Domain.Bussness.AreaService;
import App.Domain.Response.Area;
import App.Domain.Response.Localizacao;
import App.Infra.UseCase.Area.UseCaseAreaGet;
import App.Infra.UseCase.Area.UseCaseAreaPost;
import App.Infra.UseCase.Area.UseCaseAreaPut;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("Area")
@Tag(name = "Area", description = "Manipula dados relacioados a entidade")
@CrossOrigin(origins = "*")
public class AreaController {

    private final UseCaseAreaGet caseAreaGet;
    private final UseCaseAreaPost caseAreaPost;
    private final UseCaseAreaPut caseAreaPut;

    public AreaController(UseCaseAreaGet caseAreaGet, UseCaseAreaPost caseAreaPost, UseCaseAreaPut caseAreaPut) {
        this.caseAreaGet = caseAreaGet;
        this.caseAreaPost = caseAreaPost;
        this.caseAreaPut = caseAreaPut;
    }

    @Operation(summary = "Lista Registros da tabela", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algoo deu errado"),
    })
    @GetMapping("ListarAreas")
    public ResponseEntity<List<Area>> ListarAreas()
    {return caseAreaGet.ListarAreas();}


    @Operation(summary = "Busca Registros na tabela", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algoo deu errado"),
    })
    @GetMapping("BuscarAreaPorId/{id}")
    public ResponseEntity<Area> BuscarAreaPorId(@RequestParam Long id)
    {return caseAreaGet.BuscarAreaPorId(id);}

    @Operation(summary = "Salva Registros na tabela", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algoo deu errado"),
    })
    @PostMapping("NovaArea")
    public ResponseEntity<Area> NovaArea(@RequestParam String dimensao,
                                         @RequestParam String nomeIdentificador,
                                         @RequestParam int eixoX,
                                         @RequestParam int eixoY,
                                         @RequestParam  int quantidadeBlocos)
    {return caseAreaPost.NovaArea(dimensao, nomeIdentificador, eixoX,eixoY,quantidadeBlocos);}

    @Operation(summary = "Edita Registros na tabela", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algoo deu errado"),
    })
    @PutMapping("EditarArea")
    public ResponseEntity<Area> EditarArea(@RequestParam Long id,
                                           @RequestParam String dimensao,
                                           @RequestParam String nomeIdentificador)
    {return  caseAreaPut.EditarArea(id, dimensao, nomeIdentificador);}

    @Operation(summary = "Edita Registros na tabela", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Ops algoo deu errado"),
    })
    @PutMapping("NovaAdubacao")
    public ResponseEntity<Area> NovaAdubacao(@RequestParam Long id, @RequestParam String relatorio)
    {return caseAreaPut.NovaAdubacao(id, relatorio);}


}
