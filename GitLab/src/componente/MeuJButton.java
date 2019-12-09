package componente;

import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class MeuJButton extends JButton implements MeuComponente{
    private boolean obrigatorio;
    private int largura;
    private int altura;

    public MeuJButton(String nome, ImageIcon imageIcon){
        super(nome, imageIcon);
//    public MeuJButton(String nome){
//        super(nome);
//        this.setPreferredSize(new Dimension(110, 30));
    }

    public MeuJButton(String nome, ImageIcon imageIcon, int largura, int altura){
        super(nome, imageIcon);
        this.largura=largura;
        this.altura=altura;
//    public MeuJButton(String nome){
//        super(nome);
        this.setPreferredSize(new Dimension(this.largura, this.altura));      
//        this.setMaximumSize(new java.awt.Dimension(largura, altura));
    }public MeuJButton(String nome){
        super(nome);
    }

    public boolean eObrigatorio() {
        return obrigatorio;
    }

    public boolean eValido() {
        return true;
    }

    public boolean eVazio() {
        return false;
    }

    public String getDica(){
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
