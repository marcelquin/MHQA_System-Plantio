package App.Infra.Persistence.Entity;

import App.Domain.Response.Planta;
import App.Infra.Exceptions.IllegalActionException;
import App.Infra.Persistence.Enum.TAMANHO;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Subarea_Plantio")
public class SubAreaPlantioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int eixoX;

    private int eixoY;

    @JoinColumn(unique = true)
    private String codigo;

    @OneToOne
    @JoinColumn(name = "plantaEntity_id", referencedColumnName = "id")
    private PlantaEntity planta;

    private Boolean disponivel;

    private String nomeAreaPlantio;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataInicioCiclo;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataAdubacao;

    private List<String> notificacoes;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime timeStamp;

    public SubAreaPlantioEntity() {
    }

    public SubAreaPlantioEntity(Long id, int eixoX, int eixoY, String codigo, PlantaEntity planta, Boolean disponivel, String nomeAreaPlantio, LocalDate dataInicioCiclo, LocalDate dataAdubacao, List<String> notificacoes, LocalDateTime timeStamp) {
        this.id = id;
        this.eixoX = eixoX;
        this.eixoY = eixoY;
        this.codigo = codigo;
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


    public PlantaEntity getPlanta() {
        return planta;
    }

    public void setPlanta(PlantaEntity planta) {
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

    public Boolean validaAtribuicao(PlantaEntity planta)
    {
        if(this.planta != null){throw new IllegalActionException();}
        if(this.planta.getId() == planta.getId()){throw new IllegalActionException();}
        return Boolean.TRUE;
    }

    public void SetInfoInicial()
    {
        List<String> list = new ArrayList<>();
        this.notificacoes = list;
        this.timeStamp = LocalDateTime.now();
    }

    public void SetInfo(int eixox, int eixoY, String nomeAreaPlantio, String codigo)
    {
        this.eixoX = eixox;
        this.eixoY = eixoY;
        this.codigo = codigo;
        this.nomeAreaPlantio = nomeAreaPlantio;
        this.timeStamp = LocalDateTime.now();
        this.disponivel = Boolean.TRUE;
    }

    public void AtribuirPlanta(PlantaEntity planta)
    {
        if(this.planta == null)
        {
            this.planta = planta;
            this.disponivel = Boolean.FALSE;
            this.dataInicioCiclo = LocalDate.now();
            this.timeStamp = LocalDateTime.now();
        }
    }

    public void Adubacao(String adubacao)
    {
        this.notificacoes.add(adubacao);
        this.timeStamp = LocalDateTime.now();
        this.dataAdubacao = LocalDate.now();
    }

    public void ResetInformacao()
    {
        this.planta = null;
        this.disponivel = Boolean.TRUE;
        this.dataInicioCiclo = null;
        this.timeStamp = LocalDateTime.now();
    }

}
