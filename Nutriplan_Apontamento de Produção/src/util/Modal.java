/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import tela.TelaSistema;

/**
 *
 * @author diogo.melo
 */
public class Modal extends JInternalFrame {

    private final AlphaComposite comp = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f);
    private static Modal tela;
    public static JInternalFrame telaPai;
    JPanel panel = new TransparentPanel();

    public static Modal getTela(JInternalFrame telaPai) {
        if (tela == null) {
            tela = new Modal(telaPai);
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

    public Modal(JInternalFrame telaPai) {
        setSize(this.getMaximumSize());
        add(panel);
        this.telaPai = telaPai;
        setOpaque(false);
        setBackground(new Color(0, 0, 0, 0));
        setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        ((javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI()).setNorthPane(null);
        setVisible(true);
        listener();
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setComposite(comp);
        super.paint(g);
    }

    private void listener() {
        panel.addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
                telaPai.moveToFront();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                telaPai.moveToFront();
            }
        });
    }

    private class TransparentPanel extends JPanel {

        {
            setOpaque(false);
        }

        public void paintComponent(Graphics g) {
            g.setColor(getBackground());
            Rectangle r = g.getClipBounds();
            g.fillRect(r.x, r.y, r.width, r.height);
            super.paintComponent(g);
        }
    }

}
