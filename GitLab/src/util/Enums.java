/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import dao.ControleOperacaoDao;
import pojo.ControleOperacao;

/**
 *
 * @author diogo.melo
 */
public class Enums {

    public static int STATUSTELA;

    public static int MENU = 1;
    public static int PRODUCAO = 2;
    public static int LIBERADOPRODUCAO = 10;
    public static int FINALIZADO = 3;
    public static int APONTAMENTODEPARADA = 4;
    public static int AVISOTRAVAMENTO = 5;
    public static int CONFIGURACAO = 6;
    public static int REFUGO = 7;
    public static int AVISOINICIOPRODUCAO = 9;
    public static int PRIORIDADE;
    public static String SENHA = "00";
    public static String TELAOP = "TELAOP";
    public static String TELAMENU = "TELAMENU";
    public static String TELAAP = "TELAMENU";
    public static String TIPOSISTEMA;
    public static String RASPERBERRY = "RASPERBERRY";
    public static String WINDOWS = "WINDOWS";
    public static String CODIGOOPERADOR = "0";
    public static String NOMEOPERADOR = "";
    public static int CODIGOTURNO;
    public static int SEQUENCIAAPONTAMENTO;

    public static int REFUGOSNAOIDENTIFICADOS = 0;
    public static int REFUGOSJUSTIFICADOS = 0;
    public static int LIMITEREFUGO = 0;
    public static int CICLOATUAL = 0;

    static ControleOperacao controleOperacao = new ControleOperacao();
    static ControleOperacaoDao controleOperacaoDao = new ControleOperacaoDao(controleOperacao);

    public static String getTIPOSISTEMA() {
        return TIPOSISTEMA;
    }

    public static void setTIPOSISTEMA(String TIPOSISTEMA) {
        Enums.TIPOSISTEMA = TIPOSISTEMA;
    }

    public static int getSTATUSTELA() {
        controleOperacaoDao.consultar();
        return controleOperacao.getCodOpe();
    }

    public static void setSTATUSTELA(int STATUSTELA) {
        Enums.STATUSTELA = STATUSTELA;
        controleOperacao.setCodOpe(STATUSTELA);
        if (Enums.STATUSTELA == Enums.PRODUCAO && (getSTATUSTELA() == Enums.AVISOINICIOPRODUCAO || getSTATUSTELA() == Enums.AVISOTRAVAMENTO)) {
        } else if (Enums.STATUSTELA == Enums.LIBERADOPRODUCAO) {
            controleOperacao.setCodOpe(Enums.PRODUCAO);
            controleOperacaoDao.incluir();
        } else {
            controleOperacaoDao.incluir();
        }
    }

    public static int getPRODUCAO() {
        return PRODUCAO;
    }

    public static void setPRODUCAO(int PRODUCAO) {
        Enums.PRODUCAO = PRODUCAO;
    }

}
