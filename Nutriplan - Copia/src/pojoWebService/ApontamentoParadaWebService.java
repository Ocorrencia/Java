/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pojoWebService;

import Dados.EnviarDadosMotivoParada;
import br.com.senior.services.OpApontamentoParadaInApontar;
import br.com.senior.services.OpApontamentoParadaOutRespostaApontar;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.mail.MessagingException;
import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;
import util.ConexaoMysql;
import util.EnviarEmail;
import util.Notificacao;

/**
 *
 * @author diogo.melo
 */
public class ApontamentoParadaWebService {

    EnviarEmail enviarEmail = new EnviarEmail();
    String codCre;
    EnviarDadosMotivoParada enviarDados = new EnviarDadosMotivoParada();
    private final String CONSULTARSQL = "SELECT \n"
            + "    CODEMP,\n"
            + "    CODCRE,\n"
            + "    CODETG,\n"
            + "    CODMTV,\n"
            + "    DATPAR,\n"
            + "    SEQMPR,\n"
            + "    HORINI,\n"
            + "    HORFIM,\n"
            + "    TURTRB,\n"
            + "    EXPERP,\n"
            + "    CODORI,\n"
            + "    NUMORP,\n"
            + "    SEQEOQ,\n"
            + "    SEQROT,\n"
            + "    NUMCAD\n"
            + "FROM\n"
            + "    nutri_op.op930mpr\n WHERE EXPERP = 0";

    JAXBElement<Integer> jaxbCodCre = new JAXBElement(new QName("", "codCre"), Integer.class, 0);
    JAXBElement<Integer> jaxbCodEtg = new JAXBElement(new QName("", "codEtg"), Integer.class, 0);
    JAXBElement<String> jaxbCodMtv = new JAXBElement(new QName("", "codMtv"), String.class, "");
    JAXBElement<String> jaxbDatPar = new JAXBElement(new QName("", "datMov"), String.class, "");
    JAXBElement<Integer> jaxbSeqMpr = new JAXBElement(new QName("", "sequenciaMovimento"), Integer.class, 0);
    JAXBElement<String> jaxbHorIni = new JAXBElement(new QName("", "horMov"), String.class, "");
    JAXBElement<String> jaxbHorFim = new JAXBElement(new QName("", "horMov"), String.class, "");
    JAXBElement<Integer> jaxbTurTrb = new JAXBElement(new QName("", "turTrb"), Integer.class, 0);
    JAXBElement<String> jaxbExpErp = new JAXBElement(new QName("", "expErp"), String.class, "");
    JAXBElement<String> jaxbCodOri = new JAXBElement(new QName("", "codOri"), String.class, "");
    JAXBElement<Integer> jaxbNumOrp = new JAXBElement(new QName("", "numOrp"), Integer.class, 0);
    JAXBElement<Integer> jaxbSeqEoq = new JAXBElement(new QName("", "seqEoq"), Integer.class, 0);
    JAXBElement<Integer> jaxbSeqRot = new JAXBElement(new QName("", "seqRot"), Integer.class, 0);
    JAXBElement<Integer> jaxbNumCad = new JAXBElement(new QName("", "numCad"), Integer.class, 0);

    public void limpar() {
        jaxbCodCre = new JAXBElement(new QName("", "codCre"), Integer.class, 0);
        jaxbCodEtg = new JAXBElement(new QName("", "codEtg"), Integer.class, 0);
        jaxbCodMtv = new JAXBElement(new QName("", "codMtv"), String.class, "");
        jaxbDatPar = new JAXBElement(new QName("", "datMov"), String.class, "");
        jaxbSeqMpr = new JAXBElement(new QName("", "sequenciaMovimento"), Integer.class, "");
        jaxbHorIni = new JAXBElement(new QName("", "horMov"), String.class, "");
        jaxbHorFim = new JAXBElement(new QName("", "horMov"), String.class, "");
        jaxbTurTrb = new JAXBElement(new QName("", "turTrb"), String.class, "");
        jaxbExpErp = new JAXBElement(new QName("", "expErp"), String.class, "");
        jaxbCodOri = new JAXBElement(new QName("", "codOri"), String.class, "");
        jaxbNumOrp = new JAXBElement(new QName("", "numOrp"), Integer.class, "");
        jaxbSeqEoq = new JAXBElement(new QName("", "seqEoq"), Integer.class, "");
        jaxbSeqRot = new JAXBElement(new QName("", "seqRot"), Integer.class, "");
        jaxbNumCad = new JAXBElement(new QName("", "numCad"), Integer.class, 0);
    }

