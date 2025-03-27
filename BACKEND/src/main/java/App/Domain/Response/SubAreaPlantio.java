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

    @JoinColumn(unique = true)
    private int numero;

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

    public SubAreaPlantio(Long id, String cor, int numero, TAMANHO tamanho, Planta planta, Boolean disponivel, String nomeAreaPlantio, LocalDate dataInicioCiclo, LocalDate dataAdubacao, List<String> notificacoes, LocalDateTime timeStamp) {
        this.id = id;
        this.cor = cor;
        this.numero = numero;
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

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
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

    public String getNomeCanteiro() {
        return nomeAreaPlantio;
    }

    public void setNomeCanteiro(String nomeCanteiro) {
        this.nomeAreaPlantio = nomeCanteiro;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
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

    public void AtribuirPlanta(Planta planta)
    {
        this.planta = planta;
        this.disponivel = Boolean.FALSE;
        this.dataInicioCiclo = LocalDate.now();
        this.timeStamp = LocalDateTime.now();
    }
    public void Adubacao(String adubacao)
    {
        this.notificacoes.add(adubacao);
        this.setTimeStamp(LocalDateTime.now());
        this.setDataAdubacao(LocalDate.now());
    }

    public void ResetInformacao()
    {
        List<String> list = new ArrayList<>();
        setPlanta(null);
        setDisponivel(Boolean.TRUE);
        setNotificacoes(list);
        setDataAdubacao(null);
        setDataInicioCiclo(null);
        setTimeStamp(null);
    }
}
