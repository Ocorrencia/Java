/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

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

    public void atualizar() {
        try {
            String realizado = Consulta.CONSULTASTRING("nutri_op.op900eoq", "MAX(SEQMOV)", "EXPERP = 0 AND QTDRE1 = 1");
            Consulta.UPDATE("nutri_op.op900eoq", "QTDRE1 = 0", "SEQMOV = " + realizado+"");
        } catch (Exception e) {
            Notificacao.infoBox("ERRO AO ATUALIZAR REFG. /32RD", true);
            try {
                enviarEmail.enviaEmail(e.getMessage(), "Erro ao atualizar Refugo");
            } catch (MessagingException ex) {
                Logger.getLogger(EventosDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
