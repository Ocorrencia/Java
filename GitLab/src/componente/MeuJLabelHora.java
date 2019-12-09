package componente;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.swing.Timer;
import javax.swing.JLabel;

public class MeuJLabelHora extends JLabel implements ActionListener{
    private DateFormat formataHora = new SimpleDateFormat("HH:mm:ss");
    private Timer tempo = new Timer(0,null);

    private Hora hora = new Hora();
    public MeuJLabelHora(){
        tempo.addActionListener(this);
        tempo.start();
    }

    public void actionPerformed(ActionEvent e) {
        this.setText(formataHora.format(System.currentTimeMillis()));
    }

}
