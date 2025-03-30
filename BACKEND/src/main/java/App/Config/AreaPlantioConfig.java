package App.Config;

import App.Infra.Gateway.AreaPlantioGateway;
import App.Infra.UseCase.AreaPlantio.UseCaseAreaPlantioGet;
import App.Infra.UseCase.AreaPlantio.UseCaseAreaPlantioPost;
import App.Infra.UseCase.AreaPlantio.UseCaseAreaPlantioPut;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AreaPlantioConfig {

    @Bean
    UseCaseAreaPlantioGet useCaseAreaPlantioGet(AreaPlantioGateway areaPlantioGateway)
    {return new UseCaseAreaPlantioGet(areaPlantioGateway);}
    @Bean
    UseCaseAreaPlantioPost useCaseAreaPlantioPost(AreaPlantioGateway areaPlantioGateway)
    {return new UseCaseAreaPlantioPost(areaPlantioGateway);}
    @Bean
    UseCaseAreaPlantioPut useCaseAreaPlantioPut(AreaPlantioGateway areaPlantioGateway)
    {return new UseCaseAreaPlantioPut(areaPlantioGateway);}
}
