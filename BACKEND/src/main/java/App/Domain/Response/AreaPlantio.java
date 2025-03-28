package App.Domain.Response;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;

import java.time.LocalDateTime;
import java.util.List;

public class AreaPlantio {

    private Long id;

    private String nomeIdentificador;

    private String codigo;

    private String dimencao;

    private String gps;

    private List<String> notificacoes;

    private List<SubAreaPlantio> subareas;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime timeStamp;

    public AreaPlantio() {
    }

    public AreaPlantio(Long id, String nomeIdentificador, String codigo, String dimencao, String gps, List<String> notificacoes, List<SubAreaPlantio> subareas, LocalDateTime timeStamp) {
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

    public List<String> getNotificacoes() {
        return notificacoes;
    }

    public void setNotificacoes(List<String> notificacoes) {
        this.notificacoes = notificacoes;
    }

    public List<SubAreaPlantio> getSubareas() {
        return subareas;
    }

    public void setSubareas(List<SubAreaPlantio> subareas) {
        this.subareas = subareas;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getGps() {
        return gps;
    }

    public void setGps(String gps) {
        this.gps = gps;
    }

    public void Adubacao(String resumoAdubacao) {
        this.notificacoes.add(resumoAdubacao);
        this.setTimeStamp(LocalDateTime.now());
    }

    public void AddNovaSubArea(SubAreaPlantio subAreaPlantio)
    {
        this.getSubareas().add(subAreaPlantio);
        this.timeStamp = LocalDateTime.now();
    }

}
