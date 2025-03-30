package App.Config;

import App.Infra.Gateway.PlantaGateway;
import App.Infra.UseCase.Planta.UseCasePlantaGet;
import App.Infra.UseCase.Planta.UseCasePlantaPost;
import App.Infra.UseCase.Planta.UseCasePlantaPut;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PlantaConfig {

    @Bean
    UseCasePlantaGet useCasePlantaGet(PlantaGateway plantaGateway)
    {return new UseCasePlantaGet(plantaGateway);}
    @Bean
    UseCasePlantaPost useCasePlantaPost(PlantaGateway plantaGateway)
    {return new UseCasePlantaPost(plantaGateway);}
    @Bean
    UseCasePlantaPut useCasePlantaPut(PlantaGateway plantaGateway)
    {return new UseCasePlantaPut(plantaGateway);}
}
