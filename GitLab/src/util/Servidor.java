/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import dao.RefugoDao;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.Scanner;
import javax.swing.JOptionPane;
import pojo.Refugo;
import tela.TelaApontamentoParada;
import tela.TelaOP;
import static tela.TelaOP.tela;
import tela.TelaRefugo;

/**
 *
 * @author diogo.melo
 */
public class Servidor {

    public static ServerSocket servidor;
    public static Socket cliente;
    CicloAtual ciclo = new CicloAtual();
    boolean controleAcionamento = true;
    Thread thr = new Thread(ciclo);
    RefugoDao refugoDao = new RefugoDao();

    public Servidor() {
        try {
            System.out.println("SERVIDOR RASPERBERRY INICIADO...");
            if (!thr.isAlive()) {
                thr.start();
                CicloAtual.stopWatch.start();
            } else {
                ciclo.ciclo();
            }
            InetAddress addr = InetAddress.getByName("10.1.1.234");
            servidor = new ServerSocket(12000, 1000, addr);
            //  iniciarServidor();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void enviar() {
        try {
            Socket cliente = new Socket("paulo", 12345);
            try (ObjectInputStream entrada = new ObjectInputStream(cliente.getInputStream())) {
                Date data_atual = (Date) entrada.readObject();
                JOptionPane.showMessageDialog(null, "Data recebida do servidor:" + data_atual.toString());
            }
            System.out.println("Conexão encerrada");
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    public void iniciarServidor() {
        try {

            new Thread() {
                @Override
                public void run() {
                    try {
                        while (true) {
                            Socket cliente;
                            cliente = servidor.accept();
                            try (Scanner entrada = new Scanner(cliente.getInputStream())) {
                                while (entrada.hasNextLine()) {
                                    String dados = entrada.nextLine();
                                    if (!dados.equals("")) {
                                        switch (dados) {
                                            case "1":

                                                break;
                                            case "2":
                                                Modal.getTela(tela).setVisible(true);
                                                TelaApontamentoParada.getTela();
                                                break;
                                            case "3":
                                                DadosRaspberry.QUANTIDADEPRODUZIDA = DadosRaspberry.QUANTIDADEPRODUZIDA + 1;
                                                TelaOP.getTela().controleProducao();

                                                break;
                                            case "4":
                                                enviarRefugo();
                                                break;
                                            case "5":
                                                break;
                                            case "6":
                                                break;
                                            case "7":
                                                break;
                                        }
                                    }
                                }
                            }
                            cliente.close();
                            iniciarServidor();
                        }
                    } catch (IOException e) {
                    }
                }
            }.start();
        } catch (Exception e) {
        }
    }

    public ServerSocket getServidor() {
        return servidor;
    }

    public void setServidor(ServerSocket servidor) {
        Servidor.servidor = servidor;
    }

    public Socket getCliente() {
        return cliente;
    }

    public void setCliente(Socket cliente) {
        Servidor.cliente = cliente;
    }

    public void enviarRefugo() {
        if (Enums.STATUSTELA == 2) {
            if (Integer.parseInt(TelaOP.tela.campoQuantidadeProgramada.getText().replace("UN", "").trim()) <= Enums.REFUGOSJUSTIFICADOS + Enums.REFUGOSNAOIDENTIFICADOS) {
                Notificacao.infoBox("QUANTIDADE EXCEDE A PROGRAMADA", false);
            } else if (Integer.parseInt(TelaOP.tela.campoQuantidadeRealizada.getText().replace("UN", "").trim()) == 0) {
                Notificacao.infoBox("QUANTIDADE REALIZADA AINDA É 0", false);
            } else if (Enums.REFUGOSNAOIDENTIFICADOS > Consulta.CONSULTAINT("nutri_op.op900eoq", "SUM(QTDRE1)", "EXPERP = 0 AND QTDRE1 = 1")) {
                Notificacao.infoBox("NÃO HÁ SALDO PARA REFUGO", false);
            } else if (Integer.parseInt(TelaOP.tela.campoQuantidadeProgramada.getText().replace("UN", "").trim()) <= Integer.parseInt(TelaOP.tela.campoQuantidadeRealizada.getText().replace("UN", "").trim())) {
            } else {
                Enums.REFUGOSNAOIDENTIFICADOS = Enums.REFUGOSNAOIDENTIFICADOS + 1;
                if (TelaRefugo.tela != null) {
                    TelaRefugo.tela.campoQuantidadeNaoJustificados.setText("0" + Enums.REFUGOSNAOIDENTIFICADOS);
                }
                TelaOP.tela.campoQuantidadeRefugo.setText(Enums.REFUGOSJUSTIFICADOS + Enums.REFUGOSNAOIDENTIFICADOS + " UN");
                TelaOP.tela.adicionarRefugo(1);
                Refugo.setQuantidade(1);
                refugoDao.atualizar();
            }
        } else {
            Notificacao.infoBox("BOTÃO BLOQUEADO", false);
        }
    }
}
