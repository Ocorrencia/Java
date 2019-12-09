/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pojoWebService;

import Dados.EnviarDadosOperador;
import br.com.senior.services.OpOperadorOutConsultar;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;
import pojo.Operador;
import util.Consulta;
import util.EnviarEmail;
import util.Notificacao;

/**
 *
 * @author diogo.melo
 */
public class OperadorWebService {

    EnviarEmail enviarEmail = new EnviarEmail();
    String codCre;
    EnviarDadosOperador enviarDados = new EnviarDadosOperador();

    public boolean buscarOperadoresSapiens() {
        try {

            codCre = Consulta.CONSULTASTRING("op000maq", "CODCRE", "1 = 1");

            JAXBElement<String> jaxbCodCre = new JAXBElement(new QName("", "codCre"), String.class, codCre);

            br.com.senior.services.G5SeniorServices service = new br.com.senior.services.G5SeniorServices();
            br.com.senior.services.SapiensSyncnutriplanOp port = service.getSapiensSyncnutriplanOpPort();
            br.com.senior.services.OpOperadorIn parameters = new br.com.senior.services.OpOperadorIn();
            parameters.setCodCre(jaxbCodCre);
            br.com.senior.services.OpOperadorOut result = port.operador("integracao.op", "ERPintegracao.4651", 0, parameters);

            if (result.getErroExecucao().getValue() != null) {
                try {
                    Notificacao.infoBox("ERRO AO SINCRONIZAR OPERADORES /47OWS", false);
                    enviarEmail.enviaEmail(result.getErroExecucao().getValue(), "Erro WebService Operadores");
                    return false;
                } catch (MessagingException e) {
                    return false;
                }
            } else {
                return preencherOperador(result);
            }
        } catch (Exception e) {
            try {
                enviarEmail.enviaEmail(e.getMessage(), "ERRO DE CONEXÃO COM O SAPIENS");
            } catch (MessagingException ex) {
                Logger.getLogger(OperadorWebService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }

    public boolean preencherOperador(br.com.senior.services.OpOperadorOut result) {
        List<Operador> itensOperadores = new ArrayList<Operador>();
        Operador operador = new Operador();
        for (OpOperadorOutConsultar operadores : result.getConsultar()) {
            operador = new Operador();
            System.out.println(operadores.getNomOpe().getValue());
            operador.setNomOpe(operadores.getNomOpe().getValue());
            operador.setNumCad(operadores.getNumCad().getValue());
            operador.setTurTrb(operadores.getTurTrb().getValue());
            itensOperadores.add(operador);
        }
        operador.setItensOperadores(itensOperadores);
        enviarDados.EnviarDadosOperador(itensOperadores);
        return true;
        //TODO: enviar dados(itensOperadores) para o dao e executar a inclusao no banco
    }
}
