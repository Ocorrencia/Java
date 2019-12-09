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
public class CentroRecurso {

    int codEmp;
    String codCre;
    Integer codEtg;
    String descCre;
    List<CentroRecurso> itensCentroRecurso = new ArrayList<CentroRecurso>();

    public int getCodEmp() {
        return codEmp;
    }

    public void setCodEmp(int codEmp) {
        this.codEmp = codEmp;
    }

    public List<CentroRecurso> getItensCentroRecurso() {
        return itensCentroRecurso;
    }

    public void setItensCentroRecurso(List<CentroRecurso> itensCentroRecurso) {
        this.itensCentroRecurso = itensCentroRecurso;
    }

    public String getCodCre() {
        return codCre;
    }

    public void setCodCre(String codCre) {
        this.codCre = codCre;
    }

    public Integer getCodEtg() {
        return codEtg;
    }

    public void setCodEtg(Integer codEtg) {
        this.codEtg = codEtg;
    }

    public String getDescCre() {
        return descCre;
    }

    public void setDescCre(String descCre) {
        this.descCre = descCre;
    }

}
