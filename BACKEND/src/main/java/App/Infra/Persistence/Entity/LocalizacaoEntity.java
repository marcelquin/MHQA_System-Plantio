package App.Infra.Persistence.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "Localizacao")
public class LocalizacaoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String area;

    private int eixoX;

    private int eixoY;

    private String referencia;

    private Boolean disponivel;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime timeStamp;

    public LocalizacaoEntity() {
    }

    public LocalizacaoEntity(Long id, String area, int eixoX, int eixoY, String referencia, Boolean disponivel, LocalDateTime timeStamp) {
        this.id = id;
        this.area = area;
        this.eixoX = eixoX;
        this.eixoY = eixoY;
        this.referencia = referencia;
        this.disponivel = disponivel;
        this.timeStamp = timeStamp;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
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

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public Boolean getDisponivel() {
        return disponivel;
    }

    public void setDisponivel(Boolean disponivel) {
        this.disponivel = disponivel;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }

    public Boolean ValidaValor(int eixoX, int eixoY)
    {
        if(eixoX < 0){return Boolean.FALSE;}
        if(eixoY < 0){return Boolean.FALSE;}
        return Boolean.TRUE;
    }


    public void SetInfo(String area, int eixoX, int eixoY)
    {
        this.area = area;
        this.eixoY = eixoY;
        this.eixoX = eixoX;
        this.referencia = "C "+eixoX+" x L "+eixoY;
        this.timeStamp = LocalDateTime.now();
        this.disponivel = Boolean.TRUE;
    }

    public void SetPlanta()
    {
        this.disponivel = Boolean.FALSE;
        this.timeStamp = LocalDateTime.now();
    }

    public void SetDisponivel()
    {
        this.disponivel = Boolean.TRUE;
        this.timeStamp = LocalDateTime.now();
    }

    public void EditInfo(String area, int eixoX, int eixoY)
    {
        if(!this.area.equals(area))
        {
            this.area = area;
        }
        this.eixoX = eixoX;
        this.eixoY = eixoY;
        this.referencia = eixoX+" x "+eixoY;
    }

}
