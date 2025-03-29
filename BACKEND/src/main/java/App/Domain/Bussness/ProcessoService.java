package App.Domain.Bussness;

import App.Domain.Response.AreaPlantio;
import App.Domain.Response.SubAreaPlantio;
import App.Infra.Exceptions.NullargumentsException;
import App.Infra.Persistence.Enum.TAMANHO;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ProcessoService {

    private final PlantaService plantaService;
    private final SubareaPlantioService subareaPlantioService;
    private final AreaPlantioService areaPlantioService;

    public ProcessoService(@Lazy PlantaService plantaService,@Lazy SubareaPlantioService subareaPlantioService,@Lazy AreaPlantioService areaPlantioService) {
        this.plantaService = plantaService;
        this.subareaPlantioService = subareaPlantioService;
        this.areaPlantioService = areaPlantioService;
    }

    public ResponseEntity<Void> NovaPlanta(String nomeCientifico,
                                           String nomePopular,
                                           String instrucoes,
                                           int quantidade)
    {
        try
        {
            if(nomeCientifico != null && nomePopular != null && instrucoes != null && quantidade >0)
            {
                for(int i = 0; i < quantidade; i++)
                {
                    plantaService.AdicionarNovaPlanta(nomeCientifico, nomePopular, instrucoes);
                }
                return new ResponseEntity<>(HttpStatus.OK);
            }
            else {throw new NullargumentsException();}
        }
        catch (Exception e)
        {
            e.getMessage();
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }




}
