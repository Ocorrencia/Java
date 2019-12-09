package componente;

public interface MeuComponente {

    public void setEnabled(boolean status);

    public void limpar();

    public boolean eValido();

    public boolean eObrigatorio();

    public boolean eVazio();

    public void setValor(Object valor);

    public Object getValor();

    public String getDica();

    public void requestFocus();
}
