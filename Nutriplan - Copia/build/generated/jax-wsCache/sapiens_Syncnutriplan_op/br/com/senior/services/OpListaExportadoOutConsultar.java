
package br.com.senior.services;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java de opListaExportadoOutConsultar complex type.
 * 
 * <p>O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.
 * 
 * <pre>
 * &lt;complexType name="opListaExportadoOutConsultar">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="desExp" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="expErp" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "opListaExportadoOutConsultar", propOrder = {
    "desExp",
    "expErp"
})
public class OpListaExportadoOutConsultar {

    @XmlElementRef(name = "desExp", type = JAXBElement.class, required = false)
    protected JAXBElement<String> desExp;
    @XmlElementRef(name = "expErp", type = JAXBElement.class, required = false)
    protected JAXBElement<Integer> expErp;

    /**
     * Obtém o valor da propriedade desExp.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDesExp() {
        return desExp;
    }

    /**
     * Define o valor da propriedade desExp.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDesExp(JAXBElement<String> value) {
        this.desExp = value;
    }

    /**
     * Obtém o valor da propriedade expErp.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Integer }{@code >}
     *     
     */
    public JAXBElement<Integer> getExpErp() {
        return expErp;
    }

    /**
     * Define o valor da propriedade expErp.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Integer }{@code >}
     *     
     */
    public void setExpErp(JAXBElement<Integer> value) {
        this.expErp = value;
    }

}
