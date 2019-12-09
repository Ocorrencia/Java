package componente;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.JComponent;
import javax.swing.JPanel;

public class MeuPainelBotao extends JPanel {

    private int larguraPainel, alturaPainel, larguraTela, alturaTela, posY;

    public MeuPainelBotao(final JComponent painelPai) {
        painelPai.add(this);
        painelPai.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent ce) {
                larguraTela = painelPai.getSize().width;
                alturaTela = painelPai.getSize().height;
                larguraPainel = getPreferredSize().width;
                alturaPainel = getPreferredSize().height;
                posY = alturaTela - alturaPainel;
                setBounds((larguraTela - larguraPainel) / 2, alturaTela-getPreferredSize().height, larguraPainel, alturaPainel);
            }
        });
    }
}
