package App.Infra.Persistence.Entity;

import App.Infra.Persistence.Enum.CICLO;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import org.hibernate.annotations.Cascade;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Planta")
public class PlantaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomeCimentifico;

    private String nomePopular;

    private String instrucoes;

    private List<String> notificacoes;

    @OneToOne
    @JoinColumn(name = "localizacaoEntity_id", referencedColumnName = "id")
    private LocalizacaoEntity localizacao;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate DataPlantio;

    @OneToOne
    @JoinColumn(name = "cicloEntity_id", referencedColumnName = "id")
    private CicloEntity ciclo;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime timeStamp;

    public PlantaEntity() {
    }

    public PlantaEntity(Long id, String nomeCimentifico, String nomePopular, String instrucoes, List<String> notificacoes, LocalizacaoEntity localizacao, LocalDate dataPlantio, CicloEntity ciclo, LocalDateTime timeStamp) {
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

    public String getInstrucoes() {
        return instrucoes;
    }

    public void setInstrucoes(String instrucoes) {
        this.instrucoes = instrucoes;
    }

    public List<String> getNotificacoes() {
        return notificacoes;
    }

    public void setNotificacoes(List<String> notificacoes) {
        this.notificacoes = notificacoes;
    }

    public LocalizacaoEntity getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(LocalizacaoEntity localizacao) {
        this.localizacao = localizacao;
    }

    public CicloEntity getCiclo() {
        return ciclo;
    }

    public void setCiclo(CicloEntity ciclo) {
        this.ciclo = ciclo;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }

    public void SetInfo(String nomePopular, String nomeCimentifico, String instrucoes)
    {
        List<String> list = new ArrayList<>();
        this.nomePopular = nomePopular;
        this.instrucoes = instrucoes;
        this.nomeCimentifico = nomeCimentifico;
        this.notificacoes = list;
        this.timeStamp = LocalDateTime.now();
        this.setDataPlantio(LocalDate.now());
    }

    public void EditInfo(String nomePopular, String nomeCimentifico)
    {
        this.nomePopular = nomePopular;
        this.nomeCimentifico = nomeCimentifico;
        this.timeStamp = LocalDateTime.now();
    }

    public void FimCiclo()
    {
        this.localizacao = null;
        this.timeStamp = LocalDateTime.now();
    }

}
