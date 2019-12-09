/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import dao.EventosDao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;

/**
 *
 * @author diogo.melo
 */
public class Consulta {

    static EnviarEmail enviarEmail = new EnviarEmail();

    public static String CONSULTASTRING(String tabela, String coluna, String where) {
        String sql = "SELECT " + coluna + " FROM " + tabela + " WHERE " + where + "";
        try {
            PreparedStatement ps = ConexaoMysql.getConexaoMySQL().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString(1);
            } else {
                return "VAZIO";
            }
        } catch (SQLException e) {
            e.printStackTrace();
            Notificacao.infoBox("ERRO NA CONSULTA /37C", true);
            try {
                enviarEmail.enviaEmail(e.getMessage(), "ERRO NO CONSULTA STRING");
            } catch (MessagingException ex) {
                Logger.getLogger(EventosDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return "VAZIO";
    }

    public static void UPDATE(String tabela, String valores, String where) {
        String sql = "UPDATE  " + tabela + " SET " + valores + " WHERE " + where + "";
        try {
            PreparedStatement ps = ConexaoMysql.getConexaoMySQL().prepareStatement(sql);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            Notificacao.infoBox("ERRO NO UPDATE /54C", true);
            try {
                enviarEmail.enviaEmail(e.getMessage(), "ERRO NO UPDATE");
            } catch (MessagingException ex) {
                Logger.getLogger(EventosDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static int CONSULTAINT(String tabela, String coluna, String where) {
        String sql = "SELECT " + coluna + " FROM " + tabela + " WHERE " + where + "";
        try {
            PreparedStatement ps = ConexaoMysql.getConexaoMySQL().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            } else {
                return 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            Notificacao.infoBox("ERRO NA CONSULTA /75C", true);
            try {
                enviarEmail.enviaEmail(e.getMessage(), "ERRO NO CONSULTA INT");
            } catch (MessagingException ex) {
                Logger.getLogger(EventosDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return 0;
    }

    public static ArrayList<Integer> CONSULTAARRAYINT(String tabela, String coluna, String where) {
        String sql = "SELECT " + coluna + " FROM " + tabela + " WHERE " + where + "";
        ArrayList<Integer> lista = new ArrayList<Integer>();
        try {
            PreparedStatement ps = ConexaoMysql.getConexaoMySQL().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                lista.add(rs.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            Notificacao.infoBox("ERRO NA CONSULTA /96C", true);
            try {
                enviarEmail.enviaEmail(e.getMessage(), "ERRO NA CONSULTA ARRAY INT");
            } catch (MessagingException ex) {
                Logger.getLogger(EventosDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return lista;
    }

    public static ArrayList<String> CONSULTAARRAYSTRING(String tabela, String coluna, String where) {
        String sql = "SELECT " + coluna + " FROM " + tabela + " WHERE " + where + "";
        ArrayList<String> lista = new ArrayList<String>();
        try {
            PreparedStatement ps = ConexaoMysql.getConexaoMySQL().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                lista.add(rs.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            Notificacao.infoBox("ERRO NA CONSULTA /117C", true);
            try {
                enviarEmail.enviaEmail(e.getMessage(), "ERRO NA CONSULTA ARRAY STRING");
            } catch (MessagingException ex) {
                Logger.getLogger(EventosDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return lista;
    }
}
