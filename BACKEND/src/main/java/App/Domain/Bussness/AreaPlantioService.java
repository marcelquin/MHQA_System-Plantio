package App.Domain.Bussness;

import App.Domain.Response.AreaPlantio;
import App.Domain.Response.SubAreaPlantio;
import App.Domain.Util.AreaPlantioMapper;
import App.Domain.Util.SubareaPlantioMapper;
import App.Infra.Exceptions.EntityNotFoundException;
import App.Infra.Exceptions.NullargumentsException;
import App.Infra.Persistence.Entity.AreaPlantioEntity;
import App.Infra.Persistence.Entity.SubAreaPlantioEntity;
import App.Infra.Persistence.Repository.AreaPlantioRepositoty;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AreaPlantioService {

    private final AreaPlantioRepositoty areaPlantioRepositoty;
    private final AreaPlantioMapper areaPlantioMapper;
    private final SubareaPlantioService subareaPlantioService;
    private final PlantaService plantaService;
    private final SubareaPlantioMapper subareaPlantioMapper;


    public AreaPlantioService(AreaPlantioRepositoty areaPlantioRepositoty, AreaPlantioMapper areaPlantioMapper, @Lazy SubareaPlantioService subareaPlantioService, @Lazy PlantaService plantaService, SubareaPlantioMapper subareaPlantioMapper) {
        this.areaPlantioRepositoty = areaPlantioRepositoty;
        this.areaPlantioMapper = areaPlantioMapper;
        this.subareaPlantioService = subareaPlantioService;
        this.plantaService = plantaService;
        this.subareaPlantioMapper = subareaPlantioMapper;
    }

    public ResponseEntity<List<AreaPlantio>> ListarAreas() {
        try {
            List<AreaPlantioEntity> list = areaPlantioRepositoty.findAll();
            List<AreaPlantio> response = new ArrayList<>();
            for (AreaPlantioEntity entity : list) {
                AreaPlantio dto = areaPlantioMapper.EntityToDto(entity);
                response.add(dto);
            }
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            e.getMessage();
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<AreaPlantio> BuscarAreaPlantioPorId(Long id) {
        try {
            if (id != null) {
                AreaPlantioEntity entity = areaPlantioRepositoty.findById(id).orElseThrow(
                        EntityNotFoundException::new
                );
                AreaPlantio response = areaPlantioMapper.EntityToDto(entity);
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                throw new NullargumentsException();
            }
        } catch (Exception e) {
            e.getMessage();
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<AreaPlantio> BuscarAreaPlantioPorNome(String nome) {
        try {
            if (nome != null) {
                AreaPlantioEntity entity = areaPlantioRepositoty.findBynomeIdentificador(nome).orElseThrow(
                        EntityNotFoundException::new
                );
                AreaPlantio response = areaPlantioMapper.EntityToDto(entity);
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                throw new NullargumentsException();
            }
        } catch (Exception e) {
            e.getMessage();
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<AreaPlantio> NovaAreaPlantio(String nomeIdentificador, String dimencao, String gps, int tamanhoEixoX, int tamanhoEixoY)
    {
        try {
            if (nomeIdentificador != null &&
                    dimencao != null &&
                    gps != null) {
                int dig = (int) (111 + Math.random() * 999);
                String codigo = nomeIdentificador + "_" + dig;
                AreaPlantioEntity entity = new AreaPlantioEntity();
                System.out.println(entity.getEixoX());
                entity.SetInfo(nomeIdentificador, dimencao, gps, codigo,tamanhoEixoX, tamanhoEixoY);
                entity.SetListsIniciais();
                System.out.println(entity.getEixoX());
                areaPlantioRepositoty.save(entity);
                AreaPlantio response = areaPlantioMapper.EntityToDto(entity);
                return new ResponseEntity<>(response, HttpStatus.CREATED);
            } else {
                throw new NullargumentsException();
            }
        } catch (Exception e) {
            e.getMessage();
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<Void> SalvarAlteracaoAreaPlantio(AreaPlantio areaPlantio) {
        try {
            AreaPlantioEntity entity = areaPlantioMapper.DtoToEntity(areaPlantio);
            areaPlantioRepositoty.save(entity);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            e.getMessage();
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    public void SetNovaSubArea(Long id, SubAreaPlantio subAreaPlantio)
    {
        try
        {
            if(id != null && subAreaPlantio != null)
            {
                AreaPlantio areaPlantio = BuscarAreaPlantioPorId(id).getBody();
                AreaPlantioEntity entity = areaPlantioMapper.DtoToEntity(areaPlantio);
                SubAreaPlantioEntity subAreaPlantioEntity = subareaPlantioMapper.DtoToEntity(subAreaPlantio);
                entity.SetNovaSubArea(subAreaPlantioEntity);
                areaPlantioRepositoty.save(entity);
            }
            else {throw new NullargumentsException();}
        }
        catch (Exception e)
        {
            e.getMessage();
        }
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
                    subareaPlantioService.AdubacaoSubAreaIndividual(subAreaPlantio.getCodigo(),adubacao);
                }
                entity.Adubacao(adubacao);
                areaPlantioRepositoty.save(entity);
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
