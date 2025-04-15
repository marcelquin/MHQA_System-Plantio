package App.Infra.Gateway;

import App.Domain.Response.Area;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface AreaGateway {

    public ResponseEntity<List<Area>> ListarAreas();

    public ResponseEntity<Area> BuscarAreaPorId(@RequestParam Long id);

    public ResponseEntity<Area> NovaArea(@RequestParam String dimensao,
                                         @RequestParam String nomeIdentificador,
                                         @RequestParam int eixoX,
                                         @RequestParam int eixoY,
                                         @RequestParam  int quantidadeBlocos);

    public ResponseEntity<Area> EditarArea(@RequestParam Long id,
                                           @RequestParam String dimensao,
                                           @RequestParam String nomeIdentificador);

    public ResponseEntity<Area> NovaAdubacao(@RequestParam Long id, @RequestParam String relatorio);

}
