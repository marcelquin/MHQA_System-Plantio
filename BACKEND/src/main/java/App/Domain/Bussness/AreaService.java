package App.Domain.Bussness;

import App.Domain.Response.Area;
import App.Domain.Response.Localizacao;
import App.Infra.Exceptions.EntityNotFoundException;
import App.Infra.Exceptions.IllegalActionException;
import App.Infra.Exceptions.NullargumentsException;
import App.Infra.Gateway.AreaGateway;
import App.Infra.Mapper.AreaMapper;
import App.Infra.Mapper.LocalizacaoMapper;
import App.Infra.Persistence.Entity.AreaEntity;
import App.Infra.Persistence.Entity.LocalizacaoEntity;
import App.Infra.Persistence.Repository.AreaRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AreaService implements AreaGateway {

    private final AreaRepository areaRepository;
    private final AreaMapper areaMapper;
    private final LocalizacaoService localizacaoService;
    private final LocalizacaoMapper localizacaoMapper;


    public AreaService(AreaRepository areaRepository, AreaMapper areaMapper, @Lazy LocalizacaoService localizacaoService, LocalizacaoMapper localizacaoMapper) {
        this.areaRepository = areaRepository;
        this.areaMapper = areaMapper;
        this.localizacaoService = localizacaoService;
        this.localizacaoMapper = localizacaoMapper;
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
    public ResponseEntity<Area> NovaArea(String dimensao,String nomeIdentificador, int eixoX, int eixoY)
    {
        try
        {
            if(eixoX < 0){throw new IllegalActionException();}
            if(eixoY < 0){throw new IllegalActionException();}
            if (dimensao != null &&
                nomeIdentificador != null &&
                eixoX > 0 &&
                eixoY > 0)
            {
                List<Localizacao> localizacaoList = new ArrayList<>();
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
                            int eixoXAtual = i + 1;
                            int eixoYAtual = j + 1;
                            //"C "+eixoX+" x L "+eixoY
                            Localizacao localizacao = localizacaoService.NovaLocalizacao(nomeIdentificador,eixoXAtual,eixoYAtual).getBody();
                            localizacaoList.add(localizacao);
                        }
                    }
                }
                AreaEntity entity = new AreaEntity();
                entity.SetInfo(dimensao,nomeIdentificador);
                for(Localizacao localizacao : localizacaoList)
                {
                    LocalizacaoEntity localizacaoEntity = localizacaoMapper.DtoToEntity(localizacao);
                    entity.getLocalizacoes().add(localizacaoEntity);
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

    /*
    public ResponseEntity<Area> EditarArea(Long id, String dimensao, String nomeIdentificador, int eixos)
    {
        try
        {
            if (dimensao != null &&
                id != null)
            {
                Area area = BuscarAreaPorId(id).getBody();
                List<String> cods = new ArrayList<>();
                List<String> codsInterno = new ArrayList<>();
                if(codsInterno.size() > cods.size())
                {
                    int ROWS = eixos;
                    int COLS = eixos;
                    int[][] matrix = new int[ROWS][COLS];
                    int currentValue = 1;
                    for (int i = 0; i < ROWS; i++)
                    {
                        for (int j = 0; j < COLS; j++)
                        {
                            if (matrix[i][j] == 0)
                            {
                                matrix[i][j] = currentValue++;
                                int eixoXAtual = i + 1;
                                int eixoYAtual = j + 1;
                                String cod = "C "+eixoXAtual+" x L "+eixoYAtual;
                                System.out.println(cod);
                                Boolean verifica = localizacaoService.verificaReferencia(cod).getBody();
                                System.out.println(verifica);
                            }
                        }
                    }
                    System.out.println("remove");
                }
                if(codsInterno.size() < cods.size())
                {
                    System.out.println("add");
                }
                return new ResponseEntity<>(HttpStatus.OK);
            }
            else {throw new NullargumentsException(); }
        } catch (Exception e)
        {
            e.getMessage();
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

     */
}
