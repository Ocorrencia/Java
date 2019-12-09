/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tela;

import dao.OrdemProducaoDao;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseMotionListener;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import net.miginfocom.swing.MigLayout;
import pojo.OrdemProducao;
import util.DadosRaspberry;
import util.Enums;
import util.Modal;
import util.Notificacao;
import util.Sincronizacao;

/**
 *
 * @author diogo.melo
 */
public class TelaAtualizacao extends JInternalFrame {

    URL urlTopo = getClass().getResource("/imagem/iconePrincipal.png");
    private final ImageIcon iconeprincipal = new ImageIcon(urlTopo);

    JButton btnAtualizarParcial = new JButton("ATUALIZAÇÃO PARCIAL");
    JButton btnAtualizarTotal = new JButton("ATUALIZAÇÃO TOTAL");
    JButton btnFechar = new JButton("FECHAR");
    private static TelaAtualizacao tela;
    MigLayout migLayout = new MigLayout();
    OrdemProducao ordemProducao = new OrdemProducao();
    OrdemProducaoDao ordemProducaoDao = new OrdemProducaoDao(ordemProducao);

    public static TelaAtualizacao getTela() {
        if (tela == null) {
            tela = new TelaAtualizacao();
            tela.addInternalFrameListener(new InternalFrameAdapter() {
                @Override
                public void internalFrameClosed(InternalFrameEvent e) {
                    if (tela != null) {
                        TelaSistema.jdp.remove(tela);
                    }
                    tela = null;
                }
            });
            TelaSistema.jdp.add(tela);
        }
        TelaSistema.jdp.setSelectedFrame(tela);
        if (Modal.telaPai == null) {
            Modal.getTela(tela).moveToFront();
        } else {
            Modal.telaPai = tela;
        }
        TelaSistema.jdp.moveToFront(tela);
        TelaSistema.centraliza(tela);
        return tela;
    }

    public TelaAtualizacao() {
        super("Tela de Atualização dos Dados", false, false, false, false);
        this.setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
        montarTela();
        listener();
    }

    private void montarTela() {
        setVisible(true);
        this.setSize(new Dimension(630, 240));
        this.setLayout(migLayout);
        this.setFrameIcon(iconeprincipal);

        btnAtualizarTotal.setFont(new Font("Arial", Font.BOLD, 32));
        btnAtualizarParcial.setFont(new Font("Arial", Font.BOLD, 32));
        btnFechar.setFont(new Font("Arial", Font.BOLD, 32));

        btnAtualizarTotal.setPreferredSize(new Dimension(630, 80));
        btnAtualizarParcial.setPreferredSize(new Dimension(630, 80));
        btnFechar.setPreferredSize(new Dimension(630, 80));

        this.add(btnAtualizarTotal, "span, wrap");
        this.add(btnAtualizarParcial, "span, wrap");
        this.add(btnFechar, "span");

        travarTela();
    }

    public void mensagemErroSincronizacao(String msg) {
        Notificacao.infoBox(msg, false);
        TelaLoading.getTela("").dispose();
        Modal.getTela(null).dispose();
    }

    private void listener() {
        btnAtualizarParcial.addActionListener((ActionEvent e) -> {
            new Thread(new ThreadAtualizarParcial()).start();

        });
        btnAtualizarTotal.addActionListener((ActionEvent e) -> {
            new Thread(new ThreadAtualizarTotal()).start();
        });

        btnFechar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tela.dispose();
                Modal.getTela(tela).dispose();
            }
        });
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

    public class ThreadAtualizarParcial implements Runnable {

        @Override
        public void run() {
            TelaAtualizacao.getTela();
            TelaLoading tela1 = TelaLoading.getTela("Carrengando dados via WEBSERVICE...");
            Modal.getTela(tela1).setVisible(true);
            tela1.moveToFront();
            Modal.telaPai = tela1;

            if (!Sincronizacao.sincOrdemProducao(true)) {
                mensagemErroSincronizacao("Ocorreu um erro ao sincronizar a ordem de produção");
            } else if (!Sincronizacao.sincPrioridade()) {
                mensagemErroSincronizacao("Ocorreu um erro ao sincronizar a prioridade");
            } else if (true) {
                Notificacao.infoBox("SINCRONIZADA FINALIZADA", true);
                tela1.getTela("").dispose();
                Modal.getTela(tela1).dispose();
                tela.dispose();
                ordemProducao.setNumOrp(TelaOP.getTela().getOrdemProducao().getNumOrp());
                if (ordemProducao.getNumOrp() != null) {
                    ordemProducao = ordemProducaoDao.consultaOrdemProducao(ordemProducao.getNumOrp());
                    Enums.REFUGOSJUSTIFICADOS = (int) Math.round(ordemProducao.getQtdRfg());
                    Enums.REFUGOSNAOIDENTIFICADOS = (int) Math.round(ordemProducao.getQtdRfgn());
                    DadosRaspberry.QUANTIDADEPRODUZIDA = (int) Math.round(ordemProducao.getQtdRe1());
                }
                TelaOP.getTela().setOrdemProducao(ordemProducao);
                TelaOP.getTela().campoOp.setText(ordemProducao.getCodDer() + " - " + ordemProducao.getCodPro() + " - " + ordemProducao.getDesPro() + " " + ordemProducao.getDesDer());
                TelaOP.getTela().setGUI();
            }
        }
    }

    public class ThreadAtualizarTotal implements Runnable {

        @Override
        public void run() {
            TelaLoading tela1 = TelaLoading.getTela("Carrengando dados via WEBSERVICE...");
            Modal.getTela(tela1).setVisible(true);
            tela1.moveToFront();
            Modal.telaPai = tela1;
            if (!Sincronizacao.sincCentroRecurso()) {
                mensagemErroSincronizacao("Ocorreu um erro ao sincronizar o centro de recurso");
            } else if (!Sincronizacao.sincFichaTecnica()) {
                mensagemErroSincronizacao("Ocorreu um erro ao sincronizar a ficha técnica");
            } else if (!Sincronizacao.sincTurno()) {
                mensagemErroSincronizacao("Ocorreu um erro ao sincronizar o turno");
            } else if (!Sincronizacao.sintTurnoTrabalho()) {
                mensagemErroSincronizacao("Ocorreu um erro ao sincronizar o turno de trabalho");
            } else if (!Sincronizacao.sincOperadores()) {
                mensagemErroSincronizacao("Ocorreu um erro ao sincronizar os operadores");
            } else if (!Sincronizacao.sincMotivoParada()) {
                mensagemErroSincronizacao("Ocorreu um erro ao sincronizar os motivo de parada");
            } else if (!Sincronizacao.sincDefeito()) {
                mensagemErroSincronizacao("Ocorreu um erro ao sincronizar os defeitos");
            } else if (!Sincronizacao.sincOrdemProducao(true)) {
                mensagemErroSincronizacao("Ocorreu um erro ao sincronizar a ordem de produção");
            } else if (!Sincronizacao.sincPrioridade()) {
                mensagemErroSincronizacao("Ocorreu um erro ao sincronizar a prioridade");
            } else if (true) {
                Notificacao.infoBox("SINCRONIZADA FINALIZADA", true);
                tela1.getTela("").dispose();
                Modal.getTela(tela1).dispose();
                tela.dispose();
            }
        }
    }
}
