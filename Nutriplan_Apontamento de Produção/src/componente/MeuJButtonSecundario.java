package componente;

import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class MeuJButtonSecundario extends JButton implements MouseListener {

    private ImageIcon iconeMenor;
    private ImageIcon iconeMaior;
    public boolean status;

    public MeuJButtonSecundario(String texto, ImageIcon iconeMenor, ImageIcon iconeMaior) {
        setIcon(iconeMenor);
        this.iconeMenor = iconeMenor;
        this.iconeMaior = iconeMaior;
        setToolTipText(texto);
        setBorder(null);
        setFocusable(false);
        setBorderPainted(false);
        setContentAreaFilled(false);
        addMouseListener(this);
    }

    public MeuJButtonSecundario(String titulo, ImageIcon iconeMenor, String texto) {
        setIcon(iconeMenor);
        setText(titulo);
        setToolTipText(texto);
        setFocusable(true);
    }
    
    public MeuJButtonSecundario(String texto, ImageIcon iconeMenor, ImageIcon iconeMaior, boolean status) {
        setIcon(iconeMenor);
        this.iconeMenor = iconeMenor;
        this.iconeMaior = iconeMaior;
        this.status=status;
        setToolTipText(texto);
        setBorder(null);
        setFocusable(false);
        setBorderPainted(false);
        setContentAreaFilled(false);
        addMouseListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent me) {
        setIcon(iconeMenor);
    }

    @Override
    public void mousePressed(MouseEvent me) {
        if(status==true){
            setIcon(iconeMaior);
        }
    }

    @Override
    public void mouseReleased(MouseEvent me) {
        if (status == true) {
            setIcon(iconeMaior);
        }
    }

    @Override
    public void mouseEntered(MouseEvent me) {
        if (status==true) {
            setIcon(iconeMaior);
        }   
    }

    @Override
    public void mouseExited(MouseEvent me) {
        setIcon(iconeMenor);
    }
    
    public void mudaStatus(boolean status){
        this.status=status;
    }

}
