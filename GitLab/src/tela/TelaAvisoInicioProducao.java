/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tela;

import componente.MeuComboBox;
import dao.ApontamentoParadaDao;
import dao.MovimentoOrdemProducaoDao;
import dao.OrdemProducaoDao;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseMotionListener;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
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
import pojo.MovimentoOrdemProducao;
import pojo.OrdemProducao;
import pojoWebService.ApontamentoParadaWebService;
import static tela.TelaOP.tela;
import util.Consulta;
import util.DadosRaspberry;
import util.Enums;
import util.Modal;
import util.Notificacao;
import util.Sincronizacao;

/**
 *
 * @author diogo.melo
 */
public class TelaAvisoInicioProducao extends JInternalFrame {

    URL urlTopo = getClass().getResource("/imagem/iconePrincipal.png");
    ImageIcon iconeprincipal = new ImageIcon(urlTopo);

    URL urlRefresh = getClass().getResource("/imagens/icons8-available-updates-40.png");
    ImageIcon iconeRefresh = new ImageIcon(urlRefresh);

    JLabel lbParada = new JLabel("INÍCIO DE PRODUÇÃO");
    JLabel lbTempo = new JLabel(getTime());
    JLabel lbTipo = new JLabel("");
    JLabel lbOrdemProducao = new JLabel("ORDEM DE PRODUÇÃO");

    URL urlTimeWarning = getClass().getResource("/imagens/nutriplan-superior-logo-80x80.png");
    ImageIcon icoWarning = new ImageIcon(urlTimeWarning);

    JLabel iconeWarning = new JLabel(icoWarning);
    JButton btnIniciar = new JButton("INICIAR");
    JButton btnParada = new JButton("PARADA DE MÁQUINA");
    JButton btnAtualizar = new JButton(iconeRefresh);
    public static TelaAvisoInicioProducao telaInicio;

    MeuComboBox comboOp = new MeuComboBox("SELECT NUMORP ,CONCAT(CODPRO, ' - ',  CODDER, ' - ', DESPRO, ' ', DESDER) as PRODUTO  FROM nutri_op.op900qdo WHERE STATUS <> 3 order by numpri", true, "OPS");

    OrdemProducao ordemProducao = new OrdemProducao();
    OrdemProducaoDao ordemProducaoDao = new OrdemProducaoDao(ordemProducao);

    MovimentoOrdemProducao mvp = new MovimentoOrdemProducao();
    MovimentoOrdemProducaoDao mvpDao = new MovimentoOrdemProducaoDao(mvp);

    ApontamentoParada ap = new ApontamentoParada();
    ApontamentoParadaDao apDao = new ApontamentoParadaDao();
    String sequenciaUltimaParada;

    ApontamentoParadaWebService apWebService = new ApontamentoParadaWebService();
    public long seg = 1;
    public long minuto = 0;
    public long horas = 0;

    public TelaAvisoInicioProducao() {
        JPanel painelInfo = new JPanel();
        setVisible(true);
        setResizable(false);
        setTitle("AVISO");

        lbParada.setFont(new Font("Arial", Font.BOLD, 42));
        lbTempo.setFont(new Font("Arial", Font.BOLD, 30));
        lbTipo.setFont(new Font("Arial", Font.BOLD, 40));
        lbOrdemProducao.setFont(new Font("Arial", Font.BOLD, 30));
        btnIniciar.setFont(new Font("Arial", Font.BOLD, 40));
        btnParada.setFont(new Font("Arial", Font.BOLD, 40));

        painelInfo.setLayout(new MigLayout());
        this.setFrameIcon(iconeprincipal);
        painelInfo.add(iconeWarning, "align center,span,wrap");
        painelInfo.add(lbParada, "align center,span,wrap");
        painelInfo.add(lbTempo, "align center,span,wrap");
        painelInfo.add(new JLabel(" "), "align center,span,wrap");
        painelInfo.add(lbOrdemProducao, "align center,span, wrap");
        painelInfo.add(comboOp, "align center");
        painelInfo.add(btnAtualizar, "align center,wrap");
        painelInfo.add(lbTipo, "align center,span, wrap");
        painelInfo.add(btnIniciar, "align center,span, wrap");
        painelInfo.add(btnParada, "align center,span");
        add(painelInfo);
        listener();
        travarTela();
        setTitle("INÍCIO DE PRODUÇÃO");
        comboOp.setFont(new Font("Arial", Font.BOLD, 30));
        comboOp.setPreferredSize(new Dimension(660, 60));
        comboOp.setMaximumSize(new Dimension(660, 60));
        btnIniciar.setPreferredSize(new Dimension(740, 60));
        btnParada.setPreferredSize(new Dimension(740, 60));
        btnAtualizar.setPreferredSize(new Dimension(40, 60));
        comboOp.setSelectedIndex(-1);
        verificarOps();
        verificarParada();
        pack();
        this.repaint();
        cronos();
    }

