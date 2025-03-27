package App.Infra.Persistence.Entity;

import App.Infra.Exceptions.IllegalActionException;
import App.Infra.Persistence.Enum.FASEATUAL;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
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

    private int numeroSubareaPlantio;

    private String areaPlantio;

    @Enumerated(EnumType.STRING)
    @Column(name = "faseatual")
    private FASEATUAL faseatual;

    private String instrucoes;

    private Boolean cavalo;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataPlantio;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataAdubacao;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime timeStamp;

    private List<String> notificacoes;

    public PlantaEntity() {
    }

    public PlantaEntity(Long id, String nomeCientifico, String nomePopular, String codigo, int numeroSubareaPlantio, String areaPlantio, FASEATUAL faseatual, String instrucoes, Boolean cavalo, LocalDate dataPlantio, LocalDate dataAdubacao, LocalDateTime timeStamp, List<String> notificacoes) {
        this.id = id;
        this.nomeCientifico = nomeCientifico;
        this.nomePopular = nomePopular;
        this.codigo = codigo;
        this.numeroSubareaPlantio = numeroSubareaPlantio;
        this.areaPlantio = areaPlantio;
        this.faseatual = faseatual;
        this.instrucoes = instrucoes;
        this.cavalo = cavalo;
        this.dataPlantio = dataPlantio;
        this.dataAdubacao = dataAdubacao;
        this.timeStamp = timeStamp;
        this.notificacoes = notificacoes;
    }

    public int getNumeroSubareaPlantio() {
        return numeroSubareaPlantio;
    }

    public void setNumeroSubareaPlantio(int numeroSubareaPlantio) {
        this.numeroSubareaPlantio = numeroSubareaPlantio;
    }

    public String getAreaPlantio() {
        return areaPlantio;
    }

    public void setAreaPlantio(String areaPlantio) {
        this.areaPlantio = areaPlantio;
    }

    public List<String> getNotificacoes() {
        return notificacoes;
    }

    public void setNotificacoes(List<String> notificacoes) {
        this.notificacoes = notificacoes;
    }

    public LocalDate getDataAdubacao() {
        return dataAdubacao;
    }

    public void setDataAdubacao(LocalDate dataAdubacao) {
        this.dataAdubacao = dataAdubacao;
    }

    public Boolean getCavalo() {
        return cavalo;
    }

    public void setCavalo(Boolean cavalo) {
        this.cavalo = cavalo;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeCientifico() {
        return nomeCientifico;
    }

    public void setNomeCientifico(String nomeCientifico) {
        this.nomeCientifico = nomeCientifico;
    }

    public String getNomePopular() {
        return nomePopular;
    }

    public void setNomePopular(String nomePopular) {
        this.nomePopular = nomePopular;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }

    public FASEATUAL getFaseatual() {
        return faseatual;
    }

    public void setFaseatual(FASEATUAL faseatual) {
        this.faseatual = faseatual;
    }

    public LocalDate getDataPlantio() {
        return dataPlantio;
    }

    public void setDataPlantio(LocalDate dataPlantio) {
        this.dataPlantio = dataPlantio;
    }

    public String getInstrucoes() {
        return instrucoes;
    }

    public void setInstrucoes(String instrucoes) {
        this.instrucoes = instrucoes;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public void FimCiclo()
    {
        this.faseatual = FASEATUAL.FIM;
        this.timeStamp = LocalDateTime.now();
        this.numeroSubareaPlantio = 0;
        this.areaPlantio = null;
    }

    public void SetDadosEnxertia(String nomeCientifico, String nomePopular, String instrucoes)
    {
        this.nomeCientifico = nomeCientifico;
        this.nomePopular = nomePopular;
        this.instrucoes = instrucoes;
        this.timeStamp = LocalDateTime.now();
    }

    public Boolean AtribuirSubArea(int numero, String nomeAreaPlantio)
    {
        if(this.numeroSubareaPlantio == numero) {throw new IllegalActionException();}
        this.numeroSubareaPlantio = numero;
        this.areaPlantio = nomeAreaPlantio;
        this.faseatual = FASEATUAL.GERMINACAO;
        this.timeStamp = LocalDateTime.now();
        return Boolean.TRUE;
    }

    public Boolean ValidaAlteracao(FASEATUAL faseatual)
    {
        if(this.faseatual.equals(faseatual)){throw new IllegalActionException();}
        if(this.faseatual == FASEATUAL.AGUARDANDO && faseatual != FASEATUAL.GERMINACAO){throw new IllegalActionException();}
        if(this.faseatual == FASEATUAL.GERMINACAO && faseatual != FASEATUAL.MUDA){throw new IllegalActionException();}
        if(this.faseatual == FASEATUAL.MUDA && faseatual != FASEATUAL.CRESCIMENTO){throw new IllegalActionException();}
        return Boolean.TRUE;
    }

    public Boolean ValidaAtribuicao(int numeroSubareaPlantio)
    {
        if(this.numeroSubareaPlantio == numeroSubareaPlantio){throw new IllegalActionException();}
        return Boolean.TRUE;
    }

    public Boolean SetNovoCiclo(FASEATUAL faseatual)
    {
        this.faseatual = faseatual;
        this.timeStamp = LocalDateTime.now();
        return Boolean.TRUE;
    }

    public void Adubacao(String mensagem)
    {
        if(mensagem != null)
        {
            this.dataAdubacao = LocalDate.now();
            this.timeStamp = LocalDateTime.now();
            this.notificacoes.add(mensagem);
        }
    }

}
