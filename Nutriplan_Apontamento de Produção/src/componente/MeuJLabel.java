package componente;


import java.awt.Font;
import javax.swing.JLabel;

public class MeuJLabel extends JLabel implements MeuComponente{
    private boolean obrigatorio;
    private String nome;

    public MeuJLabel(String nome){
        super(nome);
        this.nome = nome;
        this.setFont(new Font("Calibri", Font.BOLD, 12));
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
        return "";
    }
}
