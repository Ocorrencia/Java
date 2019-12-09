/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tela;

import componente.MeuComboBox;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.net.URL;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import pojoWebService.MovimentoOrdemProducaoWebService;
import util.Consulta;
import util.ConsultaOracle;
import util.Enums;
import util.Modal;
import util.Notificacao;

public class TelaSincronizacao extends TelaCadastro {

    MeuComboBox campoNomeMaquina = new MeuComboBox("SELECT CODCRE, DESCRE ||' - '|| USU_ENDAIP FROM SAPIENS.E725CRE ORDER BY DESCRE", true, "Máquina:");
    MeuComboBox campoNomeCentroRecurso = new MeuComboBox("SELECT DISTINCT CRE.CODCCU, UPPER(CCU.DESCCU) DESCCU\n"
            + "  FROM SAPIENS.E725CRE CRE\n"
            + " INNER JOIN SAPIENS.E093ETG ETG\n"
            + "    ON ETG.CODEMP = CRE.CODEMP\n"
            + "   AND ETG.CODETG = CRE.CODETG\n"
            + " INNER JOIN SAPIENS.E044CCU CCU\n"
            + "    ON CCU.CODEMP = CRE.CODEMP\n"
            + "   AND CCU.CODCCU = CRE.CODCCU\n"
            + " WHERE CRE.TIPCRE = 'I'\n"
            + "   AND ETG.TIPETG = 'I'", true, "Centro Custo:");
    URL urlTopo = getClass().getResource("/imagem/iconePrincipal.png");
    ImageIcon iconeprincipal = new ImageIcon(urlTopo);
    private static TelaSincronizacao tela;
    MovimentoOrdemProducaoWebService mvWebService = new MovimentoOrdemProducaoWebService();
    Thread threadEnvio;
    Thread threadEnvioCentroRecurso;

