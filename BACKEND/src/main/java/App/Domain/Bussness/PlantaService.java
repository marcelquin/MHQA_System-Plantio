package App.Domain.Bussness;

import App.Domain.Response.*;
import App.Infra.Exceptions.EntityNotFoundException;
import App.Infra.Exceptions.IllegalActionException;
import App.Infra.Exceptions.NullargumentsException;
import App.Infra.Gateway.PlantaGateway;
import App.Infra.Mapper.*;
import App.Infra.Persistence.Entity.*;
import App.Infra.Persistence.Enum.CICLO;
import App.Infra.Persistence.Repository.PlantaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Blob;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static App.Infra.Persistence.Enum.CICLO.*;

@Service
public class PlantaService implements PlantaGateway {

    private final PlantaRepository plantaRepository;
    private final PlantaMapper plantaMapper;
    private final AreaService areaService;
    private final AreaMapper areaMapper;
    private final CicloMapper cicloMapper;
    private final CicloService cicloService;
    private final LocalizacaoMapper localizacaoMapper;
    private final LocalizacaoService localizacaoService;
    private final BlocoService blocoService;
    private final BlocoMapper blocoMapper;

    public PlantaService(PlantaRepository plantaRepository, PlantaMapper plantaMapper, @Lazy AreaService areaService, AreaMapper areaMapper, CicloMapper cicloMapper, @Lazy CicloService cicloService, LocalizacaoMapper localizacaoMapper, @Lazy LocalizacaoService localizacaoService, BlocoService blocoService, BlocoMapper blocoMapper) {
        this.plantaRepository = plantaRepository;
        this.plantaMapper = plantaMapper;
        this.areaService = areaService;
        this.areaMapper = areaMapper;
        this.cicloMapper = cicloMapper;
        this.cicloService = cicloService;
        this.localizacaoMapper = localizacaoMapper;
        this.localizacaoService = localizacaoService;
        this.blocoService = blocoService;
        this.blocoMapper = blocoMapper;
    }

