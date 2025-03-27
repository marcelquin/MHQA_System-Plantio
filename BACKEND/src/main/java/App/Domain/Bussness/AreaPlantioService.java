package App.Domain.Bussness;

import App.Domain.Response.AreaPlantio;
import App.Domain.Response.Planta;
import App.Domain.Response.SubAreaPlantio;
import App.Domain.Util.AreaPlantioMapper;
import App.Infra.Exceptions.EntityNotFoundException;
import App.Infra.Exceptions.NullargumentsException;
import App.Infra.Persistence.Entity.AreaPlantioEntity;
import App.Infra.Persistence.Entity.SubAreaPlantioEntity;
import App.Infra.Persistence.Repository.AreaPlantioRepositoty;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class AreaPlantioService {

    private final AreaPlantioRepositoty areaPlantioRepositoty;
    private final AreaPlantioMapper areaPlantioMapper;
    private final SubareaPlantioService subareaPlantioService;
    private final PlantaService plantaService;


    public AreaPlantioService(AreaPlantioRepositoty areaPlantioRepositoty, AreaPlantioMapper areaPlantioMapper, @Lazy SubareaPlantioService subareaPlantioService, @Lazy PlantaService plantaService) {
        this.areaPlantioRepositoty = areaPlantioRepositoty;
        this.areaPlantioMapper = areaPlantioMapper;
        this.subareaPlantioService = subareaPlantioService;
        this.plantaService = plantaService;
    }

    public ResponseEntity<List<AreaPlantio>> ListarAreas()
    {
        try
        {
            List<AreaPlantioEntity> list = areaPlantioRepositoty.findAll();
            List<AreaPlantio> response = new ArrayList<>();
            for(AreaPlantioEntity entity : list)
            {
                AreaPlantio dto = areaPlantioMapper.EntityToDto(entity);
                response.add(dto);
            }
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        catch (Exception e)
        {
            e.getMessage();
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<AreaPlantio> BuscarAreaPlantioPorId(Long id)
    {
        try
        {
            if(id != null)
            {
                AreaPlantioEntity entity = areaPlantioRepositoty.findById(id).orElseThrow(
                        EntityNotFoundException::new
                );
                AreaPlantio response = areaPlantioMapper.EntityToDto(entity);
                return new ResponseEntity<>(response,HttpStatus.OK);
            }
            else {throw new NullargumentsException();}
        }
        catch (Exception e)
        {
            e.getMessage();
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<AreaPlantio> BuscarAreaPlantioPorNome(String nome)
    {
        try
        {
            if(nome != null)
            {
                AreaPlantioEntity entity = areaPlantioRepositoty.findBynomeIdentificador(nome).orElseThrow(
                        EntityNotFoundException::new
                );
                AreaPlantio response = areaPlantioMapper.EntityToDto(entity);
                return new ResponseEntity<>(response,HttpStatus.OK);
            }
            else {throw new NullargumentsException();}
        }
        catch (Exception e)
        {
            e.getMessage();
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }


    public ResponseEntity<AreaPlantio> NovaAreaPlantio(String nomeIdentificador, String dimencao, String gps)
    {
        try
        {
            if(nomeIdentificador != null &&
               dimencao != null &&
               gps != null)
            {
                int dig = (int) (1111 + Math.random() * 9999);
                String codigo = nomeIdentificador+"_"+dig;
                List<SubAreaPlantioEntity> subareaPlantioEntities = new ArrayList<>();
                List<String> notificacoes = new ArrayList<>();
                AreaPlantioEntity entity = new AreaPlantioEntity();
                entity.setDimencao(dimencao);
                entity.setNomeIdentificador(nomeIdentificador);
                entity.setGps(gps);
                entity.setCodigo(codigo);
                entity.setSubareas(subareaPlantioEntities);
                entity.setNotificacoes(notificacoes);
                entity.setTimeStamp(LocalDateTime.now());
                areaPlantioRepositoty.save(entity);
                AreaPlantio response = areaPlantioMapper.EntityToDto(entity);
                return new ResponseEntity<>(response,HttpStatus.CREATED);
            }
            else {throw new NullargumentsException();}
        }
        catch (Exception e)
        {
            e.getMessage();
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<Void> SalvarAlteracaoAreaPlantio(AreaPlantio areaPlantio)
    {
        try
        {
            AreaPlantioEntity entity = areaPlantioMapper.DtoToEntity(areaPlantio);
            areaPlantioRepositoty.save(entity);
            return new ResponseEntity<>(HttpStatus.OK);
            /*if(areaPlantio != null)
            {
                AreaPlantioEntity entity = areaPlantioMapper.DtoToEntity(areaPlantio);
                areaPlantioRepositoty.save(entity);
                return new ResponseEntity<>(HttpStatus.OK);
            }
            else {throw new NullargumentsException();}*/
        }
        catch (Exception e)
        {
            e.getMessage();
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<Void> AdubacaoAreaPlantioGeral(String nomeIdentificador, String adubacao)
    {
        try
        {
            if(nomeIdentificador != null &&
               adubacao != null)
            {

                AreaPlantio response = BuscarAreaPlantioPorNome(nomeIdentificador).getBody();
                AreaPlantioEntity entity = areaPlantioMapper.DtoToEntity(response);
                System.out.println(entity.getSubareas());
                for(SubAreaPlantioEntity subAreaPlantio : entity.getSubareas())
                {
                    subareaPlantioService.AdubacaoSubAreaIndividual(nomeIdentificador,subAreaPlantio.getNumero(),adubacao);
                }
                response.Adubacao(adubacao);
                this.SalvarAlteracaoAreaPlantio(response);
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
