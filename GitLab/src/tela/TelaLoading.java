/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tela;

import com.alee.laf.progressbar.WebProgressBar;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.MouseMotionListener;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import net.miginfocom.swing.MigLayout;
import util.Enums;
import util.Modal;

/**
 *
 * @author diogo.melo
 */
public class TelaLoading extends JInternalFrame implements InternalFrameListener {

    URL urlTopo = getClass().getResource("/imagem/iconePrincipal.png");
    ImageIcon iconeprincipal = new ImageIcon(urlTopo);

    JLabel lbParada = new JLabel("INÍCIO DE PRODUÇÃO");
    JLabel lbTempo = new JLabel("00:00:00");
    JLabel lbTipo = new JLabel("");

    URL urlTimeWarning = getClass().getResource("/imagens/icons8-potted-plant.png");
    ImageIcon icoWarning = new ImageIcon(urlTimeWarning);

    JLabel iconeWarning = new JLabel(icoWarning);
    JButton btnIniciar = new JButton("INICIAR");
    public static TelaLoading telaInicio;

    WebProgressBar load = new WebProgressBar();
    static TelaLoading tela;
    static Modal modal = new Modal(tela);

    public TelaLoading(String mensagem) {
        setTitle("LOADING..");
        JPanel painelInfo = new JPanel();
        setVisible(true);
        setResizable(false);
        btnIniciar.setPreferredSize(new Dimension(80, 40));

        lbParada.setFont(new Font("Arial", Font.BOLD, 20));
        lbTempo.setFont(new Font("Arial", Font.BOLD, 16));
        lbTipo.setFont(new Font("Arial", Font.BOLD, 16));

        load.setIndeterminate(true);
        load.setStringPainted(true);
        load.setString(mensagem);

        painelInfo.setLayout(new MigLayout());
        this.setFrameIcon(iconeprincipal);
        painelInfo.add(load, "align center,wrap");
        add(painelInfo);
        listener();
        travarTela();
        pack();
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

    public static TelaLoading getTela(String mensagem) {
        if (telaInicio == null) {
            telaInicio = new TelaLoading(mensagem);
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
            modal.getTela(tela).moveToFront();
        } else {
            modal.telaPai = tela;
        }
        // Enums.setSTATUSTELA(Enums.MENU);
        return telaInicio;
    }

    public static void fechar() {
        telaInicio.dispose();
    }

    public void listener() {
        btnIniciar.addActionListener((ActionEvent e) -> {
            dispose();
            Modal.getTela(telaInicio).setVisible(false);
            //   Cronometro.iniciaCronometro(10000);
            Enums.setSTATUSTELA(Enums.LIBERADOPRODUCAO);
        });
    }

    @Override
    public void internalFrameOpened(InternalFrameEvent e) {
    }

    @Override
    public void internalFrameClosing(InternalFrameEvent e) {
    }

    @Override
    public void internalFrameClosed(InternalFrameEvent e) {
        modal.dispose();
    }

    @Override
    public void internalFrameIconified(InternalFrameEvent e) {
    }

    @Override
    public void internalFrameDeiconified(InternalFrameEvent e) {
    }

    @Override
    public void internalFrameActivated(InternalFrameEvent e) {
    }

    @Override
    public void internalFrameDeactivated(InternalFrameEvent e) {
    }
}
