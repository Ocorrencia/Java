package tela;

import com.alee.laf.progressbar.WebProgressBar;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseMotionListener;
import java.io.IOException;
import java.net.URL;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import util.Enums;
import util.ListenGPIO;

public class TelaSistema extends JFrame {

    public static MeuJDesktopPane jdp = new MeuJDesktopPane();
    public static TelaSistema telaSistema;
    //ListenGPIO listenGPIO = new ListenGPIO();

    public static void main(String args[]) throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        try {
            telaSistema = new TelaSistema();
            System.gc();
            Enums.STATUSTELA = Enums.getSTATUSTELA();
            UIManager.put("ScrollBar.width", 50);
            if (Enums.STATUSTELA == Enums.AVISOINICIOPRODUCAO) {
                TecladoVirtual.getTela("DIGITE O OPERADOR", Enums.TELAOP);
            }
            if (Enums.STATUSTELA == Enums.PRODUCAO || Enums.STATUSTELA == Enums.APONTAMENTODEPARADA) {
                TelaOP.getTela();
            } else if (Enums.getSTATUSTELA() == Enums.MENU) {
                TelaMenu.getTela();
            } else if (Enums.getSTATUSTELA() == Enums.FINALIZADO) {
                TecladoVirtual.getTela("DIGITE O OPERADOR", Enums.TELAOP);
            }
        } catch (Exception e) {
        }
    }

    public static void centraliza(JInternalFrame janela) {
        int larguraDesk = jdp.getWidth();
        int alturaDesk = jdp.getHeight();
        int larguraIFrame = janela.getWidth();
        int alturaIFrame = janela.getHeight();
        janela.setLocation(larguraDesk / 2 - larguraIFrame / 2, alturaDesk / 2 - alturaIFrame / 2);
    }

    public static void centralizaJPanel(JPanel janela) {
        int larguraDesk = jdp.getWidth();
        int alturaDesk = jdp.getHeight();
        int larguraIFrame = janela.getWidth();
        int alturaIFrame = janela.getHeight();
        janela.setLocation(larguraDesk / 2 - larguraIFrame / 2, alturaDesk / 2 - alturaIFrame / 2);
    }

    public static void centralizaJDialog(JDialog janela) {
        int larguraDesk = jdp.getWidth();
        int alturaDesk = jdp.getHeight();
        int larguraIFrame = janela.getWidth();
        int alturaIFrame = janela.getHeight();
        janela.setLocation(larguraDesk / 2 - larguraIFrame / 2, alturaDesk / 2 - alturaIFrame / 2);
    }
    final WebProgressBar progressBar = new WebProgressBar(0, 100);
    JPanel jPanelProgressBar = new JPanel();
    URL urlTopo = getClass().getResource("/imagem/iconePrincipal.png");
    ImageIcon iconeprincipal = new ImageIcon(urlTopo);
    Enums consta;

    public TelaSistema() {
        getContentPane().add(jdp);
        setIconImage(iconeprincipal.getImage());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(new Dimension(800, 480));
        setUndecorated(true);
        getRootPane().setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.white));
        setVisible(true);
        controleDeOperacao();
        setLocationRelativeTo(null);
        // verificarTurno.inicarVerificacao();
        travar();
    }

    public void controleDeOperacao() {
        Enums.setTIPOSISTEMA(Enums.WINDOWS);
        /* switch (ControleOperacao.consultar()) {
            case "PRODUCAO":
                Enums.setSTATUSTELA(Enums.PRODUCAO);
                break;
            case "MENU":
                Enums.setSTATUSTELA(Enums.MENU);
                break;
            case "FINALIZADO":
                Enums.setSTATUSTELA(Enums.FINALIZADO);
                break;
        }
    }*/
    }

    public static void fecharTela() {
        telaSistema.dispose();
    }

    public void travar() {
        JInternalFrame[] frames = TelaSistema.jdp.getAllFrames();
        for (JInternalFrame frame : frames) {
            BasicInternalFrameUI ui = (BasicInternalFrameUI) frame.getUI();
            Component northPane = ui.getNorthPane();
            MouseMotionListener[] motionListeners = (MouseMotionListener[]) northPane.getListeners(MouseMotionListener.class);

            for (MouseMotionListener listener : motionListeners) {
                northPane.removeMouseMotionListener(listener);
            }
        }
        this.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                //  TecladoVirtual teclado = TecladoVirtual.getTela("DIGITE O OPERADOR", Enums.TELAOP);
            }

            @Override
            public void focusLost(FocusEvent e) {
            }
        });
    }
}
