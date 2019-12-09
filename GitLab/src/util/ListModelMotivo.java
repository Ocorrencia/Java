/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import dao.MotivoDao;
import java.util.ArrayList;
import java.util.List;
import pojo.Motivo;

/**
 *
 * @author diogo.melo
 */
public class ListModelMotivo {

    MotivoDao motivoDao = new MotivoDao();
    static List<Motivo> itensMotivos = new ArrayList<Motivo>();

    public ListModelMotivo() {
        itensMotivos = motivoDao.consultar();
    }

    public List<String> getElementos() {
        List<String> itens = new ArrayList<String>();
        for (Motivo itensMotivo : itensMotivos) {
            itens.add(itensMotivo.getCodMtv() + " - " + itensMotivo.getDesMtv());
        }
        return itens;
    }

    public String getElemento(String valor) {
        for (Motivo itensMotivo : itensMotivos) {
            if (itensMotivo.getCodMtv().equals(valor)) {
                return itensMotivo.getCodMtv() + " - " + itensMotivo.getDesMtv();
            }
        }
        Notificacao.infoBox("MOTIVO NÃO ENCONTRADO", false);
        return "";
    }

    public String getElementoConcatenado(String valor) {
        String n1 = "";
        for (Motivo itensMotivo : itensMotivos) {
            n1 = itensMotivo.getCodMtv() + " - " + itensMotivo.getDesMtv();
            if (n1.equals(valor)) {
                return itensMotivo.getCodMtv();
            }
        }
        Notificacao.infoBox("MOTIVO NÃO ENCONTRADO", false);
        return "";
    }

    public int getIndex(String valor) {

        for (int i = 0; i < itensMotivos.size(); i++) {
            if (itensMotivos.get(i).getCodMtv().equals(valor)) {
                return i;
            }
        }
        return -1;
    }

}
