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
public class Motivo {

    int codEmp;
    String codMtv;
    String desMtv;
    int codAmp;
    String desAmp;
    String statusRegistro;
    List<Motivo> itensMotivo = new ArrayList<Motivo>();

    public List<Motivo> getItensMotivo() {
        return itensMotivo;
    }

    public void setItensMotivo(List<Motivo> itensMotivo) {
        this.itensMotivo = itensMotivo;
    }

    public String getStatusRegistro() {
        return statusRegistro;
    }

    public void setStatusRegistro(String statusRegistro) {
        this.statusRegistro = statusRegistro;
    }

    public int getCodEmp() {
        return codEmp;
    }

    public void setCodEmp(int codEmp) {
        this.codEmp = codEmp;
    }

    public String getCodMtv() {
        return codMtv;
    }

    public void setCodMtv(String codMtv) {
        this.codMtv = codMtv;
    }

    public String getDesMtv() {
        return desMtv;
    }

    public void setDesMtv(String desMtv) {
        this.desMtv = desMtv;
    }

    public int getCodAmp() {
        return codAmp;
    }

    public void setCodAmp(int codAmp) {
        this.codAmp = codAmp;
    }

    public String getDesAmp() {
        return desAmp;
    }

    public void setDesAmp(String desAmp) {
        this.desAmp = desAmp;
    }

}
