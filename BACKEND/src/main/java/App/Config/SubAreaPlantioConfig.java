package App.Config;

import App.Infra.Gateway.SubareaGateway;
import App.Infra.UseCase.SubareaPlantio.UseCaseSubareaPlantioGet;
import App.Infra.UseCase.SubareaPlantio.UseCaseSubareaPlantioPut;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SubAreaPlantioConfig {

    @Bean
    UseCaseSubareaPlantioGet useCaseSubareaPlantioGet(SubareaGateway subareaGateway)
    {return new UseCaseSubareaPlantioGet(subareaGateway);}
    @Bean
    UseCaseSubareaPlantioPut useCaseSubareaPlantioPut(SubareaGateway subareaGateway)
    {return new UseCaseSubareaPlantioPut(subareaGateway);}
}
