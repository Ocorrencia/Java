
package componente;


import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Timer;

public class Hora implements MeuComponente{
    private DecimalFormat formato = new DecimalFormat("00");
    private Calendar hora;
    private Timer tempo;
    public String sHora;
    private int iHora;
    private int iMin;
    private int iSeg;

    public Hora(){
        horas();
    }
    public void horas(){
        hora = Calendar.getInstance();
        iHora = hora.get(Calendar.HOUR_OF_DAY);
        iMin = hora.get(Calendar.MINUTE);
        iSeg = hora.get(Calendar.SECOND);
        sHora = formatar(iHora%24) + ":" + formatar(iMin) + ":" + formatar(iSeg);
    }

    private String formatar(int num){
        return formato.format(num);
    }

    @Override
    public boolean eObrigatorio() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean eValido() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean eVazio() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setEnabled(boolean status) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void requestFocus() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getDica() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
