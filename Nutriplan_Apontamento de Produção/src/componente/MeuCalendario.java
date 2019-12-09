package componente;

import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JDayChooser;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JPanel;

public class MeuCalendario extends JPanel implements MeuComponente {

    public JDateChooser campo = new JDateChooser("dd/MM/yyyy", "##/##/####", '_');
    protected JDayChooser c = new JDayChooser();
    protected String dica;
    protected boolean obrigatorio;
    protected SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    public MeuCalendario(String dica, boolean obrigatorio) {
        setLayout(new FlowLayout(0, 0, 0));
        this.dica = dica;
        this.obrigatorio = obrigatorio;
        campo.setPreferredSize(new Dimension(120, 25));
        campo.setEnabled(false);
        add(campo);
    }
    
    public MeuCalendario(String dica, boolean obrigatorio,String nada) {
        setLayout(new FlowLayout(0, 0, 0));
        
        Calendar maxData = Calendar.getInstance();
        maxData.set(Calendar.DAY_OF_MONTH,1);
        
        Calendar minData = Calendar.getInstance();
        minData.add(Calendar.MONTH,1);
        minData.set(Calendar.DAY_OF_MONTH, 1);
        minData.add(Calendar.DAY_OF_MONTH, -1);
        
        campo.setMinSelectableDate(maxData.getTime());
        campo.setMaxSelectableDate(minData.getTime());
        this.dica = dica.toUpperCase();
        this.obrigatorio = obrigatorio;
        campo.setPreferredSize(new Dimension(120, 25));
        campo.setEnabled(false);
        add(campo);
    }

    @Override
    public void limpar() {
        campo.setDate(null);
    }

    @Override
    public boolean eValido() {
        return true;
    }

    @Override
    public boolean eObrigatorio() {
        return obrigatorio;
    }

    @Override
    public boolean eVazio() {
        return (campo.getDate() == null);
    }

    @Override
    public void setValor(Object valor) {
        campo.setDate((Date) valor);
    }

    @Override
    public Object getValor() {
        return campo.getDate();
    }

    @Override
    public String getDica() {
        return dica;
    }

    @Override
    public void setEnabled(boolean status) {
        campo.setEnabled(status);
    }
}
