package App.Infra.Persistence.Entity;

import App.Infra.Exceptions.IllegalActionException;
import App.Infra.Persistence.Enum.FASEATUAL;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "planta")
public class PlantaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomeCientifico;

    private String nomePopular;

    @JoinColumn(unique = true)
    private String codigo;

    private String localizacao;

    private String areaPlantio;

    @Enumerated(EnumType.STRING)
    @Column(name = "faseatual")
    private FASEATUAL faseatual;

    private String instrucoes;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataPlantio;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataUltimoCiclo;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataCicloAtual;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataAdubacao;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime timeStamp;

    private List<String> notificacoes;

    public PlantaEntity() {
    }

    public PlantaEntity(Long id, String nomeCientifico, String nomePopular, String codigo, String localizacao, String areaPlantio, FASEATUAL faseatual, String instrucoes, LocalDate dataPlantio, LocalDate dataUltimoCiclo, LocalDate dataCicloAtual, LocalDate dataAdubacao, LocalDateTime timeStamp, List<String> notificacoes) {
        this.id = id;
        this.nomeCientifico = nomeCientifico;
        this.nomePopular = nomePopular;
        this.codigo = codigo;
        this.localizacao = localizacao;
        this.areaPlantio = areaPlantio;
        this.faseatual = faseatual;
        this.instrucoes = instrucoes;
        this.dataPlantio = dataPlantio;
        this.dataUltimoCiclo = dataUltimoCiclo;
        this.dataCicloAtual = dataCicloAtual;
        this.dataAdubacao = dataAdubacao;
        this.timeStamp = timeStamp;
        this.notificacoes = notificacoes;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public Long getId() {
        return id;
    }

    public String getNomeCientifico() {
        return nomeCientifico;
    }

    public String getNomePopular() {
        return nomePopular;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getAreaPlantio() {
        return areaPlantio;
    }

    public FASEATUAL getFaseatual() {
        return faseatual;
    }

    public String getInstrucoes() {
        return instrucoes;
    }

    public LocalDate getDataPlantio() {
        return dataPlantio;
    }

    public LocalDate getDataAdubacao() {
        return dataAdubacao;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public List<String> getNotificacoes() {
        return notificacoes;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNomeCientifico(String nomeCientifico) {
        this.nomeCientifico = nomeCientifico;
    }

    public void setNomePopular(String nomePopular) {
        this.nomePopular = nomePopular;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    public void setAreaPlantio(String areaPlantio) {
        this.areaPlantio = areaPlantio;
    }

    public void setFaseatual(FASEATUAL faseatual) {
        this.faseatual = faseatual;
    }

    public void setInstrucoes(String instrucoes) {
        this.instrucoes = instrucoes;
    }

    public void setDataPlantio(LocalDate dataPlantio) {
        this.dataPlantio = dataPlantio;
    }

    public void setDataAdubacao(LocalDate dataAdubacao) {
        this.dataAdubacao = dataAdubacao;
    }

    public void setTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }

    public void setNotificacoes(List<String> notificacoes) {
        this.notificacoes = notificacoes;
    }

    public LocalDate getDataUltimoCiclo() {
        return dataUltimoCiclo;
    }

    public void setDataUltimoCiclo(LocalDate dataUltimoCiclo) {
        this.dataUltimoCiclo = dataUltimoCiclo;
    }

    public LocalDate getDataCicloAtual() {
        return dataCicloAtual;
    }

    public void setDataCicloAtual(LocalDate dataCicloAtual) {
        this.dataCicloAtual = dataCicloAtual;
    }

    public void AtribuirSubArea(String codigoSubArea , String nomeAreaPlantio)
    {
        this.localizacao = codigoSubArea;
        this.areaPlantio = nomeAreaPlantio;
        this.faseatual = FASEATUAL.GERMINACAO;
        this.timeStamp = LocalDateTime.now();
        this.dataPlantio = LocalDate.now();
        this.dataCicloAtual = LocalDate.now();
    }

    public Boolean SetNovoCiclo(FASEATUAL faseatual)
    {
        this.faseatual = faseatual;
        this.timeStamp = LocalDateTime.now();
        this.dataUltimoCiclo = this.dataCicloAtual;
        this.dataCicloAtual = LocalDate.now();
        return Boolean.TRUE;
    }

    public void SetNovaInfo(){this.dataCicloAtual = LocalDate.now();}

    public void Adubacao(String mensagem)
    {
        if(mensagem != null)
        {
            this.dataAdubacao = LocalDate.now();
            this.timeStamp = LocalDateTime.now();
            this.notificacoes.add(mensagem);
        }
    }

    public void SetInfo(String nomeCientifico, String nomePopular, String instrucoes)
    {
        this.nomePopular = nomePopular;
        this.nomeCientifico = nomeCientifico;
        this.instrucoes = instrucoes;
        this.timeStamp = LocalDateTime.now();
        this.dataCicloAtual = LocalDate.now();
    }

    public void SetInfoInicial(String nomeCientifico, String nomePopular, String codigo, String instrucoes)
    {
        List<String> list = new ArrayList<>();
        this.faseatual = FASEATUAL.AGUARDANDO;
        this.timeStamp = LocalDateTime.now();
        this.notificacoes = list;
        this.nomeCientifico = nomeCientifico;
        this.nomePopular = nomePopular;
        this.codigo = codigo;
        this.instrucoes = instrucoes;
    }
    public void ResetInfo()
    {
        this.areaPlantio = null;
        this.dataPlantio = null;
        this.localizacao = null;
        this.dataAdubacao = null;
        this.timeStamp = LocalDateTime.now();
    }

    public void FimCiclo()
    {
        this.faseatual = FASEATUAL.FIM;
        this.timeStamp = LocalDateTime.now();
        this.localizacao = null;
        this.areaPlantio = null;
        this.dataCicloAtual = LocalDate.now();
    }

    public Boolean ValidaAtribuicao(String localizacao)
    {
        if(this.localizacao == localizacao) {throw new IllegalActionException();}
        return Boolean.TRUE;
    }

    public Boolean ValidaAlteracaoCiclo(FASEATUAL faseatual)
    {
        if(this.faseatual.equals(faseatual)){throw new IllegalActionException();}
        if(this.faseatual == FASEATUAL.AGUARDANDO && faseatual != FASEATUAL.GERMINACAO){throw new IllegalActionException();}
        if(this.faseatual == FASEATUAL.GERMINACAO && faseatual != FASEATUAL.MUDA){throw new IllegalActionException();}
        if(this.faseatual == FASEATUAL.MUDA && faseatual != FASEATUAL.CRESCIMENTO){throw new IllegalActionException();}
        return Boolean.TRUE;
    }

}
