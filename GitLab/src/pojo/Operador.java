/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pojo;

import java.util.ArrayList;
import java.util.List;

public class Operador {

    int codEmp;
    String nomOpe;
    Integer numCad;
    Integer turTrb;
    List<Operador> itensOperadores = new ArrayList<Operador>();

    public int getCodEmp() {
        return codEmp;
    }

    public void setCodEmp(int codEmp) {
        this.codEmp = codEmp;
    }

    public List<Operador> getItensOperadores() {
        return itensOperadores;
    }

    public void setItensOperadores(List<Operador> itensOperadores) {
        this.itensOperadores = itensOperadores;
    }

    public String getNomOpe() {
        return nomOpe;
    }

    public void setNomOpe(String nomOpe) {
        this.nomOpe = nomOpe;
    }

    public Integer getNumCad() {
        return numCad;
    }

    public void setNumCad(Integer numCad) {
        this.numCad = numCad;
    }

    public Integer getTurTrb() {
        return turTrb;
    }

    public void setTurTrb(Integer turTrb) {
        this.turTrb = turTrb;
    }

}
