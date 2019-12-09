/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pojo;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author diogo.melo
 */
public class OrdemProducao {

    Double cicPad;
    String codDer;
    String codOri;
    String codPro;
    String desDer;
    String desPro;
    String desPrxDer;
    String desPrxPro;
    String MenssagemRetorno;
    Integer numOrp;
    String prxDer;
    String prxPro;
    Double qtdMax;
    Double qtdPrv;
    Double qtdRfg;
    Double qtdRfgn;
    Double qtdRe1;
    List<OrdemProducao> itensOrdemProducao = new ArrayList<OrdemProducao>();
    int codEmp;
    int seqRot;
    int seqEtr;
    int codEtg;
    String desEtg;
    int numPri;
    double pesPad;
    double capsMt;

    public Double getQtdPrv() {
        return qtdPrv;
    }

    public void setQtdPrv(Double qtdPrv) {
        this.qtdPrv = qtdPrv;
    }

    public Double getQtdRfg() {
        return qtdRfg;
    }

    public void setQtdRfg(Double qtdRfg) {
        this.qtdRfg = qtdRfg;
    }

    public Double getQtdRfgn() {
        return qtdRfgn;
    }

    public void setQtdRfgn(Double qtdRfgn) {
        this.qtdRfgn = qtdRfgn;
    }

    public Double getQtdRe1() {
        return qtdRe1;
    }

    public void setQtdRe1(Double qtdRe1) {
        this.qtdRe1 = qtdRe1;
    }

    public double getPesPad() {
        return pesPad;
    }

    public void setPesPad(double pesPad) {
        this.pesPad = pesPad;
    }

    public double getCapsMt() {
        return capsMt;
    }

    public void setCapsMt(double capsMt) {
        this.capsMt = capsMt;
    }

    public int getNumPri() {
        return numPri;
    }

    public void setNumPri(int numPri) {
        this.numPri = numPri;
    }

    public String getDesEtg() {
        return desEtg;
    }

    public void setDesEtg(String desEtg) {
        this.desEtg = desEtg;
    }

    public int getCodEtg() {
        return codEtg;
    }

    public void setCodEtg(int codEtg) {
        this.codEtg = codEtg;
    }

    String statusRegistro;

    public String getStatusRegistro() {
        return statusRegistro;
    }

    public void setStatusRegistro(String statusRegistro) {
        this.statusRegistro = statusRegistro;
    }

    public int getSeqRot() {
        return seqRot;
    }

    public void setSeqRot(int seqRot) {
        this.seqRot = seqRot;
    }

    public int getSeqEtr() {
        return seqEtr;
    }

    public void setSeqEtr(int seqEtr) {
        this.seqEtr = seqEtr;
    }

    public int getCodEmp() {
        return codEmp;
    }

    public void setCodEmp(int codEmp) {
        this.codEmp = codEmp;
    }

    public Double getCicPad() {
        return cicPad;
    }

    public void setCicPad(Double cicPad) {
        this.cicPad = cicPad;
    }

    public String getCodDer() {
        return codDer;
    }

    public void setCodDer(String codDer) {
        this.codDer = codDer;
    }

    public String getCodOri() {
        return codOri;
    }

    public void setCodOri(String codOri) {
        this.codOri = codOri;
    }

    public String getCodPro() {
        return codPro;
    }

    public void setCodPro(String codPro) {
        this.codPro = codPro;
    }

    public String getDesDer() {
        return desDer;
    }

    public void setDesDer(String desDer) {
        this.desDer = desDer;
    }

    public String getDesPro() {
        return desPro;
    }

    public void setDesPro(String desPro) {
        this.desPro = desPro;
    }

    public String getDesPrxDer() {
        return desPrxDer;
    }

    public void setDesPrxDer(String desPrxDer) {
        this.desPrxDer = desPrxDer;
    }

    public String getDesPrxPro() {
        return desPrxPro;
    }

    public void setDesPrxPro(String desPrxPro) {
        this.desPrxPro = desPrxPro;
    }

    public String getMenssagemRetorno() {
        return MenssagemRetorno;
    }

    public void setMenssagemRetorno(String MenssagemRetorno) {
        this.MenssagemRetorno = MenssagemRetorno;
    }

    public Integer getNumOrp() {
        return numOrp;
    }

    public void setNumOrp(Integer numOrp) {
        this.numOrp = numOrp;
    }

    public String getPrxDer() {
        return prxDer;
    }

    public void setPrxDer(String prxDer) {
        this.prxDer = prxDer;
    }

    public String getPrxPro() {
        return prxPro;
    }

    public void setPrxPro(String prxPro) {
        this.prxPro = prxPro;
    }

    public Double getQtdMax() {
        return qtdMax;
    }

    public void setQtdMax(Double qtdMax) {
        this.qtdMax = qtdMax;
    }

    public List<OrdemProducao> getItensOrdemProducao() {
        return itensOrdemProducao;
    }

    public void setItensOrdemProducao(List<OrdemProducao> itensOrdemProducao) {
        this.itensOrdemProducao = itensOrdemProducao;
    }

}
