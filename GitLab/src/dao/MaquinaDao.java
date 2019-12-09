/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import pojo.Maquina;
import util.ConexaoMysql;
import util.Consulta;
import util.EnviarEmail;
import util.Notificacao;
import util.Sincronizacao;

/**
 *
 * @author diogo.melo
 */
public class MaquinaDao {

    private final String INCLUIRSQL = "INSERT INTO nutri_op.op000maq VALUES(?,?,?, ?)";
    private final String ALTERARSQL = "UPDATE nutri_op.op000maq SET CODCRE = ?, IPMAQ = ? WHERE CODMAQ = 1";
    private final String CONSULTARSQL = "SELECT * FROM nutri_op.op000maq";

    Maquina maq = new Maquina();
    EnviarEmail enviarEmail = new EnviarEmail();

    public MaquinaDao(Maquina maquina) {
        this.maq = maquina;
    }

    public void INCLUIR() {
        try {
            if (Consulta.CONSULTASTRING("nutri_op.op000maq", "CODMAQ", "1 = 1").equals("VAZIO")) {
                PreparedStatement ps = ConexaoMysql.getConexaoMySQL().prepareStatement(INCLUIRSQL);
                ps.setInt(1, 1);
                ps.setInt(2, maq.getCodCre());
                ps.setString(3, maq.getIpMaq());
                ps.setInt(4, 0);
                ps.executeUpdate();
                Notificacao.infoBox("SALVO COM SUCESSO", true);
                ConexaoMysql.FecharConexao();
                Sincronizacao.sincPrioridade();
            } else {
                PreparedStatement ps = ConexaoMysql.getConexaoMySQL().prepareStatement(ALTERARSQL);
                ps.setInt(1, maq.getCodCre());
                ps.setString(2, maq.getIpMaq());
                ps.executeUpdate();
                Notificacao.infoBox("ALTERADO COM SUCESSO", true);
                ConexaoMysql.FecharConexao();
                Sincronizacao.sincPrioridade();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            Notificacao.infoBox("ERRO AO INCLUIR CENTRO DE REC. /58MD", true);
            try {
                enviarEmail.enviaEmail(e.getMessage(), "Erro ao incluir a Máquina");
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
                maq.setCodMaq(rs.getInt(1));
                maq.setCodCre(rs.getInt(2));
                maq.setIpMaq(rs.getString(3));
                maq.setPrioridade(rs.getInt(4));
                return true;
            } else {
                maq.setCodCre(-1);
                maq.setIpMaq("");
            }
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            Notificacao.infoBox("ERRO AO CONSULTAR MAQ. /83MD", true);
            try {
                enviarEmail.enviaEmail(e.getMessage(), "Erro ao consultar a máquina");
            } catch (MessagingException ex) {
                // Logger.getLogger(EventosDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }
}
