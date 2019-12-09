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
import pojo.ConfiguracaoBanco;
import util.ConexaoMysql;
import util.Consulta;
import util.EnviarEmail;
import util.Notificacao;

/**
 *
 * @author diogo.melo
 */
public class ConfiguracaoBancoDao {

    ConfiguracaoBanco configBanco = new ConfiguracaoBanco();
    private final String INCLUIRSQL = "INSERT INTO nutri_op.op000ban VALUES(?,?,?,?,?,?)";
    private final String CONSULTARSQL = "SELECT SERBAN, PORBAN, URLBAN, USEBAN, SENBAN FROM nutri_op.op000ban WHERE CODBAN = 1;";
    private final String ALTERARSQL = "UPDATE nutri_op.op000ban SET SERBAN = ?, PORBAN = ?,URLBAN = ?, USEBAN= ?, SENBAN =? WHERE CODBAN = 1";

    EnviarEmail enviarEmail = new EnviarEmail();

    public ConfiguracaoBancoDao(ConfiguracaoBanco configBanco) {
        this.configBanco = configBanco;
    }

    public void incluir() {
        try {
            if (Consulta.CONSULTASTRING("OP000BAN", "CODBAN", "1 = 1").equals("VAZIO")) {
                PreparedStatement ps = ConexaoMysql.getConexaoMySQL().prepareStatement(INCLUIRSQL);
                ps.setString(1, configBanco.getCodBan());
                ps.setString(2, configBanco.getSerBan());
                ps.setString(3, configBanco.getPorBan());
                ps.setString(4, configBanco.getUrlBan());
                ps.setString(5, configBanco.getUseBan());
                ps.setString(6, configBanco.getSenBan());
                ps.executeUpdate();
                ConexaoMysql.FecharConexao();
                Notificacao.infoBox("SALVO COM SUCESSO", true);
            } else {
                PreparedStatement ps = ConexaoMysql.getConexaoMySQL().prepareStatement(ALTERARSQL);
                ps.setString(1, configBanco.getSerBan());
                ps.setString(2, configBanco.getPorBan());
                ps.setString(3, configBanco.getUrlBan());
                ps.setString(4, configBanco.getUseBan());
                ps.setString(5, configBanco.getSenBan());
                ps.executeUpdate();
                ConexaoMysql.FecharConexao();
                Notificacao.infoBox("ALTERADO COM SUCESSO", true);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            Notificacao.infoBox("ERRO AO INCLUIR CONFG BANCO /63CBD", true);
            try {
                enviarEmail.enviaEmail(e.getMessage(), "Erro ao incluir o banco");
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
                configBanco.setSerBan(rs.getString(1));
                configBanco.setPorBan(rs.getString(2));
                configBanco.setUrlBan(rs.getString(3));
                configBanco.setUseBan(rs.getString(4));
                configBanco.setSenBan(rs.getString(5));
                return true;
            }
            Notificacao.infoBox("NÃO HÁ DADOS", true);
            return false;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            Notificacao.infoBox("ERRO AO CONSULTAR CONFG. BANCO /88CBD", true);
            try {
                enviarEmail.enviaEmail(e.getMessage(), "Erro ao consultar o banco");
            } catch (MessagingException ex) {
                Logger.getLogger(EventosDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }
}
