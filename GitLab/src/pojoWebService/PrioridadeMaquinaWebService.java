/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pojoWebService;

import Dados.EnviarDadosMaquina;
import br.com.senior.services.OpPrioridadeCentroDeRecursoOutConsultar;
import dao.TurnoDao;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;
import org.joda.time.DateTime;
import pojo.Maquina;
import pojo.TurnoTrabalho;
import util.Consulta;
import util.Enums;
import util.EnviarEmail;
import util.Notificacao;

/**
 *
 * @author diogo.melo
 */
public class PrioridadeMaquinaWebService {

    EnviarEmail enviarEmail = new EnviarEmail();
    String codCre;
    int emp = 1;
    String data;
    EnviarDadosMaquina enviarDados = new EnviarDadosMaquina();

    TurnoTrabalho turnoTrabalho = new TurnoTrabalho();
    TurnoDao turnoDao = new TurnoDao(turnoTrabalho);

    DateTime dt3 = new DateTime();
    DateTime dt4 = new DateTime();

    DateTime dt1 = new DateTime();
    DateTime dt2 = new DateTime();
    DateTime tempo = new DateTime();

    String horaInicial = "";

    public boolean buscarPrioridadeSapiens() {
        try {

            codCre = Consulta.CONSULTASTRING("op000maq", "CODCRE", "1 = 1");

            JAXBElement<String> jaxbCodCre = new JAXBElement(new QName("", "codCre"), String.class, codCre);
            JAXBElement<Integer> jaxbEmp = new JAXBElement(new QName("", "codEmp"), Integer.class, emp);
            JAXBElement<String> jaxbDatPrg = new JAXBElement(new QName("", "datPrg"), String.class, getDate());
            JAXBElement<Integer> jaxbTurTrb = new JAXBElement(new QName("", "turTrb"), Integer.class, pegarTurno());

            br.com.senior.services.G5SeniorServices service = new br.com.senior.services.G5SeniorServices();
            br.com.senior.services.SapiensSyncnutriplanOp port = service.getSapiensSyncnutriplanOpPort();
            br.com.senior.services.OpPrioridadeCentroDeRecursoIn parameters = new br.com.senior.services.OpPrioridadeCentroDeRecursoIn();
            parameters.setCodCre(jaxbCodCre);
            parameters.setCodEmp(jaxbEmp);
            parameters.setDatPrg(jaxbDatPrg);
            parameters.setTurTrb(jaxbTurTrb);

            br.com.senior.services.OpPrioridadeCentroDeRecursoOut result = port.prioridadeCentroDeRecurso("integracao.op", "ERPintegracao.4651", 0, parameters);

            if (result.getErroExecucao().getValue() != null) {
                try {
                    Notificacao.infoBox("ERRO AO SINCRONIZAR PRIORIDADE /38PMWS", false);
                    enviarEmail.enviaEmail(result.getErroExecucao().getValue(), "Erro WebService Prioridade");
                    return false;
                } catch (MessagingException e) {
                    return false;
                }
            } else {
                return preencherListaPrioridade(result);
            }
        } catch (Exception e) {
            try {
                enviarEmail.enviaEmail(e.getMessage(), "ERRO DE CONEXÃO COM O SAPIENS");
            } catch (MessagingException ex) {
                Logger.getLogger(PrioridadeMaquinaWebService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }

    public Integer pegarTurno() {
        try {
            turnoTrabalho.setItensTurnoTrabalho(turnoDao.consultar());
            for (TurnoTrabalho turnoTrabalho1 : turnoTrabalho.getItensTurnoTrabalho()) {

                SimpleDateFormat datFormatAtual = new SimpleDateFormat("HH");
                long timeInMillisAtual = System.currentTimeMillis();
                Calendar dataAtual = Calendar.getInstance();
                dataAtual.setTimeInMillis(timeInMillisAtual);

                dt3 = new DateTime()
                        .withHourOfDay(Integer.parseInt(turnoTrabalho1.getHorIni().substring(0, 2)))
                        .withMinuteOfHour(Integer.parseInt(turnoTrabalho1.getHorIni().substring(3, 5)));

                dt4 = new DateTime()
                        .withHourOfDay(Integer.parseInt(turnoTrabalho1.getHorFim().substring(0, 2)))
                        .withMinuteOfHour(Integer.parseInt(turnoTrabalho1.getHorFim().substring(3, 5)));

                if (dataAtual.getTimeInMillis() >= dt3.getMillis() && dataAtual.getTimeInMillis() < dt4.getMillis()) {
                    System.out.println("TURNOOOOO " + turnoTrabalho1.getTurTrb());
                    return turnoTrabalho1.getTurTrb();
                }
            }
        } catch (NumberFormatException e) {
        }
        return 0;
    }

    public String getDate() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        return dateFormat.format(date);
    }

    public boolean preencherListaPrioridade(br.com.senior.services.OpPrioridadeCentroDeRecursoOut result) {
        Maquina maquina = new Maquina();
        for (OpPrioridadeCentroDeRecursoOutConsultar listaPrioridade : result.getConsultar()) {
            maquina = new Maquina();
            maquina.setPrioridade(listaPrioridade.getSeqPri().getValue());
            Enums.PRIORIDADE = maquina.getPrioridade();
        }
        enviarDados.EnviarDadosMaquina(maquina.getPrioridade());
        return true;
    }
}