    @Override
    public ResponseEntity<List<Planta>> ListarPlantas()
    {
        try
        {
            List<PlantaEntity> plantaEntities = plantaRepository.findAll();
            List<Planta> response = new ArrayList<>();
            for(PlantaEntity entity : plantaEntities)
            {
                Planta planta = plantaMapper.EntityToDto(entity);
                response.add(planta);

            }
            return new ResponseEntity<>(response,HttpStatus.OK);
        } catch (Exception e)
        {
            e.getMessage();
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<List<Planta>> ListarPlantasGerminacao()
    {
        try
        {
            List<PlantaEntity> plantaEntities = plantaRepository.findAll();
            List<Planta> response = new ArrayList<>();
            for(PlantaEntity entity : plantaEntities)
            {
                if(entity.getCiclo().getCiclo().equals(GERMINACAO))
                {
                    Planta planta = plantaMapper.EntityToDto(entity);
                    response.add(planta);
                }
            }
            return new ResponseEntity<>(response,HttpStatus.OK);
        } catch (Exception e)
        {
            e.getMessage();
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<List<Planta>> ListarPlantasMuda()
    {
        try
        {
            List<PlantaEntity> plantaEntities = plantaRepository.findAll();
            List<Planta> response = new ArrayList<>();
            for(PlantaEntity entity : plantaEntities)
            {
                if(entity.getCiclo().getCiclo().equals(MUDA))
                {
                    Planta planta = plantaMapper.EntityToDto(entity);
                    response.add(planta);
                }
            }
            return new ResponseEntity<>(response,HttpStatus.OK);
        } catch (Exception e)
        {
            e.getMessage();
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<List<Planta>> ListarPlantasCrescimento()
    {
        try
        {
            List<PlantaEntity> plantaEntities = plantaRepository.findAll();
            List<Planta> response = new ArrayList<>();
            for(PlantaEntity entity : plantaEntities)
            {
                if(entity.getCiclo().getCiclo().equals(CRESCIMENTO))
                {
                    Planta planta = plantaMapper.EntityToDto(entity);
                    response.add(planta);
                }
            }
            return new ResponseEntity<>(response,HttpStatus.OK);
        } catch (Exception e)
        {
            e.getMessage();
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<List<Planta>> ListarPlantasFloracao()
    {
        try
        {
            List<PlantaEntity> plantaEntities = plantaRepository.findAll();
            List<Planta> response = new ArrayList<>();
            for(PlantaEntity entity : plantaEntities)
            {
                if(entity.getCiclo().getCiclo().equals(FLORACAO))
                {
                    Planta planta = plantaMapper.EntityToDto(entity);
                    response.add(planta);
                }
            }
            return new ResponseEntity<>(response,HttpStatus.OK);
        } catch (Exception e)
        {
            e.getMessage();
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<List<Planta>> ListarPlantasFrutificacao()
    {
        try
        {
            List<PlantaEntity> plantaEntities = plantaRepository.findAll();
            List<Planta> response = new ArrayList<>();
            for(PlantaEntity entity : plantaEntities)
            {
                if(entity.getCiclo().getCiclo().equals(FRUTIFICACAO))
                {
                    Planta planta = plantaMapper.EntityToDto(entity);
                    response.add(planta);
                }
            }
            return new ResponseEntity<>(response,HttpStatus.OK);
        } catch (Exception e)
        {
            e.getMessage();
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<List<Planta>> ListarPlantasMaturacao()
    {
        try
        {
            List<PlantaEntity> plantaEntities = plantaRepository.findAll();
            List<Planta> response = new ArrayList<>();
            for(PlantaEntity entity : plantaEntities)
            {
                if(entity.getCiclo().getCiclo().equals(MATURACAO))
                {
                    Planta planta = plantaMapper.EntityToDto(entity);
                    response.add(planta);
                }
            }
            return new ResponseEntity<>(response,HttpStatus.OK);
        } catch (Exception e)
        {
            e.getMessage();
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<List<Planta>> ListarPlantasFimCiclo()
    {
        try
        {
            List<PlantaEntity> plantaEntities = plantaRepository.findAll();
            List<Planta> response = new ArrayList<>();
            for(PlantaEntity entity : plantaEntities)
            {
                if(entity.getCiclo().getCiclo().equals(FIM))
                {
                    Planta planta = plantaMapper.EntityToDto(entity);
                    response.add(planta);
                }
            }
            return new ResponseEntity<>(response,HttpStatus.OK);
        } catch (Exception e)
        {
            e.getMessage();
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }


    @Override
    public ResponseEntity<Planta> BuscarPlantaPorId(Long id)
    {
        try
        {
            if(id != null)
            {
                PlantaEntity entity = plantaRepository.findById(id).orElseThrow(
                        EntityNotFoundException::new
                );
                Planta response = plantaMapper.EntityToDto(entity);
                return new ResponseEntity<>(response,HttpStatus.OK);
            }
            else {throw new NullargumentsException();}
        } catch (Exception e)
        {
            e.getMessage();
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<Planta> NovaPlanta(Long areaId, Long localizacaoId, Long blocoId, String nomeCientifico, String nomePopular, String instrucoes)
    {
        try
        {
            if(nomeCientifico != null &&
               nomePopular != null &&
               instrucoes != null &&
               areaId != null)
            {
                Area area = areaService.BuscarAreaPorId(areaId).getBody();
                AreaEntity areaEntity = areaMapper.toToEntity(area);
                PlantaEntity entity = new PlantaEntity();
                Ciclo ciclo = cicloService.NovoCiclo().getBody();
                cicloService.AlterarCiclo(ciclo.getId(),CICLO.GERMINACAO);
                CicloEntity cicloEntity = cicloMapper.DtoToEntity(ciclo);
                entity.setCiclo(cicloEntity);
                if(localizacaoId > 0)
                {
                    Localizacao localizacao = localizacaoService.BuscarLocalizacaoPorId(localizacaoId).getBody();
                    LocalizacaoEntity localizacaoEntity = localizacaoMapper.DtoToEntity(localizacao);
                    entity.setLocalizacao(localizacaoEntity);
                    localizacaoEntity.SetPlanta();
                    Localizacao localizacaoRequest = localizacaoMapper.EntityToDto(localizacaoEntity);
                    localizacaoService.SalvarAlteracao(localizacaoRequest);
                }
                if(blocoId > 0)
                {
                    Bloco bloco = blocoService.BuscarBlocoPorId(blocoId).getBody();
                    BlocoEntity blocoEntity = blocoMapper.DtoToEntity(bloco);
                    entity.setBloco(blocoEntity);
                    blocoEntity.SetPlanta();
                    Bloco request = blocoMapper.EntityToDto(blocoEntity);
                    blocoService.SalvarAlteracoes(request);
                }
                entity.SetInfo(nomePopular,nomeCientifico,instrucoes);
                plantaRepository.save(entity);
                Planta response = plantaMapper.EntityToDto(entity);
                areaEntity.getPlantas().add(entity);
                Area request = areaMapper.EntityToDto(areaEntity);
                areaService.SalvarAlteracao(request);
                return  new ResponseEntity<>(response, HttpStatus.CREATED);
            }
            else {throw new NullargumentsException();}
        } catch (Exception e)
        {
            e.getMessage();
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<Planta> EditarPlanta(Long plantaId, String nomeCientifico, String nomePopular,String instrucoes)
    {
        try
        {
            if(plantaId != null && nomePopular != null && nomeCientifico != null)
            {
                Planta planta = BuscarPlantaPorId(plantaId).getBody();
                PlantaEntity entity = plantaMapper.DtoToEntity(planta);
                entity.EditInfo(nomePopular,nomeCientifico,instrucoes);
                plantaRepository.save(entity);
                return new ResponseEntity<>(planta, HttpStatus.OK);
            }
            else {throw new NullargumentsException();}
        } catch (Exception e)
        {
            e.getMessage();
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<Planta> AlterarCiclo(Long id, String ciclo)
    {
        try
        {
            if(id != null)
            {
                Planta planta = BuscarPlantaPorId(id).getBody();
                PlantaEntity entity = plantaMapper.DtoToEntity(planta);
                CICLO cicloConvertido = RetornaCicloAtual(ciclo);
                cicloService.AlterarCiclo(entity.getCiclo().getId(), cicloConvertido);
                if(cicloConvertido.equals(FIM))
                {
                    Localizacao localizacao = localizacaoService.BuscarLocalizacaoPorId(entity.getLocalizacao().getId()).getBody();
                    localizacao.setDisponivel(Boolean.TRUE);
                    localizacao.setTimeStamp(LocalDateTime.now());
                    localizacaoService.SalvarAlteracao(localizacao);
                    entity.FimCiclo();
                }
                plantaRepository.save(entity);
                String local = "";
                if(planta.getLocalizacao().equals(null))
                {
                    local = planta.getBloco().getReferencia();
                }
                else
                {
                    local = planta.getLocalizacao().getReferencia();
                }
                String mensagem = "Na data "+ LocalDate.now()+" a planta "+planta.getNomePopular()+
                        " presente no local "+local+" passou para o ciclo "+cicloConvertido;
                Area area = areaService.BuscarAreaPorNome(planta.getLocalizacao().getArea()).getBody();
                AreaEntity areaEntity = areaMapper.toToEntity(area);
                areaEntity.SetNotificacao(mensagem);
                area = areaMapper.EntityToDto(areaEntity);
                areaService.SalvarAlteracao(area);
                Planta response = plantaMapper.EntityToDto(entity);
                return new ResponseEntity<>(response,HttpStatus.OK);
            }
            else {throw new NullargumentsException();}
        } catch (Exception e)
        {
            e.getMessage();
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    public CICLO RetornaCicloAtual(String ciclo)
    {
        try
        {
            if(ciclo.equals("GERMINACAO"))
            { return GERMINACAO;}
            if(ciclo.equals("MUDA"))
            { return MUDA;}
            if(ciclo.equals("CRESCIMENTO"))
            { return CRESCIMENTO;}
            if(ciclo.equals("FLORACAO"))
            { return FLORACAO;}
            if(ciclo.equals("FRUTIFICACAO"))
            { return FRUTIFICACAO;}
            if(ciclo.equals("MATURACAO"))
            { return MATURACAO;}
            if(ciclo.equals("FIM"))
            { return FIM;}
        }
        catch (Exception e)
        {
            e.getMessage();
        }
        return null;
    }

    public ResponseEntity<Void> SalvarAlteracao(Planta planta)
    {
        try
        {
            if(planta != null)
            {
                PlantaEntity entity = plantaMapper.DtoToEntity(planta);
                plantaRepository.save(entity);
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
