package pojo;

import java.util.ArrayList;
import java.util.List;

public class FichaTecnica {

    String codCmp;
    String derCmp;
    String desCmp;
    String desDer;
    String mensagemRetorno;
    Double prdQtd;
    Double qtdTot;
    Double qtdUti;
    Integer seqMod;
    String tipPro;
    String uniMe2;
    List<FichaTecnica> itensFichaTecnica = new ArrayList<FichaTecnica>();

    public List<FichaTecnica> getItensFichaTecnica() {
        return itensFichaTecnica;
    }

    public void setItensFichaTecnica(List<FichaTecnica> itensFichaTecnica) {
        this.itensFichaTecnica = itensFichaTecnica;
    }

    public String getCodCmp() {
        return codCmp;

    }

    public void setCodCmp(String codCmp) {
        this.codCmp = codCmp;
    }

    public String getDerCmp() {
        return derCmp;
    }

    public void setDerCmp(String derCmp) {
        this.derCmp = derCmp;
    }

    public String getDesCmp() {
        return desCmp;
    }

    public void setDesCmp(String desCmp) {
        this.desCmp = desCmp;
    }

    public String getDesDer() {
        return desDer;
    }

    public void setDesDer(String desDer) {
        this.desDer = desDer;
    }

    public String getMensagemRetorno() {
        return mensagemRetorno;
    }

    public void setMensagemRetorno(String mensagemRetorno) {
        this.mensagemRetorno = mensagemRetorno;
    }

    public Double getPrdQtd() {
        return prdQtd;
    }

    public void setPrdQtd(Double prdQtd) {
        this.prdQtd = prdQtd;
    }

    public Double getQtdTot() {
        return qtdTot;
    }

    public void setQtdTot(Double qtdTot) {
        this.qtdTot = qtdTot;
    }

    public Double getQtdUti() {
        return qtdUti;
    }

    public void setQtdUti(Double qtdUti) {
        this.qtdUti = qtdUti;
    }

    public Integer getSeqMod() {
        return seqMod;
    }

    public void setSeqMod(Integer seqMod) {
        this.seqMod = seqMod;
    }

    public String getTipPro() {
        return tipPro;
    }

    public void setTipPro(String tipPro) {
        this.tipPro = tipPro;
    }

    public String getUniMe2() {
        return uniMe2;
    }

    public void setUniMe2(String uniMe2) {
        this.uniMe2 = uniMe2;
    }

}
