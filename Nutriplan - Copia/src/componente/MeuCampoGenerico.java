package componente;

import java.awt.Color;
import java.awt.event.FocusEvent;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class MeuCampoGenerico extends MeuCampoFormatado {

    public MeuCampoGenerico(String dica, boolean obrigatorio, int tamanho, int maxChar) {
        super(dica, obrigatorio, tamanho, maxChar);

        setDocument(new PlainDocument() {
            @Override
            public void insertString(int offs, String str, AttributeSet a)
                    throws BadLocationException {
                super.insertString(offs, str.toUpperCase(), a);
            }
        });

        if (eObrigatorio()) {
            setBackground(Color.WHITE);
        }
    }

    public MeuCampoGenerico(String dica, boolean obrigatorio, int tamanho) {
        super(dica, obrigatorio, tamanho);

        setDocument(new PlainDocument() {
            @Override
            public void insertString(int offs, String str, AttributeSet a)
                    throws BadLocationException {
                super.insertString(offs, str.toUpperCase(), a);
            }
        });

        if (eObrigatorio()) {
            setBackground(Color.WHITE);
        }
    }
}
