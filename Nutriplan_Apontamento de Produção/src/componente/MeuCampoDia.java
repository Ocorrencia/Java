package componente;

import componente.MeuCampoFormatado;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class MeuCampoDia extends MeuCampoGenerico {

    public MeuCampoDia(boolean obrigatorio, String dica, int tamanho) {
          super(dica, obrigatorio, tamanho);
        setDocument(new PlainDocument() {
            @Override
            public void insertString(int offs, String str, AttributeSet a)
                    throws BadLocationException {
                super.insertString(offs, str.toUpperCase().replaceAll("[^0-9]", ""), a);
            }
        });
        setHorizontalAlignment(RIGHT);
        setColumns(2);
        setText("");
    }

    @Override
    public boolean eVazio() {
        return getText().trim().equals("");
    }

    @Override
    public void setValor(Object valor) {
        setText("" + (Integer) valor);
    }

    @Override
    public Object getValor() {
        return (Float.parseFloat("0" + getText()));
    }

    @Override
    public void limpar() {
        setText("");
    }

    @Override
    public boolean eValido() {
        try {
            Float valor = Float.parseFloat("" + getText());
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
