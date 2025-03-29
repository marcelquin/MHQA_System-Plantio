package App.Domain.Response;

import App.Infra.Exceptions.IllegalActionException;
import App.Infra.Persistence.Entity.PlantaEntity;
import App.Infra.Persistence.Enum.TAMANHO;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class SubAreaPlantio {

    private Long id;

    private String cor;

    private int eixoX;

    private int eixoY;

    private String codigo;

    @Enumerated(EnumType.STRING)
    private TAMANHO tamanho;

    private Planta planta;

    private Boolean disponivel;

    private String nomeAreaPlantio;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataInicioCiclo;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataAdubacao;

    private List<String> notificacoes;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime timeStamp;

    public SubAreaPlantio() {
    }

    public SubAreaPlantio(Long id, String cor, int eixoX, int eixoY, String codigo, TAMANHO tamanho, Planta planta, Boolean disponivel, String nomeAreaPlantio, LocalDate dataInicioCiclo, LocalDate dataAdubacao, List<String> notificacoes, LocalDateTime timeStamp) {
        this.id = id;
        this.cor = cor;
        this.eixoX = eixoX;
        this.eixoY = eixoY;
        this.codigo = codigo;
        this.tamanho = tamanho;
        this.planta = planta;
        this.disponivel = disponivel;
        this.nomeAreaPlantio = nomeAreaPlantio;
        this.dataInicioCiclo = dataInicioCiclo;
        this.dataAdubacao = dataAdubacao;
        this.notificacoes = notificacoes;
        this.timeStamp = timeStamp;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public int getEixoX() {
        return eixoX;
    }

    public void setEixoX(int eixoX) {
        this.eixoX = eixoX;
    }

    public int getEixoY() {
        return eixoY;
    }

    public void setEixoY(int eixoY) {
        this.eixoY = eixoY;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public TAMANHO getTamanho() {
        return tamanho;
    }

    public void setTamanho(TAMANHO tamanho) {
        this.tamanho = tamanho;
    }

    public Planta getPlanta() {
        return planta;
    }

    public void setPlanta(Planta planta) {
        this.planta = planta;
    }

    public Boolean getDisponivel() {
        return disponivel;
    }

    public void setDisponivel(Boolean disponivel) {
        this.disponivel = disponivel;
    }

    public String getNomeAreaPlantio() {
        return nomeAreaPlantio;
    }

    public void setNomeAreaPlantio(String nomeAreaPlantio) {
        this.nomeAreaPlantio = nomeAreaPlantio;
    }

    public LocalDate getDataInicioCiclo() {
        return dataInicioCiclo;
    }

    public void setDataInicioCiclo(LocalDate dataInicioCiclo) {
        this.dataInicioCiclo = dataInicioCiclo;
    }

    public LocalDate getDataAdubacao() {
        return dataAdubacao;
    }

    public void setDataAdubacao(LocalDate dataAdubacao) {
        this.dataAdubacao = dataAdubacao;
    }

    public List<String> getNotificacoes() {
        return notificacoes;
    }

    public void setNotificacoes(List<String> notificacoes) {
        this.notificacoes = notificacoes;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }
}
