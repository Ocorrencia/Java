package componente;

import javax.swing.JLabel;

public class MeuJLabelData extends JLabel{
    Data data = new Data();
    public MeuJLabelData(){
        this.setText(data.sDataEscrita);        
    }

}
