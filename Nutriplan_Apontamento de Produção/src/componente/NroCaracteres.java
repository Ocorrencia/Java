package componente;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class NroCaracteres extends PlainDocument {

    private int iMaxLength;

    public NroCaracteres(int nrocaracteres) {
        super();
        iMaxLength = nrocaracteres;
    }

    @Override
    public void insertString(int offset, String str, AttributeSet attr)
            throws BadLocationException {
        //if (s == null) return;  

        if (iMaxLength <= 0) // aceitara qualquer no. de caracteres  
        {
            super.insertString(offset, str.toUpperCase().replaceAll("[^A-Z|^ |^@^.^^]", ""), attr);
            return;
        }

        int ilen = (getLength() + str.length());
        if (ilen <= iMaxLength) // se o comprimento final for menor...  
        {
            super.insertString(offset, str.toUpperCase(), attr);   // ...aceita str  
        }
    }

}
