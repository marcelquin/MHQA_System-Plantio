package App.Infra.Gateway;

import App.Domain.Response.AreaPlantio;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface AreaPlantioGateway {

    public ResponseEntity<List<AreaPlantio>> ListarAreas();

    public ResponseEntity<AreaPlantio> BuscarAreaPlantioPorId(@RequestParam Long id);

    public ResponseEntity<AreaPlantio> BuscarAreaPlantioPorNome(@RequestParam String nome);

    public ResponseEntity<AreaPlantio> NovaAreaPlantio(@RequestParam String nomeIdentificador,
                                                       @RequestParam String dimensao,
                                                       @RequestParam String gps,
                                                       @RequestParam int tamanhoEixoX,
                                                       @RequestParam int tamanhoEixoY);


    public ResponseEntity<Void> EditarAreaPlantio(@RequestParam Long id,
                                                  @RequestParam String nomeIdentificador,
                                                  @RequestParam String dimensao,
                                                  @RequestParam String gps,
                                                  @RequestParam int eixoX,
                                                  @RequestParam int eixoY);

    public ResponseEntity<Void> AdubacaoAreaPlantioGeral(@RequestParam String nomeIdentificador,
                                                         @RequestParam String adubacao);
}
