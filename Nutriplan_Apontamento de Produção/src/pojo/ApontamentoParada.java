/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pojo;

import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;

/**
 *
 * @author diogo.melo
 */
public class ApontamentoParada {

    Integer codCre;
    Integer codEtg;
    String codMtv;
    String datPar;
    Integer seqMpr;
    String horIni;
    String horFim;
    Integer turTrb;
    String expErp;
    String codOri;
    Integer numOrp;
    Integer seqEoq;
    Integer seqRot;
    Integer numCad;

    public Integer getNumCad() {
        return numCad;
    }

    public void setNumCad(Integer numCad) {
        this.numCad = numCad;
    }

    public Integer getSeqRot() {
        return seqRot;
    }

    public void setSeqRot(Integer seqRot) {
        this.seqRot = seqRot;
    }

    public Integer getCodCre() {
        return codCre;
    }

    public void setCodCre(Integer codCre) {
        this.codCre = codCre;
    }

    public Integer getCodEtg() {
        return codEtg;
    }

    public void setCodEtg(Integer codEtg) {
        this.codEtg = codEtg;
    }

    public String getCodMtv() {
        return codMtv;
    }

    public void setCodMtv(String codMtv) {
        this.codMtv = codMtv;
    }

    public String getDatPar() {
        return datPar;
    }

    public void setDatPar(String datPar) {
        this.datPar = datPar;
    }

    public Integer getSeqMpr() {
        return seqMpr;
    }

    public void setSeqMpr(Integer seqMpr) {
        this.seqMpr = seqMpr;
    }

    public String getHorIni() {
        return horIni;
    }

    public void setHorIni(String horIni) {
        this.horIni = horIni;
    }

    public String getHorFim() {
        return horFim;
    }

    public void setHorFim(String horFim) {
        this.horFim = horFim;
    }

    public Integer getTurTrb() {
        return turTrb;
    }

    public void setTurTrb(Integer turTrb) {
        this.turTrb = turTrb;
    }

    public String getExpErp() {
        return expErp;
    }

    public void setExpErp(String expErp) {
        this.expErp = expErp;
    }

    public String getCodOri() {
        return codOri;
    }

    public void setCodOri(String codOri) {
        this.codOri = codOri;
    }

    public Integer getNumOrp() {
        return numOrp;
    }

    public void setNumOrp(Integer numOrp) {
        this.numOrp = numOrp;
    }

    public Integer getSeqEoq() {
        return seqEoq;
    }

    public void setSeqEoq(Integer seqEoq) {
        this.seqEoq = seqEoq;
    }

}
