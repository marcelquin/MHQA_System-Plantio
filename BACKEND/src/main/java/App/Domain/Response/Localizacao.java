package App.Domain.Response;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public class Localizacao {

    private Long id;

    private String area;

    private int eixoX;

    private int eixoY;

    private String referencia;

    private Boolean disponivel;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime timeStamp;

    public Localizacao() {
    }

    public Localizacao(Long id, String area, int eixoX, int eixoY, String referencia, Boolean disponivel, LocalDateTime timeStamp) {
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
}
