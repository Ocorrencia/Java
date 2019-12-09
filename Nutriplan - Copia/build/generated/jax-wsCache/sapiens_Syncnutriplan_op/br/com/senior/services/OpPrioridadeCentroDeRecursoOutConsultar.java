
package br.com.senior.services;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java de opPrioridadeCentroDeRecursoOutConsultar complex type.
 * 
 * <p>O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.
 * 
 * <pre>
 * &lt;complexType name="opPrioridadeCentroDeRecursoOutConsultar">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="seqPri" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="tipoRetorno" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "opPrioridadeCentroDeRecursoOutConsultar", propOrder = {
    "seqPri",
    "tipoRetorno"
})
public class OpPrioridadeCentroDeRecursoOutConsultar {

    @XmlElementRef(name = "seqPri", type = JAXBElement.class, required = false)
    protected JAXBElement<Integer> seqPri;
    @XmlElementRef(name = "tipoRetorno", type = JAXBElement.class, required = false)
    protected JAXBElement<Integer> tipoRetorno;

    /**
     * Obtém o valor da propriedade seqPri.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Integer }{@code >}
     *     
     */
    public JAXBElement<Integer> getSeqPri() {
        return seqPri;
    }

    /**
     * Define o valor da propriedade seqPri.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Integer }{@code >}
     *     
     */
    public void setSeqPri(JAXBElement<Integer> value) {
        this.seqPri = value;
    }

    /**
     * Obtém o valor da propriedade tipoRetorno.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Integer }{@code >}
     *     
     */
    public JAXBElement<Integer> getTipoRetorno() {
        return tipoRetorno;
    }

    /**
     * Define o valor da propriedade tipoRetorno.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Integer }{@code >}
     *     
     */
    public void setTipoRetorno(JAXBElement<Integer> value) {
        this.tipoRetorno = value;
    }

}
