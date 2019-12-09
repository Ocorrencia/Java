
package util;

/**
 *
 * @author Giuliano
 */
public class ColunasConsulta {
    private String titulo;
    private String campo;
    
    public ColunasConsulta(String titulo,String campo){
        this.titulo=titulo;
        this.campo=campo;
    }
    
    public String getCampo(){
        return campo;
    }
    
    public String getTitulo(){
        return titulo;
    }
}
