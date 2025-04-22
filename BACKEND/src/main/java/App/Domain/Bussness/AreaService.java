package App.Domain.Bussness;


import App.Domain.Response.*;
import App.Infra.Exceptions.EntityNotFoundException;
import App.Infra.Exceptions.IllegalActionException;
import App.Infra.Exceptions.NullargumentsException;
import App.Infra.Gateway.AreaGateway;

import App.Infra.Mapper.*;

import App.Infra.Persistence.Entity.*;
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
    private final CicloMapper cicloMapper;


    public AreaService(AreaRepository areaRepository, AreaMapper areaMapper, @Lazy LocalizacaoService localizacaoService, @Lazy BlocoService blocoService, LocalizacaoMapper localizacaoMapper, BlocoMapper blocoMapper, PlantaMapper plantaMapper, PlantaService plantaService, @Lazy CicloService cicloService, CicloMapper cicloMapper) {
        this.areaRepository = areaRepository;
        this.areaMapper = areaMapper;
        this.localizacaoService = localizacaoService;
        this.blocoService = blocoService;
        this.localizacaoMapper = localizacaoMapper;
        this.blocoMapper = blocoMapper;
        this.plantaMapper = plantaMapper;
        this.plantaService = plantaService;
        this.cicloService = cicloService;
        this.cicloMapper = cicloMapper;
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
                Area area = BuscarAreaPorId(id).getBody();
                AreaEntity entity = areaMapper.toToEntity(area);
                List<String> localizacoesAtuais = new ArrayList<>();
                List<String> blocosAtuais = new ArrayList<>();
                List<String> localizacoesAtuaisEntity = new ArrayList<>();
                List<Localizacao> novasLocalizacoes = new ArrayList<>();
                List<Bloco> blocoList = new ArrayList<>();
                int localizacaoRequest = eixoX * eixoY;
                for(Localizacao localizacaoInterno : area.getLocalizacoes())
                {
                    localizacoesAtuais.add(localizacaoInterno.getReferencia());
                }
                for(Bloco blocoInterno : area.getBlocos())
                {
                    blocosAtuais.add(blocoInterno.getReferencia());
                }
                for(LocalizacaoEntity loc : entity.getLocalizacoes())
                {
                    localizacoesAtuaisEntity.add(loc.getReferencia());
                }
 /*               if(area.getLocalizacoes().size() < localizacaoRequest)
                {
                    System.out.println("adicionar");
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
                                String cod = "C "+eixoXAtual+" x L "+eixoYAtual;
                                //novasLocalizacoes.add(cod);
                                if(localizacoesAtuais.contains(cod))
                                {

                                }
                                else
                                {
                                    System.out.println("novo "+ cod);
                                    Localizacao localizacao = localizacaoService.NovaLocalizacao(area.getNomeIdentificador(),eixoXAtual,eixoYAtual).getBody();
                                    novasLocalizacoes.add(localizacao);
                                }
                            }
                        }
                    }
                    area.getLocalizacoes().addAll(novasLocalizacoes);
                }
                else
                {
                    System.out.println("remover");
                    List<String> cods = new ArrayList<>();
                    List<Localizacao> novaLista = new ArrayList<>();
                    int ROWS = eixoY;
                    int COLS = eixoX;
                    int[][] matrix = new int[ROWS][COLS];
                    int currentValue = 1;
                    for (int i = 0; i < ROWS; i++) {
                        for (int j = 0; j < COLS; j++) {
                            if (matrix[i][j] == 0) {
                                matrix[i][j] = currentValue++;
                                int eixoXAtual = j + 1;
                                int eixoYAtual = i + 1;
                                String cod = "C " + eixoXAtual + " x L " + eixoYAtual;
                                cods.add(cod);
                            }
                        }
                    }
                    System.out.println(cods);
                    System.out.println(localizacoesAtuais);
                    System.out.println("antes: "+area.getLocalizacoes().size());
                    for(String cod : cods)
                    {
                        Localizacao localizacaoInterna = localizacaoService.BuscarLocalizacaoPorreferencia(cod).getBody();
                        novaLista.add(localizacaoInterna);
                    }
                    area.setLocalizacoes(novaLista);
                    entity = areaMapper.toToEntity(area);
                    areaRepository.save(entity);
                    for(String cod : localizacoesAtuaisEntity)
                    {
                        if(!cods.contains(cod))
                        {
                            Localizacao localizacao = localizacaoService.BuscarLocalizacaoPorreferencia(cod).getBody();
                            for(Planta planta : area.getPlantas())
                            {
                                if(planta.getLocalizacao().getId().equals(localizacao.getId()))
                                {
                                    Ciclo ciclo = cicloService.BuscarCicloPorId(planta.getCiclo().getId()).getBody();
                                    CicloEntity cicloEntity = cicloMapper.DtoToEntity(ciclo);
                                    cicloEntity.FimCiclo();
                                    ciclo = cicloMapper.EntityToDto(cicloEntity);
                                    cicloService.SalvarAlteracao(ciclo);
                                    PlantaEntity plantaEntity = plantaMapper.DtoToEntity(planta);
                                    plantaEntity.FimCiclo();
                                    planta = plantaMapper.EntityToDto(plantaEntity);
                                    plantaService.SalvarAlteracao(planta);
                                }
                            }
                            localizacaoService.DeletarLocalizacaoPorId(localizacao.getId());
                        }
                    }
                    System.out.println("depois: "+area.getLocalizacoes().size());
                }*/
                if(area.getBlocos().size() < quantidadeBlocos)
                {
                    int referencia = area.getBlocos().size()+1;
                    System.out.println(referencia);
                    for(int i = referencia; i <= quantidadeBlocos; i++)
                    {
                        Bloco bloco = blocoService.NovoBloco(area.getNomeIdentificador(),i).getBody();
                        blocoList.add(bloco);
                    }
                    area.getBlocos().addAll(blocoList);
                    System.out.println("lista: "+area.getBlocos().size());
                }
                else
                {
                    System.out.println("salvo mas não apagou");
                    List<String> novosBlocos = new ArrayList<>();
                    List<Bloco> novosBlocosEntity = new ArrayList<>();
                    for(int i = 1; i <= quantidadeBlocos; i++)
                    {
                        String ref = area.getNomeIdentificador()+"_"+i;
                        novosBlocos.add(ref);
                    }
                    for(String cod : novosBlocos)
                    {
                        Bloco bloco = blocoService.BuscarBlocoPorReferencia(cod).getBody();
                        novosBlocosEntity.add(bloco);
                    }
                    area.setBlocos(novosBlocosEntity);
                    entity = areaMapper.toToEntity(area);
                    areaRepository.saveAndFlush(entity);
                    for(String codBloco : blocosAtuais)
                    {
                        if(!novosBlocos.contains(codBloco))
                        {
                            Bloco bloco = blocoService.BuscarBlocoPorReferencia(codBloco).getBody();
                            for(Planta planta : area.getPlantas())
                            {
                                if(planta.getBloco().getId().equals(bloco.getId()))
                                {
                                    Ciclo ciclo = cicloService.BuscarCicloPorId(planta.getCiclo().getId()).getBody();
                                    CicloEntity cicloEntity = cicloMapper.DtoToEntity(ciclo);
                                    cicloEntity.FimCiclo();
                                    ciclo = cicloMapper.EntityToDto(cicloEntity);
                                    cicloService.SalvarAlteracao(ciclo);
                                    PlantaEntity plantaEntity = plantaMapper.DtoToEntity(planta);
                                    plantaEntity.FimCiclo();
                                    planta = plantaMapper.EntityToDto(plantaEntity);
                                    plantaService.SalvarAlteracao(planta);
                                }
                            }
                            blocoService.DeleteBlocoPorId(bloco.getId());
                        }
                    }
                }
                //entity = areaMapper.toToEntity(area);
                //areaRepository.save(entity);
                return new ResponseEntity<>(area, HttpStatus.OK);
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
