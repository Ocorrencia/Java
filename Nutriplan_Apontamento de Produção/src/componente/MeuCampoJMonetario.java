package componente;

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import static javax.swing.SwingConstants.RIGHT;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class MeuCampoJMonetario extends JLabel implements FocusListener, MeuComponente, CaretListener {

    private boolean obrigatorio;
    private boolean podeHabilitar;
    private String dica;

    protected boolean permiteNegativo;

    public MeuCampoJMonetario(String dica, int tamanho) {
        this.obrigatorio = obrigatorio;
        this.dica = dica;
    }

    @Override
    public Object getValor() {
        try {
            return BigDecimal.valueOf(Double.parseDouble(getText().replace(".", "").replace(",", ".") + "0"));//Mais "0" para evitar erros quando o campo está em branco
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Campo" + getDica() + " contém informação incorreta(CampoMonetario)");
            return null;
        }
    }

    @Override
    public void limpar() {
        setText("0,00");
    }

    @Override
    public boolean eVazio() {
        return getText().equals("0,00");
    }

    @Override
    public boolean eValido() {
        if (!eObrigatorio()) {
            return true;
        }
        try {
            double valor = Double.parseDouble(getText().replace(".", "").replace(",", "."));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public void setValor(Object valor) {
        String novoValor = "0.00";
        if (valor != null) {
            if (valor instanceof String) {
                novoValor = ((String) valor).replace(".", "").replace(",", ".");
            } else if (valor instanceof Double) {
                novoValor = Double.toString((Double) valor);
            } else if (valor instanceof BigDecimal) {
                DecimalFormat df = new DecimalFormat("0.00", new DecimalFormatSymbols(new Locale("pt", "BR")));
                df.setParseBigDecimal(true);
                novoValor = df.format((BigDecimal) valor);
            } else {
                JOptionPane.showMessageDialog(null, "Tipo de entrada (" + valor.getClass().getName() + ") de valor invalido. (CampoMonetario)");
            }
        }
        setText(novoValor);
    }

    @Override
    public void focusGained(FocusEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void focusLost(FocusEvent e) {
    }

    @Override
    public boolean eObrigatorio() {
        return false;
    }

    @Override
    public String getDica() {
        return dica;
    }

    @Override
    public void caretUpdate(CaretEvent e) {
    }

    class MeuDocument extends PlainDocument {

        private Integer tamanho;

        public MeuDocument(int tamanho) {
            this.tamanho = tamanho + 1;//tem que contar o separador de decimal (,)
        }

        @Override
        public void insertString(int offs, String str, AttributeSet a) {
            try {
                Pattern padrao = Pattern.compile("[-0123456789]");
                Matcher matcher = padrao.matcher(str);
                if ((!permiteNegativo) && (str.contains("-")) || (!matcher.find())) {
                    return;
                }
                int multiplicador = 1;
                String valorAtual = getText(0, getLength()).trim().replace(".", "").replace(",", "");
                str = str.trim().replace(",", ".");
                if (str.indexOf("-") >= 0) { //indica que foi solicitado a mudança de sinal
                    multiplicador = -1;
                    str = str.replace("-", "");
                }
                if (valorAtual.indexOf("-") >= 0) { //indica que foi solicitado a mudança de sinal
                    multiplicador = multiplicador * (-1);
                    valorAtual = valorAtual.replace("-", "");
                }
                valorAtual = "000" + valorAtual + str;
                Double valor = Double.parseDouble(valorAtual);
                valor = valor * multiplicador;
                if (str.indexOf(".") == -1) //se não tem "." indica que o valor não tem os centavos
                {
                    valor = valor / 100;
                }
                NumberFormat nf = NumberFormat.getCurrencyInstance();
                String valorFormatado = nf.format(valor).replace("R$ ", "");
                if (valorFormatado.trim().equals("-0,00")) {
                    valorFormatado = "0,00";
                }
                StringBuilder strBuf = new StringBuilder(valorFormatado);
                if ((tamanho != null) && (strBuf.length() > (permiteNegativo ? tamanho + 1 : tamanho))) {
                    return;
                }
                super.remove(0, getLength());
                super.insertString(0, strBuf.toString(), a);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Não foi possivel formatar / insert(MeuDocument-->CampoMonetario)");
            }
        }

    }
}
