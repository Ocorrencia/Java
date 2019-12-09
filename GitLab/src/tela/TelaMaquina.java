/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tela;

import componente.MeuCampoFormatado;
import componente.MeuComboBox;
import dao.MaquinaDao;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseMotionListener;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import pojo.Maquina;
import util.Enums;

/**
 *
 * @author diogo.melo
 */
public class TelaMaquina extends TelaCadastro {

    MeuComboBox campoNomeMaquina = new MeuComboBox("SELECT CODCRE, DESCRE FROM nutri_op.op725cre", true, "Máquina:");
    MeuCampoFormatado campoIpMaquina = new MeuCampoFormatado("IP:", true, 30);
    private static TelaMaquina tela;
    URL urlTopo = getClass().getResource("/imagem/iconePrincipal.png");
    ImageIcon iconeprincipal = new ImageIcon(urlTopo);
    Maquina maquina = new Maquina();
    MaquinaDao maquinaDao = new MaquinaDao(maquina);

    public static TelaMaquina getTela() {
        if (tela == null) {
            tela = new TelaMaquina();
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

    public TelaMaquina() {
        super("Tela Máquina");
        setVisible(true);
        this.setSize(600, 260);
        travarTela();
        this.setFrameIcon(iconeprincipal);
        this.getContentPane().remove(painelDescricaoMaquina);
        listener();
        iniciarComponentes();
        consultar();
    }

    private void iniciarComponentes() {
        migLayout(0, 1, 20, 0, 1, 70, 60, campoNomeMaquina, "", "");
        migLayout(0, 2, 20, 0, 1, 70, 60, campoIpMaquina, "", "");

        painelBotoes.add(btnIncluir);
        painelBotoes.add(btnAlterar);
        painelBotoes.add(btnExcluir);
        painelBotoes.add(botaocancelar);

        campoIpMaquina.setFont(new Font("Arial", Font.BOLD, 20));
        campoNomeMaquina.setFont(new Font("Arial", Font.BOLD, 20));
        campoNomeMaquina.setPreferredSize(new Dimension(440, 60));
        campoIpMaquina.setPreferredSize(new Dimension(240, 60));

        btnAlterar.setEnabled(false);
        btnExcluir.setEnabled(false);
    }

    public void setPersistencia() {
        maquina.setCodCre((Integer) campoNomeMaquina.getValor());
        maquina.setIpMaq((String) campoIpMaquina.getValor());
    }

    public void setGUI() {
        campoNomeMaquina.setValor((Integer) maquina.getCodCre());
        campoIpMaquina.setValor((String) maquina.getIpMaq());
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

    public void consultar() {
        maquinaDao.consultar();
        setGUI();
    }

    public void fechar() {
        this.dispose();
    }

    public void listener() {
        btnIncluir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setPersistencia();
                maquinaDao.INCLUIR();
            }
        });
        botaocancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fechar();
            }
        });
    }
}
