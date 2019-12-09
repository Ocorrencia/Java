/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package componente;

import javax.swing.JSeparator;

/**
 *
 * @author USER
 */
public class MeuJSepartor extends JSeparator implements MeuComponente {

    @Override
    public void limpar() {
    }

    @Override
    public boolean eValido() {
        return false;
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
    public void setValor(Object valor) {
    }

    @Override
    public Object getValor() {
        return null;
    }

    @Override
    public String getDica() {
        return null;
    }
    
}
