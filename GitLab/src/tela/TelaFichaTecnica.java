/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tela;

import com.alee.laf.separator.WebSeparator;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseMotionListener;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author diogo.melo
 */
public class TelaFichaTecnica extends JInternalFrame {

    URL urlTopo = getClass().getResource("/imagem/iconePrincipal.png");
    ImageIcon iconeprincipal = new ImageIcon(urlTopo);
    private static TelaFichaTecnica tela;

    JPanel painelFichaTecnica = new JPanel();
    JPanel painelItensFicha = new JPanel();

    JLabel lbFichaTecnica = new JLabel("Ficha Técnica");
    JLabel lbProduto = new JLabel("68-735 VASSOURA P/ GRAMA VERDE");

    JPanel painelQuantidade = new JPanel();

    JPanel painelConsumo = new JPanel();
    JLabel lbConsumo = new JLabel("Consumo");
    JLabel campoConsumo = new JLabel("12");

    JPanel painelPerda = new JPanel();
    JLabel lbPerda = new JLabel("Perda");
    JLabel campoPerda = new JLabel("12");

    JPanel painelTotal = new JPanel();
    JLabel lbTotal = new JLabel("Total");
    JLabel campoTotal = new JLabel("12");

    JPanel painelUm = new JPanel();
    JLabel lbUm = new JLabel("U.M");
    JLabel campoUm = new JLabel("12");

    JPanel painelCp = new JPanel();
    JLabel lbCp = new JLabel("C.P");
    JLabel campoCP = new JLabel("12");

    JPanel painelSeq = new JPanel();
    JLabel lbSeq = new JLabel("Seq");
    JLabel campoSeq = new JLabel("12");

    JPanel painelCodigo = new JPanel();
    JLabel lbCodigo = new JLabel("Código");
    JLabel campoCodigo = new JLabel("200");

    JPanel painelComponente = new JPanel();
    JLabel lbDescComponente = new JLabel("Descrição do Componente");
    JLabel campoDescricaoProduto = new JLabel("200");

    JSeparator separador1 = new JSeparator(1);
    JSeparator separador2 = new JSeparator(1);
    JSeparator separador3 = new JSeparator(1);
    JSeparator separador4 = new JSeparator(1);
    JSeparator separador5 = new JSeparator(1);
    JSeparator separador6 = new JSeparator(1);
    JSeparator separador7 = new JSeparator(1);
    WebSeparator separator = new WebSeparator();

    public static TelaFichaTecnica getTela() {
        if (tela == null) {
            tela = new TelaFichaTecnica();
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

    public TelaFichaTecnica() {
        super("Ficha Técnica", true, true, true);
        setVisible(true);
        this.setFrameIcon(iconeprincipal);
        travarTela();
        this.setSize(700, 420);
        initComponente();
    }

    private void initComponente() {
        this.setLayout(new MigLayout());

        painelFichaTecnica.setLayout(new MigLayout());
        painelQuantidade.setLayout(new MigLayout());
        painelConsumo.setLayout(new MigLayout());
        painelTotal.setLayout(new MigLayout());
        painelUm.setLayout(new MigLayout());
        painelCp.setLayout(new MigLayout());
        painelSeq.setLayout(new MigLayout());
        painelCodigo.setLayout(new MigLayout());
        painelComponente.setLayout(new MigLayout());
        painelPerda.setLayout(new MigLayout());

        painelFichaTecnica.add(lbFichaTecnica, "align center, wrap");
        painelFichaTecnica.add(separator, "align center, wrap");
        painelFichaTecnica.add(lbProduto);

        painelConsumo.add(lbConsumo, "wrap");
        painelConsumo.add(campoConsumo);
        painelQuantidade.add(painelConsumo);

        painelQuantidade.add(separador1);

        painelPerda.add(lbPerda, "wrap");
        painelPerda.add(campoPerda);
        painelQuantidade.add(painelPerda);

        painelQuantidade.add(separador2);

        painelTotal.add(lbTotal, "wrap");
        painelTotal.add(campoTotal);
        painelQuantidade.add(painelTotal);

        painelQuantidade.add(separador3);

        painelUm.add(lbUm, "wrap");
        painelUm.add(campoUm);
        painelQuantidade.add(painelUm);

        painelQuantidade.add(separador4);

        painelCp.add(lbCp, "wrap");
        painelCp.add(campoCP);
        painelQuantidade.add(painelCp);

        painelQuantidade.add(separador5);

        painelSeq.add(lbSeq, "wrap");
        painelSeq.add(campoSeq);
        painelQuantidade.add(painelSeq);

        painelQuantidade.add(separador6);

        painelCodigo.add(lbCodigo, "wrap");
        painelCodigo.add(campoCodigo);
        painelQuantidade.add(painelCodigo);

        painelQuantidade.add(separador7);

        painelComponente.add(lbDescComponente, "wrap");
        painelComponente.add(campoDescricaoProduto);
        painelQuantidade.add(painelComponente, "wrap");

        separator.setPreferredSize(new Dimension(690, 10));
        lbProduto.setFont(new Font("Arial", Font.BOLD, 14));
        lbFichaTecnica.setFont(new Font("Arial", Font.BOLD, 30));
        painelFichaTecnica.setPreferredSize(new Dimension(690, 60));

        add(painelFichaTecnica, "wrap");
        add(painelQuantidade);
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
}
