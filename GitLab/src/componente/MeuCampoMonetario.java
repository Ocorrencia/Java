package componente;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import static javax.swing.SwingConstants.RIGHT;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class MeuCampoMonetario extends MeuCampoFormatado {

    protected boolean permiteNegativo;

    public MeuCampoMonetario(boolean obrigatorio, String dica, int tamanho) {
        super(dica, obrigatorio, tamanho);
        setHorizontalAlignment(RIGHT);
        this.setDocument(new MeuCampoMonetario.MeuDocument(25));
        setText("0.00");
    }

    @Override
    public Object getValor() {
        try {
            return BigDecimal.valueOf(Double.parseDouble(getText().replace(".", "").replace(",", ".") + "0"));//Mais "0" para evitar erros quando o campo está em branco
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Campo" + getDica() + " contém informação incorreta(CampoMonetario)");
            return null;
        }
    }

    @Override
    public void limpar() {
        setText("0.00");
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
        } catch (NumberFormatException e) {
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
                DecimalFormat df = new DecimalFormat("0", new DecimalFormatSymbols(new Locale("pt", "BR")));
                df.setParseBigDecimal(true);
                novoValor = df.format((BigDecimal) valor);
            } else {
                JOptionPane.showMessageDialog(null, "Tipo de entrada (" + valor.getClass().getName() + ") de valor invalido. (CampoMonetario)");
            }
        }
        setText(novoValor);
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
                if (str.contains("-")) { //indica que foi solicitado a mudança de sinal
                    multiplicador = -1;
                    str = str.replace("-", "");
                }
                if (valorAtual.contains("-")) { //indica que foi solicitado a mudança de sinal
                    multiplicador = multiplicador * (-1);
                    valorAtual = valorAtual.replace("-", "");
                }
                valorAtual = "000" + valorAtual + str;
                Double valor = Double.parseDouble(valorAtual);
                valor = valor * multiplicador;
                if (!str.contains(".")) //se não tem "." indica que o valor não tem os centavos
                {
                    valor = valor / 100;
                }
                NumberFormat nf = NumberFormat.getCurrencyInstance();
                String valorFormatado = nf.format(valor).replace("R$ ", "");
                if (valorFormatado.trim().equals("-0,00")) {
                    valorFormatado = "0,00";
                }
                String strBuf = valorFormatado;
                if ((tamanho != null) && (strBuf.length() > (permiteNegativo ? tamanho + 1 : tamanho))) {
                    return;
                }
                super.remove(0, getLength());
                super.insertString(0, strBuf, a);
            } catch (NumberFormatException | BadLocationException e) {
                JOptionPane.showMessageDialog(null, "Não foi possivel formatar / insert(MeuDocument-->CampoMonetario)");
            }
        }

    }
}
