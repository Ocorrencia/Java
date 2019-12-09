package componente;


import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;
import tela.TelaSistema;

public class MensagensSistema {

    private static Icon msgAtencao = new ImageIcon("src/imagens/atencao.png");
    private static Icon msgErro = new ImageIcon("src/imagens/erro.png");
    private static Icon msgSucesso = new ImageIcon("src/imagens/sucesso.png");

    private static final JLabel labelOperador = new JLabel();

    private static final JOptionPane jop = new JOptionPane();

    static int resposta = 14;

    public static void MensagemAtencao(String msg) {
//       UIManager.put("OptionPane.messageFont", new FontUIResource(new Font("Times New Roman", Font.BOLD, 14)));
        JOptionPane.showMessageDialog(null, msg, "ATENÇÃO", JOptionPane.PLAIN_MESSAGE);
    }

    public static void MensagemSucesso(String msg) {
//        UIManager.put("OptionPane.messageFont", new FontUIResource(new Font("Times New Roman", Font.BOLD, 14)));
        JOptionPane.showMessageDialog(null, msg, "SUCESSO", JOptionPane.PLAIN_MESSAGE);
    }

    public static void MensagemErro(String msg) {
//        UIManager.put("OptionPane.messageFont", new FontUIResource(new Font("Times New Roman", Font.BOLD, 14)));
        JOptionPane.showMessageDialog(null, msg, "ERRO", JOptionPane.PLAIN_MESSAGE);
    }

    public static String MensagemConfirmarOperacao1(String msg) {
//        UIManager.put("OptionPane.messageFont", new FontUIResource(new Font("Times New Roman", Font.BOLD, 14)));
        jop.setMaximumSize(new Dimension(800, 470));
        labelOperador.setText("<html><body><p style='width: 470px;' align='center'>" + msg + "</p></body></html>");
        labelOperador.setFont(new Font("Arial", Font.BOLD, 40));

        jop.setMessage(labelOperador);
        jop.setMessageType(JOptionPane.INFORMATION_MESSAGE);

        JButton jbSim = getButton(jop, "SIM");
        JButton jbNao = getButton(jop, "NÃO");

        jop.setOptions(new Object[]{jbSim, jbNao});

        JDialog dialog = jop.createDialog(TelaSistema.jdp, "OPERADOR SELECIONADO");
        dialog.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        dialog.setVisible(true);
        dialog.setAlwaysOnTop(true);
        return (String) jop.getValue();
    }

    public static JButton getButton(final JOptionPane optionPane, String text) {
        final JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 40));
        ActionListener actionListener = new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                optionPane.setValue(button.getText());
            }
        };
        button.addActionListener(actionListener);
        return button;
    }
}
