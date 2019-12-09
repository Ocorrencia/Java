/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.util.TimerTask;
import org.apache.commons.lang.time.StopWatch;
import tela.TelaApontamentoParada;

/**
 *
 * @author diogo.melo
 */
public class Cronometro {

    public static void iniciaCronometro(int tempo) {
        new java.util.Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                Modal.telaPai = TelaApontamentoParada.getTela();
                Modal.getTela(TelaApontamentoParada.getTela()).setVisible(true);
                Modal.telaPai.moveToFront();
            }
        }, tempo);
    }

    public void contador() {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        // Do something
        stopWatch.stop();
        System.out.println(stopWatch.getTime());;
    }
}
