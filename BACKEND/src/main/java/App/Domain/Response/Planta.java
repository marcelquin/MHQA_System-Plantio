package App.Domain.Response;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class Planta {

    private Long id;

    private String nomeCimentifico;

    private String nomePopular;

    private String instrucoes;

    private List<String> notificacoes;

    private Localizacao localizacao;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate DataPlantio;

    private Ciclo ciclo;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime timeStamp;

    public Planta() {
    }

    public Planta(Long id, String nomeCimentifico, String nomePopular, String instrucoes, List<String> notificacoes, Localizacao localizacao, LocalDate dataPlantio, Ciclo ciclo, LocalDateTime timeStamp) {
        this.id = id;
        this.nomeCimentifico = nomeCimentifico;
        this.nomePopular = nomePopular;
        this.instrucoes = instrucoes;
        this.notificacoes = notificacoes;
        this.localizacao = localizacao;
        DataPlantio = dataPlantio;
        this.ciclo = ciclo;
        this.timeStamp = timeStamp;
    }

    public LocalDate getDataPlantio() {
        return DataPlantio;
    }

    public void setDataPlantio(LocalDate dataPlantio) {
        DataPlantio = dataPlantio;
    }

    public String getInstrucoes() {
        return instrucoes;
    }

    public void setInstrucoes(String instrucoes) {
        this.instrucoes = instrucoes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeCimentifico() {
        return nomeCimentifico;
    }

    public void setNomeCimentifico(String nomeCimentifico) {
        this.nomeCimentifico = nomeCimentifico;
    }

    public String getNomePopular() {
        return nomePopular;
    }

    public void setNomePopular(String nomePopular) {
        this.nomePopular = nomePopular;
    }

    public List<String> getNotificacoes() {
        return notificacoes;
    }

    public void setNotificacoes(List<String> notificacoes) {
        this.notificacoes = notificacoes;
    }

    public Localizacao getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(Localizacao localizacao) {
        this.localizacao = localizacao;
    }

    public Ciclo getCiclo() {
        return ciclo;
    }

    public void setCiclo(Ciclo ciclo) {
        this.ciclo = ciclo;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }
}
