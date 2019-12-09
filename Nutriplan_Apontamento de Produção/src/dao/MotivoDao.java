package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import pojo.Motivo;
import util.ConexaoMysql;
import util.EnviarEmail;
import util.Notificacao;

/**
 *
 * @author diogo.melo
 */
public class MotivoDao {

    private final String CONSULTARSQL = "SELECT CODMTV, DESMTV from nutri_op.op018mtv";

    Motivo motivo = new Motivo();
    EnviarEmail enviarEmail = new EnviarEmail();
    List<Motivo> motivos = new ArrayList<Motivo>();

    public MotivoDao() {
    }

    public List<Motivo> consultar() {
        try {
            PreparedStatement ps = ConexaoMysql.getConexaoMySQL().prepareStatement(CONSULTARSQL);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                motivo = new Motivo();
                motivo.setCodMtv(rs.getString(1));
                motivo.setDesMtv(rs.getString(2));
                motivos.add(motivo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            Notificacao.infoBox("ERRO AO CONSUNTAR MTV. PARADA /50MD", true);
            try {
                enviarEmail.enviaEmail(e.getMessage(), "Erro ao consultar os motivos de parada");
            } catch (MessagingException ex) {
                Logger.getLogger(EventosDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return motivos;
    }
}
