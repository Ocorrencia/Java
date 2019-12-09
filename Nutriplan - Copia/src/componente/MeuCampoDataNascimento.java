package componente;

import java.util.Date;

public class MeuCampoDataNascimento extends MeuCalendario {

    public MeuCampoDataNascimento(boolean obrigatorio, String dica) {
        super(dica, obrigatorio);
    }

    @Override
    public boolean eValido() {
        Date dataAtual = new Date();
        if (!eVazio()) {
            return campo.getDate().before(dataAtual);
        } else {
            return true;
        }
    }
}
