/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.net.URL;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;

/**
 *
 * @author diogo.melo
 */
public class Loading extends JInternalFrame {

    public void showLoader() {
        URL url = this.getClass().getResource("images/ajax-loader.gif");
        Icon icon = new ImageIcon(url);
        JLabel label = new JLabel(icon);
        //  setUndecorated(true);
        getContentPane().add(label);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        //setLocationRelativeTo(null);
        setVisible(true);
    }
}
