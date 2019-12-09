package componente;

import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.*;

public class MeuToolBar extends JToolBar {

    public MeuToolBar() {
        super();
        //add(btNome);
        //this.setBorder(BorderFactory.createEtchedBorder());
        //Não deixa mudar a Tool de lugar, fixa ela
        setFloatable(false);
        //Troca a cor de fundo da dica dos botoes da barra
        UIManager.put("ToolTip.background", SystemColor.controlShadow);
//        troca a cor da letra da dica dos botoes da barra
        UIManager.put("ToolTip.foreground", Color.TRANSLUCENT);
        setRollover(true);

    }

}
