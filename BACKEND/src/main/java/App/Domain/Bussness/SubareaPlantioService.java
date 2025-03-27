package App.Domain.Bussness;

import App.Domain.Response.AreaPlantio;
import App.Domain.Response.Planta;
import App.Domain.Response.SubAreaPlantio;
import App.Domain.Util.SubareaPlantioMapper;
import App.Infra.Exceptions.EntityNotFoundException;
import App.Infra.Exceptions.IllegalActionException;
import App.Infra.Exceptions.NullargumentsException;
import App.Infra.Persistence.Entity.AreaPlantioEntity;
import App.Infra.Persistence.Entity.PlantaEntity;
import App.Infra.Persistence.Entity.SubAreaPlantioEntity;
import App.Infra.Persistence.Enum.TAMANHO;
import App.Infra.Persistence.Repository.SubareaPlantioRepositoty;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class SubareaPlantioService {

    private final SubareaPlantioRepositoty subareaPlantioRepositoty;
    private final SubareaPlantioMapper subareaPlantioMapper;
    private final AreaPlantioService areaPlantioService;
    private final PlantaService plantaService;

    public SubareaPlantioService(SubareaPlantioRepositoty subareaPlantioRepositoty, SubareaPlantioMapper subareaPlantioMapper, @Lazy AreaPlantioService areaPlantioService,@Lazy PlantaService plantaService) {
        this.subareaPlantioRepositoty = subareaPlantioRepositoty;
        this.subareaPlantioMapper = subareaPlantioMapper;
        this.areaPlantioService = areaPlantioService;
        this.plantaService = plantaService;
    }

    public ResponseEntity<List<SubAreaPlantio>> ListarSubareas()
    {
        try
        {
            List<SubAreaPlantioEntity> list = subareaPlantioRepositoty.findAll();
            List<SubAreaPlantio> response = new ArrayList<>();
            for(SubAreaPlantioEntity entity : list)
            {
                SubAreaPlantio dto = subareaPlantioMapper.EntityToDto(entity);
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

    public ResponseEntity<SubAreaPlantio> BuscarSubAreaPorId(Long id)
    {
        try
        {
           if(id != null)
           {
               SubAreaPlantioEntity entity = subareaPlantioRepositoty.findById(id).orElseThrow(
                       EntityNotFoundException::new
               );
               SubAreaPlantio response = subareaPlantioMapper.EntityToDto(entity);
               return new ResponseEntity<>(response, HttpStatus.OK);
           }
           else {throw new NullargumentsException();}
        }
        catch (Exception e)
        {
            e.getMessage();
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<SubAreaPlantio> BuscarSubAreaPorNumero(int numero)
    {
        try
        {
            if(numero > 0)
            {
                SubAreaPlantioEntity entity = subareaPlantioRepositoty.findBynumero(numero).orElseThrow(
                        EntityNotFoundException::new
                );
                SubAreaPlantio response = subareaPlantioMapper.EntityToDto(entity);
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
            else {throw new NullargumentsException();}
        }
        catch (Exception e)
        {
            e.getMessage();
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<SubAreaPlantio> AdicionarNovaSubArea(String cor, TAMANHO tamanho, String nomeAreaPlantio)
    {
        try
        {
            if (nomeAreaPlantio.length() < 1){throw new IllegalActionException();}
            if (nomeAreaPlantio == null){throw new IllegalActionException();}
            if(cor != null && tamanho != null && nomeAreaPlantio != null)
            {
                System.out.println(nomeAreaPlantio);
                int dig = (int) (111 + Math.random() * 999);
                AreaPlantio areaPlantio = areaPlantioService.BuscarAreaPlantioPorNome(nomeAreaPlantio).getBody();
                SubAreaPlantioEntity entity = new SubAreaPlantioEntity();
                List<String> list = new ArrayList<>();
                entity.setnomeAreaPlantio(nomeAreaPlantio);
                entity.setNumero(dig);
                entity.setNotificacoes(list);
                entity.setCor(cor);
                entity.setTamanho(tamanho);
                entity.setDisponivel(Boolean.TRUE);
                entity.setTimeStamp(LocalDateTime.now());
                subareaPlantioRepositoty.save(entity);
                SubAreaPlantio response = subareaPlantioMapper.EntityToDto(entity);
                System.out.println("antes: "+ areaPlantio.getSubareas().size());
                areaPlantio.AddNovaSubArea(response);
                areaPlantioService.SalvarAlteracaoAreaPlantio(areaPlantio);
                System.out.println("depois: "+ areaPlantio.getSubareas().size());
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
            else {throw new NullargumentsException();}
        }
        catch (Exception e)
        {
            e.getMessage();
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<Void> SalvarAlteracao(SubAreaPlantio subAreaPlantio)
    {
        try
        {
            if(subAreaPlantio != null)
            {
                SubAreaPlantioEntity entity = subareaPlantioMapper.DtoToEntity(subAreaPlantio);
                subareaPlantioRepositoty.save(entity);
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

    public ResponseEntity<SubAreaPlantio> AdubacaoSubAreaIndividual(String nomeAreaPlantio ,int numeroSubarea, String resumoAdubacao)
    {
        try
        {
            if(numeroSubarea > 0 &&
               resumoAdubacao != null &&
               nomeAreaPlantio != null)
            {
                SubAreaPlantio response = BuscarSubAreaPorNumero(numeroSubarea).getBody();
                response.Adubacao(resumoAdubacao);
                AreaPlantio areaPlantio = areaPlantioService.BuscarAreaPlantioPorNome(nomeAreaPlantio).getBody();
                String mensagem = "Hoje dia: "+LocalDate.now()+" ,Houve uma Adubação na Subárea de número "+response.getNumero()+" em que foi realizado: "+resumoAdubacao;
                Planta planta = plantaService.BuscarPlantaPorCodigo(response.getPlanta().getCodigo()).getBody();
                areaPlantio.Adubacao(mensagem);
                response.Adubacao(mensagem);
                planta.Adubacao(mensagem);
                plantaService.SalvarAlteracao(planta);
                areaPlantioService.SalvarAlteracaoAreaPlantio(areaPlantio);
                SalvarAlteracao(response);
                return new ResponseEntity<>(response, HttpStatus.OK);
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
