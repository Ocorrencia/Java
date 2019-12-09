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
import pojo.TurnoTrabalho;
import util.ConexaoMysql;
import util.EnviarEmail;
import util.Notificacao;

/**
 *
 * @author diogo.melo
 */
public class TurnoDao {

    private final String CONSULTARSQL = "SELECT DISTINCT\n"
            + "    tur.TURTRB, tur.destrb, trb.horini, trb.horfim\n"
            + "FROM\n"
            + "    nutri_op.op803trb AS trb\n"
            + "        INNER JOIN\n"
            + "    nutri_op.op000tur AS tur ON (tur.turtrb = trb.turtrb);\n"
            + "\n"
            + "";

    TurnoTrabalho turnoTrabalho = new TurnoTrabalho();
    EnviarEmail enviarEmail = new EnviarEmail();

    public TurnoDao(TurnoTrabalho obj) {
        this.turnoTrabalho = obj;
    }

    public List<TurnoTrabalho> consultar() {
        try {
            PreparedStatement ps = ConexaoMysql.getConexaoMySQL().prepareStatement(CONSULTARSQL);
            ResultSet rs = ps.executeQuery();
            List<TurnoTrabalho> itensTurnoTrabalho = new ArrayList<TurnoTrabalho>();
            while (rs.next()) {
                turnoTrabalho = new TurnoTrabalho();
                turnoTrabalho.setTurTrb(rs.getInt(1));
                turnoTrabalho.setDesTrb(rs.getString(2));
                turnoTrabalho.setHorIni(rs.getString(3));
                turnoTrabalho.setHorFim(rs.getString(4));
                itensTurnoTrabalho.add(turnoTrabalho);
            }
            turnoTrabalho.setItensTurnoTrabalho(itensTurnoTrabalho);
            return turnoTrabalho.getItensTurnoTrabalho();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            Notificacao.infoBox("ERRO AO CONSULTAR TURNO /60TD", true);
            try {
                enviarEmail.enviaEmail(e.getMessage(), "Erro ao consultar o turno de trabalho");
            } catch (MessagingException ex) {
                Logger.getLogger(EventosDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }
}