    public static TelaSincronizacao getTela() {
        if (tela == null) {
            tela = new TelaSincronizacao();
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

    public TelaSincronizacao() {
        super("Sincronização");
        setVisible(true);
        // this.setSize(400, 260);
        this.setFrameIcon(iconeprincipal);
        adicionarComponente();
        modificarBotoes();
        adicionarListener();
        adicionarItemListener();
        pack();
    }

    private void adicionarComponente() {
        migLayout(0, 1, 15, 0, 1, 70, 60, campoNomeMaquina, "N", "N");
        migLayout(0, 2, 15, 0, 1, 70, 60, campoNomeCentroRecurso, "N", "N");

    }

    private void modificarBotoes() {
        btnIncluir.setEnabled(false);
        btnConfirmar.setEnabled(false);
        btnExcluir.setEnabled(false);
        btnAlterar.setText("Sincronizar");
    }

    private void adicionarListener() {
        btnAlterar.addActionListener((ActionEvent e) -> {

            if (campoNomeMaquina.getSelectedIndex() < 0 && campoNomeCentroRecurso.getSelectedIndex() < 0) {
                Notificacao.infoBox("SELECIONE UMA DAS OPÇÕES", false);
                return;
            }
            if (campoNomeMaquina.getSelectedIndex() >= 0) {
                String ip = ConsultaOracle.CONSULTASTRING("SAPIENS.E725CRE", "COALESCE(USU_ENDAIP, 'VAZIO') AS USU_ENDAIP", "CODCRE = '" + campoNomeMaquina.getValor() + "'");
                if (!"VAZIO".equals(ip)) {
                    Enums.IPMAQUINA = ip;
                    if (!"VAZIO".equals(Consulta.CONSULTASTRING("nutri_op.op900eoq eoq", "COUNT(1)", "eoq.EXPERP = 0"))) {
                        Notificacao.infoBox("NÃO EXISTEM INF. PARA INTEGRAR", false);
                        return;
                    }
                    threadEnvio = new Thread(new ThreadEnvio());
                    threadEnvio.start();
                } else {
                    Notificacao.infoBox("NÃO HÁ IP CADASTRADO PARA ESTA MÁQUINA", false);
                }
            } else {
                List<String> arrayIp = ConsultaOracle.CONSULTAARRAYSTRING("SAPIENS.E725CRE CRE", "COALESCE(CRE.USU_ENDAIP, 'NOIP') AS ENDERECOIP", "CODCCU ='" + campoNomeCentroRecurso.getValor() + "' AND USU_ENDAIP <> 'NOIP'");
                if (arrayIp.size() > 0) {
                    if (!"VAZIO".equals(Consulta.CONSULTASTRING("nutri_op.op900eoq eoq", "COUNT(1)", "eoq.EXPERP = 0"))) {
                        Notificacao.infoBox("NÃO EXISTEM INF. PARA INTEGRAR", false);
                        return;
                    }
                    threadEnvioCentroRecurso = new Thread(new ThreadEnvioCentroRecurso());
                    threadEnvioCentroRecurso.start();
                } else {
                    Notificacao.infoBox("NÃO HÁ IP CADASTRADO PARA ESTE CENTRO DE RECURSO", false);
                }
            }
        });
        btnCancelar.addActionListener((ActionEvent e) -> {
            tela.dispose();
        });
    }

    private void adicionarItemListener() {
        campoNomeCentroRecurso.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if ((e.getSource() == campoNomeCentroRecurso) && (e.getStateChange() == 1)) {
                    campoNomeMaquina.setSelectedIndex(-1);
                }
            }
        });
        campoNomeMaquina.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if ((e.getSource() == campoNomeMaquina) && (e.getStateChange() == 1)) {
                    campoNomeCentroRecurso.setSelectedIndex(-1);
                }
            }
        });
    }

    public class ThreadEnvioCentroRecurso implements Runnable {

        @Override
        public void run() {
            TelaLoading tela1 = TelaLoading.getTela("Recebendo dados via WEBSERVICE...");
            Modal.getTela(tela1).setVisible(true);
            tela1.moveToFront();
            Modal.telaPai = tela1;

            List<String> arrayIp = ConsultaOracle.CONSULTAARRAYSTRING("SAPIENS.E725CRE CRE", "COALESCE(CRE.USU_ENDAIP, 'NOIP') AS ENDERECOIP", "CODCCU ='" + campoNomeCentroRecurso.getValor() + "' AND USU_ENDAIP <> 'NOIP'");
            if (arrayIp.size() > 0) {
                Notificacao.infoBox("NÃO EXISTEM INF. PARA INTEGRAR", false);
            }
            for (String itemIP : arrayIp) {
                Enums.IPMAQUINA = itemIP;

                if (!mvWebService.enviarMovimentoOrdemProducaoSapiens()) {
                    Notificacao.infoBox("OCORREU UM ERRO AO INTEGRAR", false);
                } else {
                    Notificacao.infoBox("INTEGRAÇÃO EFETUADA COM SUCESSO", true);
                }
            }

            tela1.getTela("").dispose();
            Modal.getTela(tela1).dispose();
            threadEnvioCentroRecurso.interrupt();
        }
    }

    public class ThreadEnvio implements Runnable {

        @Override
        public void run() {
            TelaLoading tela1 = TelaLoading.getTela("Recebendo dados via WEBSERVICE...");

            Modal.getTela(tela1).setVisible(true);
            tela1.moveToFront();
            Modal.telaPai = tela1;

            if (!"VAZIO".equals(Consulta.CONSULTASTRING("nutri_op.op900eoq eoq", "COUNT(1)", "eoq.EXPERP = 0"))) {
                if (!mvWebService.enviarMovimentoOrdemProducaoSapiens()) {
                    Notificacao.infoBox("OCORREU UM ERRO AO INTEGRAR", false);
                } else {
                    Notificacao.infoBox("INTEGRAÇÃO EFETUADA COM SUCESSO", true);
                }
            } else {
                Notificacao.infoBox("NÃO EXISTEM INF. PARA INTEGRAR", false);
            }

            tela1.getTela("").dispose();
            Modal.getTela(tela1).dispose();
            threadEnvio.interrupt();
        }
    }
}
