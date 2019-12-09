
package util;

/**
 *
 * @author Giuliano
 */
public class FiltrosConsulta {
    public String nomeFiltro;
    public String nomeAtributo;
    public Class tipo;

    public FiltrosConsulta(String nomeFiltro, String nomeAtributo, Class tipo) {
        this.nomeFiltro = nomeFiltro;
        this.nomeAtributo = nomeAtributo;
        this.tipo = tipo;
    }
}
