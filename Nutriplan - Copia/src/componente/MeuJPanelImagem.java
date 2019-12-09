package componente;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 *
 * @author diogo.melo
 */
public class MeuJPanelImagem extends JPanel {

    private BufferedImage imagem;
    URL urlLoginNutriplan = getClass().getResource("/imagem/loginNutriplan.png");
    URL urlTopo = getClass().getResource("/imagem/iconePrincipal.png");

    public MeuJPanelImagem() throws Exception {
        imagem = ImageIO.read(urlLoginNutriplan);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        int x = (this.getWidth() - imagem.getWidth(null)) / 2;
        int y = (this.getHeight() - imagem.getHeight(null)) / 2;
        g.drawImage(imagem, x, y, this);
        g2d.dispose();
    }
}
