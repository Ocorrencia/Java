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
public class Defeito {

    int codEmp;
    String codDft;
    String desDft;
    List<Defeito> itensDefeito = new ArrayList<Defeito>();

    public List<Defeito> getItensDefeito() {
        return itensDefeito;
    }

    public void setItensDefeito(List<Defeito> itensDefeito) {
        this.itensDefeito = itensDefeito;
    }

    public int getCodEmp() {
        return codEmp;
    }

    public void setCodEmp(int codEmp) {
        this.codEmp = codEmp;
    }

    public String getCodDft() {
        return codDft;
    }

    public void setCodDft(String codDft) {
        this.codDft = codDft;
    }

    public String getDesDft() {
        return desDft;
    }

    public void setDesDft(String desDft) {
        this.desDft = desDft;
    }

}
