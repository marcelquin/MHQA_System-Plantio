package App.Infra.Persistence.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
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

    private String dimensao;

    private String gps;

    private int eixoX;

    private int eixoy;

    private int maxQuantidadeSubareas;

    private List<String> notificacoes;

    @OneToMany
    private List<SubAreaPlantioEntity> subareas;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime timeStamp;

    public AreaPlantioEntity() {
    }

    public AreaPlantioEntity(Long id, String nomeIdentificador, String codigo, String dimensao, String gps, int eixoX, int eixoy, int maxQuantidadeSubareas, List<String> notificacoes, List<SubAreaPlantioEntity> subareas, LocalDateTime timeStamp) {
        this.id = id;
        this.nomeIdentificador = nomeIdentificador;
        this.codigo = codigo;
        this.dimensao = dimensao;
        this.gps = gps;
        this.eixoX = eixoX;
        this.eixoy = eixoy;
        this.maxQuantidadeSubareas = maxQuantidadeSubareas;
        this.notificacoes = notificacoes;
        this.subareas = subareas;
        this.timeStamp = timeStamp;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNomeIdentificador(String nomeIdentificador) {
        this.nomeIdentificador = nomeIdentificador;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public void setDimensao(String dimensao) {
        this.dimensao = dimensao;
    }

    public void setGps(String gps) {
        this.gps = gps;
    }

    public void setEixoX(int eixoX) {
        this.eixoX = eixoX;
    }

    public void setEixoy(int eixoy) {
        this.eixoy = eixoy;
    }

    public void setMaxQuantidadeSubareas(int maxQuantidadeSubareas) {
        this.maxQuantidadeSubareas = maxQuantidadeSubareas;
    }

    public void setNotificacoes(List<String> notificacoes) {
        this.notificacoes = notificacoes;
    }

    public void setSubareas(List<SubAreaPlantioEntity> subareas) {
        this.subareas = subareas;
    }

    public void setTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }

    public int getEixoX() {
        return eixoX;
    }

    public int getEixoy() {
        return eixoy;
    }

    public int getMaxQuantidadeSubareas() {
        return maxQuantidadeSubareas;
    }

    public Long getId() {
        return id;
    }

    public String getNomeIdentificador() {
        return nomeIdentificador;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getDimensao() {
        return dimensao;
    }

    public String getGps() {
        return gps;
    }

    public List<String> getNotificacoes() {
        return notificacoes;
    }

    public List<SubAreaPlantioEntity> getSubareas() {
        return subareas;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public void SetInfo(String nomeIdentificador, String dimensao, String gps, String codigo, int eixoXrequest, int eixoYrequest)
    {
        this.gps = gps;
        this.nomeIdentificador = nomeIdentificador;
        this.dimensao = dimensao;
        this.codigo = codigo;
        this.timeStamp = LocalDateTime.now();
        this.eixoX = eixoXrequest;
        this.eixoy = eixoYrequest;
        this.maxQuantidadeSubareas = this.eixoX * this.eixoy;
    }

    public void SetListsIniciais()
    {
        List<SubAreaPlantioEntity> list = new ArrayList<>();
        List<String> list1 = new ArrayList<>();
        this.subareas = list;
        this.notificacoes = list1;
        this.timeStamp = LocalDateTime.now();
    }

    public void SetNovaSubArea(SubAreaPlantioEntity subAreaPlantio)
    {
        this.subareas.add(subAreaPlantio);
        this.timeStamp = LocalDateTime.now();
    }

    public void Adubacao(String resumoAdubacao)
    {
        this.notificacoes.add(resumoAdubacao);
        this.timeStamp = LocalDateTime.now();
    }



}
