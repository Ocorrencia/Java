package util;

import javax.swing.JOptionPane;

public class Validacao_CPF_CNPJ {  
    
    public static boolean validaCNPJ(String texto){
        if(texto.equals("00.000.000/0000-00")){
            return false;
        }
            if (!texto.substring(0, 1).equals("")) {  
            try {  
                texto = texto.replace('.', ' ');//onde há ponto coloca espaço  
                texto = texto.replace('/', ' ');//onde há barra coloca espaço  
                texto = texto.replace('-', ' ');//onde há traço coloca espaço  
                texto = texto.replaceAll(" ", "");//retira espaço  
                int soma = 0, digito;  
                String cnpj_calc = texto.substring(0, 12);  
  
                if (texto.length() != 14) {  
                    return false;  
                }  
                char[] chr_cnpj = texto.toCharArray();  
                /* Primeira parte */  
                for (int i = 0; i < 4; i++) {  
                    if (chr_cnpj[i] - 48 >= 0 && chr_cnpj[i] - 48 <= 9) {  
                        soma += (chr_cnpj[i] - 48) * (6 - (i + 1));  
                    }  
                }  
                for (int i = 0; i < 8; i++) {  
                    if (chr_cnpj[i + 4] - 48 >= 0 && chr_cnpj[i + 4] - 48 <= 9) {  
                        soma += (chr_cnpj[i + 4] - 48) * (10 - (i + 1));  
                    }  
                }  
                digito = 11 - (soma % 11);  
                cnpj_calc += (digito == 10 || digito == 11) ? "0" : Integer.toString(digito);  
                /* Segunda parte */  
                soma = 0;  
                for (int i = 0; i < 5; i++) {  
                    if (chr_cnpj[i] - 48 >= 0 && chr_cnpj[i] - 48 <= 9) {  
                        soma += (chr_cnpj[i] - 48) * (7 - (i + 1));  
                    }  
                }  
                for (int i = 0; i < 8; i++) {  
                    if (chr_cnpj[i + 5] - 48 >= 0 && chr_cnpj[i + 5] - 48 <= 9) {  
                        soma += (chr_cnpj[i + 5] - 48) * (10 - (i + 1));  
                    }  
                }  
                digito = 11 - (soma % 11);  
                cnpj_calc += (digito == 10 || digito == 11) ? "0" : Integer.toString(digito);  
                return texto.equals(cnpj_calc);  
            }  
            catch (Exception e) {
                //e.printStackTrace();
                //JOptionPane.showMessageDialog(null,"ERRO AO VALIDAR CNPJ!");
                return false;  
            }  
        }
         return false;
    }
    
    public static boolean validaCPF(String texto){
        
        if(texto.equals("000.000.000-00")||texto.equals("111.111.111-11")||texto.equals("222.222.222-22")||texto.equals("333.333.333-33")||texto.equals("444.444.444-44")||
                texto.equals("555.555.555-55")||texto.equals("666.666.666-66")||texto.equals("777.777.777-77")||texto.equals("888.888.888-88")||texto.equals("999.999.999-99")){
            return false;
        }
        if (! texto.substring(0,1).equals("")){  // metodo subString (0,1) casa 0 , ou seja , começa a partir dessa casa e  que determina que ele pega a casa 1
            try{         
                int     d1, d2;     
                int     digito1, digito2, resto;     
                int     digitoCPF;     
                String  DigResultado;
                
                // tira os pontos e poem espaço
                texto=texto.replace('.',' ');  
                texto=texto.replace('-',' ');   
                texto=texto.replaceAll(" ","");
                d1 = d2 = 0;   
                
                digito1 = digito2 = resto = 0;  
                
                // exemplo 085564619
                     
                for (int nCount = 1; nCount < texto.length() -1; nCount++) {     
                    digitoCPF = Integer.valueOf(texto.substring(nCount -1, nCount)).intValue();     
                    //0                                              //1 -1 = 0 , 1 (0,1) .. (1,2) .. (2,3)
                    //multiplique a ultima casa por 2 a seguinte por 3 a seguinte por 4 e assim por diante.     
                    d1 = d1 + ( 11 - nCount ) * digitoCPF; 
                    // 0 = 0 + (11 - 1) * 0 primeiro digito
                    //248
                         
                    //para o segundo digito repita o procedimento incluindo o primeiro digito calculado no passo anterior.     
                    d2 = d2 + ( 12 - nCount ) * digitoCPF;  
                    //248
                }  
    
                //Primeiro resto da divisão por 11.     
                resto = (d1 % 11);
                        //248 / 11 = 22 = 242 >> 248-242 = 6 >> resto
                     
                //Se o resultado for 0 ou 1 o digito é 0 caso contrário o digito é 11 menos o resultado anterior.     
                if (resto < 2) {
                    digito1 = 0;
                }     
                else {
                    digito1 = 11 - resto; 
                                  //6 = 5  
                }     
                
                // primeiro digito verificador multiplicado por 2, que depois é somado a d2
                d2 += 2 * digito1;
                // adiciona ao d2 o primeiro digito verificador (5)
                     
                //Segundo resto da divisão por 11.     
                resto = (d2 % 11);   
                        //302  / 11 = 297 >> 302-297 = 5 >> resto
                     
                //Se o resultado for 0 ou 1 o digito é 0 caso contrário o digito é 11 menos o resultado anterior.     
                if (resto < 2) {
                    digito2 = 0;
                }     
                else {
                    digito2 = 11 - resto;
                                    // 5 = 6
                }     
                     
                //Digito verificador do CPF que está sendo validado.     
                String DigVerificador = texto.substring(texto.length()-2, texto.length()); 
                                                            // 11-2 = 9 , a partir da casa 10 ,ou seja , pega o que sobrou , que no caso foi 5
                     
                //Concatenando o primeiro resto com o segundo.     
                DigResultado = String.valueOf(digito1) + String.valueOf(digito2); 
                                                //5                         //6
                     
                //comparar o digito verificador do cpf com o primeiro resto + o segundo resto.     
                return DigVerificador.equals(DigResultado);  
                        //56                    //56 retorna true ; o CPF é válido   
            }catch (Exception e){ 
                //e.printStackTrace();
                //JOptionPane.showMessageDialog(null,"ERRO AO VALIDAR CPF!");
                return false;     
            }      
        }
        else{       
            return false;     
        }  
    }
}