    public boolean enviarApontamentoParadaSapiens() {
        try {
            br.com.senior.services.G5SeniorServices service = new br.com.senior.services.G5SeniorServices();
            br.com.senior.services.SapiensSyncnutriplanOp port = service.getSapiensSyncnutriplanOpPort();
            br.com.senior.services.OpApontamentoParadaIn parameters = new br.com.senior.services.OpApontamentoParadaIn();

            PreparedStatement ps = ConexaoMysql.getConexaoMySQL().prepareStatement(CONSULTARSQL);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                OpApontamentoParadaInApontar objApontar = new OpApontamentoParadaInApontar();

                jaxbCodCre.setValue(rs.getInt(2));
                jaxbCodEtg.setValue(rs.getInt(3));
                jaxbCodMtv.setValue(rs.getString(4));
                jaxbDatPar.setValue(rs.getString(5));
                jaxbSeqMpr.setValue(rs.getInt(6));
                jaxbHorIni.setValue(rs.getString(7));
                jaxbHorFim.setValue(rs.getString(8));
                jaxbTurTrb.setValue(rs.getInt(9));
                jaxbExpErp.setValue(rs.getString(10));
                jaxbCodOri.setValue(rs.getString(11));
                jaxbNumOrp.setValue(rs.getInt(12));
                jaxbSeqEoq.setValue(rs.getInt(13));
                jaxbSeqRot.setValue(rs.getInt(14));
                jaxbNumCad.setValue(rs.getInt(15));

                objApontar.setCodCre(jaxbCodCre);
                objApontar.setCodEtg(jaxbCodEtg);
                objApontar.setCodMtv(jaxbCodMtv);
                objApontar.setDatMov(jaxbDatPar);
                objApontar.setHorMov(jaxbHorIni);
                objApontar.setCodOri(jaxbCodOri);
                objApontar.setNumOrp(jaxbNumOrp);
                objApontar.setSequenciaMovimento(jaxbSeqMpr);
                objApontar.setSeqRot(jaxbSeqRot);
                objApontar.setNumCad(jaxbNumCad);
                objApontar.setCodOri(jaxbCodOri);
                parameters.getApontar().add(objApontar);
            }
            br.com.senior.services.OpApontamentoParadaOut result = port.apontamentoParada("integracao.op", "ERPintegracao.4651", 0, parameters);
            limpar();
            if (result.getErroExecucao().getValue() != null) {
                try {
                    System.out.println("" + result.getErroExecucao().getValue());
                    Notificacao.infoBox("ERRO AO ENVIAR AP. PARADA /129 APWS", false);
                    enviarEmail.enviaEmail(result.getErroExecucao().getValue(), "Erro WebService Motivo Parada");
                    return false;
                } catch (MessagingException e) {
                    return false;
                }
            } else {
                for (OpApontamentoParadaOutRespostaApontar itens : result.getRespostaApontar()) {
                    if ("OK".equals(itens.getMensagemRetorno().getValue())) {
                    }
                }
                return true;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            Notificacao.infoBox("ERRO AO CONSULTAR AP. PARADA /144APWS", true);
            try {
                enviarEmail.enviaEmail(e.getMessage(), "Erro ao consultar o movimento de apontamento de parada");
            } catch (MessagingException ex) {
                // Logger.getLogger(EventosDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }

    public String getTime() {
        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }
}
