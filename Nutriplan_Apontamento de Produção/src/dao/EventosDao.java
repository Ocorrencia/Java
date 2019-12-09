/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import pojo.Eventos;
import util.ConexaoMysql;
import util.EnviarEmail;
import util.Notificacao;

/**
 *
 * @author diogo.melo
 */
public class EventosDao {

    private final String INCLUIRSQL = "INSERT INTO nutri_op.op000eve VALUES(?,?,?)";

    Eventos eventos = new Eventos();
    EnviarEmail enviarEmail = new EnviarEmail();

    public EventosDao(Eventos eventos) {
        this.eventos = eventos;
    }

    public void INCLUIR() {
        try {
            PreparedStatement ps = ConexaoMysql.getConexaoMySQL().prepareStatement(INCLUIRSQL);
            ps.setInt(1, 0);
            ps.setString(2, eventos.getNomeEve() + "teste");
            ps.setString(3, eventos.getHorEve() + "teste");
            ps.executeUpdate();
            ConexaoMysql.FecharConexao();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            Notificacao.infoBox("ERRO AO INCLUIR EVENTO /43ED", true);
            try {
                enviarEmail.enviaEmail(e.getMessage(), "Erro ao incluir o evento");
            } catch (MessagingException ex) {
                Logger.getLogger(EventosDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
