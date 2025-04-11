package App.Domain.Bussness;

import App.Domain.Response.Ciclo;
import App.Domain.Response.Localizacao;
import App.Infra.Exceptions.EntityNotFoundException;
import App.Infra.Exceptions.NullargumentsException;
import App.Infra.Mapper.CicloMapper;
import App.Infra.Persistence.Entity.CicloEntity;
import App.Infra.Persistence.Entity.LocalizacaoEntity;
import App.Infra.Persistence.Enum.CICLO;
import App.Infra.Persistence.Repository.CicloRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class CicloService {

    private final CicloRepository cicloRepository;
    private final CicloMapper cicloMapper;

    public CicloService(CicloRepository cicloRepository, CicloMapper cicloMapper) {
        this.cicloRepository = cicloRepository;
        this.cicloMapper = cicloMapper;
    }

    public ResponseEntity<Ciclo> BuscarCicloPorId(Long id)
    {
        try
        {
            if(id != null)
            {
                CicloEntity entity = cicloRepository.findById(id).orElseThrow(
                        EntityNotFoundException::new
                );
                Ciclo response = cicloMapper.EntityToDto(entity);
                return new ResponseEntity<>(response,HttpStatus.OK);
            }
            else {throw new NullargumentsException();}
        } catch (Exception e)
        {
            e.getMessage();
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<Ciclo> NovoCiclo()
    {
        try
        {
            CicloEntity entity = new CicloEntity();
            entity.SetInfo();
            cicloRepository.save(entity);
            Ciclo response = cicloMapper.EntityToDto(entity);
            return new ResponseEntity<>(response,HttpStatus.CREATED);
        } catch (Exception e)
        {
            e.getMessage();
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<Boolean> AlterarCiclo(Long id, CICLO ciclo)
    {
        try
        {
            if(id != null && ciclo != null)
            {
                CicloEntity entity = cicloRepository.findById(id).orElseThrow(
                        EntityNotFoundException::new
                );
                String mensagem = "na data "+ LocalDate.now()+" foi alterado para "+ciclo+".";
                System.out.println(mensagem);
                Boolean valida = entity.ValidaCiclo(ciclo);
                if(valida.equals(Boolean.TRUE))
                {
                    entity.setDataUltimoCiclo(entity.getDataCicloAtual());
                    entity.setDataCicloAtual(LocalDate.now());
                    entity.setTimeStamp(LocalDateTime.now());
                    entity.setCiclo(ciclo);
                    cicloRepository.save(entity);
                    return new ResponseEntity<>(Boolean.TRUE,HttpStatus.OK);
                }
            }
            else {throw new NullargumentsException();}
        } catch (Exception e)
        {
            e.getMessage();
        }
        return new ResponseEntity<>(Boolean.FALSE,HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<Ciclo> SalvarAlteracao(Ciclo ciclo)
    {
        try
        {
            if(ciclo != null)
            {
                CicloEntity entity = cicloMapper.DtoToEntity(ciclo);
                cicloRepository.save(entity);
                Ciclo response = cicloMapper.EntityToDto(entity);
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

}
