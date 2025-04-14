package App.Infra.Gateway;

import App.Domain.Response.Planta;
import App.Infra.Persistence.Enum.CICLO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface PlantaGateway {

    public ResponseEntity<List<Planta>> ListarPlantas();

    public ResponseEntity<List<Planta>> ListarPlantasGerminacao();

    public ResponseEntity<List<Planta>> ListarPlantasMuda();

    public ResponseEntity<List<Planta>> ListarPlantasCrescimento();

    public ResponseEntity<List<Planta>> ListarPlantasFloracao();

    public ResponseEntity<List<Planta>> ListarPlantasFrutificacao();

    public ResponseEntity<List<Planta>> ListarPlantasMaturacao();

    public ResponseEntity<List<Planta>> ListarPlantasFimCiclo();

    public ResponseEntity<Planta> BuscarPlantaPorId(@RequestParam Long id);

    public ResponseEntity<Planta> NovaPlanta(@RequestParam Long plantioId,
                                             @RequestParam Long localizacaoId,
                                             @RequestParam Long blocoId,
                                             @RequestParam String nomeCientifico,
                                             @RequestParam String nomePopular,
                                             @RequestParam String instrucoes);

    public ResponseEntity<Planta> EditarPlanta(@RequestParam Long plantaId,
                                               @RequestParam String nomeCientifico,
                                               @RequestParam String nomePopular,
                                               @RequestParam String instrucoes);

    public ResponseEntity<Planta> AlterarCiclo(@RequestParam Long id,
                                               @RequestParam String ciclo);

}
