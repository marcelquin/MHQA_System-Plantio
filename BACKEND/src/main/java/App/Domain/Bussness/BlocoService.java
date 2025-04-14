package App.Domain.Bussness;

import App.Domain.Response.Bloco;
import App.Domain.Response.Localizacao;
import App.Infra.Exceptions.EntityNotFoundException;
import App.Infra.Exceptions.NullargumentsException;
import App.Infra.Gateway.BlocoGateway;
import App.Infra.Mapper.BlocoMapper;
import App.Infra.Persistence.Entity.BlocoEntity;
import App.Infra.Persistence.Repository.BlocoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.smartcardio.ATR;
import java.util.ArrayList;
import java.util.List;

@Service
public class BlocoService implements BlocoGateway {

    private final BlocoRepository blocoRepository;
    private final BlocoMapper blocoMapper;

    public BlocoService(BlocoRepository blocoRepository, BlocoMapper blocoMapper) {
        this.blocoRepository = blocoRepository;
        this.blocoMapper = blocoMapper;
    }

    @Override
    public ResponseEntity<List<Bloco>> ListarBlocos()
    {
        try
        {
            List<BlocoEntity> blocoEntities = blocoRepository.findAll();
            List<Bloco> response = new ArrayList<>();
            for(BlocoEntity entity : blocoEntities)
            {
                Bloco bloco = blocoMapper.EntityToDto(entity);
                response.add(bloco);
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
    public ResponseEntity<List<Bloco>> ListarBlocosDisponiveis()
    {
        try
        {
            List<BlocoEntity> blocoEntities = blocoRepository.findAll();
            List<Bloco> response = new ArrayList<>();
            for(BlocoEntity entity : blocoEntities)
            {
                if(entity.getDisponivel().equals(Boolean.TRUE))
                {
                    Bloco bloco = blocoMapper.EntityToDto(entity);
                    response.add(bloco);
                }
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
    public ResponseEntity<List<Bloco>> ListarBlocosUtilizados()
    {
        try
        {
            List<BlocoEntity> blocoEntities = blocoRepository.findAll();
            List<Bloco> response = new ArrayList<>();
            for(BlocoEntity entity : blocoEntities)
            {
                if(entity.getDisponivel().equals(Boolean.FALSE))
                {
                    Bloco bloco = blocoMapper.EntityToDto(entity);
                    response.add(bloco);
                }
            }
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        catch (Exception e)
        {
            e.getMessage();
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<Bloco> BuscarBlocoPorId(Long id)
    {
        try
        {
            if(id != null)
            {
                BlocoEntity entity = blocoRepository.findById(id).orElseThrow(
                        EntityNotFoundException::new
                );
                Bloco response = blocoMapper.EntityToDto(entity);
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

    public ResponseEntity<Bloco> NovoBloco(String area, int referencia)
    {
        try
        {
            if(area != null)
            {
                BlocoEntity entity = new BlocoEntity();
                entity.SetInfo(area);
                entity.setReferencia(area+"_"+referencia);
                blocoRepository.save(entity);
                Bloco response = blocoMapper.EntityToDto(entity);
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

    public ResponseEntity<Bloco> DeleteBlocoPorId(Long id)
    {
        try
        {
            if(id != null)
            {

            }
            else {throw new NullargumentsException();}
        }
        catch (Exception e)
        {
            e.getMessage();
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<Void> SalvarAlteracoes(Bloco bloco)
    {
        try
        {
            if(bloco != null)
            {
                BlocoEntity entity = blocoMapper.DtoToEntity(bloco);
                blocoRepository.save(entity);
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
