/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.net.Authenticator;
import java.util.Date;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import util.Notificacao;

/**
 *
 * @author diogo.melo
 */
public class EnviarEmail extends Authenticator {

    Session session = null;

    public void enviaEmail(String bodyEmail, String assunto) throws MessagingException {
        try {
            Properties props = new Properties();
            props.put("mail.smtp.host", "webmail.nutriplan.com.br");
            session = session.getInstance(props, null);
            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress("apontamento@nutriplan.com.br"));
            msg.setRecipient(Message.RecipientType.TO, new InternetAddress("diogo.melo@nutriplan.com.br"));
            msg.setSubject(assunto);
            msg.setContent(bodyEmail, "text/plain ; charset=UTF-8");
            msg.setSentDate(new Date());
            Transport transport = session.getTransport("smtp");
            transport.connect("webmail.nutriplan.com.br", "apontamento", "WEBnutri2017");
            if (transport.isConnected()) {
                Transport.send(msg);
            } else {
                Notificacao.infoBox("ERRO NA CLASSE DE EMAIL", false);
            }
        } catch (MessagingException e) {
        }
    }
}
