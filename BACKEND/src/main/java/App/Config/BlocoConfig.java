package App.Config;

import App.Infra.Gateway.BlocoGateway;
import App.Infra.UseCase.Bloco.UseCaseBlocoGet;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BlocoConfig {

    @Bean
    UseCaseBlocoGet useCaseBlocoGet(BlocoGateway blocoGateway)
    {return new UseCaseBlocoGet(blocoGateway); }
}
