/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import org.apache.commons.lang.time.StopWatch;
import tela.TelaApontamentoParada;
import tela.TelaOP;

/**
 *
 * @author diogo.melo
 */
public class CicloAtual implements Runnable {

    public static StopWatch stopWatch = new StopWatch();
    Integer tempo;

    @Override
    public void run() {
        while (true) {
            tempo = ((int) stopWatch.getTime()) / 1000;
            if (tempo > 120 && tempo != 0) {
                    System.out.println((int) stopWatch.getTime() / 1000);
                    stopWatch.reset();
                    Modal.telaPai = TelaApontamentoParada.getTela();
                    Modal.getTela(TelaApontamentoParada.getTela()).setVisible(true);
                    Modal.telaPai.moveToFront();
            }
        }
    }

    public synchronized void ciclo() {

        if (DadosRaspberry.QUANTIDADEPRODUZIDA != 0) {
            Enums.CICLOATUAL = ((int) stopWatch.getTime() / 1000);
            TelaOP.getTela().campoCicloPadraoAtual.setText(TelaOP.getTela().ordemProducao.getCicPad().intValue() + "S/" + Enums.CICLOATUAL + "S");
        }
        stopWatch.reset();
        stopWatch.start();
        this.notifyAll();
    }

}
