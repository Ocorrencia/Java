/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tela;

import com.alee.laf.button.WebButton;
import com.alee.laf.rootpane.WebFrame;
import com.alee.laf.tooltip.WebToolTip;
import com.alee.managers.language.data.TooltipWay;
import com.alee.managers.tooltip.TooltipManager;
import componente.MeuJPanelImagem;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import net.miginfocom.swing.MigLayout;
import util.ActiveDirectory;

public class TelaLogin extends WebFrame {

    WebButton btnLogar = new WebButton("Entrar");
    WebButton btnSair = new WebButton("Sair");

    JPanel painelCampos = new JPanel();
    JPanel painelSpace = new JPanel();
    MeuJPanelImagem painelImagem;
    JPanel painelBotoes = new JPanel();

    JLabel lbLogin = new JLabel("LOGIN:");
    JTextField login = new JTextField();
    JLabel lbSenha = new JLabel("SENHA:");
    JPasswordField senha = new JPasswordField();
    URL urlTopo = getClass().getResource("/imagem/iconePrincipal.png");
    URL urlLoginNutriplan = getClass().getResource("/imagem/loginNutriplan.png");
    ImageIcon iconeprincipal = new ImageIcon(urlTopo);
    ActiveDirectory ac = new ActiveDirectory();

    WebToolTip tpLogin = new WebToolTip();
    TooltipManager tpSenha = new TooltipManager();
    TooltipManager tpErro = new TooltipManager();

    public TelaLogin() {
        try {
            setTitle("Login");
            setPreferredSize(new Dimension(500, 200));
            this.painelImagem = new MeuJPanelImagem();
            centraliza(this);
            setVisible(true);
            setIconImage(iconeprincipal.getImage());
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            //  setLocationRelativeTo(null);
            adicionarComponente();
            configuracoesTela();
            adicionarListener();
            pack();
            login.requestFocus();
            centraliza(this);
            setResizable(false);
            
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void centraliza(JFrame janela) {
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension d = tk.getScreenSize();

        int larguraDesk = d.width;
        int alturaDesk = d.height;
        int larguraIFrame = janela.getWidth();
        int alturaIFrame = janela.getHeight();
        janela.setLocation(larguraDesk / 2 - larguraIFrame / 2, alturaDesk / 2 - alturaIFrame / 2);
    }

    private void adicionarComponente() {
        setLayout(new MigLayout());
        painelCampos.setLayout(new MigLayout("align 50% 50%"));
        painelBotoes.setLayout(new GridLayout(1, 2));

        add(painelCampos);
        add(painelImagem, "west");

        //add(painelSpace, "south");
        //  add(painelBotoes, "south");
        painelCampos.add(lbLogin, "");
        painelCampos.add(login, "span,  wrap");
        painelCampos.add(lbSenha, "");
        painelCampos.add(senha, "span, wrap");
        painelCampos.add(painelBotoes, "span");

        painelBotoes.add(btnSair);
        painelBotoes.add(btnLogar);

    }

    private void configuracoesTela() {
        login.setPreferredSize(new Dimension(220, 25));
        senha.setPreferredSize(new Dimension(220, 25));

        painelImagem.setPreferredSize(new Dimension(200, 250));

        painelCampos.setPreferredSize(new Dimension(300, 70));

        painelBotoes.setPreferredSize(new Dimension(300, 50));
        painelSpace.setPreferredSize(new Dimension(300, 60));
    }

    public void fechar() {
        this.dispose();
    }

    public boolean validarCampos() {
        if (login.getText().isEmpty()) {
            TooltipManager.showOneTimeTooltip(TooltipManager.setTooltip(login, "Digite a LOGIN", TooltipWay.up, 0));
            login.requestFocus();
            return false;
        } else if (senha.getText().isEmpty()) {
            TooltipManager.showOneTimeTooltip(TooltipManager.setTooltip(senha, "Digite a SENHA", TooltipWay.up, 0));
            senha.requestFocus();
            return false;
        } else {
            return true;
        }
    }

    private void adicionarListener() {
        btnLogar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TooltipManager.hideAllTooltips();
                if (validarCampos()) {
                    String requestLogin = ac.login(login.getText(), senha.getText());
                    if ("".equals(requestLogin)) {
                        fechar();
                        TelaSistema telaSistema = new TelaSistema();
                    } else {
                        TooltipManager.showOneTimeTooltip(TooltipManager.setTooltip(login, requestLogin, TooltipWay.up, 0));
                    }
                }
            }
        });
        btnLogar.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    TooltipManager.hideAllTooltips();
                    if (validarCampos()) {
                        String requestLogin = ac.login(login.getText(), senha.getText());
                        if ("".equals(requestLogin)) {

                            fechar();
                            TelaSistema telaSistema = new TelaSistema();
                        } else {
                            TooltipManager.showOneTimeTooltip(TooltipManager.setTooltip(login, requestLogin, TooltipWay.up, 0));
                            login.requestFocus();
                        }
                    }
                }
            }
        });
        senha.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    TooltipManager.hideAllTooltips();
                    if (validarCampos()) {
                        String requestLogin = ac.login(login.getText(), senha.getText());
                        if ("".equals(requestLogin)) {
                            fechar();
                            TelaSistema telaSistema = new TelaSistema();
                        } else {
                            TooltipManager.showOneTimeTooltip(TooltipManager.setTooltip(login, requestLogin, TooltipWay.up, 0));
                            login.requestFocus();
                        }
                    }
                }
            }
        });
        btnSair.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    fechar();
                }
            }
        });
        btnSair.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fechar();
            }
        });
    }
}
