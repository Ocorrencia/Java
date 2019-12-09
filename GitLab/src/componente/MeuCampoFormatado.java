package componente;

import com.alee.laf.text.WebTextField;
import java.awt.Color;
import java.awt.Toolkit;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class MeuCampoFormatado extends WebTextField implements MeuComponente {

    private boolean obrigatorio;
    private String dica;

    public MeuCampoFormatado(boolean obrigatorio, String dica, int tamanho, int maxChar) {
        this.obrigatorio = obrigatorio;
        this.dica = dica;
        setDrawBorder(false);
        try {
            setDocument(new PlainDocument() {
                @Override
                public void insertString(int offs, String str, AttributeSet a)
                        throws BadLocationException {
                    String novoValor = getValor().toString();
                    if (novoValor.length() >= maxChar) {
                        Toolkit.getDefaultToolkit().beep();
                        return;
                    }
                    super.insertString(offs, str.toUpperCase().replaceAll("[^A-Z|^ |^@^.^^]", ""), a);
                }
            });
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Campo Com valores Invalidos");
        }
        this.setHorizontalAlignment(JTextField.CENTER);
    }

    public MeuCampoFormatado(String dica, boolean obrigatorio, int tamanho, int maxChar) {
        this.obrigatorio = obrigatorio;
        this.dica = dica;
        setColumns(tamanho);
         setDrawBorder(false);
        try {
            setDocument(new PlainDocument() {
                @Override
                public void insertString(int offs, String str, AttributeSet a)
                        throws BadLocationException {
                    String novoValor = getValor().toString();
                    if (novoValor.length() >= maxChar) {
                        Toolkit.getDefaultToolkit().beep();
                        return;
                    }
                    super.insertString(offs, str.toUpperCase(), a);
                }
            });
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Campo Com valores Invalidos");
        }
        
        this.setHorizontalAlignment(JTextField.CENTER);
    }

    public MeuCampoFormatado(String dica, boolean obrigatorio, int tamanho) {
        this.obrigatorio = obrigatorio;
        this.dica = dica;
        setColumns(tamanho);
          setDrawBorder(false);
        try {
            setDocument(new PlainDocument() {
                @Override
                public void insertString(int offs, String str, AttributeSet a)
                        throws BadLocationException {
                    String novoValor = getValor().toString();
                    super.insertString(offs, str.toUpperCase(), a);
                }
            });
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Campo Com valores Invalidos");
        }

        if (eObrigatorio()) {
            setBackground(Color.WHITE);
        }
        this.setHorizontalAlignment(JTextField.CENTER);
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
}
