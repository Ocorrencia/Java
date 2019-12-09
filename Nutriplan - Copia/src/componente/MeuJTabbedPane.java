

package componente;

import javax.swing.JTabbedPane;



public class MeuJTabbedPane extends JTabbedPane implements MeuComponente{

    public MeuJTabbedPane(){
        super();
        
    }

    public boolean eObrigatorio() {
        return false;
    }

    public boolean eValido() {
        return true;
    }

    public boolean eVazio() {
        return false;
    }

    public String getDica() {
        return "";
    }

    @Override
    public void limpar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setValor(Object valor) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object getValor() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }



}
