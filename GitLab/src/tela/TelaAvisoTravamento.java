/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tela;

import componente.MensagensSistema;
import dao.ApontamentoParadaDao;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseMotionListener;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import net.miginfocom.swing.MigLayout;
import pojo.ApontamentoParada;
import pojoWebService.ApontamentoParadaWebService;
import util.Consulta;
import util.Enums;
import util.ListModelMotivo;
import util.Modal;
import util.Notificacao;

/**
 *
 * @author diogo.melo
 */
public class TelaAvisoTravamento extends JInternalFrame {

    URL urlTopo = getClass().getResource("/imagem/iconePrincipal.png");
    ImageIcon iconeprincipal = new ImageIcon(urlTopo);

    JLabel lbParada = new JLabel("PARADA DE MÁQUINA");
    JLabel lbTempo = new JLabel(getTime());
    public JLabel lbTipo = new JLabel("");

    URL urlTimeWarning = getClass().getResource("/imagens/icons8-expired_1.png");
    ImageIcon icoWarning = new ImageIcon(urlTimeWarning);

    JLabel iconeWarning = new JLabel(icoWarning);
    JButton btnIniciar = new JButton("INICIAR");
    JButton btnVoltar = new JButton("VOLTAR");
    public static TelaAvisoTravamento telaAviso;
    ListModelMotivo listMotivo = new ListModelMotivo();
    ApontamentoParadaWebService apontamentoParadaWebService = new ApontamentoParadaWebService();
    ApontamentoParada ap = new ApontamentoParada();
    ApontamentoParadaDao apDao = new ApontamentoParadaDao();

    public TelaAvisoTravamento() {

        JPanel painelInfo = new JPanel();
        setVisible(true);
        setResizable(false);

        btnIniciar.setPreferredSize(new Dimension(700, 60));
        btnVoltar.setPreferredSize(new Dimension(700, 60));
        lbParada.setFont(new Font("Arial", Font.BOLD, 42));
        lbTempo.setFont(new Font("Arial", Font.BOLD, 30));
        lbTipo.setFont(new Font("Arial", Font.BOLD, 30));
        btnIniciar.setFont(new Font("Arial", Font.BOLD, 40));
        btnVoltar.setFont(new Font("Arial", Font.BOLD, 40));
        painelInfo.setLayout(new MigLayout());
        this.setFrameIcon(iconeprincipal);
        painelInfo.add(iconeWarning, "align center,wrap");
        painelInfo.add(lbParada, "align center,wrap");
        painelInfo.add(lbTempo, "align center,wrap");
        painelInfo.add(lbTipo, "align center, wrap");
        painelInfo.add(btnIniciar, "align center, wrap");
        painelInfo.add(btnVoltar, "align center");
        add(painelInfo);
        listener();
        travarTela();
        setTitle("Aviso de Travamento Operacional");
        setPreferredSize(new Dimension(700, 400));
        pack();
    }

    public void travarTela() {
        BasicInternalFrameUI ui = (javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI();
        Component cp = ui.getNorthPane();
        MouseMotionListener[] actions
                = (MouseMotionListener[]) cp.getListeners(MouseMotionListener.class);
        for (int i = 0; i < actions.length; i++) {
            cp.removeMouseMotionListener(actions[i]);
        }
    }

    public static TelaAvisoTravamento getTela() {
        if (telaAviso == null) {
            telaAviso = new TelaAvisoTravamento();
            telaAviso.addInternalFrameListener(new InternalFrameAdapter() {
                @Override
                public void internalFrameClosed(InternalFrameEvent e) {
                    TelaSistema.jdp.remove(telaAviso);
                    telaAviso = null;
                }
            });
            TelaSistema.jdp.add(telaAviso);
        }
        TelaSistema.jdp.setSelectedFrame(telaAviso);
        TelaSistema.jdp.moveToFront(telaAviso);
        TelaSistema.centraliza(telaAviso);
        if (Modal.telaPai == null) {
            Modal.getTela(telaAviso).moveToFront();
        } else {
            Modal.telaPai = telaAviso;
        }
        Enums.setSTATUSTELA(Enums.APONTAMENTODEPARADA);
        return telaAviso;
    }

    public String getDate() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        return dateFormat.format(date);
    }