    public ApontamentoParada getAp() {
        return ap;
    }

    public void setAp(ApontamentoParada ap) {
        this.ap = ap;
    }

    private void verificarOps() {
        String numOrp = Consulta.CONSULTASTRING(" nutri_op.op900qdo qdo INNER JOIN nutri_op.op930mpr mpr ON mpr.NUMORP = qdo.NUMORP", "qdo.NUMORP", "qdo.STATUS = '1' AND mpr.HORFIM = '' AND CODMTV <> 1001");
        if (!"VAZIO".equals(numOrp)) {
            comboOp.setSql("SELECT NUMORP ,CONCAT(CODPRO, ' - ', CODDER, ' - ', DESPRO, ' ', DESDER) as PRODUTO FROM nutri_op.op900qdo WHERE NUMORP = " + numOrp + " AND STATUS <> 3 order by numpri");
        } else {
            if (!"VAZIO".equals(numOrp)) {
                Consulta.UPDATE("nutri_op.op900qdo", "STATUS = 0", "NUMORP = " + numOrp + "");
            }
            comboOp.setSql("SELECT NUMORP ,CONCAT(CODPRO, ' - ',  CODDER, ' - ', DESPRO, ' ', DESDER) as PRODUTO  FROM nutri_op.op900qdo WHERE STATUS <> 3 order by numpri");
        }
    }

    public void cronos() {
        final long time = 1000; // a cada X ms
        Timer timer = new Timer();

        TimerTask tarefa = new TimerTask() {
            public void run() {
                try {
                    seg++;
                    if (seg >= 59) {
                        seg = 00;
                        minuto++;
                    }
                    if (minuto >= 59) {
                        minuto = 00;
                        horas++;
                    }
                    lbTempo.setText((horas < 10 ? "0" + horas : horas) + ":" + (minuto < 10 ? "0" + minuto : "") + ":" + (seg < 10 ? "0" + seg : seg));
                } catch (Exception e) {
                }
            }
        };
        timer.scheduleAtFixedRate(tarefa, time, time);
    }

