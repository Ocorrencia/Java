package dao;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import pojo.OrdemProducao;
import util.Consulta;
import util.EnviarEmail;
import util.Notificacao;

public class RefugoDao {

    OrdemProducao ordemProducao = new OrdemProducao();
    EnviarEmail enviarEmail = new EnviarEmail();

    public boolean atualizar() {
        try {
            ArrayList<Integer> listaItens = Consulta.CONSULTAARRAYINT("nutri_op.op900eoq", "SEQMOV", "EXPERP = 0 AND QTDRE1 = 1");
            if (listaItens.size() > 0) {
                String realizado = Consulta.CONSULTASTRING("nutri_op.op900eoq", "MAX(SEQMOV)", "EXPERP = 0 AND QTDRE1 = 1");
                if (Consulta.UPDATE("nutri_op.op900eoq", "QTDRE1 = 0", "SEQMOV = " + realizado + "")) {
                    return true;
                } else {
                    Notificacao.infoBox("ERRO AO ATUALIZAR!", false);
                    return false;
                }
            } else {
                Notificacao.infoBox("ITENS JÁ INTEGRADOS!", false);
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            Notificacao.infoBox("ERRO AO ATUALIZAR REFG. /32RD", true);
            try {
                enviarEmail.enviaEmail(e.getMessage(), "Erro ao atualizar Refugo");
            } catch (MessagingException ex) {
                Logger.getLogger(EventosDao.class.getName()).log(Level.SEVERE, null, ex);
            }
            return false;
        }
    }
}
