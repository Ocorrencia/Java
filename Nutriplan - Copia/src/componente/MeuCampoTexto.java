
package componente;

import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.JTextArea;

public class MeuCampoTexto extends JTextArea implements FocusListener, MeuComponente {

    private boolean obrigatorio;
    private String dica;

    public MeuCampoTexto(boolean obrigatorio, String dica) {
        this.obrigatorio = obrigatorio;
        this.dica = dica;
        setColumns(14);
        setLineWrap(false);
        addFocusListener(this);

        if (eObrigatorio()) {
            setBackground(Color.GRAY);
        }
    }

    @Override
    public void focusGained(FocusEvent e) {
        setBackground(new Color(240, 230, 210));
    }

    @Override
    public void focusLost(FocusEvent e) {
        if (eObrigatorio()) {
            setBackground(Color.GRAY);
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
}
