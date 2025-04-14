package App.Infra.Gateway;

import App.Domain.Response.Bloco;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface BlocoGateway {

    public ResponseEntity<List<Bloco>> ListarBlocos();

    public ResponseEntity<List<Bloco>> ListarBlocosDisponiveis();

    public ResponseEntity<List<Bloco>> ListarBlocosUtilizados();
}
