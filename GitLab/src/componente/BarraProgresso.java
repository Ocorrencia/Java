/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package componente;

import com.alee.laf.progressbar.WebProgressBar;
import javax.swing.JPanel;

/**
 *
 * @author diogo.melo
 */
public class BarraProgresso {

    final WebProgressBar progressBar1 = new WebProgressBar(0, 100);
    private boolean increasing = true;
    JPanel jpanel = new JPanel();

    public BarraProgresso() {
        progressBar1.setIndeterminate(true);
        progressBar1.setStringPainted(true);
        progressBar1.setString("Please wait...");
        jpanel.add(progressBar1);
        progressBar1.setValue(50);
        //   valores();
    }

    public void valores() {
        int value = progressBar1.getValue();
        if (increasing) {
            progressBar1.setValue(value + 1);
            if (value + 1 == 100) {
                increasing = false;
            }
        } else {
            progressBar1.setValue(value - 1);
            if (value - 1 == 0) {
                increasing = true;
            }
        }
    }
}
