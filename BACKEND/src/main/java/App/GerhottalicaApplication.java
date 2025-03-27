package App;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Gerencia de Horta",
		version = "1",
		description = "Manipula informaçoes e organiza fases atuais de plantio"))
public class GerhottalicaApplication {

	public static void main(String[] args) {
		SpringApplication.run(GerhottalicaApplication.class, args);
	}

}
