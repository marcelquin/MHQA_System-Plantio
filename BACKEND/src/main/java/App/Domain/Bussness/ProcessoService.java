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

    public ResponseEntity<Void> NovaSubArea(String cor, TAMANHO tamanho, String nomeAreaPlantio, int quantidade)
    {
        try
        {
            if(cor != null && tamanho != null && nomeAreaPlantio != null && quantidade > 0)
            {
                AreaPlantio areaPlantio = areaPlantioService.BuscarAreaPlantioPorNome(nomeAreaPlantio).getBody();
                for(int i = 0;i < quantidade;i++)
                {
                    SubAreaPlantio subAreaPlantio = subareaPlantioService.AdicionarNovaSubArea(cor, tamanho, nomeAreaPlantio).getBody();
                    areaPlantio.getSubareas().add(subAreaPlantio);
                }
                areaPlantio.setTimeStamp(LocalDateTime.now());
                areaPlantioService.SalvarAlteracaoAreaPlantio(areaPlantio);
                return new ResponseEntity<>(HttpStatus.CREATED);
            }
            else {throw new NullargumentsException();}
        }
        catch (Exception e)
        {
            e.getMessage();
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

    }

    public ResponseEntity<Void> NovaPlanta(String nomeCientifico,
                                           String nomePopular,
                                           String instrucoes,
                                           Boolean cavalo,
                                           int quantidade)
    {
        try
        {
            if(nomeCientifico != null && nomePopular != null && instrucoes != null && cavalo != null && quantidade >0)
            {
                for(int i = 0; i < quantidade; i++)
                {
                    plantaService.AdicionarNovaPlanta(nomeCientifico, nomePopular, instrucoes, cavalo);
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
