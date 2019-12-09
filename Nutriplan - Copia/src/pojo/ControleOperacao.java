/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pojo;

/**
 *
 * @author diogo.melo
 */
public class ControleOperacao {

    int codCop;
    int codOpe;
    String horOpe;
    String datOpe;

    public int getCodCop() {
        return codCop;
    }

    public void setCodCop(int codCop) {
        this.codCop = codCop;
    }

    public int getCodOpe() {
        return codOpe;
    }

    public void setCodOpe(int codOpe) {
        this.codOpe = codOpe;
    }

    public String getHorOpe() {
        return horOpe;
    }

    public void setHorOpe(String horOpe) {
        this.horOpe = horOpe;
    }

    public String getDatOpe() {
        return datOpe;
    }

    public void setDatOpe(String datOpe) {
        this.datOpe = datOpe;
    }

}
