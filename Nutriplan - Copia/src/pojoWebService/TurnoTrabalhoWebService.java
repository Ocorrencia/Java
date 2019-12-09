/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pojoWebService;

import Dados.EnviarDadosTurnoTrabalho;
import br.com.senior.services.OpFichaTecnicaOutConsultar;
import br.com.senior.services.OpTurnoDeTrabalhoXHorariosOut;
import br.com.senior.services.OpTurnoDeTrabalhoXHorariosOutConsultar;
import java.util.ArrayList;
import java.util.List;
import javax.mail.MessagingException;
import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;
import pojo.FichaTecnica;
import pojo.TurnoTrabalho;
import util.Consulta;
import util.EnviarEmail;
import util.Notificacao;

/**
 *
 * @author diogo.melo
 */
public class TurnoTrabalhoWebService {

    EnviarEmail enviarEmail = new EnviarEmail();
    String codCre;
    EnviarDadosTurnoTrabalho enviarDados = new EnviarDadosTurnoTrabalho();

    public boolean buscarTurnoTrabalho() {
        codCre = Consulta.CONSULTASTRING("nutri_op.op000maq", "CODCRE", "1 = 1");
        JAXBElement<String> jaxbCodCre = new JAXBElement(new QName("", "codCre"), String.class, codCre);

        br.com.senior.services.G5SeniorServices service = new br.com.senior.services.G5SeniorServices();
        br.com.senior.services.SapiensSyncnutriplanOp port = service.getSapiensSyncnutriplanOpPort();
        br.com.senior.services.OpTurnoDeTrabalhoXHorariosIn parameters = new br.com.senior.services.OpTurnoDeTrabalhoXHorariosIn();

        parameters.setCodCre(jaxbCodCre);

        br.com.senior.services.OpTurnoDeTrabalhoXHorariosOut result = port.turnoDeTrabalhoXHorarios("integracao.op", "ERPintegracao.4651", 0, parameters);

        if (result.getErroExecucao().getValue() != null) {
            try {
                Notificacao.infoBox("ERRO AO SINCRONIZAR TURN. DE TRABA. /47TTWS", false);
                enviarEmail.enviaEmail(result.getErroExecucao().getValue(), "Erro WebService Turno Trabalho");
                return false;
            } catch (MessagingException e) {
                return false;
            }
        } else {
            return preencherTurnoTrabalho(result);
        }
    }

    public boolean preencherTurnoTrabalho(br.com.senior.services.OpTurnoDeTrabalhoXHorariosOut result) {
        List<TurnoTrabalho> itensTurnoTrabalho = new ArrayList<TurnoTrabalho>();
        TurnoTrabalho turnoTrabalho = new TurnoTrabalho();

        for (OpTurnoDeTrabalhoXHorariosOutConsultar objTurnoTrabalho : result.getConsultar()) {

            turnoTrabalho = new TurnoTrabalho();
            turnoTrabalho.setCodCre(objTurnoTrabalho.getCodCre().getValue());
            turnoTrabalho.setCodEmp(objTurnoTrabalho.getCodEmp().getValue());
            turnoTrabalho.setCodEtg(objTurnoTrabalho.getCodEtg().getValue());
            turnoTrabalho.setHorFim(objTurnoTrabalho.getHorFim().getValue());
            turnoTrabalho.setHorIni(objTurnoTrabalho.getHorIni().getValue());
            turnoTrabalho.setSeqHtr(objTurnoTrabalho.getSeqHtr().getValue());
            turnoTrabalho.setStatusRegistro(objTurnoTrabalho.getStatusRegistro().getValue());
            turnoTrabalho.setTipHtr(objTurnoTrabalho.getTipHtr().getValue());
            turnoTrabalho.setTurTrb(objTurnoTrabalho.getTurTrb().getValue());
            itensTurnoTrabalho.add(turnoTrabalho);
        }
        turnoTrabalho.setItensTurnoTrabalho(itensTurnoTrabalho);
        enviarDados.EnviarDadosTurnoTrabalho(itensTurnoTrabalho);
        return true;
    }

}
