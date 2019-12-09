/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tela;

import com.alee.laf.list.WebList;
import com.alee.laf.text.WebTextField;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import net.miginfocom.swing.MigLayout;
import util.Enums;
import util.ListModelMotivo;
import util.Modal;
import util.Notificacao;

/**
 *
 * @author diogo.melo
 */
public class TelaApontamentoParada extends JInternalFrame {
    
    public static TelaApontamentoParada telaAP;
    
    JPanel painelCampo = new JPanel();
    JPanel painelLista = new JPanel();
    
    URL urlTopo = getClass().getResource("/imagem/iconePrincipal.png");
    URL imagemOK = getClass().getResource("/imagens/icons8-enter-key.png");
    ImageIcon iconeprincipal = new ImageIcon(urlTopo);
    
    URL imagemApagar = getClass().getResource("/imagens/icons8-backspace-filled.png");
    private final ImageIcon icoApagar = new ImageIcon(imagemApagar);
    
    private ImageIcon icoOK = new ImageIcon(imagemOK);
    
    final WebTextField campoApontamento = new WebTextField();
    JButton ok = new JButton("", icoOK);
    JButton apagar = new JButton("", icoApagar);
    ListModelMotivo listMotivo = new ListModelMotivo();
    WebList webList = new WebList(listMotivo.getElementos());
    JScrollPane js = new JScrollPane(webList);
    
    public TelaApontamentoParada() {
        super("Apontamento de Parada", false, false, false);
        setVisible(true);
        this.setFrameIcon(iconeprincipal);
        this.setSize(740, 450);
        initiComponents();
        lista();
        config();
        listener();
        travarTela();
        webList.changeFontSize(22);
    }
    
    public static TelaApontamentoParada getTela() {
        if (telaAP == null) {
            telaAP = new TelaApontamentoParada();
            telaAP.addInternalFrameListener(new InternalFrameAdapter() {
                @Override
                public void internalFrameClosed(InternalFrameEvent e) {
                    TelaSistema.jdp.remove(telaAP);
                    telaAP = null;
                }
            });
            TelaSistema.jdp.add(telaAP);
        }
        TelaSistema.jdp.setSelectedFrame(telaAP);
        TelaSistema.jdp.moveToFront(telaAP);
        TelaSistema.centraliza(telaAP);
        if (Modal.telaPai == null) {
            Modal.getTela(telaAP).moveToFront();
        } else {
            Modal.telaPai = telaAP;
        }
        Enums.setSTATUSTELA(Enums.APONTAMENTODEPARADA);
        return telaAP;
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
    
    public void lista() {
        webList.setVisibleRowCount(4);
        webList.setSelectedIndex(0);
        webList.setEditable(false);
    }
    
    public void listener() {
        campoApontamento.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                TecladoVirtual tela = TecladoVirtual.getTela("DIGITE O CÓDIGO DA PARADA", "");
                tela.addInternalFrameListener(new InternalFrameAdapter() {
                    @Override
                    public void internalFrameClosed(InternalFrameEvent e) {
                        if (!tela.meuCampoValor.getText().isEmpty()) {
                            Modal.telaPai = telaAP;
                            webList.setSelectedIndex(listMotivo.getIndex(tela.meuCampoValor.getText()));
                            campoApontamento.setText(listMotivo.getElemento(tela.meuCampoValor.getText()));
                        } else {
                            Modal.telaPai = telaAP;
                        }
                    }
                });
            }
        });
        webList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                campoApontamento.setText(webList.getSelectedValue().toString());
            }
        });
        ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!"".equals(campoApontamento.getText())) {
                   Enums.setSTATUSTELA(Enums.APONTAMENTODEPARADA);
                    TecladoVirtual.getTela("DIGITE O OPERADOR", "");
                } else {
                    Notificacao.infoBox("DIGITE OU SELECIONE UM CÓD. DE PARADA", false);
                }
            }
        });
        apagar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (campoApontamento.getText().length() > 0) {
                    campoApontamento.setText(campoApontamento.getText().substring(0, campoApontamento.getText().length() - 1));
                }
            }
        });
    }
    
    public void config() {
        campoApontamento.setPreferredSize(new Dimension(550, 80));
        ok.setPreferredSize(new Dimension(80, 80));
        apagar.setPreferredSize(new Dimension(80, 80));
        js.setPreferredSize(new Dimension(703, 300));
        campoApontamento.setFont(new Font("Arial", Font.BOLD, 35));
    }
    
    private void initiComponents() {
        
        js.getVerticalScrollBar().setPreferredSize(new Dimension(50, 0));
        js.getHorizontalScrollBar().setPreferredSize(new Dimension(0, 50));
        painelCampo.add(campoApontamento);
        painelLista.add(js);
        
        setLayout(new MigLayout());
        add(painelCampo);
        add(apagar);
        add(ok, "wrap");
        add(painelLista, "span");
    }
    
}
