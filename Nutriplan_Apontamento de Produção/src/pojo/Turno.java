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
public class Turno {

    int turTrb;
    String desTrb;
    List<Turno> itensTurno = new ArrayList<Turno>();

    public List<Turno> getItensTurno() {
        return itensTurno;
    }

    public void setItensTurno(List<Turno> itensTurno) {
        this.itensTurno = itensTurno;
    }

    public int getTurTrb() {
        return turTrb;
    }

    public void setTurTrb(int turTrb) {
        this.turTrb = turTrb;
    }

    public String getDesTrb() {
        return desTrb;
    }

    public void setDesTrb(String desTrb) {
        this.desTrb = desTrb;
    }

}
