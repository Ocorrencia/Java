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
public class Refugo {

    static Integer quantidade = 0;
    static String expErp;
    static int codDft;
    static int numorp;

    public static Integer getQuantidade() {
        return quantidade;
    }

    public static void setQuantidade(Integer quantidade) {
        Refugo.quantidade = quantidade;
    }

    public static int getNumorp() {
        return numorp;
    }

    public static void setNumorp(int numorp) {
        Refugo.numorp = numorp;
    }

    public static int getCodDft() {
        return codDft;
    }

    public static void setCodDft(int codDft) {
        Refugo.codDft = codDft;
    }

    public static String getExpErp() {
        return expErp;
    }

    public static void setExpErp(String expErp) {
        Refugo.expErp = expErp;
    }

}
