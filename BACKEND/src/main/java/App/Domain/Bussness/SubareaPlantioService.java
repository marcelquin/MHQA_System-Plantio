package App.Domain.Bussness;

import App.Domain.Response.AreaPlantio;
import App.Domain.Response.Planta;
import App.Domain.Response.SubAreaPlantio;
import App.Domain.Util.PlantaMapper;
import App.Domain.Util.SubareaPlantioMapper;
import App.Infra.Exceptions.EntityNotFoundException;
import App.Infra.Exceptions.IllegalActionException;
import App.Infra.Exceptions.NullargumentsException;
import App.Infra.Gateway.SubareaGateway;
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
public class SubareaPlantioService implements SubareaGateway {

    private final SubareaPlantioRepositoty subareaPlantioRepositoty;
    private final SubareaPlantioMapper subareaPlantioMapper;
    private final AreaPlantioService areaPlantioService;
    private final PlantaService plantaService;
    private final PlantaMapper plantaMapper;

    public SubareaPlantioService(SubareaPlantioRepositoty subareaPlantioRepositoty, SubareaPlantioMapper subareaPlantioMapper, @Lazy AreaPlantioService areaPlantioService, @Lazy PlantaService plantaService, PlantaMapper plantaMapper) {
        this.subareaPlantioRepositoty = subareaPlantioRepositoty;
        this.subareaPlantioMapper = subareaPlantioMapper;
        this.areaPlantioService = areaPlantioService;
        this.plantaService = plantaService;
        this.plantaMapper = plantaMapper;
    }

    @Override
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

    @Override
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

    @Override
    public ResponseEntity<SubAreaPlantio> BuscarSubAreaPorCodigo(String codigo)
    {
        try
        {
            if(codigo != null)
            {
                SubAreaPlantioEntity entity = subareaPlantioRepositoty.findBycodigo(codigo).orElseThrow(
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

    public ResponseEntity<SubAreaPlantio> AdicionarNovaSubArea(int eixoX, int eixoY, String nomeAreaPlantio)
    {
        try
        {
            if(eixoX < 0){throw new IllegalActionException();}
            if(eixoY < 0){throw new IllegalActionException();}
            if (nomeAreaPlantio.length() < 5){throw new IllegalActionException();}
            if (nomeAreaPlantio.equals(null)){throw new IllegalActionException();}
            if(eixoX > 0 && eixoY > 0 )
            {
                AreaPlantio areaPlantio = areaPlantioService.BuscarAreaPlantioPorNome(nomeAreaPlantio).getBody();
                if(areaPlantio.getEixoX() < eixoX){throw new IllegalActionException();}
                if(areaPlantio.getEixoy() < eixoY){throw new IllegalActionException();}
                List<SubAreaPlantio> subAreaPlantios = ListarSubareas().getBody();
                SubAreaPlantioEntity entity = new SubAreaPlantioEntity();
                String codigo = areaPlantio.getNomeIdentificador()+"_"+eixoX+"x"+eixoY;
                List<String> list = new ArrayList<>();
                entity.SetInfo(eixoX,eixoY,nomeAreaPlantio,codigo);
                entity.SetInfoInicial();
                subareaPlantioRepositoty.save(entity);
                SubAreaPlantio response = subareaPlantioMapper.EntityToDto(entity);
                //areaPlantioService.SetNovaSubArea(areaPlantio.getId(), response);
                //areaPlantioService.SalvarAlteracaoAreaPlantio(areaPlantio);
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

    public ResponseEntity<SubAreaPlantio> AdicionarPlanta(Planta planta, SubAreaPlantio subAreaPlantio)
    {
        try
        {
            if(planta != null && subAreaPlantio != null)
            {
                SubAreaPlantioEntity entity = subareaPlantioMapper.DtoToEntity(subAreaPlantio);
                PlantaEntity plantaEntity = plantaMapper.RecordToEntity(planta);
                entity.AtribuirPlanta(plantaEntity);
                subareaPlantioRepositoty.save(entity);
                SubAreaPlantio response = subareaPlantioMapper.EntityToDto(entity);
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

    public ResponseEntity<SubAreaPlantio> ResetarInformacoes(SubAreaPlantio subAreaPlantio)
    {
        try
        {
            if(subAreaPlantio != null)
            {
                SubAreaPlantioEntity entity = subareaPlantioMapper.DtoToEntity(subAreaPlantio);
                entity.ResetInformacao();
                subareaPlantioRepositoty.save(entity);
                SubAreaPlantio response = subareaPlantioMapper.EntityToDto(entity);
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

    @Override
    public ResponseEntity<SubAreaPlantio> AdubacaoSubAreaIndividual(String codigo, String resumoAdubacao)
    {
        try
        {
            if(codigo != null&&
               resumoAdubacao != null)
            {
                SubAreaPlantio subAreaPlantio = BuscarSubAreaPorCodigo(codigo).getBody();
                SubAreaPlantioEntity entity = subareaPlantioMapper.DtoToEntity(subAreaPlantio);
                entity.Adubacao(resumoAdubacao);
                AreaPlantio areaPlantio = areaPlantioService.BuscarAreaPlantioPorNome(subAreaPlantio.getNomeAreaPlantio()).getBody();
                String mensagem = "Hoje dia: "+LocalDate.now()+" ,Houve uma Adubação na Subárea de número "+subAreaPlantio.getCodigo()+" em que foi realizado: "+resumoAdubacao;
                Planta planta = plantaService.BuscarPlantaPorCodigo(subAreaPlantio.getPlanta().getCodigo()).getBody();
                areaPlantioService.AdubacaoAreaPlantioGeral(entity.getNomeAreaPlantio(), resumoAdubacao);
                entity.Adubacao(mensagem);
                plantaService.SetAdubacao(planta.getId(), mensagem);
                subareaPlantioRepositoty.save(entity);
                return new ResponseEntity<>(subAreaPlantio, HttpStatus.OK);
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
