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
public class Maquina {

    int codMaq;
    int codCre;
    String ipMaq;
    int prioridade;

    public int getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(int prioridade) {
        this.prioridade = prioridade;
    }

    public int getCodMaq() {
        return codMaq;
    }

    public void setCodMaq(int codMaq) {
        this.codMaq = codMaq;
    }

    public int getCodCre() {
        return codCre;
    }

    public void setCodCre(int codCre) {
        this.codCre = codCre;
    }

    public String getIpMaq() {
        return ipMaq;
    }

    public void setIpMaq(String ipMaq) {
        this.ipMaq = ipMaq;
    }


}
