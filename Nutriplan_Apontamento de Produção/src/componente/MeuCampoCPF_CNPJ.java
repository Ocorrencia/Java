package componente;

import java.text.ParseException;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.text.Caret;
import javax.swing.text.MaskFormatter;
import util.Validacao_CPF_CNPJ;

public class MeuCampoCPF_CNPJ extends MeuCampoFormatado implements CaretListener{

    MaskFormatter mf = new MaskFormatter();

    public MeuCampoCPF_CNPJ(String dica, boolean obrigatorio,String tipo) {
        super(dica,obrigatorio,12);
        addCaretListener(this);
        setToolTipText("É NECESSÁRIO SELECIONAR O TIPO PESSOA PARA PREENCHER O CAMPO CPF / CNPJ");

        if(tipo.equals("cnpj")){
            try{
                mf.uninstall();
                mf = new MaskFormatter("##.###.###/####-##");
                mf.setPlaceholderCharacter('_');
            }catch(ParseException e){
                e.printStackTrace();
            }
        }
        else if(tipo.equals("cpf")){
            try{
                mf.uninstall();
                mf = new MaskFormatter("###.###.###-##");
                mf.setPlaceholderCharacter('_');
            }catch(ParseException e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public void caretUpdate(CaretEvent e) {
        if (eVazio()) {
            getCaret().setSelectionVisible(false);
            getCaret().setDot(0);
            getCaret().setSelectionVisible(true);
        }
    }

    @Override
    public boolean eValido() {
        
        if(eVazio()){
            return false;
        }
        
        String pega_valor = getText();// pega o texto do campo
        return (pega_valor.length()>14) ? Validacao_CPF_CNPJ.validaCNPJ(pega_valor) : (pega_valor.length()<=14) ? Validacao_CPF_CNPJ.validaCPF(pega_valor) : false;
    }

    @Override
    public boolean eVazio() {
        return (getText().equals("___.___.___-__") || getText().equals("__.___.___/____-__"));
    }
}
