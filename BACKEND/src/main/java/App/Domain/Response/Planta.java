package App.Domain.Response;

import App.Infra.Exceptions.IllegalActionException;
import App.Infra.Persistence.Enum.FASEATUAL;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static App.Infra.Persistence.Enum.FASEATUAL.*;


public class Planta {

        private Long id;

        private String nomeCientifico;

        private String nomePopular;

        private String codigo;

        private int numeroSubareaPlantio;

        private String areaPlantio;

        private FASEATUAL faseatual;

        private String instrucoes;

        private Boolean cavalo;

        @JsonFormat(pattern = "dd/MM/yyyy")
        private LocalDate dataPlantio;

        @JsonFormat(pattern = "dd/MM/yyyy")
        private LocalDate dataAdubacao;

        @JsonFormat(pattern = "dd/MM/yyyy")
        private LocalDate dataFimCiclo;

        @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
        private LocalDateTime timeStamp;

        private List<String> notificacoes;

        public Planta() {
        }

        public Planta(Long id, String nomeCientifico, String nomePopular, String codigo, int numeroSubareaPlantio, String areaPlantio, FASEATUAL faseatual, String instrucoes, Boolean cavalo, LocalDate dataPlantio, LocalDate dataAdubacao, LocalDateTime timeStamp, List<String> notificacoes) {
                this.id = id;
                this.nomeCientifico = nomeCientifico;
                this.nomePopular = nomePopular;
                this.codigo = codigo;
                this.numeroSubareaPlantio = numeroSubareaPlantio;
                this.areaPlantio = areaPlantio;
                this.faseatual = faseatual;
                this.instrucoes = instrucoes;
                this.cavalo = cavalo;
                this.dataPlantio = dataPlantio;
                this.dataAdubacao = dataAdubacao;
                this.timeStamp = timeStamp;
                this.notificacoes = notificacoes;
        }

        public Long getId() {
                return id;
        }

        public void setId(Long id) {
                this.id = id;
        }

        public String getNomeCientifico() {
                return nomeCientifico;
        }

        public void setNomeCientifico(String nomeCientifico) {
                this.nomeCientifico = nomeCientifico;
        }

        public String getNomePopular() {
                return nomePopular;
        }

        public void setNomePopular(String nomePopular) {
                this.nomePopular = nomePopular;
        }

        public String getCodigo() {
                return codigo;
        }

        public void setCodigo(String codigo) {
                this.codigo = codigo;
        }

        public int getNumeroSubareaPlantio() {
                return numeroSubareaPlantio;
        }

        public void setNumeroSubareaPlantio(int numeroSubareaPlantio) {
                this.numeroSubareaPlantio = numeroSubareaPlantio;
        }

        public String getAreaPlantio() {
                return areaPlantio;
        }

        public void setAreaPlantio(String areaPlantio) {
                this.areaPlantio = areaPlantio;
        }

        public FASEATUAL getFaseatual() {
                return faseatual;
        }

        public void setFaseatual(FASEATUAL faseatual) {
                this.faseatual = faseatual;
        }

        public String getInstrucoes() {
                return instrucoes;
        }

        public void setInstrucoes(String instrucoes) {
                this.instrucoes = instrucoes;
        }

        public Boolean getCavalo() {
                return cavalo;
        }

        public void setCavalo(Boolean cavalo) {
                this.cavalo = cavalo;
        }

        public LocalDate getDataPlantio() {
                return dataPlantio;
        }

        public void setDataPlantio(LocalDate dataPlantio) {
                this.dataPlantio = dataPlantio;
        }

        public LocalDate getDataAdubacao() {
                return dataAdubacao;
        }

        public void setDataAdubacao(LocalDate dataAdubacao) {
                this.dataAdubacao = dataAdubacao;
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

        public void FimCiclo()
        {
                if(this.faseatual != FASEATUAL.FIM)
                {
                        this.faseatual = FASEATUAL.FIM;
                        this.timeStamp = LocalDateTime.now();
                        this.areaPlantio = null;
                        this.numeroSubareaPlantio = 0;
                        this.instrucoes = null;
                        this.notificacoes = null;
                }
        }

        public Boolean ValidaAlteracao(FASEATUAL faseatual)
        {
                if(this.faseatual.equals(faseatual)){throw new IllegalActionException();}
                if(this.faseatual == FASEATUAL.AGUARDANDO && faseatual != FASEATUAL.GERMINACAO){throw new IllegalActionException();}
                if(this.faseatual == FASEATUAL.GERMINACAO && faseatual != FASEATUAL.MUDA){throw new IllegalActionException();}
                if(this.faseatual == FASEATUAL.MUDA && faseatual != FASEATUAL.CRESCIMENTO){throw new IllegalActionException();}
                if(faseatual.equals(FIM)){return  Boolean.TRUE;}
                return Boolean.TRUE;
        }

        public Boolean validaDoadora()
        {
                if(this.getCavalo().equals(Boolean.FALSE)){throw new IllegalActionException();}
                if(this.getNumeroSubareaPlantio() > 0){throw new IllegalActionException();}
                if(this.getFaseatual() != AGUARDANDO){throw new IllegalActionException();}
                return Boolean.TRUE;
        }

        public Boolean validaReceptora()
        {
                if(this.getCavalo().equals(Boolean.TRUE)){throw new IllegalActionException();}
                if(this.getNumeroSubareaPlantio() == 0){throw new IllegalActionException();}
                if(this.getFaseatual().equals(FIM)){throw new IllegalActionException();}
                if(this.getFaseatual() != CRESCIMENTO){throw new IllegalActionException();}
                return Boolean.TRUE;
        }

        public void SetDadosEnxertia(String nomeCientifico, String nomePopular, String instrucoes)
        {
                this.nomeCientifico = nomeCientifico;
                this.nomePopular = nomePopular;
                this.instrucoes = instrucoes;
                this.timeStamp = LocalDateTime.now();
                this.faseatual = FASEATUAL.MUDA;
        }

        public Boolean AtribuirSubArea(int numero, String nomeAreaPlantio) {
                //if(this.numeroSubareaPlantio == numero) {throw new IllegalActionException();}
                this.numeroSubareaPlantio = numero;
                this.areaPlantio = nomeAreaPlantio;
                this.faseatual = FASEATUAL.GERMINACAO;
                this.timeStamp = LocalDateTime.now();
                return Boolean.TRUE;
        }

        public Boolean AlterarFaseAtual(FASEATUAL faseatual)
        {
                this.faseatual = faseatual;
                this.timeStamp = LocalDateTime.now();
                return Boolean.TRUE;
        }

        public void VerificarLocalizacao(String nomeAreaPlantio, int numeroSubareaPlantio)
        {
            if(nomeAreaPlantio != null && numeroSubareaPlantio > 0)
            {
                    if(!this.areaPlantio.equals(nomeAreaPlantio))
                    {
                            this.areaPlantio = nomeAreaPlantio;
                            this.numeroSubareaPlantio = numeroSubareaPlantio;
                    }
                    this.timeStamp = LocalDateTime.now();
            }
        }

        public void Adubacao(String mensagem)
        {
                this.setTimeStamp(LocalDateTime.now());
                this.setDataAdubacao(LocalDate.now());
                this.getNotificacoes().add(mensagem);
        }

}
