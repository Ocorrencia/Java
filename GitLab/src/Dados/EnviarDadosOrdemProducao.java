/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dados;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import pojo.OrdemProducao;
import util.ConexaoMysql;

/**
 *
 * @author diogo.melo
 */
public class EnviarDadosOrdemProducao {

    private final String INCLUIRSQL = "INSERT INTO nutri_op_sinc.op900qdo VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    private final String ATUALIZARTABELAPADRAO = "INSERT IGNORE\n"
            + "   INTO nutri_op.op900qdo\n"
            + " SELECT *\n"
            + "   FROM nutri_op_sinc.op900qdo";

   
    private final String LIMPARTABELA = "DELETE FROM nutri_op_sinc.op900qdo";
    private final String UPDATEQDO = "UPDATE nutri_op.op900qdo prod\n"
            + "        INNER JOIN\n"
            + "    nutri_op_sinc.op900qdo sinc ON prod.NUMORP = sinc.NUMORP \n"
            + "SET \n"
            + "    prod.CAPSMT = sinc.CAPSMT,\n"
            + "    prod.CICPAD = sinc.CICPAD,\n"
            + "    prod.CODDER = sinc.CODDER,\n"
            + "    prod.CODEMP = sinc.CODEMP,\n"
            + "    prod.CODETG = sinc.CODETG,\n"
            + "    prod.CODORI = sinc.CODORI,\n"
            + "    prod.CODPRO = sinc.CODPRO,\n"
            + "    prod.DESDER = sinc.DESDER,\n"
            + "    prod.DESPRO = sinc.DESPRO,\n"
            + "    prod.DESPRXDER = sinc.DESPRXDER,\n"
            + "    prod.DESPRXPRO = sinc.DESPRXPRO,\n"
            + "    prod.NUMORP = sinc.NUMORP,\n"
            + "    prod.NUMPRI = sinc.NUMPRI,\n"
            + "    prod.PESPAD = sinc.PESPAD,\n"
            + "    prod.PRXDER = sinc.PRXDER,\n"
            + "    prod.PRXPRO = sinc.PRXPRO,\n"
            + "    prod.QTDMAX = sinc.QTDMAX,\n"
            + "    prod.QTDPRV = sinc.QTDPRV,\n"
            + "    prod.SEQETR = sinc.SEQETR,\n"
            + "    prod.SEQROT = sinc.SEQROT";
    List<OrdemProducao> ordemProducao = new ArrayList<OrdemProducao>();
    boolean atualizarParcial;
    public void EnviarDadosOrdemProducao(List<OrdemProducao> ordemProducaoObj,boolean atualizarParcial) {
        this.ordemProducao = ordemProducaoObj;
        this.atualizarParcial = atualizarParcial;
        enviarDados();
    }

    public void enviarDados() {
        limparDados();
        try {
            PreparedStatement ps = ConexaoMysql.getConexaoMySQL().prepareStatement(INCLUIRSQL);
            for (OrdemProducao op : ordemProducao) {
                ps.setInt(1, op.getCodEmp());
                ps.setString(2, op.getCodOri());
                ps.setInt(3, op.getNumOrp());
                ps.setString(4, op.getCodPro());
                ps.setString(5, op.getCodDer());
                ps.setInt(6, op.getCodEtg());
                ps.setInt(7, op.getSeqRot());
                ps.setInt(8, op.getSeqEtr());
                ps.setDouble(9, op.getQtdPrv());
                ps.setDouble(10, op.getQtdRe1() == null ? 0 : op.getQtdRe1());
                ps.setDouble(11, op.getQtdRfg());
                ps.setDouble(12, op.getQtdMax());
                ps.setDouble(13, op.getCicPad());
                ps.setString(14, op.getDesPro());
                ps.setString(15, op.getDesDer());
                ps.setString(16, op.getPrxPro());
                ps.setString(17, op.getPrxDer());
                ps.setString(18, op.getDesPrxDer());
                ps.setString(19, op.getDesPrxPro());
                ps.setInt(20, op.getNumPri());
                ps.setDouble(21, op.getCapsMt());
                ps.setDouble(22, op.getPesPad());
                ps.setString(23, op.getStatusRegistro());
                ps.setInt(24, 0);
                ps.executeUpdate();
            }
            ConexaoMysql.FecharConexao();
            if (atualizarParcial) {
                atualizarQDO();
            }else{
                atualizarTabelaPadrao();
            }
        } catch (SQLException e) {
        }
    }

    public void atualizarTabelaPadrao() {
        try {
            PreparedStatement ps = ConexaoMysql.getConexaoMySQL().prepareStatement(ATUALIZARTABELAPADRAO);
            ps.executeUpdate();
            ConexaoMysql.FecharConexao();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void atualizarQDO() {
        try {
            PreparedStatement ps = ConexaoMysql.getConexaoMySQL().prepareStatement(UPDATEQDO);
            ps.executeUpdate();
            ConexaoMysql.FecharConexao();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void limparDados() {
        try {
            PreparedStatement ps = ConexaoMysql.getConexaoMySQL().prepareStatement(LIMPARTABELA);
            ps.executeUpdate();
            ConexaoMysql.FecharConexao();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
