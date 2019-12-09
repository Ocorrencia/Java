/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pojoWebService;

import br.com.senior.services.OpApontamentoProducaoInApontar;
import br.com.senior.services.OpApontamentoProducaoOutRespostaApontar;
import dao.RefugoDao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.NumberFormat;
import javax.mail.MessagingException;
import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;
import pojo.Refugo;
import util.ConexaoMysql;
import util.EnviarEmail;
import util.Notificacao;
import util.Consulta;

/**
 *
 * @author diogo.melo
 */
public class MovimentoOrdemProducaoWebService {
    
    EnviarEmail enviarEmail = new EnviarEmail();
    String codCre;
    Refugo refugo;
    NumberFormat nf = NumberFormat.getInstance();
    double number;
    RefugoDao refugoDao = new RefugoDao();
    
    private final String CONSULTARSQL = "SELECT \n"
            + "    eoq.CODORI,\n"
            + "    eoq.NUMORP,\n"
            + "    eoq.CODETG,\n"
            + "    eoq.SEQROT,\n"
            + "    eoq.DATMOV,\n"
            + "    eoq.HORMOV,\n"
            + "    eoq.NUMCAD,\n"
            + "    eoq.QTDRE1,\n"
            + "    eoq.QTDRFG,\n"
            + "    eoq.TURTRB,\n"
            + "    eoq.SEQMOV,\n"
            + "    eoq.CODDFT\n"
            + "FROM\n"
            + "    nutri_op.op900eoq eoq\n"
            + "        INNER JOIN\n"
            + "    nutri_op.op900qdo qdo ON (eoq.NUMORP = qdo.NUMORP)\n"
            + "WHERE\n"
            + "    eoq.EXPERP = 0 AND eoq.QTDRE1 > 0 \n"
            + "UNION ALL SELECT \n"
            + "    eoq.CODORI,\n"
            + "    eoq.NUMORP,\n"
            + "    eoq.CODETG,\n"
            + "    eoq.SEQROT,\n"
            + "    eoq.DATMOV,\n"
            + "    eoq.HORMOV,\n"
            + "    eoq.NUMCAD,\n"
            + "    eoq.QTDRE1,\n"
            + "    eoq.QTDRFG,\n"
            + "    eoq.TURTRB,\n"
            + "    eoq.SEQMOV,\n"
            + "    eoq.CODDFT\n"
            + "FROM\n"
            + "    nutri_op.op900eoq eoq\n"
            + "        INNER JOIN\n"
            + "    nutri_op.op900qdo qdo ON (eoq.NUMORP = qdo.NUMORP)\n"
            + "WHERE\n"
            + "    eoq.EXPERP = 0 AND eoq.QTDRFG > 0 AND eoq.CODDFT IS NOT NULL";
    
    JAXBElement<String> jaxbCodOri = new JAXBElement(new QName("", "codOri"), String.class, "");
    JAXBElement<Integer> jaxbNumOrp = new JAXBElement(new QName("", "numOrp"), Integer.class, 0);
    JAXBElement<Integer> jaxbCodEtg = new JAXBElement(new QName("", "codEtg"), Integer.class, 0);
    JAXBElement<Integer> jaxbSeqRot = new JAXBElement(new QName("", "seqRot"), Integer.class, 0);
    JAXBElement<Integer> laxbNumcad = new JAXBElement(new QName("", "numCad"), Integer.class, 0);
    JAXBElement<Integer> jaxbturTrb = new JAXBElement(new QName("", "turTrb"), Integer.class, 0);
    JAXBElement<Integer> jaxbSeqMov = new JAXBElement(new QName("", "sequenciaMovimento"), Integer.class, 0);
    JAXBElement<String> jaxbDatMov = new JAXBElement(new QName("", "datMov"), String.class, "");
    JAXBElement<String> jaxbHorMov = new JAXBElement(new QName("", "horMov"), String.class, "");
    JAXBElement<Double> jaxbQtdRe1 = new JAXBElement(new QName("", "qtdRe1"), Double.class, 0);
    JAXBElement<Double> jaxbQtdRfg = new JAXBElement(new QName("", "qtdRfg"), Double.class, 0);
    JAXBElement<String> jaxbcodDft = new JAXBElement(new QName("", "codDft"), String.class, "");
    
