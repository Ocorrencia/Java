
package br.com.senior.services;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java de opCentroDeRecursoInDefinirCentroDeRecurso complex type.
 * 
 * <p>O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.
 * 
 * <pre>
 * &lt;complexType name="opCentroDeRecursoInDefinirCentroDeRecurso">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="codCre" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="codEmp" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="enderecoIP" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "opCentroDeRecursoInDefinirCentroDeRecurso", propOrder = {
    "codCre",
    "codEmp",
    "enderecoIP"
})
public class OpCentroDeRecursoInDefinirCentroDeRecurso {

    @XmlElementRef(name = "codCre", type = JAXBElement.class, required = false)
    protected JAXBElement<String> codCre;
    @XmlElementRef(name = "codEmp", type = JAXBElement.class, required = false)
    protected JAXBElement<Integer> codEmp;
    @XmlElementRef(name = "enderecoIP", type = JAXBElement.class, required = false)
    protected JAXBElement<String> enderecoIP;

    /**
     * Obtém o valor da propriedade codCre.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCodCre() {
        return codCre;
    }

    /**
     * Define o valor da propriedade codCre.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCodCre(JAXBElement<String> value) {
        this.codCre = value;
    }

    /**
     * Obtém o valor da propriedade codEmp.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Integer }{@code >}
     *     
     */
    public JAXBElement<Integer> getCodEmp() {
        return codEmp;
    }

    /**
     * Define o valor da propriedade codEmp.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Integer }{@code >}
     *     
     */
    public void setCodEmp(JAXBElement<Integer> value) {
        this.codEmp = value;
    }

    /**
     * Obtém o valor da propriedade enderecoIP.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getEnderecoIP() {
        return enderecoIP;
    }

    /**
     * Define o valor da propriedade enderecoIP.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setEnderecoIP(JAXBElement<String> value) {
        this.enderecoIP = value;
    }

}
