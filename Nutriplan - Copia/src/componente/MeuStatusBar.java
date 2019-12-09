package componente;

import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class MeuStatusBar extends JPanel{

    private MeuJLabelData data = new MeuJLabelData();
    private MeuJLabelHora hora = new MeuJLabelHora();
    private MeuJLabel statusUsuario = new MeuJLabel("Usuário: Administrador");
    private MeuJLabel statusGerencial = new MeuJLabel("Sistema Gerencial");

    private JPanel painelData = new JPanel(new GridLayout(1, 1));
    private JPanel painelHora = new JPanel(new GridLayout(1, 1));
    private JPanel painelUsuario = new JPanel(new GridLayout(1, 1));
    private JPanel painelTipoUser = new JPanel(new GridLayout(1, 1));

    public MeuStatusBar(){
        this.setLayout(new GridLayout(1, 3));
        this.setBorder(BorderFactory.createEtchedBorder());

        data.setHorizontalAlignment(SwingConstants.CENTER);
        hora.setHorizontalAlignment(SwingConstants.CENTER);
        statusUsuario.setHorizontalAlignment(SwingConstants.CENTER);
        statusGerencial.setHorizontalAlignment(SwingConstants.CENTER);

        painelTipoUser.add(statusGerencial);
        painelUsuario.add(statusUsuario);
        painelData.add(data);
        painelHora.add(hora);
        

        painelData.setBorder(BorderFactory.createEtchedBorder());
        painelHora.setBorder(BorderFactory.createEtchedBorder());
        painelUsuario.setBorder(BorderFactory.createEtchedBorder());
        painelTipoUser.setBorder(BorderFactory.createEtchedBorder());

        this.add(painelData);
        this.add(painelHora);
        this.add(painelUsuario);
        this.add(painelTipoUser);
    }

}
