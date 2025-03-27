package App.Infra.Persistence.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "Area_Plantio")
public class AreaPlantioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(unique = true)
    private String nomeIdentificador;

    private String codigo;

    private String dimencao;

    private String gps;

    private List<String> notificacoes;

    @OneToMany
    private List<SubAreaPlantioEntity> subareas;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime timeStamp;

    public AreaPlantioEntity() {
    }

    public AreaPlantioEntity(Long id, String nomeIdentificador, String codigo, String dimencao, String gps, List<String> notificacoes, List<SubAreaPlantioEntity> subareas, LocalDateTime timeStamp) {
        this.id = id;
        this.nomeIdentificador = nomeIdentificador;
        this.codigo = codigo;
        this.dimencao = dimencao;
        this.gps = gps;
        this.notificacoes = notificacoes;
        this.subareas = subareas;
        this.timeStamp = timeStamp;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeIdentificador() {
        return nomeIdentificador;
    }

    public void setNomeIdentificador(String nomeIdentificador) {
        this.nomeIdentificador = nomeIdentificador;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDimencao() {
        return dimencao;
    }

    public void setDimencao(String dimencao) {
        this.dimencao = dimencao;
    }

    public String getGps() {
        return gps;
    }

    public void setGps(String gps) {
        this.gps = gps;
    }

    public List<String> getNotificacoes() {
        return notificacoes;
    }

    public void setNotificacoes(List<String> notificacoes) {
        this.notificacoes = notificacoes;
    }

    public List<SubAreaPlantioEntity> getSubareas() {
        return subareas;
    }

    public void setSubareas(List<SubAreaPlantioEntity> subareas) {
        this.subareas = subareas;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }

    public void Adubacao(String resumoAdubacao)
    {
        this.notificacoes.add(resumoAdubacao);
        this.setTimeStamp(LocalDateTime.now());
    }
}
