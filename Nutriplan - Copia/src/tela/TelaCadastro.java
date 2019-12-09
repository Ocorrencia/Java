/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tela;

import com.alee.laf.button.WebButton;
import com.alee.laf.toolbar.WebToolBar;
import componente.MensagensSistema;
import componente.MeuComponente;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import java.awt.Toolkit;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.border.EtchedBorder;
import net.miginfocom.swing.MigLayout;


/**
 *
 * @author diogo.melo
 */
public class TelaCadastro extends JInternalFrame {

    //Paineis
    public JPanel painelBotoes = new JPanel();
    protected JPanel painelComponentes = new JPanel();
    protected JPanel painelWest = new JPanel();
    //Paineis de Botoes
    private static TelaCadastro tela;

    //instancia dos objetos do meu componente
    protected List<MeuComponente> campos = new ArrayList();
    protected GridBagConstraints gbc = new GridBagConstraints();

    URL urlTopo = getClass().getResource("/imagem/iconePrincipal.png");
    ImageIcon iconeprincipal = new ImageIcon(urlTopo);

    URL urlSobre = getClass().getResource("/imagem/iconeSobre.png");
    URL urlBackTela = getClass().getResource("/imagem/backTela.gif");
    URL urlAtualizar = getClass().getResource("/imagem/atualizar.png");

    Icon iconeSobre = new ImageIcon(urlSobre);
    Icon iconeBackTela = new ImageIcon(urlBackTela);
    Icon iconeAtualizar = new ImageIcon(urlAtualizar);

    WebToolBar tollBarBotoes = new WebToolBar(WebToolBar.VERTICAL);
    WebButton btnVoltar = new WebButton(iconeBackTela);
    WebButton btnAtualizar = new WebButton(iconeAtualizar);

    URL urlIncluir = getClass().getResource("/imagens/iconeincluir.png");
    URL urlAlterar = getClass().getResource("/imagens/iconealterar.png");
    URL ulrExcluir = getClass().getResource("/imagens/iconeexcluir.png");
    URL urlConsultar = getClass().getResource("/imagens/iconeconsultar.png");
    URL urlCancelarB = getClass().getResource("/imagens/cancelar.png");
    URL urlVoltar = getClass().getResource("/imagem/iconevoltar.png");
    URL urlConfirmar = getClass().getResource("/imagens/confirmar.png");
    URL urlPesquisarCAD = getClass().getResource("/imagens/pesquisarcad.png");

    Icon iconeincluir = new ImageIcon(urlIncluir);
    public JButton btnIncluir = new JButton("Salvar", iconeincluir);
    Icon iconealterar = new ImageIcon(urlAlterar);
    public JButton btnAlterar = new JButton("Alterar", iconealterar);
    Icon iconexcluir = new ImageIcon(ulrExcluir);
    public JButton btnExcluir = new JButton("Excluir", iconexcluir);
    Icon iconeconsultar = new ImageIcon(urlConsultar);
    public JButton btnConsultar = new JButton("Consultar", iconeconsultar);
    Icon iconeconfirmar = new ImageIcon(urlConfirmar);
    public JButton btnConfirmar = new JButton("Confirmar", iconeconfirmar);
    Icon iconecancelar = new ImageIcon(urlCancelarB);
    public JButton btnCancelar = new JButton("Cancelar/Sair", iconecancelar);
    Icon iconeVoltar = new ImageIcon(urlVoltar);
    public JButton btnVoltar1 = new JButton("Voltar", iconeVoltar);
    Icon iconepesquisar = new ImageIcon(urlPesquisarCAD);

    public static TelaCadastro getTela() {
        if (tela == null) {
            tela = new TelaCadastro("teste");
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
        return tela;
    }

    public TelaCadastro(String titulo) {
        super(titulo, false, false, false, false);
        painelBotoes.add(btnIncluir);
        painelBotoes.add(btnAlterar);
        painelBotoes.add(btnExcluir);
        painelBotoes.add(btnCancelar);
        iniciarComponentes();
    }

    private void iniciarComponentes() {
        setVisible(true);
        painelBotoes.setLayout(new GridLayout());
        painelComponentes.setLayout(new MigLayout());
        getContentPane().add("Center", painelComponentes);
        getContentPane().add("South", painelBotoes);
        //  getContentPane().add("West", painelWest);

        /*painelComponentes.setPreferredSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize().width, 25));
        painelBotoes.setPreferredSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize().width, 55));*/

    }

    public void migLayout(int coluna, int linha, int tamanhoFonte, int tamanhoCampoLargura, int tamanhoCampoAltura, int alturaPainel, int larguraPainel, JComponent componente, String tituloPainel, String alinhamento) {
        JPanel painel = new JPanel();
        painel.setBorder(null);
        if (componente instanceof MeuComponente) {
            campos.add((MeuComponente) componente);
            String nomeRotulo = "<html><body>" + ((MeuComponente) componente).getDica();
            if (((MeuComponente) componente).eObrigatorio()) {
                nomeRotulo = nomeRotulo + "<font color = red>*</font>";
            }
            nomeRotulo = nomeRotulo + "</body></html>";
            JLabel rotulo = new JLabel(nomeRotulo);

            rotulo.setFont(new Font("Arial", Font.BOLD, tamanhoFonte));
          //  painel.setPreferredSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize().width - larguraPainel, alturaPainel));
            if (!"".equals(alinhamento)) {
                painel.setLayout(new MigLayout());
                painel.add(rotulo, "align right");
            }
            painel.add(rotulo);
            painel.add(componente);
            painelComponentes.add(painel, "cell " + coluna + " " + linha + " " + tamanhoCampoLargura + " " + tamanhoCampoAltura + "");
         //   painel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
        } else {
            painel.setPreferredSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize().width - larguraPainel, alturaPainel));
            painel.add(componente);
            if (!"".equals(tituloPainel)) {
                painel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
            }
            painelComponentes.add(painel, "cell " + coluna + " " + linha + " " + tamanhoCampoLargura + " " + tamanhoCampoAltura + ", span");
        }
    }

    public void adicionarBotoes(JPanel painel, JButton botao) {
        JPanel painelComp = new JPanel();
        painelComp.setLayout(new GridLayout());
        painelComp.add(botao);
        painel.add(painelComp);
    }

    @Override
    public void pack() {
        super.pack();
        setSize(getSize().width + 10, getSize().height + 10);
    }

    protected boolean validarCampos() {
        String mensagemInvalidos = "";
        String mensagemObrigatoriosNaoPreenchidos = "";

        for (int i = 0; i < campos.size(); i++) {
            if (!campos.get(i).eValido()) {
                mensagemInvalidos = mensagemInvalidos + campos.get(i).getDica() + "\n";
            }
            if (campos.get(i).eObrigatorio() && campos.get(i).eVazio()) {
                mensagemObrigatoriosNaoPreenchidos = mensagemObrigatoriosNaoPreenchidos + "\n" + campos.get(i).getDica();
            }
        }
        if (!mensagemInvalidos.isEmpty()) {
            MensagensSistema.MensagemAtencao("OS CAMPOS ABAIXO ESTÃO INVÁLIDOS:\n" + mensagemInvalidos);
        }
        if (!mensagemObrigatoriosNaoPreenchidos.isEmpty()) {
            MensagensSistema.MensagemAtencao("OS CAMPOS ABAIXO SÃO OBRIGATÓRIOS E NÃO FORAM PREENCHIDOS CORRETAMENTE: " + mensagemObrigatoriosNaoPreenchidos);
        }

        return (mensagemInvalidos.isEmpty() && mensagemObrigatoriosNaoPreenchidos.isEmpty());
    }

}
