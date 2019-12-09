package componente;

import static javax.swing.SwingConstants.RIGHT;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class MeuCampoInteiro extends MeuCampoGenerico {

    private int iMaxLength;
    int retorno;

    public MeuCampoInteiro(boolean obrigatorio, String dica, int tamanho, int maxChar) {
        super(dica, obrigatorio, tamanho);
        iMaxLength = maxChar;
        setColumns(tamanho);
        setDocument(new PlainDocument() {
            @Override
            public void insertString(int offs, String str, AttributeSet a)
                    throws BadLocationException {
                if (iMaxLength <= 0) // aceitara qualquer no. de caracteres  
                {
                    super.insertString(offs, str.toUpperCase().replaceAll("[^0-9]", ""), a);
                }

                int ilen = (getLength() + str.length());
                if (ilen <= iMaxLength) // se o comprimento final for menor...  
                {
                    super.insertString(offs, str.toUpperCase().replaceAll("[^0-9]", ""), a);
                }
            }
        });
        setHorizontalAlignment(RIGHT);
        setText("");
    }

    @Override
    public boolean eVazio() {
        return getText().trim().equals("0,00");
    }

    @Override
    public void setValor(Object valor) {
        setText("" + (Integer) valor);
    }

    @Override
    public Object getValor() {
        try {
            retorno = (Integer.parseInt(0 + getText()));
        } catch (NumberFormatException e) {
        }
        return retorno;
    }

    @Override
    public void limpar() {
        setText(" ");
    }

    @Override
    public boolean eValido() {
        try {
            int valor = Integer.parseInt(getText());
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