    public void travarTela() {
        BasicInternalFrameUI ui = (javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI();
        Component cp = ui.getNorthPane();
        MouseMotionListener[] actions
                = (MouseMotionListener[]) cp.getListeners(MouseMotionListener.class);
        for (MouseMotionListener action : actions) {
            cp.removeMouseMotionListener(action);
        }
    }

    public static TelaAvisoInicioProducao getTela() {
        if (telaInicio == null) {
            telaInicio = new TelaAvisoInicioProducao();
            telaInicio.addInternalFrameListener(new InternalFrameAdapter() {
                @Override
                public void internalFrameClosed(InternalFrameEvent e) {
                    TelaSistema.jdp.remove(telaInicio);
                    telaInicio = null;

                }
            });
            TelaSistema.jdp.add(telaInicio);
        }
        TelaSistema.jdp.setSelectedFrame(telaInicio);
        TelaSistema.jdp.moveToFront(telaInicio);
        TelaSistema.centraliza(telaInicio);
        if (Modal.telaPai == null) {
            Modal.getTela(telaInicio).moveToFront();
        } else {
            Modal.telaPai = telaInicio;
        }
        Enums.setSTATUSTELA(Enums.AVISOINICIOPRODUCAO);
        return telaInicio;
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
        btnAtualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Sincronizacao.sincOrdemProducao(true);
                comboOp.setSql("SELECT NUMORP ,CONCAT(CODPRO , ' - ', CODDER, ' - ', DESPRO, ' ', DESDER) as PRODUTO  FROM nutri_op.op900qdo WHERE STATUS <> 3 order by numpri");
            }
        });
        btnParada.addActionListener((ActionEvent e) -> {
            String lista = Consulta.CONSULTASTRING("nutri_op.op930mpr mpr INNER JOIN nutri_op.op018mtv mtv ON (mpr.CODMTV = mtv.CODMTV)", "CONCAT(mtv.CODMTV,' - ',DESMTV) AS MOTIVO", "HORFIM = ''");
            if (!lista.equals("VAZIO")) {
                Notificacao.infoBox("EXISTEM AP. EM ABERTO \n " + lista + "", false);
                return;
            }

            Modal.getTela(tela).setVisible(true);
            TelaApontamentoParada.getTela();
        });
        btnIniciar.addActionListener((ActionEvent e) -> {
            if (comboOp.getSelectedIndex() == -1) {
                Notificacao.infoBox("SELECIONE UMA ORDEM DE PROD.", false);
                return;
            }
            if (ap.getCodCre() != null) {
                ap.setHorIni(getTime());
                ap.setDatPar(getDate());
                apDao.setApontamentoParada(ap);
                //TODO FAZER TRATAMENTO DE RETORNO
                verificarParada();
                if (sequenciaUltimaParada != null) {
                    apWebService.enviarApontamentoParadaSapiens();
                    Consulta.UPDATE("nutri_op.op930mpr", "HORFIM = '" + getTime() + "', EXPERP = 1", "HORFIM = ''");
                } else {
                    apDao.INCLUIR();
                }
            }

            dispose();
            Modal.getTela(telaInicio).setVisible(false);
            Enums.setSTATUSTELA(Enums.LIBERADOPRODUCAO);

            ordemProducao.setNumOrp((Integer) comboOp.getValor());
            if (ordemProducao.getNumOrp() != null) {
                Consulta.UPDATE("nutri_op.op900qdo", "STATUS = 1", "NUMORP = " + ordemProducao.getNumOrp() + "");
                ordemProducao = ordemProducaoDao.consultaOrdemProducao(ordemProducao.getNumOrp());
                DadosRaspberry.SEQUENCIA = Consulta.CONSULTAINT("nutri_op.op900eoq eoq INNER JOIN nutri_op.op900qdo qdo ON (qdo.NUMORP = eoq.NUMORP) ", "eoq.SEQMOV", "qdo.STATUS <> 3 ORDER BY SEQMOV DESC");
                Enums.REFUGOSJUSTIFICADOS = (int) Math.round(ordemProducao.getQtdRfg());
                Enums.REFUGOSNAOIDENTIFICADOS = (int) Math.round(ordemProducao.getQtdRfgn());
                DadosRaspberry.QUANTIDADEPRODUZIDA = (int) Math.round(ordemProducao.getQtdRe1());
            }

            TelaOP.getTela().campoOp.setText(ordemProducao.getCodPro() + " - " + ordemProducao.getCodDer() + " - " + ordemProducao.getDesPro() + " " + ordemProducao.getDesDer());
            TelaOP.tela.setMvp(mvp);
            TelaOP.tela.setOrdemProducao(ordemProducao);
        });
    }

    /* private String getDateTime() {
        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }

    private String getDate() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        return dateFormat.format(date);
    }*/
    private void verificarParada() {
        sequenciaUltimaParada = Consulta.CONSULTASTRING("nutri_op.op930mpr", "MAX(SEQMPR)", "HORFIM = '' AND EXPERP = 0");
        if (sequenciaUltimaParada != null) {
            ap = apDao.consultar(sequenciaUltimaParada);
            btnIniciar.setText("FINALIZAR");
            lbParada.setText("FINALIZAR PARADA");
            btnParada.setEnabled(false);
        } else {
            btnParada.setEnabled(true);
            btnIniciar.setText("INICIAR");
            lbParada.setText("INÍCIO DE PRODUÇÃO");
        }
    }
}
