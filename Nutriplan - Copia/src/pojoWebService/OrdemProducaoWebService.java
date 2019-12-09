/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pojoWebService;

import Dados.EnviarDadosOrdemProducao;
import br.com.senior.services.OpOrdemProducaoOutConsultar;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;
import pojo.OrdemProducao;
import util.Consulta;
import util.EnviarEmail;
import util.Notificacao;

/**
 *
 * @author diogo.melo
 */
public class OrdemProducaoWebService {

    EnviarEmail enviarEmail = new EnviarEmail();
    EnviarDadosOrdemProducao enviarDados = new EnviarDadosOrdemProducao();
    String codCre;
    boolean atualizarParcial;
    public boolean buscarOrdemProducaoSapiens(boolean atualizarParcial) {
        try {
            this.atualizarParcial = atualizarParcial;
            codCre = Consulta.CONSULTASTRING("nutri_op.op000maq", "CODCRE", "1 = 1");

            JAXBElement<String> jaxbCodCre = new JAXBElement(new QName("", "codCre"), String.class, codCre);

            br.com.senior.services.G5SeniorServices service = new br.com.senior.services.G5SeniorServices();
            br.com.senior.services.SapiensSyncnutriplanOp port = service.getSapiensSyncnutriplanOpPort();
            br.com.senior.services.OpOrdemProducaoIn parameters = new br.com.senior.services.OpOrdemProducaoIn();
            parameters.setCodCre(jaxbCodCre);
            br.com.senior.services.OpOrdemProducaoOut result = port.ordemProducao("integracao.op", "ERPintegracao.4651", 0, parameters);

            if (result.getErroExecucao().getValue() != null) {
                try {
                    Notificacao.infoBox("ERRO AO SINCRONIZAR ORDEM DE PROD. /46OPWS", false);
                    enviarEmail.enviaEmail(result.getErroExecucao().getValue(), "Erro WebService Ordem Produção");
                    return false;
                } catch (MessagingException e) {
                    return false;
                }
            } else {
                return preencherOrdemProducao(result);
            }
        } catch (Exception e) {
            try {
                enviarEmail.enviaEmail(e.getMessage(), "ERRO DE CONEXÃO COM O SAPIENS");
            } catch (MessagingException ex) {
                Logger.getLogger(OrdemProducaoWebService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }

    public boolean preencherOrdemProducao(br.com.senior.services.OpOrdemProducaoOut result) {
        List<OrdemProducao> itensOrdemProducao = new ArrayList<OrdemProducao>();
        OrdemProducao ordemProducao = new OrdemProducao();
        for (OpOrdemProducaoOutConsultar ordemProducaoobj : result.getConsultar()) {
            ordemProducao = new OrdemProducao();
            ordemProducao.setCicPad(ordemProducaoobj.getCicPad().getValue());
            ordemProducao.setCodDer(ordemProducaoobj.getCodDer().getValue());
            ordemProducao.setCodEmp(ordemProducaoobj.getCodEmp().getValue());
            ordemProducao.setCodEtg(ordemProducaoobj.getCodEtg().getValue());
            ordemProducao.setSeqRot(ordemProducaoobj.getSeqRot().getValue());
            ordemProducao.setCodOri(ordemProducaoobj.getCodOri().getValue());
            ordemProducao.setCodPro(ordemProducaoobj.getCodPro().getValue());
            ordemProducao.setDesDer(ordemProducaoobj.getDesDer().getValue());
            ordemProducao.setDesEtg(ordemProducaoobj.getDesEtg().getValue());
            ordemProducao.setDesPro(ordemProducaoobj.getDesPro().getValue());
            ordemProducao.setDesPrxDer(ordemProducaoobj.getDesPrxDer().getValue());
            ordemProducao.setDesPrxPro(ordemProducaoobj.getDesPrxPro().getValue());
            ordemProducao.setNumOrp(ordemProducaoobj.getNumOrp().getValue());
            ordemProducao.setNumPri(ordemProducaoobj.getNumPri().getValue());
            ordemProducao.setPrxDer(ordemProducaoobj.getPrxDer().getValue());
            ordemProducao.setPrxPro(ordemProducaoobj.getPrxPro().getValue());
            ordemProducao.setQtdMax(ordemProducaoobj.getQtdMax().getValue());
            ordemProducao.setQtdPrv(ordemProducaoobj.getQtdPrv().getValue());
            ordemProducao.setQtdRfg(ordemProducaoobj.getQtdRfg().getValue());
            ordemProducao.setPesPad(ordemProducaoobj.getPesPad().getValue());
            ordemProducao.setCapsMt(ordemProducaoobj.getCapSmt().getValue());
            ordemProducao.setStatusRegistro("0");
            itensOrdemProducao.add(ordemProducao);
        }
        enviarDados.EnviarDadosOrdemProducao(itensOrdemProducao, atualizarParcial);
        return true;
    }
}
