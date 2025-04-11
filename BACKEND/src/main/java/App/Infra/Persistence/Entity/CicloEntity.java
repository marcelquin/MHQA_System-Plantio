package App.Infra.Persistence.Entity;

import App.Infra.Persistence.Enum.CICLO;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Ciclo")
public class CicloEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private CICLO ciclo;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataUltimoCiclo;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataCicloAtual;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime timeStamp;

    private List<String> notificacoes;

    public CicloEntity() {
    }

    public CicloEntity(Long id, CICLO ciclo, LocalDate dataUltimoCiclo, LocalDate dataCicloAtual, LocalDateTime timeStamp, List<String> notificacoes) {
        this.id = id;
        this.ciclo = ciclo;
        this.dataUltimoCiclo = dataUltimoCiclo;
        this.dataCicloAtual = dataCicloAtual;
        this.timeStamp = timeStamp;
        this.notificacoes = notificacoes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CICLO getCiclo() {
        return ciclo;
    }

    public void setCiclo(CICLO ciclo) {
        this.ciclo = ciclo;
    }

    public LocalDate getDataUltimoCiclo() {
        return dataUltimoCiclo;
    }

    public void setDataUltimoCiclo(LocalDate dataUltimoCiclo) {
        this.dataUltimoCiclo = dataUltimoCiclo;
    }

    public LocalDate getDataCicloAtual() {
        return dataCicloAtual;
    }

    public void setDataCicloAtual(LocalDate dataCicloAtual) {
        this.dataCicloAtual = dataCicloAtual;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }

    public List<String> getNotificacoes() {
        return notificacoes;
    }

    public void setNotificacoes(List<String> notificacoes) {
        this.notificacoes = notificacoes;
    }

    public void SetInfo()
    {
        List<String>list = new ArrayList<>();
        this.notificacoes = list;
        this.ciclo = CICLO.GERMINACAO;
        this.dataCicloAtual = LocalDate.now();
        this.timeStamp = LocalDateTime.now();
    }

    public Boolean ValidaCiclo(CICLO ciclo)
    {
        if(this.ciclo.equals(CICLO.GERMINACAO) && ciclo != CICLO.MUDA){return Boolean.FALSE;}
        if(this.ciclo.equals(CICLO.MUDA) && ciclo != CICLO.CRESCIMENTO){return Boolean.FALSE;}
        if(this.ciclo.equals(CICLO.CRESCIMENTO) && ciclo == CICLO.MUDA){return Boolean.FALSE;}
        if(this.ciclo.equals(CICLO.CRESCIMENTO) && ciclo == CICLO.GERMINACAO){return Boolean.FALSE;}
        if(ciclo.equals(CICLO.GERMINACAO) && this.dataUltimoCiclo != null){return Boolean.FALSE;}
        if(ciclo.equals(this.ciclo)){return Boolean.FALSE;}
        return Boolean.TRUE;
    }

    public void FimCiclo()
    {
        List<String>list = new ArrayList<>();
        this.dataCicloAtual = null;
        this.dataUltimoCiclo = null;
        this.ciclo = CICLO.FIM;
        this.notificacoes = list;
        this.timeStamp = LocalDateTime.now();
    }
}
