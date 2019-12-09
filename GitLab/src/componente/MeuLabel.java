/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package componente;

import javax.swing.JLabel;

/**
 *
 * @author USER
 */
public class MeuLabel extends JLabel implements MeuComponente {

    private boolean obrigatorio;
    private String dica;

    public MeuLabel(String dica) {
         this.dica = dica;
        
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
