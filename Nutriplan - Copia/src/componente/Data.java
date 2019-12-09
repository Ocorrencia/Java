package componente;

import java.util.Calendar;

public class Data {

    private Calendar data;
    private int iDiaSemana;
    private int iDia;
    private int iMes;
    private int iAno;
    private String sDiaSemana[] = {"Domingo", "Segunda", "Terça", "Quarta", "Quinta", "Sexta", "Sábado"};
    private String sMes[] = {"Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"};
    private String sMes2;
    private String sDia;
    private String sDia2;
    public String sDataEscrita;
    public String sDataNumerica;

    public Data() {
        data = Calendar.getInstance();
        iDiaSemana = data.get(Calendar.DAY_OF_WEEK);
        iDia = data.get(Calendar.DAY_OF_MONTH);
        iMes = data.get(Calendar.MONTH);
        iAno = data.get(Calendar.YEAR);
        sDataEscrita = (sDiaSemana[iDiaSemana - 1] + ", " + iDia + " de " + sMes[iMes] + " de " + iAno);
        sDia2 = (Integer.toString(iDia));
        if (sDia2.length() == 1) {
            sDia2 = "0" + iDia;
        }

        iMes = iMes + 1;
        sMes2 = (Integer.toString(iMes));
        if (sMes2.length() == 1) {
            sMes2 = "0" + iMes;
        }

        sDataNumerica = (sDia2 + "/" + sMes2 + "/" + iAno);
    }

}
