/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tela;

import com.alee.extended.layout.ToolbarLayout;
import com.alee.extended.statusbar.WebMemoryBar;
import com.alee.extended.statusbar.WebStatusBar;
import com.alee.extended.statusbar.WebStatusLabel;
import com.alee.laf.button.WebButton;
import com.alee.laf.toolbar.WebToolBar;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import util.Consulta;
import util.Enums;
import util.Limpar;
import util.Modal;
import util.Notificacao;
import util.Sincronizacao;

/**
 *
 * @author diogo.melo
 */
public class TelaMenu extends JInternalFrame {

    URL urlTopo = getClass().getResource("/imagem/logoSuperior.png");
    URL imagemSinc = getClass().getResource("/imagens/icons8-synchronize.png");
    URL imagemRede = getClass().getResource("/imagens/icons8-network-cable.png");
    URL imagemNext = getClass().getResource("/imagens/icons8-more-than.png");
    URL imagemBack = getClass().getResource("/imagens/icons8-back.png");
    URL imagemConfig = getClass().getResource("/imagens/icons8-database-administrator.png");
    URL imagemPlay = getClass().getResource("/imagens/icons8-automation.png");
    URL imagemSair = getClass().getResource("/imagens/icons8-exit-sign-black.png");
    URL iconePrincipal = getClass().getResource("/imagem/timer.png");
    URL imagemMaquina = getClass().getResource("/imagens/icons8-engineering-80.png");

    private final ImageIcon iconeMaquina = new ImageIcon(imagemMaquina);
    private final ImageIcon icoSinc = new ImageIcon(imagemSinc);
    private final ImageIcon icoClean = new ImageIcon(imagemSinc);
    private final ImageIcon icoRede = new ImageIcon(imagemRede);
    private final ImageIcon icoNext = new ImageIcon(imagemNext);
    private final ImageIcon icoBack = new ImageIcon(imagemBack);
    private final ImageIcon icoConfig = new ImageIcon(imagemConfig);
    private final ImageIcon icoPlay = new ImageIcon(imagemPlay);
    private final ImageIcon icoSair = new ImageIcon(imagemSair);
    private final ImageIcon iconeprincipal = new ImageIcon(urlTopo);

    JPanel painelBotoes = new JPanel();
    JPanel painelBotoes1 = new JPanel();
    JPanel painelTitulo = new JPanel();
    JPanel painelRodape = new JPanel();
    JPanel painelOperacao = new JPanel();

    //icones menus
    GridLayout layoutTitulo = new GridLayout(0, 1);
    GridLayout layoutRodape = new GridLayout(0, 1);

    JButton btnOP = new JButton("ORDEM \n PRODUÇÃO", icoPlay);
    JButton btnSincronizacao = new JButton("SINCRONIZAÇÃO", icoSinc);
    JButton btnReiniciarBanco = new JButton("LIMPAR APP", icoClean);
    JButton tecladoVitrual = new JButton("Teclado", icoSinc);
    JButton btnSair = new JButton("SAIR", icoSair);
    JButton btnProximo = new JButton("PRÓXIMO", icoNext);
    JButton btnAnterior = new JButton("ANTERIOR", icoBack);
    JButton btnConfig = new JButton("Configuração", icoConfig);
    JButton btnMaquina = new JButton("MÁQUINA", iconeMaquina);

    public static TelaMenu tela;
    public static TecladoVirtual tecladoVirtual;

    WebStatusBar statusBar = new WebStatusBar();
    WebStatusBar statusBarSuperior = new WebStatusBar();
    WebMemoryBar memoryBar = new WebMemoryBar();
    final WebToolBar toolBar1 = new WebToolBar(WebToolBar.HORIZONTAL);

    public static TelaMenu getTela() {
        if (tela == null) {
            tela = new TelaMenu();
            tela.addInternalFrameListener(new InternalFrameAdapter() {
                @Override
                public void internalFrameClosed(InternalFrameEvent e) {
                    TelaSistema.jdp.remove(tela);
                    tela = null;
                }
            });
            TelaSistema.jdp.add(tela);
        }
        TelaSistema.jdp.setSelectedFrame(tela);
        TelaSistema.jdp.moveToFront(tela);
        TelaSistema.centraliza(tela);
        Enums.setSTATUSTELA(Enums.MENU);
        return tela;
    }

