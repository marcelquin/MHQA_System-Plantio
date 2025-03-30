package App.Domain.Bussness;

import App.Domain.Response.*;
import App.Domain.Util.PlantaMapper;
import App.Infra.Exceptions.EntityNotFoundException;
import App.Infra.Exceptions.IllegalActionException;
import App.Infra.Exceptions.NullargumentsException;
import App.Infra.Gateway.PlantaGateway;
import App.Infra.Persistence.Entity.PlantaEntity;
import App.Infra.Persistence.Enum.FASEATUAL;
import App.Infra.Persistence.Repository.PlantaRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static App.Infra.Persistence.Enum.FASEATUAL.*;

@Service
public class PlantaService implements PlantaGateway {

    private final PlantaRepository plantaRepository;
    private final PlantaMapper plantaMapper;
    private final SubareaPlantioService subareaPlantioService;

    public PlantaService(PlantaRepository plantaRepository, PlantaMapper plantaMapper,@Lazy SubareaPlantioService subareaPlantioService) {
        this.plantaRepository = plantaRepository;
        this.plantaMapper = plantaMapper;
        this.subareaPlantioService = subareaPlantioService;
    }

    @Override
    public ResponseEntity<List<Planta>>ListarPlantas()
    {
        try
        {
            List<Planta> response = new ArrayList<>();
            List<PlantaEntity> plantaEntities = plantaRepository.findAll();
            for(PlantaEntity entity : plantaEntities)
            {
                Planta dto = plantaMapper.EntityToRecord(entity);
                response.add(dto);
            }
            return new ResponseEntity<>(response,HttpStatus.OK);
        }
        catch (Exception e)
        {
            e.getMessage();
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<List<Planta>>ListarPlantasGerminacao()
    {
        try
        {
            List<Planta> response = new ArrayList<>();
            List<PlantaEntity> plantaEntities = plantaRepository.findAll();
            for(PlantaEntity entity : plantaEntities)
            {
                if(entity.getFaseatual().equals(FASEATUAL.GERMINACAO))
                {
                    Planta dto = plantaMapper.EntityToRecord(entity);
                    response.add(dto);
                }
            }
            return new ResponseEntity<>(response,HttpStatus.OK);
        }
        catch (Exception e)
        {
            e.getMessage();
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<List<Planta>>ListarPlantasMudas()
    {
        try
        {
            List<Planta> response = new ArrayList<>();
            List<PlantaEntity> plantaEntities = plantaRepository.findAll();
            for(PlantaEntity entity : plantaEntities)
            {
                if(entity.getFaseatual().equals(FASEATUAL.MUDA))
                {
                    Planta dto = plantaMapper.EntityToRecord(entity);
                    response.add(dto);
                }
            }
            return new ResponseEntity<>(response,HttpStatus.OK);
        }
        catch (Exception e)
        {
            e.getMessage();
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<List<Planta>>ListarPlantasProducao()
    {
        try
        {
            List<Planta> response = new ArrayList<>();
            List<PlantaEntity> plantaEntities = plantaRepository.findAll();
            for(PlantaEntity entity : plantaEntities)
            {
                if(entity.getFaseatual().equals(FRUTIFICACAO))
                {
                    Planta dto = plantaMapper.EntityToRecord(entity);
                    response.add(dto);
                }
            }
            return new ResponseEntity<>(response,HttpStatus.OK);
        }
        catch (Exception e)
        {
            e.getMessage();
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<List<Planta>>ListarPlantasCrescimento()
    {
        try
        {
            List<Planta> response = new ArrayList<>();
            List<PlantaEntity> plantaEntities = plantaRepository.findAll();
            for(PlantaEntity entity : plantaEntities)
            {
                if(entity.getFaseatual().equals(FASEATUAL.CRESCIMENTO))
                {
                    Planta dto = plantaMapper.EntityToRecord(entity);
                    response.add(dto);
                }
            }
            return new ResponseEntity<>(response,HttpStatus.OK);
        }
        catch (Exception e)
        {
            e.getMessage();
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<List<Planta>>ListarPlantasFloracao()
    {
        try
        {
            List<Planta> response = new ArrayList<>();
            List<PlantaEntity> plantaEntities = plantaRepository.findAll();
            for(PlantaEntity entity : plantaEntities)
            {
                if(entity.getFaseatual().equals(FLORACAO))
                {
                    Planta dto = plantaMapper.EntityToRecord(entity);
                    response.add(dto);
                }
            }
            return new ResponseEntity<>(response,HttpStatus.OK);
        }
        catch (Exception e)
        {
            e.getMessage();
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<List<Planta>>ListarPlantasFrutificacao()
    {
        try
        {
            List<Planta> response = new ArrayList<>();
            List<PlantaEntity> plantaEntities = plantaRepository.findAll();
            for(PlantaEntity entity : plantaEntities)
            {
                if(entity.getFaseatual().equals(FRUTIFICACAO))
                {
                    Planta dto = plantaMapper.EntityToRecord(entity);
                    response.add(dto);
                }
            }
            return new ResponseEntity<>(response,HttpStatus.OK);
        }
        catch (Exception e)
        {
            e.getMessage();
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<List<Planta>>ListarPlantasMaturacao()
    {
        try
        {
            List<Planta> response = new ArrayList<>();
            List<PlantaEntity> plantaEntities = plantaRepository.findAll();
            for(PlantaEntity entity : plantaEntities)
            {
                if(entity.getFaseatual().equals(MATURACAO))
                {
                    Planta dto = plantaMapper.EntityToRecord(entity);
                    response.add(dto);
                }
            }
            return new ResponseEntity<>(response,HttpStatus.OK);
        }
        catch (Exception e)
        {
            e.getMessage();
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<List<Planta>>ListarPlantasFimCiclo()
    {
        try
        {
            List<Planta> response = new ArrayList<>();
            List<PlantaEntity> plantaEntities = plantaRepository.findAll();
            for(PlantaEntity entity : plantaEntities)
            {
                if(entity.getFaseatual().equals(FIM))
                {
                    Planta dto = plantaMapper.EntityToRecord(entity);
                    response.add(dto);
                }
            }
            return new ResponseEntity<>(response,HttpStatus.OK);
        }
        catch (Exception e)
        {
            e.getMessage();
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<Planta>BuscarPlantaPorId(Long id)
    {
        try
        {
            if(id != null)
            {
                PlantaEntity entity = plantaRepository.findById(id).orElseThrow(
                        EntityNotFoundException::new
                );
                Planta response = plantaMapper.EntityToRecord(entity);
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

    @Override
    public ResponseEntity<Planta>BuscarPlantaPorCodigo(String codigo)
    {
        try
        {
            if(codigo != null)
            {
                PlantaEntity entity = plantaRepository.findBycodigo(codigo).orElseThrow(
                        EntityNotFoundException::new
                );
                Planta response = plantaMapper.EntityToRecord(entity);
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

    @Override
    public ResponseEntity<Planta> AdicionarNovaPlanta(String nomeCientifico,
                                            String nomePopular,
                                            String instrucoes,
                                            String codigoSubarea)
    {
        try
        {
            if(nomeCientifico != null && nomePopular != null && instrucoes != null && codigoSubarea != null)
            {
                int dig = (int) (111 + Math.random() * 999);
                String identificador = nomePopular.substring(0, 3);
                String codigo = identificador+"_"+dig;
                PlantaEntity entity = new PlantaEntity();
                SubAreaPlantio subAreaPlantio = subareaPlantioService.BuscarSubAreaPorCodigo(codigoSubarea).getBody();
                entity.SetInfoInicial(nomeCientifico,nomePopular,codigo,instrucoes);
                Boolean validaAtribuicao = entity.ValidaAtribuicao(codigoSubarea);
                if(validaAtribuicao.equals(Boolean.TRUE))
                {
                    entity.AtribuirSubArea(codigoSubarea,nomeCientifico);
                }
                plantaRepository.save(entity);
                Planta response = plantaMapper.EntityToRecord(entity);
                subareaPlantioService.AdicionarPlanta(response, subAreaPlantio);
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

    @Override
    public ResponseEntity<Void> EditarInformacaoPlanta(Long id,
                                                       String nomeCientifico,
                                                       String nomePopular,
                                                       String instrucoes)
    {
        try
        {
            if(id != null &&
               nomeCientifico != null &&
               nomePopular != null &&
               instrucoes != null)
            {
               Planta planta = BuscarPlantaPorId(id).getBody();
               PlantaEntity entity = plantaMapper.RecordToEntity(planta);
               entity.SetInfo(nomeCientifico,nomePopular,instrucoes);
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

    public Boolean SalvarAlteracao(Planta planta)
    {
        try
        {
            PlantaEntity entity = plantaMapper.RecordToEntity(planta);
            plantaRepository.save(entity);
            return Boolean.TRUE;
        }
        catch (Exception e)
        {
            e.getMessage();
        }
        return Boolean.FALSE;
    }

    @Override
    public Boolean AtualizaCiclo(String codigoPlanta, String codigoSubarea, String faseatual)
    {
        try
        {
            if(codigoPlanta != null && codigoSubarea != null && faseatual != null)
            {
                PlantaEntity planta = plantaRepository.findBycodigo(codigoPlanta).orElseThrow(
                        EntityNotFoundException::new
                );
                SubAreaPlantio subAreaPlantio = subareaPlantioService.BuscarSubAreaPorCodigo(codigoSubarea).getBody();
                FASEATUAL faseAtualConvertida = RetornaFaseAtual(faseatual);
                if(faseAtualConvertida.equals(FIM))
                {
                    subareaPlantioService.ResetarInformacoes(subAreaPlantio);
                    planta.FimCiclo();
                    plantaRepository.save(planta);
                }
                if(planta.getLocalizacao() != null)
                {
                    if(planta.getLocalizacao().equals(subAreaPlantio.getCodigo()))
                    {
                        System.out.println(faseAtualConvertida);
                        Boolean validacao = planta.ValidaAlteracaoCiclo(faseAtualConvertida);
                        System.out.println(validacao);
                        if(validacao.equals(Boolean.TRUE))
                        {
                            planta.SetNovoCiclo(faseAtualConvertida);
                            plantaRepository.save(planta);
                        }
                    }
                    else
                    {
                        if(faseAtualConvertida != CRESCIMENTO){throw new IllegalActionException();}
                        Boolean validacao = planta.ValidaAlteracaoCiclo(faseAtualConvertida);
                        if(validacao.equals(Boolean.TRUE))
                        {
                            SubAreaPlantio subAreaPlantioAtual = subareaPlantioService.BuscarSubAreaPorCodigo(planta.getLocalizacao()).getBody();
                            subareaPlantioService.ResetarInformacoes(subAreaPlantioAtual);
                            planta.AtribuirSubArea(subAreaPlantio.getCodigo(),subAreaPlantio.getNomeAreaPlantio());
                            plantaRepository.save(planta);
                            Planta planta1 = plantaMapper.EntityToRecord(planta);
                            subareaPlantioService.AdicionarPlanta(planta1, subAreaPlantio);
                        }
                    }
                }
                return Boolean.TRUE;
            }
            else{throw new NullargumentsException();}
        }
        catch (Exception e)
        {
            e.getMessage();
        }
        return Boolean.FALSE;
    }

    public void SetAdubacao(Long id, String mensagem)
    {
        try
        {
            if(id != null && mensagem != null)
            {
                Planta planta = BuscarPlantaPorId(id).getBody();
                PlantaEntity entity = plantaMapper.RecordToEntity(planta);
                entity.Adubacao(mensagem);
                plantaRepository.save(entity);
            }
        }
        catch (Exception e)
        {
            e.getMessage();
        }
    }

    public FASEATUAL RetornaFaseAtual(String faseAtual)
    {
        try
        {
            if(faseAtual.equals("G"))
            { return GERMINACAO;}
            if(faseAtual.equals("M"))
            { return MUDA;}
            if(faseAtual.equals("C"))
            { return CRESCIMENTO;}
            if(faseAtual.equals("FL"))
            { return FLORACAO;}
            if(faseAtual.equals("FR"))
            { return FRUTIFICACAO;}
            if(faseAtual.equals("MA"))
            { return MATURACAO;}
            if(faseAtual.equals("F"))
            { return FIM;}
        }
        catch (Exception e)
        {
            e.getMessage();
        }
        return null;
    }
}
