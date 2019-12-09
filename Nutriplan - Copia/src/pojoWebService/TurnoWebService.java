/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pojoWebService;

import Dados.EnviarDadosTurno;
import br.com.senior.services.OpListaTurnoOutConsultar;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import pojo.Turno;
import util.EnviarEmail;
import util.Notificacao;

/**
 *
 * @author diogo.melo
 */
public class TurnoWebService {

    EnviarEmail enviarEmail = new EnviarEmail();
    String codCre;
    EnviarDadosTurno enviarDados = new EnviarDadosTurno();

    public boolean buscarTurnoSapiens() {
        try {
            br.com.senior.services.G5SeniorServices service = new br.com.senior.services.G5SeniorServices();
            br.com.senior.services.SapiensSyncnutriplanOp port = service.getSapiensSyncnutriplanOpPort();
            br.com.senior.services.OpListaTurnoIn parameters = new br.com.senior.services.OpListaTurnoIn();
            br.com.senior.services.OpListaTurnoOut result = port.listaTurno("integracao.op", "ERPintegracao.4651", 0, parameters);

            if (result.getErroExecucao().getValue() != null) {
                try {
                    Notificacao.infoBox("ERRO AO SINCRONIZAR TURNO /38TWS", false);
                    enviarEmail.enviaEmail(result.getErroExecucao().getValue(), "Erro WebService Turno");
                    return false;
                } catch (MessagingException e) {
                    return false;
                }
            } else {
                return preencherListaTurno(result);
            }
        } catch (Exception e) {
            try {
                enviarEmail.enviaEmail(e.getMessage(), "ERRO DE CONEXÃO COM O SAPIENS");
            } catch (MessagingException ex) {
                Logger.getLogger(TurnoWebService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }

    public boolean preencherListaTurno(br.com.senior.services.OpListaTurnoOut result) {
        List<Turno> itensTurno = new ArrayList<Turno>();
        Turno turno = new Turno();
        for (OpListaTurnoOutConsultar listaTurno : result.getConsultar()) {
            turno = new Turno();
            System.out.println(listaTurno.getDesTrb().getValue());
            turno.setDesTrb(listaTurno.getDesTrb().getValue().toUpperCase());
            turno.setTurTrb(listaTurno.getTurTrb().getValue());
            itensTurno.add(turno);
        }
        turno.setItensTurno(itensTurno);
        enviarDados.EnviarDadosTurno(itensTurno);
        return true;
    }
}
