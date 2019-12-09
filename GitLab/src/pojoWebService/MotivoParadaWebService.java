/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pojoWebService;

import Dados.EnviarDadosMotivoParada;
import br.com.senior.services.OpMotivoParadaOutConsultar;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import pojo.Motivo;
import util.EnviarEmail;
import util.Notificacao;

/**
 *
 * @author diogo.melo
 */
public class MotivoParadaWebService {

    EnviarEmail enviarEmail = new EnviarEmail();
    String codCre;
    EnviarDadosMotivoParada enviarDados = new EnviarDadosMotivoParada();

    public boolean buscarMotivoParadaSapiens() {
        try {
            br.com.senior.services.G5SeniorServices service = new br.com.senior.services.G5SeniorServices();
            br.com.senior.services.SapiensSyncnutriplanOp port = service.getSapiensSyncnutriplanOpPort();
            br.com.senior.services.OpMotivoParadaIn parameters = new br.com.senior.services.OpMotivoParadaIn();
            br.com.senior.services.OpMotivoParadaOut result = port.motivoParada("integracao.op", "ERPintegracao.4651", 0, parameters);

            if (result.getErroExecucao().getValue() != null) {
                try {
                    Notificacao.infoBox("ERRO AO SINCRONIZAR MOTIVOS DE PARAD. /38MPWS", false);
                    enviarEmail.enviaEmail(result.getErroExecucao().getValue(), "Erro WebService Motivo Parada");
                    return false;
                } catch (MessagingException e) {
                    return false;
                }
            } else {
                return preencherMotivoParada(result);
            }
        } catch (Exception e) {
            try {
                enviarEmail.enviaEmail(e.getMessage(), "ERRO DE CONEXÃO COM O SAPIENS");
            } catch (MessagingException ex) {
                Logger.getLogger(MotivoParadaWebService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }

    public boolean preencherMotivoParada(br.com.senior.services.OpMotivoParadaOut result) {
        List<Motivo> itensMotivo = new ArrayList<Motivo>();
        Motivo objMotivo = new Motivo();
        for (OpMotivoParadaOutConsultar motivo : result.getConsultar()) {
            objMotivo = new Motivo();
            System.out.println(motivo.getDesMtv().getValue());
            objMotivo.setCodAmp(motivo.getCodAmp().getValue());
            objMotivo.setCodEmp(motivo.getCodEmp().getValue());
            objMotivo.setCodMtv(motivo.getCodMtv().getValue());
            objMotivo.setDesAmp(motivo.getDesAmp().getValue());
            objMotivo.setDesMtv(motivo.getDesMtv().getValue());
            objMotivo.setStatusRegistro(motivo.getStatusRegistro().getValue());
            itensMotivo.add(objMotivo);
        }
        objMotivo.setItensMotivo(itensMotivo);
        enviarDados.EnviarDadosMotivoParada(itensMotivo);
        return true;
    }
}
