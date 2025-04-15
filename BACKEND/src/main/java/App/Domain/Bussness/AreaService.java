package App.Domain.Bussness;


import App.Domain.Response.*;
import App.Infra.Exceptions.EntityNotFoundException;
import App.Infra.Exceptions.IllegalActionException;
import App.Infra.Exceptions.NullargumentsException;
import App.Infra.Gateway.AreaGateway;

import App.Infra.Mapper.AreaMapper;
import App.Infra.Mapper.BlocoMapper;
import App.Infra.Mapper.LocalizacaoMapper;

import App.Infra.Mapper.PlantaMapper;
import App.Infra.Persistence.Entity.AreaEntity;
import App.Infra.Persistence.Entity.BlocoEntity;
import App.Infra.Persistence.Entity.LocalizacaoEntity;
import App.Infra.Persistence.Entity.PlantaEntity;
import App.Infra.Persistence.Enum.CICLO;
import App.Infra.Persistence.Repository.AreaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class AreaService implements AreaGateway {

    private final AreaRepository areaRepository;
    private final AreaMapper areaMapper;
    private final LocalizacaoService localizacaoService;
    private final BlocoService blocoService;
    private final LocalizacaoMapper localizacaoMapper;
    private final BlocoMapper blocoMapper;
    private final PlantaMapper plantaMapper;
    private final PlantaService plantaService;
    private final CicloService cicloService;


    public AreaService(AreaRepository areaRepository, AreaMapper areaMapper, @Lazy LocalizacaoService localizacaoService, @Lazy BlocoService blocoService, LocalizacaoMapper localizacaoMapper, BlocoMapper blocoMapper, PlantaMapper plantaMapper, PlantaService plantaService,@Lazy CicloService cicloService) {
        this.areaRepository = areaRepository;
        this.areaMapper = areaMapper;
        this.localizacaoService = localizacaoService;
        this.blocoService = blocoService;
        this.localizacaoMapper = localizacaoMapper;
        this.blocoMapper = blocoMapper;
        this.plantaMapper = plantaMapper;
        this.plantaService = plantaService;
        this.cicloService = cicloService;
    }

    @Override
    public ResponseEntity<List<Area>> ListarAreas()
    {
        try
        {
            List<AreaEntity> entityList = areaRepository.findAll();
            List<Area> response = new ArrayList<>();
            for(AreaEntity entity : entityList)
            {
                Area dto = areaMapper.EntityToDto(entity);
                response.add(dto);
            }
            return new ResponseEntity<>(response,HttpStatus.OK);
        } catch (Exception e)
        {
            e.getMessage();

        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<Area> BuscarAreaPorId(Long id)
    {
        try
        {
            if(id != null)
            {
                AreaEntity entity = areaRepository.findById(id).orElseThrow(
                        EntityNotFoundException::new
                );
                Area response = areaMapper.EntityToDto(entity);
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
            else {throw new NullargumentsException();}
        } catch (Exception e)
        {
            e.getMessage();
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<Area> BuscarAreaPorNome(String nome)
    {
        try
        {
            if(nome != null)
            {
                AreaEntity entity = areaRepository.findBynomeIdentificador(nome).orElseThrow(
                        EntityNotFoundException::new
                );
                Area response = areaMapper.EntityToDto(entity);
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
            else {throw new NullargumentsException();}
        } catch (Exception e)
        {
            e.getMessage();
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Area> NovaArea(String dimensao,String nomeIdentificador, int eixoX, int eixoY, int quantidadeBlocos)
    {
        try
        {
            if(eixoX < 0){throw new IllegalActionException();}
            if(eixoY < 0){throw new IllegalActionException();}
            if(quantidadeBlocos < 0){throw new IllegalActionException();}
            if (dimensao != null &&
                nomeIdentificador != null)
            {
                List<Localizacao> localizacaoList = new ArrayList<>();
                List<Bloco> blocoList = new ArrayList<>();
                if(eixoX > 0 && eixoY >0)
                {
                    int ROWS = eixoY;
                    int COLS = eixoX;
                    int[][] matrix = new int[ROWS][COLS];
                    int currentValue = 1;
                    for (int i = 0; i < ROWS; i++)
                    {
                        for (int j = 0; j < COLS; j++)
                        {
                            if (matrix[i][j] == 0)
                            {
                                matrix[i][j] = currentValue++;
                                int eixoXAtual = j + 1;
                                int eixoYAtual = i + 1;
                                //"C "+eixoX+" x L "+eixoY
                                Localizacao localizacao = localizacaoService.NovaLocalizacao(nomeIdentificador,eixoXAtual,eixoYAtual).getBody();
                                localizacaoList.add(localizacao);
                            }
                        }
                    }
                }
                if(quantidadeBlocos > 0)
                {
                    int referencia = 1;
                    for(int i= 0; i < quantidadeBlocos; i++)
                    {
                        Bloco bloco = blocoService.NovoBloco(nomeIdentificador,referencia).getBody();
                        blocoList.add(bloco);
                        referencia ++;
                    }
                }
                AreaEntity entity = new AreaEntity();
                entity.SetInfo(dimensao,nomeIdentificador);
                for(Localizacao localizacao : localizacaoList)
                {
                    LocalizacaoEntity localizacaoEntity = localizacaoMapper.DtoToEntity(localizacao);
                    entity.getLocalizacoes().add(localizacaoEntity);
                }
                for(Bloco bloco : blocoList)
                {
                    BlocoEntity blocoEntity = blocoMapper.DtoToEntity(bloco);
                    entity.getBlocos().add(blocoEntity);
                }
                areaRepository.save(entity);
                Area response = areaMapper.EntityToDto(entity);
                return new ResponseEntity<>(response,HttpStatus.CREATED);
            }
            else {throw new NullargumentsException(); }
        } catch (Exception e)
        {
            e.getMessage();
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<Void> SalvarAlteracao(Area area)
    {
        try
        {
            if(area != null)
            {
                AreaEntity entity = areaMapper.toToEntity(area);
                areaRepository.save(entity);
                return new ResponseEntity<>(HttpStatus.OK);
            }
            else {throw new NullargumentsException();}
        } catch (Exception e)
        {
            e.getMessage();
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }


    public ResponseEntity<Area> EditarArea(Long id, String dimensao, String nomeIdentificador)
    {
        try
        {
            if (dimensao != null &&
                id != null)
            {
                Area area = BuscarAreaPorId(id).getBody();
                AreaEntity entity = areaMapper.toToEntity(area);
                entity.EditInfo(dimensao, nomeIdentificador);
                areaRepository.save(entity);
                Area response = areaMapper.EntityToDto(entity);
                for(Localizacao localizacao : area.getLocalizacoes())
                {
                    localizacao.setArea(nomeIdentificador);
                    localizacao.setTimeStamp(LocalDateTime.now());
                    localizacaoService.SalvarAlteracao(localizacao);
                }
                for(Bloco bloco : area.getBlocos())
                {
                    bloco.setArea(nomeIdentificador);
                    bloco.setTimeStamp(LocalDateTime.now());
                    blocoService.SalvarAlteracoes(bloco);
                }

                return new ResponseEntity<>(response,HttpStatus.OK);
            }
            else {throw new NullargumentsException(); }
        } catch (Exception e)
        {
            e.getMessage();
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<Area> NovaAdubacao(Long id, String relatorio)
    {
        try
        {
            Area area = BuscarAreaPorId(id).getBody();
            AreaEntity entity = areaMapper.toToEntity(area);
            entity.SetAdubacao(relatorio);
            for(PlantaEntity planta : entity.getPlantas())
            {
               planta.SetDataAdubacao();
               Planta request = plantaMapper.EntityToDto(planta);
               plantaService.SalvarAlteracao(request);
            }
            areaRepository.save(entity);
            area = areaMapper.EntityToDto(entity);
            return new ResponseEntity<>(area, HttpStatus.OK);
        }
        catch (Exception e)
        {
            e.getMessage();
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<Area> AlterarDimensaoLocalizacoes(Long id, int eixoX, int eixoY, int quantidadeBlocos)
    {
        try
        {
            if(id != null)
            {
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
