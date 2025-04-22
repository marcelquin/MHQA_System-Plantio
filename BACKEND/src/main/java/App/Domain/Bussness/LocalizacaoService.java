package App.Domain.Bussness;

import App.Domain.Response.Localizacao;
import App.Domain.Response.Planta;
import App.Infra.Exceptions.EntityNotFoundException;
import App.Infra.Exceptions.NullargumentsException;
import App.Infra.Gateway.LocalizacaoGateway;
import App.Infra.Mapper.LocalizacaoMapper;
import App.Infra.Persistence.Entity.LocalizacaoEntity;
import App.Infra.Persistence.Repository.LocalizacaoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LocalizacaoService implements LocalizacaoGateway {

    private final LocalizacaoRepository localizacaoRepository;
    private final LocalizacaoMapper localizacaoMapper;

    public LocalizacaoService(LocalizacaoRepository localizacaoRepository, LocalizacaoMapper localizacaoMapper) {
        this.localizacaoRepository = localizacaoRepository;
        this.localizacaoMapper = localizacaoMapper;
    }

    public ResponseEntity<List<Localizacao>> ListarLocalizacoes()
    {
        try
        {
            List<LocalizacaoEntity> localizacaoEntities = localizacaoRepository.findAll();
            List<Localizacao> response = new ArrayList<>();
            for(LocalizacaoEntity entity : localizacaoEntities)
            {
                Localizacao dto = localizacaoMapper.EntityToDto(entity);
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
    public ResponseEntity<List<Localizacao>> ListarLocalizacoesDisponiveis()
    {
        try
        {
            List<LocalizacaoEntity> localizacaoEntities = localizacaoRepository.findAll();
            List<Localizacao> response = new ArrayList<>();
            for(LocalizacaoEntity entity : localizacaoEntities)
            {
                if(entity.getDisponivel().equals(Boolean.TRUE))
                {
                    Localizacao dto = localizacaoMapper.EntityToDto(entity);
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
    public ResponseEntity<List<Localizacao>> ListarLocalizacoesIndisponiveis()
    {
        try
        {
            List<LocalizacaoEntity> localizacaoEntities = localizacaoRepository.findAll();
            List<Localizacao> response = new ArrayList<>();
            for(LocalizacaoEntity entity : localizacaoEntities)
            {
                if(entity.getDisponivel().equals(Boolean.FALSE))
                {
                    Localizacao dto = localizacaoMapper.EntityToDto(entity);
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

    public ResponseEntity<Localizacao> BuscarLocalizacaoPorId(Long id)
    {
        try
        {
            if(id != null)
            {
                LocalizacaoEntity entity = localizacaoRepository.findById(id).orElseThrow(
                        EntityNotFoundException::new
                );
                Localizacao response = localizacaoMapper.EntityToDto(entity);
                return new ResponseEntity<>(response,HttpStatus.OK);
            }
            else {throw new NullargumentsException();}
        } catch (Exception e)
        {
           e.getMessage();
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<Localizacao> BuscarLocalizacaoPorreferencia(String referencia)
    {
        try
        {
            if(referencia != null)
            {
                LocalizacaoEntity entity = localizacaoRepository.findByreferencia(referencia).orElseThrow(
                        EntityNotFoundException::new
                );
                Localizacao response = localizacaoMapper.EntityToDto(entity);
                return new ResponseEntity<>(response,HttpStatus.OK);
            }
            else {throw new NullargumentsException();}
        } catch (Exception e)
        {
            e.getMessage();
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<Localizacao> NovaLocalizacao(String area, int eixoX, int eixoY)
    {
        try
        {
            if(eixoX < 0){throw new NullargumentsException();}
            if(eixoY < 0){throw new NullargumentsException();}
            if(area != null && eixoX > 0 && eixoY > 0)
            {
                LocalizacaoEntity entity = new LocalizacaoEntity();
                entity.SetInfo(area, eixoX, eixoY);
                localizacaoRepository.save(entity);
                Localizacao response = localizacaoMapper.EntityToDto(entity);
                return new ResponseEntity<>(response, HttpStatus.CREATED);
            }
            else {throw new NullargumentsException();}
        } catch (Exception e)
        {
            e.getMessage();
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    public void SalvarAlteracao(Localizacao localizacao)
    {
        try
        {
            if(localizacao != null)
            {
                LocalizacaoEntity entity = localizacaoMapper.DtoToEntity(localizacao);
                localizacaoRepository.save(entity);
            }
            else {throw new NullargumentsException();}
        } catch (Exception e)
        {
            e.getMessage();
        }

    }

    public ResponseEntity<Boolean> verificaReferencia(String referencia)
    {
        try
        {
            if(localizacaoRepository.existsByreferencia(referencia))
            {
                return new ResponseEntity<>(Boolean.TRUE,HttpStatus.OK);
            }
            else {return new ResponseEntity<>(Boolean.FALSE,HttpStatus.OK);}
        }
        catch (Exception e)
        {
            e.getMessage();
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<Void> DeletarLocalizacaoPorId(Long id)
    {
        try
        {
            if(id != null)
            {
                localizacaoRepository.deleteById(id);
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
