package tela;

import com.alee.extended.layout.ToolbarLayout;
import com.alee.extended.layout.VerticalFlowLayout;
import com.alee.extended.statusbar.WebStatusBar;
import com.alee.extended.statusbar.WebStatusLabel;
import com.alee.extended.window.WebPopOver;
import com.alee.laf.WebLookAndFeel;
import com.alee.laf.label.WebLabel;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import util.ConexaoOracle;

public class TelaSistema extends JFrame {

    public static TelaSistema telaSistema;
    public static MeuJDesktopPane jdp = new MeuJDesktopPane();
    // MENU

    WebStatusBar statusBar = new WebStatusBar();
    JMenuBar menu = new JMenuBar();
    JMenu sincronizacao = new JMenu("SINCRONIZAÇÃO");
    JMenuItem injetora = new JMenuItem("MÁQUINA");
    JMenuItem sair = new JMenuItem("SAIR");
    /////////////////////////////////////////////
    //IMAGENS
    URL urlTopo = getClass().getResource("/imagem/iconePrincipal.png");
    ImageIcon iconeprincipal = new ImageIcon(urlTopo);

    URL urlNutriplito = getClass().getResource("/imagem/nutriplito29x29.png");
    ImageIcon iconeNutriplito = new ImageIcon(urlNutriplito);
    /////////////////////////////////////////////
    WebPopOver popOver = new WebPopOver(telaSistema);
    WebStatusLabel btnVersaoSistema = new WebStatusLabel("Clique aqui para saber as novidades da versão!", iconeNutriplito);
    JButton btnOK = new JButton("OK!");

    public static void main(String args[]) throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {

        TelaLogin telaLogin = new TelaLogin();
        UIManager.setLookAndFeel(new WebLookAndFeel());
    }

    public TelaSistema() {
        getContentPane().add(jdp);
        setTitle("Sistema de Controle de Apontamento de Produção");
        setIconImage(iconeprincipal.getImage());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setJMenuBar(menu);
        setVisible(true);
        setLocationRelativeTo(null);
        setExtendedState(6);
        adicionaMenus();
        adicionaListener();
        ConexaoOracle.getConexaoOracle();

    }

    public static void centraliza(JInternalFrame janela) {
        int larguraDesk = jdp.getWidth();
        int alturaDesk = jdp.getHeight();
        int larguraIFrame = janela.getWidth();
        int alturaIFrame = janela.getHeight();
        janela.setLocation(larguraDesk / 2 - larguraIFrame / 2, alturaDesk / 2 - alturaIFrame / 2);
    }

    private void adicionaMenus() {
        //MENUS
        menu.add(sincronizacao);
        //ITENS MENUS
        sincronizacao.add(injetora);
        sincronizacao.add(sair);
        statusBar();
    }

    public void statusBar() {
        getContentPane().add("South", statusBar);
        statusBar.setPreferredSize(new Dimension(statusBar.getWidth(), 20));

        btnVersaoSistema.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                final WebPopOver popOver = new WebPopOver(telaSistema);
                popOver.setCloseOnFocusLoss(true);
                popOver.setMargin(10);
                popOver.setLayout(new VerticalFlowLayout());
                popOver.add(new WebLabel("<html>- Adicionado tela de sicronização de software;<br />"
                        + "                     - Adicionado a opção de atualizaçao por centro de recurso;<br />"
                        + "                     - Adicionado botão de sair no menu principal;</htm>"));
                popOver.add(btnOK);
                popOver.show(btnVersaoSistema);
                btnOK.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        popOver.dispose();
                    }
                });
            }
        });
        statusBar.add(btnVersaoSistema, ToolbarLayout.END);
    }

    public void fechar() {
        dispose();
    }

    private void adicionaListener() {
        injetora.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TelaSincronizacao.getTela();
            }
        });
        sair.addActionListener(new ActionListener() {
            @Override
            
            public void actionPerformed(ActionEvent e) {
                fechar();
            }
        });
    }
}
