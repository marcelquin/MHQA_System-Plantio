package App.Infra.Persistence.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "Bloco")
public class BlocoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String area;

    private String referencia;

    private Boolean disponivel;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime timeStamp;

    public BlocoEntity() {
    }

    public BlocoEntity(Long id, String area, String referencia, Boolean disponivel, LocalDateTime timeStamp) {
        this.id = id;
        this.area = area;
        this.referencia = referencia;
        this.disponivel = disponivel;
        this.timeStamp = timeStamp;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
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

    public Boolean ValidaValor(int referencia)
    {
        if(referencia < 0){return Boolean.FALSE;}
        return Boolean.TRUE;
    }


    public void SetInfo(String area)
    {
        this.area = area;
        this.timeStamp = LocalDateTime.now();
        this.disponivel = Boolean.TRUE;
    }

    public void SetPlanta()
    {
        this.disponivel = Boolean.FALSE;
    }

    public void EditInfo(String area)
    {
        if(!this.area.equals(area))
        {
            this.area = area;
        }
    }

}
