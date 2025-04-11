package App;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Gerencia de Horta",
		version = "2",
		description = "Gerencia de Plantio de pequeno porte"))
public class Gerhortav2Application {

	public static void main(String[] args) {
		SpringApplication.run(Gerhortav2Application.class, args);
	}

}