    public TelaMenu() {
        super("Menu", false, false, false, false);
        iniciarComponentes();
        ((javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI()).setNorthPane(null);
    }

    private void iniciarComponentes() {
        setVisible(true);
        // this.setLayout(new GridLayout(3, 1));

        getContentPane().add("North", painelTitulo);
        getContentPane().add("West", painelOperacao);
        getContentPane().add("Center", painelBotoes);
        getContentPane().add("South", painelRodape);

        // adicionando layout
        painelBotoes.setLayout(new GridLayout());
        painelBotoes1.setLayout(new GridLayout());
        //  painelTitulo.setLayout(layoutTitulo);
        painelRodape.setLayout(layoutRodape);

        // adicionando colunas paineis botao 
        painelBotoes.setLayout(new GridLayout(2, 2));
        painelBotoes1.setLayout(new GridLayout(2, 2));

        //adicionando colunas painel operacao
        painelOperacao.setLayout(new GridLayout(3, 1));

        // tamanho dos painels
        painelBotoes.setPreferredSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize().width, 50));
        painelBotoes1.setPreferredSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize().width, 50));
        painelRodape.setPreferredSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize().width, 50));
        painelTitulo.setPreferredSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize().width, 50));
        painelOperacao.setPreferredSize(new Dimension(200, Toolkit.getDefaultToolkit().getScreenSize().height));

        // adicionando botoes
        painelBotoes.add(btnOP);
        painelBotoes.add(btnSincronizacao);
        painelBotoes.add(new JButton());
        painelBotoes.add(btnSair);

        painelBotoes1.add(btnMaquina);
        painelBotoes1.add(btnReiniciarBanco);
        painelBotoes1.add(new JButton());
        painelBotoes1.add(new JButton());

        painelOperacao.add(btnProximo);
        painelOperacao.add(btnAnterior);
        painelOperacao.add(btnConfig);

        configTitulo();
        configBotoes();
        configRodape();
        alinhamentoBotoes();
        this.setSize(800, 480);
        iniciarListeners();
    }

    public void mensagemErroSincronizacao(String msg) {
        Notificacao.infoBox(msg, false);
        TelaLoading.getTela("").dispose();
        Modal.getTela(null).dispose();
    }

    public class MyRunnable implements Runnable {

        public void run() {

            TelaLoading tela1 = TelaLoading.getTela("Carrengando dados via WEBSERVICE...");
            Modal.getTela(tela1).setVisible(true);
            tela1.moveToFront();
            Modal.telaPai = tela1;
            if (!Sincronizacao.sincCentroRecurso()) {
                mensagemErroSincronizacao("Ocorreu um erro ao sincronizar o centro de recurso");
                return;
            } else if (!Sincronizacao.sincFichaTecnica()) {
                mensagemErroSincronizacao("Ocorreu um erro ao sincronizar a ficha técnica");
                return;
            } else if (!Sincronizacao.sincTurno()) {
                mensagemErroSincronizacao("Ocorreu um erro ao sincronizar o turno");
                return;
            } else if (!Sincronizacao.sintTurnoTrabalho()) {
                mensagemErroSincronizacao("Ocorreu um erro ao sincronizar o turno de trabalho");
                return;
            } else if (!Sincronizacao.sincOperadores()) {
                mensagemErroSincronizacao("Ocorreu um erro ao sincronizar os operadores");
                return;
            } else if (!Sincronizacao.sincMotivoParada()) {
                mensagemErroSincronizacao("Ocorreu um erro ao sincronizar os motivo de parada");
                return;
            } else if (!Sincronizacao.sincDefeito()) {
                mensagemErroSincronizacao("Ocorreu um erro ao sincronizar os defeitos");
                return;
            } else if (!Sincronizacao.sincOrdemProducao(false)) {
                mensagemErroSincronizacao("Ocorreu um erro ao sincronizar a ordem de produção");
                return;
            } else if (!Sincronizacao.sincPrioridade()) {
                mensagemErroSincronizacao("Ocorreu um erro ao sincronizar a prioridade");
                return;
            } else if (true) {
                Notificacao.infoBox("SINCRONIZADA FINALIZADA", true);
                tela1.getTela("").dispose();
                Modal.getTela(tela1).dispose();
            }
        }
    }

    private void iniciarListeners() {

        btnMaquina.addActionListener((ActionEvent e) -> {
            TelaMaquina.getTela();
        });
        btnOP.addActionListener((ActionEvent e) -> {
            if (Consulta.CONSULTASTRING("nutri_op.op000maq", "CODCRE", "1 = 1") != "VAZIO") {
                this.dispose();
                TecladoVirtual tela1 = TecladoVirtual.getTela("DIGITE O OPERADOR", "INICIO");
                Modal.getTela(tela1);
                tela1.moveToFront();
            } else {
                Notificacao.infoBox("NÃO VINCULADO CENTRO DE REC.", false);
            }
        });
        btnSair.addActionListener((ActionEvent e) -> {
            TelaSistema.fecharTela();
        });
        btnConfig.addActionListener((ActionEvent e) -> {
            TelaConfig.getTela();
        });
        tecladoVitrual.addActionListener((ActionEvent e) -> {
            /*   TecladoVirtual.getTela();*/
        });
        btnSincronizacao.addActionListener((ActionEvent e) -> {
            new Thread(new MyRunnable()).start();
        });
        btnProximo.addActionListener((ActionEvent e) -> {
            for (Component component1 : getContentPane().getComponents()) {
                if (component1.equals(painelBotoes)) {
                    getContentPane().remove(painelBotoes);
                    getContentPane().add("Center", painelBotoes1);
                    getContentPane().revalidate();
                }
            }
            getContentPane().repaint();
        });
        btnAnterior.addActionListener((ActionEvent e) -> {
            for (Component component1 : getContentPane().getComponents()) {
                if (component1.equals(painelBotoes1)) {
                    getContentPane().remove(painelBotoes1);
                    getContentPane().add("Center", painelBotoes);
                    getContentPane().revalidate();
                }
            }
            getContentPane().repaint();
        });
        btnReiniciarBanco.addActionListener((ActionEvent e) -> {
            Limpar limpar = new Limpar();
            limpar.executeSqlScript();
        });
    }

    private void alinhamentoBotoes() {
        btnProximo.setVerticalTextPosition(SwingConstants.BOTTOM);
        btnProximo.setHorizontalTextPosition(SwingConstants.CENTER);

        btnAnterior.setVerticalTextPosition(SwingConstants.BOTTOM);
        btnAnterior.setHorizontalTextPosition(SwingConstants.CENTER);

        btnConfig.setVerticalTextPosition(SwingConstants.BOTTOM);
        btnConfig.setHorizontalTextPosition(SwingConstants.CENTER);

        tecladoVitrual.setVerticalTextPosition(SwingConstants.BOTTOM);
        tecladoVitrual.setHorizontalTextPosition(SwingConstants.CENTER);

        btnSair.setVerticalTextPosition(SwingConstants.BOTTOM);
        btnSair.setHorizontalTextPosition(SwingConstants.CENTER);

        btnSincronizacao.setVerticalTextPosition(SwingConstants.BOTTOM);
        btnSincronizacao.setHorizontalTextPosition(SwingConstants.CENTER);

        btnOP.setVerticalTextPosition(SwingConstants.BOTTOM);
        btnOP.setHorizontalTextPosition(SwingConstants.CENTER);

        btnMaquina.setVerticalTextPosition(SwingConstants.BOTTOM);
        btnMaquina.setHorizontalTextPosition(SwingConstants.CENTER);

        btnReiniciarBanco.setVerticalTextPosition(SwingConstants.BOTTOM);
        btnReiniciarBanco.setHorizontalTextPosition(SwingConstants.CENTER);
    }

    private void configTitulo() {
        painelTitulo.add(toolBar1);
        toolBar1.setFloatable(false);
        toolBar1.add(WebButton.createIconWebButton(iconeprincipal, true));
        toolBar1.addSeparator();
        toolBar1.setPreferredSize(800, 40);
    }

    private void configBotoes() {
        //painelBotoes.setBorder(BorderFactory.createLineBorder(Color.GREEN));
        btnSair.setFont(new Font("Arial", Font.BOLD, 22));
        btnSincronizacao.setFont(new Font("Arial", Font.BOLD, 22));
        btnReiniciarBanco.setFont(new Font("Arial", Font.BOLD, 22));
        tecladoVitrual.setFont(new Font("Arial", Font.BOLD, 22));
        btnOP.setFont(new Font("Arial", Font.BOLD, 22));
        btnMaquina.setFont(new Font("Arial", Font.BOLD, 22));
        btnProximo.setFont(new Font("Arial", Font.BOLD, 20));
        btnAnterior.setFont(new Font("Arial", Font.BOLD, 20));
        btnConfig.setFont(new Font("Arial", Font.BOLD, 18));
    }

    private void configRodape() {
        //painelRodape.setBorder(BorderFactory.createLineBorder(Color.ORANGE));
        statusBar.add(new WebStatusLabel());
        memoryBar.setPreferredWidth(memoryBar.getPreferredSize().width + 20);
        JLabel labelVersao = new JLabel("VERSÃO TESTE");
        statusBar.add(labelVersao, ToolbarLayout.END);
        painelRodape.add(statusBar);

    }

    public class load implements Runnable {

        public void run() {
            while (true) {
                TelaLoading.getTela("");
            }

        }
    }
}
