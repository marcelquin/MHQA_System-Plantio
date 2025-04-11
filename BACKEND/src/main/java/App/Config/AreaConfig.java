package App.Config;

import App.Infra.Gateway.AreaGateway;
import App.Infra.UseCase.Area.UseCaseAreaGet;
import App.Infra.UseCase.Area.UseCaseAreaPost;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AreaConfig {

    @Bean
    UseCaseAreaGet useCaseAreaGet(AreaGateway areaGateway)
    {return new UseCaseAreaGet(areaGateway);}

    @Bean
    UseCaseAreaPost useCaseAreaPost(AreaGateway areaGateway)
    {return new UseCaseAreaPost(areaGateway);}
}
