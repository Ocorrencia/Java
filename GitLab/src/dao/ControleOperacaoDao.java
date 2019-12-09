/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import pojo.ControleOperacao;
import util.ConexaoMysql;
import util.EnviarEmail;
import util.Notificacao;


public class ControleOperacaoDao {

    ControleOperacao controleOperacao = new ControleOperacao();
    private final String INCLUIRSQL = "INSERT INTO nutri_op.op000cop VALUES(?,?,?,?)";
    private final String CONSULTARSQL = "SELECT CODCOP, CODOPE, HOROPE, DATAOPE FROM nutri_op.op000cop ORDER BY CODCOP DESC LIMIT 1";
    Date date = new Date();
    Calendar hoje = Calendar.getInstance();
    EnviarEmail enviarEmail = new EnviarEmail();
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    public ControleOperacaoDao(ControleOperacao controleOperacao) {
        this.controleOperacao = controleOperacao;
    }

    public void incluir() {
        try {
            PreparedStatement ps = ConexaoMysql.getConexaoMySQL().prepareStatement(INCLUIRSQL);
            ps.setInt(1, 0);
            ps.setInt(2, controleOperacao.getCodOpe());
            String data = sdf.format(Calendar.getInstance().getTime());
            ps.setString(3, "" + Calendar.getInstance().getTime().getHours() + ":" + Calendar.getInstance().getTime().getMinutes() + ":" + Calendar.getInstance().getTime().getSeconds());;
            ps.setString(4, "" + data);
            ps.executeUpdate();
            ConexaoMysql.FecharConexao();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            Notificacao.infoBox("ERRO AO CONSULTAR CNTRO. OP. /52COD", true);
            try {
                enviarEmail.enviaEmail(e.getMessage(), "Erro ao incluir o controle de operação");
            } catch (MessagingException ex) {
                Logger.getLogger(EventosDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public boolean consultar() {
        try {
            PreparedStatement ps = ConexaoMysql.getConexaoMySQL().prepareStatement(CONSULTARSQL);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                controleOperacao.setCodCop(rs.getInt(1));
                controleOperacao.setCodOpe(rs.getInt(2));
                controleOperacao.setHorOpe(rs.getString(3));
                controleOperacao.setDatOpe(rs.getString(4));
                return true;
            }
            return false;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            Notificacao.infoBox("ERRO AO CONSULTAR CNTRO. OP. /76COD", true);
            try {
                enviarEmail.enviaEmail(e.getMessage(), "Erro ao incluir o controle de operação");
            } catch (MessagingException ex) {
                Logger.getLogger(EventosDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (Exception e) {

            System.out.println(e.getMessage());
            Notificacao.infoBox("ERRO AO CONSULTAR CNTRO. OP. /76COD", true);
            try {
                enviarEmail.enviaEmail(e.getMessage(), "Erro ao incluir o controle de operação");
            } catch (MessagingException ex) {
                Logger.getLogger(EventosDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }
}