    public String getTime() {
        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }

    public void listener() {
        btnVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                TelaApontamentoParada.getTela();
            }
        });

        btnIniciar.addActionListener((ActionEvent e) -> {
            ArrayList<String> horas = Consulta.CONSULTAARRAYSTRING("nutri_op.op930mpr", "HORINI", "1 = 1");
            String horaAtual = getTime();
            for (String hora : horas) {
                if (hora.substring(0, 5).equals(horaAtual.substring(0, 5))) {
                    System.out.println("sim");
                    Notificacao.infoBox("EXISTE AP. DE PARADA NESTE MINUTO", false);
                    return;
                }
            }
            String lista = Consulta.CONSULTASTRING("nutri_op.op930mpr mpr INNER JOIN nutri_op.op018mtv mtv ON (mpr.CODMTV = mtv.CODMTV)", "CONCAT(mtv.CODMTV,' - ',DESMTV) AS MOTIVO", "HORFIM = ''");
            if (!lista.equals("VAZIO")) {
                MensagensSistema.MensagemAtencao("Existem apontamentos de parada não finalizado \n " + lista + "");
                return;
            }
            dispose();
            Modal.getTela(telaAviso).dispose();

            TelaOP tela = TelaOP.getTela();
            tela.labelOperador.setText(Enums.NOMEOPERADOR);
            tela.operadorPOJO.setNumCad(Integer.parseInt(Enums.CODIGOOPERADOR));
            setPersistencia();
            TelaAvisoInicioProducao telaAviso = TelaAvisoInicioProducao.getTela();
            Modal.getTela(telaAviso);
            telaAviso.moveToFront();
            telaAviso.setAp(ap);
        });
    }

    public void setPersistencia() {

        Integer codCre = Consulta.CONSULTAINT("nutri_op.op000maq", "CODCRE", "1 = 1");
        Integer codEtg = Consulta.CONSULTAINT("nutri_op.op725cre", "CODETG", "CODCRE = " + codCre + "");
        Integer numOrp = Consulta.CONSULTAINT("nutri_op.op900qdo", "NUMORP", "STATUS = 1");
        Integer seqRot = Consulta.CONSULTAINT("nutri_op.op900qdo", "SEQROT", "STATUS = 1");
        Integer codOri = Consulta.CONSULTAINT("nutri_op.op900qdo", "CODORI", "STATUS = 1");
        Enums.SEQUENCIAAPONTAMENTO = Consulta.CONSULTAINT("nutri_op.op000seq", "VLRSEQAP", "IDSEQ = 1") + 1;
        Consulta.UPDATE("nutri_op.op000seq", "VLRSEQAP = " + Enums.SEQUENCIAAPONTAMENTO + "", "IDSEQ = 1");

        ap.setCodCre(codCre);
        ap.setCodEtg(codEtg);
        ap.setCodMtv(listMotivo.getElementoConcatenado(lbTipo.getText()));
        ap.setDatPar(getDate());
        ap.setHorIni(getTime());
        ap.setHorFim("");
        ap.setNumOrp(numOrp);
        ap.setSeqMpr(Enums.SEQUENCIAAPONTAMENTO);
        ap.setTurTrb(Enums.CODIGOTURNO);
        ap.setSeqRot(seqRot);
        ap.setCodOri(codOri + "");
        ap.setNumCad(Integer.parseInt(Enums.CODIGOOPERADOR));
        incluir();
    }

    private void incluir() {
        apDao.setApontamentoParada(ap);
        apDao.INCLUIR();
    }
}
