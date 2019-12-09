/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tela;

import componente.MeuCampoFormatado;
import componente.MeuCampoSenha;
import dao.ConfiguracaoBancoDao;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import pojo.ConfiguracaoBanco;
import util.Enums;

/**
 *
 *
 * @author diogo.melo
 */
public class TelaConfig extends TelaCadastro {

    private static TelaConfig tela;
    MeuCampoFormatado campoServidor = new MeuCampoFormatado("Servidor:", true, 40);
    MeuCampoFormatado campoPorta = new MeuCampoFormatado("Porta:", true, 40);
    MeuCampoFormatado campoUser = new MeuCampoFormatado("User:", true, 40);
    MeuCampoFormatado campoURL = new MeuCampoFormatado("URL:", true, 40);
    MeuCampoSenha campoSenha = new MeuCampoSenha(true, "Senha:");
    ConfiguracaoBanco configBanco = new ConfiguracaoBanco();
    ConfiguracaoBancoDao configBancoDAO = new ConfiguracaoBancoDao(configBanco);

    public static TelaConfig getTela() {
        if (tela == null) {
            tela = new TelaConfig();
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
        Enums.setSTATUSTELA(Enums.CONFIGURACAO);
        return tela;
    }

    public TelaConfig() {
        super("Configuração do Banco de Dados");
        this.setFrameIcon(iconeprincipal);
        iniciarComponentes();
        adicionarComponentes();
        adicionarListeners();
        consultar();

        campoServidor.setFont(new Font("Arial", Font.BOLD, 12));
        campoPorta.setFont(new Font("Arial", Font.BOLD, 12));
        campoURL.setFont(new Font("Arial", Font.BOLD, 12));
        campoUser.setFont(new Font("Arial", Font.BOLD, 12));
        campoSenha.setFont(new Font("Arial", Font.BOLD, 12));
    }

    private void iniciarComponentes() {
        setVisible(true);
        this.setSize(800, 480);
        this.getContentPane().remove(painelDescricaoMaquina);
        // painelDescricaoMaquina.removeAll();
    }

    private void adicionarComponentes() {
        migLayout(0, 1, 20, 0, 1, 70, 60, campoServidor, "", "");
        migLayout(0, 2, 20, 0, 1, 70, 60, campoPorta, "", "");
        migLayout(0, 3, 20, 0, 1, 70, 60, campoURL, "", "");
        migLayout(0, 4, 20, 0, 1, 70, 60, campoUser, "", "");
        migLayout(0, 5, 20, 0, 1, 70, 60, campoSenha, "", "");

        painelBotoes.add(btnIncluir);
        painelBotoes.add(btnAlterar);
        painelBotoes.add(btnExcluir);
        painelBotoes.add(botaocancelar);
        painelBotoes.add(btnVoltar1);

        campoServidor.setPreferredSize(new Dimension(240, 60));
        campoPorta.setPreferredSize(new Dimension(240, 60));
        campoURL.setPreferredSize(new Dimension(240, 60));
        campoUser.setPreferredSize(new Dimension(240, 60));
        campoSenha.setPreferredSize(new Dimension(240, 60));
        habilitaBotoes();
    }

    public void setPersistencia() {
        configBanco.setSerBan((String) campoServidor.getValor());
        configBanco.setPorBan((String) campoPorta.getValor());
        configBanco.setUrlBan((String) campoURL.getValor());
        configBanco.setUseBan((String) campoUser.getValor());
        configBanco.setSenBan((String) campoSenha.getValor());
    }

    public void setGUI() {
        campoServidor.setValor((String) configBanco.getSerBan());
        campoPorta.setValor((String) configBanco.getPorBan());
        campoURL.setValor((String) configBanco.getUrlBan());
        campoUser.setValor((String) configBanco.getSerBan());
        campoSenha.setValor((String) configBanco.getSenBan());
    }

    public void habilitaBotoes() {
        btnIncluir.setEnabled(true);
        btnAlterar.setEnabled(false);
        btnExcluir.setEnabled(false);
        botaocancelar.setEnabled(false);
    }

    private void adicionarListeners() {
        btnIncluir.addActionListener((ActionEvent e) -> {
            incluir();
        });
        btnAlterar.addActionListener((ActionEvent e) -> {
        });
        btnExcluir.addActionListener((ActionEvent e) -> {
        });
        botaocancelar.addActionListener((ActionEvent e) -> {
        });
        btnVoltar1.addActionListener((ActionEvent e) -> {
            TelaConfig.tela.dispose();
        });
    }

    public void incluir() {
        if (validarCampos()) {
            setPersistencia();
            configBancoDAO.incluir();
        }
    }

    public void consultar() {
        configBancoDAO.consultar();
        setGUI();
    }
}
