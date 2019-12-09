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
import pojo.ApontamentoParada;
import pojoWebService.ApontamentoParadaWebService;
import util.ConexaoMysql;
import util.EnviarEmail;
import util.Notificacao;

/**
 *
 * @author diogo.melo
 */
public class ApontamentoParadaDao {

    ApontamentoParada apontamentoParada = new ApontamentoParada();
    private final String INCLUIRSQL = "INSERT INTO nutri_op.op930mpr(CODEMP, CODCRE,CODETG,CODMTV,DATPAR,SEQMPR,HORINI,HORFIM,TURTRB,EXPERP,CODORI,NUMORP,SEQEOQ,SEQROT,NUMCAD) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    private final String CONSULTASQL = "SELECT * FROM nutri_op.op930mpr WHERE SEQMPR = ?";
    EnviarEmail enviarEmail = new EnviarEmail();
    ApontamentoParadaWebService apWebService = new ApontamentoParadaWebService();

    public ApontamentoParadaDao() {

    }

    public ApontamentoParada getApontamentoParada() {
        return apontamentoParada;
    }

    public void setApontamentoParada(ApontamentoParada apontamentoParada) {
        this.apontamentoParada = apontamentoParada;
    }

    public boolean INCLUIR() {
        try {
            PreparedStatement ps = ConexaoMysql.getConexaoMySQL().prepareStatement(INCLUIRSQL);
            ps.setInt(1, 1);
            ps.setInt(2, apontamentoParada.getCodCre());
            ps.setInt(3, apontamentoParada.getCodEtg());
            ps.setString(4, apontamentoParada.getCodMtv());
            ps.setString(5, apontamentoParada.getDatPar());
            ps.setInt(6, (int) apontamentoParada.getSeqMpr());
            ps.setString(7, apontamentoParada.getHorIni());
            ps.setString(8, apontamentoParada.getHorFim());
            ps.setInt(9, apontamentoParada.getTurTrb());
            ps.setInt(10, 0);
            ps.setString(11, apontamentoParada.getCodOri());
            ps.setInt(12, apontamentoParada.getNumOrp());
            ps.setInt(13, apontamentoParada.getSeqEoq() == null ? 0 : apontamentoParada.getSeqEoq());
            ps.setInt(14, apontamentoParada.getSeqRot() == null ? 0 : apontamentoParada.getSeqRot());
            ps.setInt(15, apontamentoParada.getNumCad() == null ? 0 : apontamentoParada.getNumCad());
            ps.executeUpdate();
            ConexaoMysql.FecharConexao();
            apWebService.enviarApontamentoParadaSapiens();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            Notificacao.infoBox("ERRO AO INCLUIR PARAD. MAQ. /68 APD", true);
            try {
                enviarEmail.enviaEmail(e.getMessage(), "Erro ao incluir o Apontamento de parada");
                return false;
            } catch (MessagingException ex) {
                Logger.getLogger(EventosDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }

    public ApontamentoParada consultar(String seqMpr) {
        try {
            PreparedStatement ps = ConexaoMysql.getConexaoMySQL().prepareStatement(CONSULTASQL);
            ps.setString(1, seqMpr);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                apontamentoParada.setCodCre(rs.getInt(2));
                apontamentoParada.setCodEtg(rs.getInt(3));
                apontamentoParada.setCodMtv(rs.getString(4));
                apontamentoParada.setDatPar(rs.getString(5));
                apontamentoParada.setSeqMpr(rs.getInt(6));
                apontamentoParada.setHorIni(rs.getString(7));
                apontamentoParada.setHorFim(rs.getString(8));
                apontamentoParada.setTurTrb(rs.getInt(9));
                apontamentoParada.setExpErp(rs.getString(10));
                apontamentoParada.setCodOri(rs.getString(11));
                apontamentoParada.setNumOrp(rs.getInt(12));
                apontamentoParada.setSeqEoq(rs.getInt(13));
                apontamentoParada.setSeqRot(rs.getInt(14));
                apontamentoParada.setNumCad(rs.getInt(15));
                return apontamentoParada;
            }
            return new ApontamentoParada();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            Notificacao.infoBox("ERRO AO CONSULTAR AP. PARADA /104APD", true);
            try {
                enviarEmail.enviaEmail(e.getMessage(), "Erro ao incluir o controle de operação");
            } catch (MessagingException ex) {
                Logger.getLogger(EventosDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return new ApontamentoParada();
    }
}
