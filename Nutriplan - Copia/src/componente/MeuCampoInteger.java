package componente;

import componente.MeuCampoFormatado;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class MeuCampoInteger extends MeuCampoGenerico {

    public MeuCampoInteger(boolean obrigatorio, String dica, int tamanho) {
        super(dica, obrigatorio, tamanho);
        setDocument(new PlainDocument() {
            @Override
            public void insertString(int offs, String str, AttributeSet a)
                    throws BadLocationException {
                super.insertString(offs, str.toUpperCase().replaceAll("[^0-9]", ""), a);
            }
        });
        setHorizontalAlignment(RIGHT);
        //  setColumns(10);
        setText("");
    }

    @Override
    public boolean eVazio() {
        return getText().trim().equals("");
    }

    @Override
    public void setValor(Object valor) {
        setText("" + (Float) valor);
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
