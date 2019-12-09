/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this temvplate file, choose Tools | Temvplates
 * and open the temvplate in the editor.
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import pojo.MovimentoOrdemProducao;
import util.ConexaoMysql;
import util.DadosRaspberry;
import util.EnviarEmail;
import util.Notificacao;

/**
 *
 * @author diogo.melo
 */
public class MovimentoOrdemProducaoDao {

    MovimentoOrdemProducao mvp = new MovimentoOrdemProducao();
    EnviarEmail enviarEmail = new EnviarEmail();
    private final String INCLUIRSQL = "INSERT INTO nutri_op.op900eoq (CODEMP,CODORI,NUMORP,CODPRO,CODDER,CODETG,SEQROT,SEQETR,SEQMOV,DATMOV,HORMOV,NUMCAD,QTDRE1,QTDRFG,EXPERP,CODDFT,TURTRB) VALUES(?, ? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,?)";

    public MovimentoOrdemProducaoDao(MovimentoOrdemProducao movimentoOrdemProducao) {
        this.mvp = movimentoOrdemProducao;
    }

    public MovimentoOrdemProducao getMvp() {
        return mvp;
    }

    public void setMvp(MovimentoOrdemProducao mvp) {
        this.mvp = mvp;
    }


    public void INCLUIR() {
        try {
            PreparedStatement ps = ConexaoMysql.getConexaoMySQL().prepareStatement(INCLUIRSQL);
            ps.setInt(1, 1);
            ps.setString(2, mvp.getCodOri());
            ps.setInt(3, mvp.getNumOrp());
            ps.setString(4, mvp.getCodPro());
            ps.setString(5, mvp.getCodDer());
            ps.setString(6, mvp.getCodEtg());
            ps.setInt(7, mvp.getSeqRot());
            ps.setInt(8, mvp.getSeqEtr());
            ps.setInt(9, DadosRaspberry.SEQUENCIA);
            ps.setString(10, mvp.getDatMov());
            ps.setString(11, mvp.getHorMov());
            ps.setInt(12, mvp.getNumCad());
            ps.setDouble(13, mvp.getQtdRe1());
            ps.setDouble(14, mvp.getQtdRfg());
            ps.setInt(15, mvp.getExpErp());
            ps.setString(16, mvp.getCodDdft());
            ps.setInt(17, mvp.getTurTrb());
            ps.executeUpdate();
            ConexaoMysql.FecharConexao();
        } catch (SQLException e) {
            Notificacao.infoBox("ERRO AO INCLUIR MOVM PRODUÇÃO /67MOPD", true);
            try {
                enviarEmail.enviaEmail(e.getMessage(), "Erro ao incluir o Movimento OP");
            } catch (MessagingException ex) {
                Logger.getLogger(EventosDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
