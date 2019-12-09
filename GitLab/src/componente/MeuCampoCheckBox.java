package componente;

import javax.swing.JCheckBox;

public class MeuCampoCheckBox extends JCheckBox implements MeuComponente {

    protected String dica;

    public MeuCampoCheckBox(String dica) {
        this.dica = dica.toUpperCase();
        setSelected(true);
    }

    @Override
    public void limpar() {
        setSelected(true);
    }

    @Override
    public boolean eValido() {
        return true;
    }

    @Override
    public boolean eObrigatorio() {
        return false;
    }

    @Override
    public boolean eVazio() {
        return false;
    }

    @Override
    public String getDica() {
        return dica;
    }

    @Override
    public void setValor(Object valor) {
        setSelected(((String) valor).toUpperCase().equals("A"));
    }

    @Override
    public Object getValor() {
        return isSelected() ? "A" : "I";
    }
}
