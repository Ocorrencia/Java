package componente;

import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class MeuCampoSenha extends JPasswordField implements MeuComponente {

    private boolean obrigatorio;
    private String dica;

    public MeuCampoSenha(boolean obrigatorio, String dica) {
        this.obrigatorio = obrigatorio;
        this.dica = dica;
        try {
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Campo Com valores Invalidos");
        }
        setColumns(30);
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
