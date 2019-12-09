/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tela;

import dao.RefugoDao;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import pojo.Refugo;

/**
 *
 * @author diogo.melo
 */
public class TelaBotaoRefugo extends JFrame {

    JButton botaoRefugo = new JButton("REFUGO");
    Refugo refugo;
    RefugoDao refugoDao = new RefugoDao();

    public TelaBotaoRefugo() {
        setSize(new Dimension(200, 200));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        add(botaoRefugo);
        botaoRefugo.addActionListener((ActionEvent e) -> {
          
        });
    }
}
