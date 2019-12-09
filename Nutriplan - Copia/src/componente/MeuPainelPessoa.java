package componente;

import java.awt.FlowLayout;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.TitledBorder;

public class MeuPainelPessoa extends JPanel implements MeuComponente {

    public static JRadioButton pFisica = new JRadioButton("Física");
    public static JRadioButton pJuridica = new JRadioButton("Jurídica");
    public static ButtonGroup bg = new ButtonGroup();

    public MeuPainelPessoa() {
        setLayout(new FlowLayout());
        TitledBorder titulo;
        titulo = BorderFactory.createTitledBorder("Tipo Pessoa");
        titulo.setTitleJustification(TitledBorder.CENTER);
        setBorder(titulo);
        bg.add(pFisica);
        bg.add(pJuridica);
        add(pFisica);
        add(pJuridica);
    }

    @Override
    public void limpar() {
        bg.clearSelection();
    }

    @Override
    public boolean eValido() {
        return (pFisica.isSelected() || pJuridica.isSelected());
    }

    @Override
    public boolean eObrigatorio() {
        return true;
    }

    @Override
    public boolean eVazio() {
        return (!pFisica.isSelected() && !pJuridica.isSelected());
    }

    @Override
    public void setValor(Object valor) {
        if (valor.equals("Física")) {
            pFisica.setSelected(true);
        } else {
            pJuridica.setSelected(true);
        }
    }

    @Override
    public Object getValor() {
        if (pFisica.isSelected()) {
            return "Física";
        } else {
            return "Jurídica";
        }
    }

    @Override
    public String getDica() {
        return "";
    }
}
