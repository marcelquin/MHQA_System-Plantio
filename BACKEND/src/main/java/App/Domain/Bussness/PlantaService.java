package App.Domain.Bussness;

import App.Domain.Response.*;
import App.Domain.Util.PlantaMapper;
import App.Infra.Exceptions.EntityNotFoundException;
import App.Infra.Exceptions.IllegalActionException;
import App.Infra.Exceptions.NullargumentsException;
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
public class PlantaService {

    private final PlantaRepository plantaRepository;
    private final PlantaMapper plantaMapper;
    private final SubareaPlantioService subareaPlantioService;

    public PlantaService(PlantaRepository plantaRepository, PlantaMapper plantaMapper,@Lazy SubareaPlantioService subareaPlantioService) {
        this.plantaRepository = plantaRepository;
        this.plantaMapper = plantaMapper;
        this.subareaPlantioService = subareaPlantioService;
    }

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


    public void AdicionarNovaPlanta(String nomeCientifico,
                                            String nomePopular,
                                            String instrucoes,
                                            Boolean cavalo)
    {
        try
        {
            if(nomeCientifico != null && nomePopular != null && instrucoes != null && cavalo != null)
            {
                int dig = (int) (111 + Math.random() * 999);
                String identificador = nomePopular.substring(0, 3);
                String codigo = identificador+"_"+dig;
                List<String>notificacoes = new ArrayList<>();
                PlantaEntity entity = new PlantaEntity();
                entity.setNomeCientifico(nomeCientifico);
                entity.setNomePopular(nomePopular);
                entity.setCodigo(codigo);
                entity.setFaseatual(AGUARDANDO);
                entity.setInstrucoes(instrucoes);
                entity.setNotificacoes(notificacoes);
                entity.setCavalo(cavalo);
                entity.setDataPlantio(LocalDate.now());
                entity.setTimeStamp(LocalDateTime.now());
                plantaRepository.save(entity);
                Planta response = plantaMapper.EntityToRecord(entity);
            }
            else {throw new NullargumentsException();}
        }
        catch (Exception e)
        {
            e.getMessage();
        }
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

    public Boolean AtualizaCiclo(String codigo, int numeroSubarea, String faseatual)
    {
        try
        {
            Planta planta = BuscarPlantaPorCodigo(codigo).getBody();
            SubAreaPlantio subAreaPlantio = subareaPlantioService.BuscarSubAreaPorNumero(numeroSubarea).getBody();
            FASEATUAL faseAtualConvertida = RetornaFaseAtual(faseatual);
            if(faseAtualConvertida.equals(FIM))
            {
                SubAreaPlantio subAreaPlantioAtual = subareaPlantioService.BuscarSubAreaPorNumero(planta.getNumeroSubareaPlantio()).getBody();
                subAreaPlantioAtual.ResetInformacao();
                planta.FimCiclo();
                SalvarAlteracao(planta);
                subareaPlantioService.SalvarAlteracao(subAreaPlantioAtual);
                return Boolean.TRUE;
            }
            Boolean validacao = planta.ValidaAlteracao(faseAtualConvertida);
            if(subAreaPlantio.getDataInicioCiclo() == null && validacao.equals(Boolean.TRUE && planta.getFaseatual().equals(AGUARDANDO)))
            {
                subAreaPlantio.AtribuirPlanta(planta);
                planta.AtribuirSubArea(subAreaPlantio.getNumero(),subAreaPlantio.getNomeAreaPlantio());
                SalvarAlteracao(planta);
                subareaPlantioService.SalvarAlteracao(subAreaPlantio);
                return Boolean.TRUE;
            }
            if(planta.getFaseatual() != AGUARDANDO)
            {
                if(planta.getNumeroSubareaPlantio() != numeroSubarea)
                {
                    //buscar atual
                    SubAreaPlantio subAreaPlantioAtual = subareaPlantioService.BuscarSubAreaPorNumero(planta.getNumeroSubareaPlantio()).getBody();
                    //zerar informações
                    subAreaPlantioAtual.ResetInformacao();
                    subareaPlantioService.SalvarAlteracao(subAreaPlantioAtual);
                    //setar inforações
                    subAreaPlantio.AtribuirPlanta(planta);
                    //setar informação planta
                    planta.AtribuirSubArea(subAreaPlantio.getNumero(),subAreaPlantio.getNomeAreaPlantio());
                    subareaPlantioService.SalvarAlteracao(subAreaPlantio);
                }
                //alterar ciclo
                planta.AlterarFaseAtual(faseAtualConvertida);
                SalvarAlteracao(planta);
                return Boolean.TRUE;
            }
        }
        catch (Exception e)
        {
            e.getMessage();
        }
        return Boolean.FALSE;
    }

    public ResponseEntity<Boolean> ExecutarEnxertia(String codigoPlantaDoadora, String codigoPlantaReceptora)
    {
        try
        {
            if(codigoPlantaDoadora != null && codigoPlantaReceptora != null)
            {
                Planta plantaDoadora = BuscarPlantaPorCodigo(codigoPlantaDoadora).getBody();
                Planta plantaReceptora = BuscarPlantaPorCodigo(codigoPlantaReceptora).getBody();
                Boolean validacaoDoadora = plantaDoadora.validaDoadora();
                Boolean validacaoReceptora = plantaReceptora.validaReceptora();
                if(validacaoDoadora.equals(Boolean.TRUE) && validacaoReceptora.equals(Boolean.TRUE))
                {
                    plantaReceptora.SetDadosEnxertia(plantaDoadora.getNomeCientifico(), plantaDoadora.getNomePopular(), plantaDoadora.getInstrucoes());
                    SalvarAlteracao(plantaReceptora);
                    plantaDoadora.FimCiclo();
                    SalvarAlteracao(plantaDoadora);
                    return new ResponseEntity<>(Boolean.TRUE,HttpStatus.OK);
                }
/*                if(plantaDoadora.getCavalo().equals(Boolean.FALSE)){throw new IllegalActionException();}
                if(plantaDoadora.getNumeroSubareaPlantio() > 0){throw new IllegalActionException();}
                if(plantaDoadora.getDataFimCiclo() != null){throw new IllegalActionException();}
                if(plantaDoadora.getFaseatual() != AGUARDANDO){throw new IllegalActionException();}
                if(plantaReceptora.getCavalo().equals(Boolean.TRUE)){throw new IllegalActionException();}
                if(plantaReceptora.getNumeroSubareaPlantio() == 0){throw new IllegalActionException();}
                if(plantaReceptora.getDataFimCiclo() != null){throw new IllegalActionException();}
                if(plantaReceptora.getFaseatual() != CRESCIMENTO){throw new IllegalActionException();}*/
            }
        } catch (Exception e) {
            throw new IllegalActionException();
        }
        return new ResponseEntity<>(Boolean.FALSE,HttpStatus.BAD_REQUEST);
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
