package componente;

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class MeuCampoSigla extends JFormattedTextField implements FocusListener, MeuComponente, CaretListener {

    private boolean obrigatorio;
    private String dica;
    private int iMaxLength;

    public MeuCampoSigla(boolean obrigatorio, String dica) {
        this.obrigatorio = obrigatorio;
        this.dica = dica;
        try {

            setDocument(new PlainDocument() {

                @Override
                public void insertString(int offs, String str, AttributeSet a)
                        throws BadLocationException {
                    String novoValor = getValor().toString();
                    if (novoValor.length() >= 2) {
                        Toolkit.getDefaultToolkit().beep();
                        return;
                    }
                    super.insertString(offs, str.toUpperCase().replaceAll("[^A-Z|^ |^@^.^^]", ""), a);
                }
            });
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Campo Com valores Invalidos");
        }
        setColumns(2);
        addFocusListener(this);
        if (eObrigatorio()) {
            setBackground(Color.WHITE);
        }
    }

    public MeuCampoSigla(String dica, boolean obrigatorio) {
        this.obrigatorio = obrigatorio;
        this.dica = dica;
//        try {
//            setDocument(new PlainDocument() {
//                @Override
//                public void insertString(int offs, String str, AttributeSet a)
//                        throws BadLocationException {
//                    super.insertString(offs, str.toUpperCase().replaceAll("[^A-Z|^ |^@^.]", ""), a);
//                }
//            });
//        } catch (Exception e) {
//            JOptionPane.showMessageDialog(null, "Campo Com valores Invalidos");
//        }

        setColumns(2);
        addFocusListener(this);
        if (eObrigatorio()) {
            setBackground(Color.WHITE);
        }
    }

    @Override
    public void focusGained(FocusEvent e) {
        setBackground(new Color(240, 230, 210));
    }

    @Override
    public void focusLost(FocusEvent e) {
        if (eObrigatorio()) {

            setBackground(Color.LIGHT_GRAY);
        } else {
            setBackground(Color.WHITE);
        }

    }

    @Override
    public void limpar() {
        setText("");
    }

    @Override
    public boolean eValido() {
        return true;
    }

    @Override
    public boolean eObrigatorio() {
        return obrigatorio;
    }

    @Override
    public boolean eVazio() {
        return getText().trim().isEmpty();
    }

    @Override
    public void setValor(Object valor) {
        setText("" + valor);
    }

    @Override
    public Object getValor() {
        return "" + getText();
    }

    @Override
    public String getDica() {
        return dica;
    }

    @Override
    public void caretUpdate(CaretEvent ce) {
    }
}
