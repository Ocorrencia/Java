/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import pojo.OrdemProducao;
import util.ConexaoMysql;
import util.EnviarEmail;
import util.Notificacao;

/**
 *
 * @author diogo.melo
 */
public class OrdemProducaoDao {

    EnviarEmail enviarEmail = new EnviarEmail();
    OrdemProducao ordemProducao = new OrdemProducao();
    private final String CONSULTARSQL = "SELECT * FROM nutri_op.op900qdo order by numpri WHERE STATUS <> 3";
    private final String CONSULTAORDEMPRODUCAORSQL = "SELECT * FROM nutri_op.op900qdo where NUMORP = ?";
    private final String UPDATEQDP = "UPDATE nutri_op.op900qdo SET QTDRE1 = ?, QTDRFG = ?, QTDRFGN = ? WHERE NUMORP = ?";

    public OrdemProducao getOrdemProducao() {
        return ordemProducao;
    }

    public void setOrdemProducao(OrdemProducao ordemProducao) {
        this.ordemProducao = ordemProducao;
    }

    public OrdemProducaoDao(OrdemProducao ordemProducao) {
        this.ordemProducao = ordemProducao;
    }

    public List<OrdemProducao> consultar() {
        try {
            PreparedStatement ps = ConexaoMysql.getConexaoMySQL().prepareStatement(CONSULTARSQL);
            ResultSet rs = ps.executeQuery();
            List<OrdemProducao> itensOrdemProducao = new ArrayList<OrdemProducao>();
            while (rs.next()) {
                ordemProducao = new OrdemProducao();
                ordemProducao.setCodEmp(rs.getInt(1));
                ordemProducao.setCodOri(rs.getString(2));
                ordemProducao.setNumOrp(rs.getInt(3));
                ordemProducao.setCodPro(rs.getString(4));
                ordemProducao.setCodDer(rs.getString(5));
                ordemProducao.setCodEtg(rs.getInt(6));
                ordemProducao.setSeqRot(rs.getInt(7));
                ordemProducao.setSeqEtr(rs.getInt(8));
                ordemProducao.setQtdPrv(rs.getDouble(9));
                ordemProducao.setQtdRe1(rs.getDouble(10));
                ordemProducao.setQtdRfg(rs.getDouble(11));
                ordemProducao.setQtdMax(rs.getDouble(12));
                ordemProducao.setCicPad(rs.getDouble(13));
                ordemProducao.setDesPro(rs.getString(14));
                ordemProducao.setDesDer(rs.getString(15));
                ordemProducao.setPrxPro(rs.getString(16));
                ordemProducao.setPrxDer(rs.getString(17));
                ordemProducao.setDesPrxPro(rs.getString(18));
                ordemProducao.setDesPrxDer(rs.getString(19));
                ordemProducao.setNumPri(rs.getInt(20));
                ordemProducao.setCapsMt(rs.getDouble(21));
                ordemProducao.setPesPad(rs.getDouble(22));
                itensOrdemProducao.add(ordemProducao);
            }
            return itensOrdemProducao;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            Notificacao.infoBox("ERRO AO CONSULTAR ORDEM PROD. /79OPD", true);
            try {
                enviarEmail.enviaEmail(e.getMessage(), "Erro ao consultar a ordem produção");
            } catch (MessagingException ex) {
                Logger.getLogger(EventosDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    public OrdemProducao consultaOrdemProducao(int numOrp) {
        try {
            PreparedStatement ps = ConexaoMysql.getConexaoMySQL().prepareStatement(CONSULTAORDEMPRODUCAORSQL);
            ps.setInt(1, numOrp);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ordemProducao = new OrdemProducao();
                ordemProducao.setCodEmp(rs.getInt(1));
                ordemProducao.setCodOri(rs.getString(2));
                ordemProducao.setNumOrp(rs.getInt(3));
                ordemProducao.setCodPro(rs.getString(4));
                ordemProducao.setCodDer(rs.getString(5));
                ordemProducao.setCodEtg(rs.getInt(6));
                ordemProducao.setSeqRot(rs.getInt(7));
                ordemProducao.setSeqEtr(rs.getInt(8));
                ordemProducao.setQtdPrv(rs.getDouble(9));
                ordemProducao.setQtdRe1(rs.getDouble(10));
                ordemProducao.setQtdRfg(rs.getDouble(11));
                ordemProducao.setQtdMax(rs.getDouble(12));
                ordemProducao.setCicPad(rs.getDouble(13));
                ordemProducao.setDesPro(rs.getString(14));
                ordemProducao.setDesDer(rs.getString(15));
                ordemProducao.setPrxPro(rs.getString(16));
                ordemProducao.setPrxDer(rs.getString(17));
                ordemProducao.setDesPrxPro(rs.getString(18));
                ordemProducao.setDesPrxDer(rs.getString(19));
                ordemProducao.setNumPri(rs.getInt(20));
                ordemProducao.setCapsMt(rs.getDouble(21));
                ordemProducao.setPesPad(rs.getDouble(22));
                ordemProducao.setStatusRegistro(rs.getString(23));
                ordemProducao.setQtdRfgn(rs.getDouble(24));
            }
            return ordemProducao;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            Notificacao.infoBox("ERRO AO CONSULTA ORDEM PROD /124OPD", true);
            try {
                enviarEmail.enviaEmail(e.getMessage(), "Erro ao consultar a ordem produção");
            } catch (MessagingException ex) {
                Logger.getLogger(EventosDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    public void atualizar() {
        try {
            PreparedStatement ps = ConexaoMysql.getConexaoMySQL().prepareStatement(UPDATEQDP);
            ps.setDouble(1, ordemProducao.getQtdRe1());
            ps.setDouble(2, ordemProducao.getQtdRfg());
            ps.setDouble(3, ordemProducao.getQtdRfgn());
            ps.setInt(4, ordemProducao.getNumOrp());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            Notificacao.infoBox("ERRO AO ATUALIZAR ORDEM PROD. /144OPD", true);
            try {
                enviarEmail.enviaEmail(e.getMessage(), "Erro ao atualizar a ordem de produção");
            } catch (MessagingException ex) {
                Logger.getLogger(EventosDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
