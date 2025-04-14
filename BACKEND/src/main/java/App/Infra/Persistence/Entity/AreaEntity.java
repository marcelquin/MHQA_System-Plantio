package App.Infra.Persistence.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import org.hibernate.annotations.Cascade;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Area")
public class AreaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomeIdentificador;

    private String dimensao;

    @OneToMany
    private List<PlantaEntity> plantas;

    @OneToMany
    private List<LocalizacaoEntity> localizacoes;

    @OneToMany
    private List<BlocoEntity> blocos;

    private List<String> notificacoes;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime dataCadastro;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime timeStamp;

    public AreaEntity() {
    }

    public AreaEntity(Long id, String nomeIdentificador, String dimensao, List<PlantaEntity> plantas, List<LocalizacaoEntity> localizacoes, List<BlocoEntity> blocos, List<String> notificacoes, LocalDateTime dataCadastro, LocalDateTime timeStamp) {
        this.id = id;
        this.nomeIdentificador = nomeIdentificador;
        this.dimensao = dimensao;
        this.plantas = plantas;
        this.localizacoes = localizacoes;
        this.blocos = blocos;
        this.notificacoes = notificacoes;
        this.dataCadastro = dataCadastro;
        this.timeStamp = timeStamp;
    }

    public List<BlocoEntity> getBlocos() {
        return blocos;
    }

    public void setBlocos(List<BlocoEntity> blocos) {
        this.blocos = blocos;
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

    public String getDimensao() {
        return dimensao;
    }

    public void setDimensao(String dimensao) {
        this.dimensao = dimensao;
    }

    public List<PlantaEntity> getPlantas() {
        return plantas;
    }

    public void setPlantas(List<PlantaEntity> plantas) {
        this.plantas = plantas;
    }

    public List<LocalizacaoEntity> getLocalizacoes() {
        return localizacoes;
    }

    public void setLocalizacoes(List<LocalizacaoEntity> localizacoes) {
        this.localizacoes = localizacoes;
    }

    public List<String> getNotificacoes() {
        return notificacoes;
    }

    public void setNotificacoes(List<String> notificacoes) {
        this.notificacoes = notificacoes;
    }

    public LocalDateTime getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDateTime dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }

    public void SetInfo(String dimensao, String nomeIdentificador)
    {
        List<String> list = new ArrayList<>();
        List<LocalizacaoEntity> list2 = new ArrayList<>();
        List<BlocoEntity> list5 = new ArrayList<>();
        List<PlantaEntity> list3 = new ArrayList<>();
        this.notificacoes = list;
        this.localizacoes = list2;
        this.blocos = list5;
        this.plantas = list3;
        this.dataCadastro = LocalDateTime.now();
        this.timeStamp = LocalDateTime.now();
        this.dimensao = dimensao;
        this.nomeIdentificador = nomeIdentificador;
    }

    public void EditInfo(String dimensao,  String nomeIdentificador)
    {
        this.nomeIdentificador = nomeIdentificador;
        this.dimensao = dimensao;
        this.timeStamp = LocalDateTime.now();
    }

    public void AddPlanta(PlantaEntity plantaEntity)
    {
        this.plantas.add(plantaEntity);
        this.timeStamp = LocalDateTime.now();
    }

}
