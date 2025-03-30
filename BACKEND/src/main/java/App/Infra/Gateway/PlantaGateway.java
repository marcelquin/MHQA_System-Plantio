package App.Infra.Gateway;

import App.Domain.Response.Planta;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface PlantaGateway {

    public ResponseEntity<List<Planta>> ListarPlantas();

    public ResponseEntity<List<Planta>>ListarPlantasGerminacao();

    public ResponseEntity<List<Planta>>ListarPlantasMudas();

    public ResponseEntity<List<Planta>>ListarPlantasProducao();

    public ResponseEntity<List<Planta>>ListarPlantasFimCiclo();

    public ResponseEntity<List<Planta>>ListarPlantasCrescimento();

    public ResponseEntity<List<Planta>>ListarPlantasMaturacao();

    public ResponseEntity<List<Planta>>ListarPlantasFloracao();

    public ResponseEntity<List<Planta>>ListarPlantasFrutificacao();

    public ResponseEntity<Planta>BuscarPlantaPorId(@RequestParam Long id);

    public ResponseEntity<Planta>BuscarPlantaPorCodigo(@RequestParam String codigo);

    public ResponseEntity<Planta> AdicionarNovaPlanta(@RequestParam String nomeCientifico,
                                    @RequestParam String nomePopular,
                                    @RequestParam String instrucoes,
                                    @RequestParam String codigoSubarea);

    public ResponseEntity<Void> EditarInformacaoPlanta(@RequestParam Long id,
                                                       @RequestParam String nomeCientifico,
                                                       @RequestParam String nomePopular,
                                                       @RequestParam String instrucoes);

    public Boolean AtualizaCiclo(@RequestParam String codigoPlanta,
                                 @RequestParam String codigoSubarea,
                                 @RequestParam String faseatual);

}