    public void limpar() {
        jaxbCodOri = new JAXBElement(new QName("", "codOri"), String.class, "");
        jaxbNumOrp = new JAXBElement(new QName("", "numOrp"), Integer.class, 0);
        jaxbCodEtg = new JAXBElement(new QName("", "codEtg"), Integer.class, 0);
        jaxbSeqRot = new JAXBElement(new QName("", "seqRot"), Integer.class, 0);
        laxbNumcad = new JAXBElement(new QName("", "numCad"), Integer.class, 0);
        jaxbturTrb = new JAXBElement(new QName("", "turTrb"), Integer.class, 0);
        jaxbSeqMov = new JAXBElement(new QName("", "sequenciaMovimento"), Integer.class, 0);
        jaxbDatMov = new JAXBElement(new QName("", "datMov"), String.class, "");
        jaxbHorMov = new JAXBElement(new QName("", "horMov"), String.class, "");
        jaxbcodDft = new JAXBElement(new QName("", "codDft"), String.class, "");
        jaxbQtdRe1 = new JAXBElement(new QName("", "qtdRe1"), Double.class, 0);
        jaxbQtdRfg = new JAXBElement(new QName("", "qtdRfg"), Double.class, 0);
    }
    
    public boolean enviarMovimentoOrdemProducaoSapiens() {
        try {
            
            br.com.senior.services.G5SeniorServices service = new br.com.senior.services.G5SeniorServices();
            br.com.senior.services.SapiensSyncnutriplanOp port = service.getSapiensSyncnutriplanOpPort();
            br.com.senior.services.OpApontamentoProducaoIn parameters = new br.com.senior.services.OpApontamentoProducaoIn();
            
            PreparedStatement ps = ConexaoMysql.getConexaoMySQL().prepareStatement(CONSULTARSQL);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                OpApontamentoProducaoInApontar objApontar = new OpApontamentoProducaoInApontar();
                jaxbCodOri.setValue(rs.getString(1));
                jaxbNumOrp.setValue(rs.getInt(2));
                jaxbCodEtg.setValue(rs.getInt(3));
                jaxbSeqRot.setValue(rs.getInt(4));
                jaxbDatMov.setValue(rs.getString(5));
                jaxbHorMov.setValue(rs.getString(6));
                laxbNumcad.setValue(rs.getInt(7));
                jaxbQtdRe1.setValue(rs.getDouble(8));
                jaxbQtdRfg.setValue(rs.getDouble(9));
                Refugo.setQuantidade(0);
                jaxbturTrb.setValue(rs.getInt(10));
                jaxbSeqMov.setValue(rs.getInt(11));
                jaxbcodDft.setValue(rs.getString(12));
                Refugo.setCodDft(0);
                
                objApontar.setCodOri(jaxbCodOri);
                objApontar.setNumOrp(jaxbNumOrp);
                objApontar.setCodEtg(jaxbCodEtg);
                objApontar.setSeqRot(jaxbSeqRot);
                objApontar.setDatMov(jaxbDatMov);
                objApontar.setHorMov(jaxbHorMov);
                objApontar.setNumCad(laxbNumcad);
                objApontar.setQtdRe1(jaxbQtdRe1);
                objApontar.setQtdRfg(jaxbQtdRfg);
                objApontar.setTurTrb(jaxbturTrb);
                objApontar.setCodDft(jaxbcodDft);
                objApontar.setSequenciaMovimento(jaxbSeqMov);
                
                parameters.getApontar().add(objApontar);
                limpar();
            }
            br.com.senior.services.OpApontamentoProducaoOut result = port.apontamentoProducao("integracao.op", "ERPintegracao.4651", 0, parameters);
            if (result.getErroExecucao().getValue() != null) {
                try {
                    Notificacao.infoBox(result.getErroExecucao().getValue(), true);
                    enviarEmail.enviaEmail(result.getErroExecucao().getValue(), "Erro WebService Movimento Ordem Produção");
                    return false;
                } catch (MessagingException e) {
                    return false;
                }
            } else {
                for (OpApontamentoProducaoOutRespostaApontar itens : result.getRespostaApontar()) {
                    if ("OK".equals(itens.getMensagemRetorno().getValue())) {
                        Consulta.UPDATE("nutri_op.op900eoq", "EXPERP = 1", "SEQMOV =  " + itens.getSequenciaMovimento().getValue() + " ");
                    }
                }
                return true;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            Notificacao.infoBox("ERRO AO CONSULTAR MOV. DE PROD. /167MOPWS", true);
            try {
                enviarEmail.enviaEmail(e.getMessage(), "Erro WebService Movimento Ordem Produção");
            } catch (MessagingException ex) {
                // Logger.getLogger(EventosDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }
}
