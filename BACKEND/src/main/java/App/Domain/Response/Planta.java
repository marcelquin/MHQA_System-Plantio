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

        private String localizacao;

        private String areaPlantio;

        private FASEATUAL faseatual;

        private String instrucoes;

        @JsonFormat(pattern = "dd/MM/yyyy")
        private LocalDate dataPlantio;

        @JsonFormat(pattern = "dd/MM/yyyy")
        private LocalDate dataUltimoCiclo;

        @JsonFormat(pattern = "dd/MM/yyyy")
        private LocalDate dataCicloAtual;

        @JsonFormat(pattern = "dd/MM/yyyy")
        private LocalDate dataAdubacao;

        @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
        private LocalDateTime timeStamp;

        private List<String> notificacoes;

        public Planta() {
        }

        public Planta(Long id, String nomeCientifico, String nomePopular, String codigo, String localizacao, String areaPlantio, FASEATUAL faseatual, String instrucoes, LocalDate dataPlantio, LocalDate dataUltimoCiclo, LocalDate dataCicloAtual, LocalDate dataAdubacao, LocalDateTime timeStamp, List<String> notificacoes) {
                this.id = id;
                this.nomeCientifico = nomeCientifico;
                this.nomePopular = nomePopular;
                this.codigo = codigo;
                this.localizacao = localizacao;
                this.areaPlantio = areaPlantio;
                this.faseatual = faseatual;
                this.instrucoes = instrucoes;
                this.dataPlantio = dataPlantio;
                this.dataUltimoCiclo = dataUltimoCiclo;
                this.dataCicloAtual = dataCicloAtual;
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

        public String getLocalizacao() {
                return localizacao;
        }

        public void setLocalizacao(String localizacao) {
                this.localizacao = localizacao;
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




}
