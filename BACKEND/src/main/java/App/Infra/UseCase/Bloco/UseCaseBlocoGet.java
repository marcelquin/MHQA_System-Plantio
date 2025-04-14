package App.Infra.UseCase.Bloco;

import App.Domain.Response.Bloco;
import App.Infra.Gateway.BlocoGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class UseCaseBlocoGet {

    private final BlocoGateway blocoGateway;

    public UseCaseBlocoGet(BlocoGateway blocoGateway) {
        this.blocoGateway = blocoGateway;
    }

    public ResponseEntity<List<Bloco>> ListarBlocos()
    {return blocoGateway.ListarBlocos();}

    public ResponseEntity<List<Bloco>> ListarBlocosDisponiveis()
    {return blocoGateway.ListarBlocosDisponiveis();}

    public ResponseEntity<List<Bloco>> ListarBlocosUtilizados()
    {return blocoGateway.ListarBlocosUtilizados();}
}
