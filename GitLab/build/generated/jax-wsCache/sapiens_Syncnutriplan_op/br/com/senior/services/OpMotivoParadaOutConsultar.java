
package br.com.senior.services;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java de opMotivoParadaOutConsultar complex type.
 * 
 * <p>O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.
 * 
 * <pre>
 * &lt;complexType name="opMotivoParadaOutConsultar">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="codAmp" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="codEmp" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="codMtv" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="desAmp" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="desMtv" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="statusRegistro" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "opMotivoParadaOutConsultar", propOrder = {
    "codAmp",
    "codEmp",
    "codMtv",
    "desAmp",
    "desMtv",
    "statusRegistro"
})
public class OpMotivoParadaOutConsultar {

    @XmlElementRef(name = "codAmp", type = JAXBElement.class, required = false)
    protected JAXBElement<Integer> codAmp;
    @XmlElementRef(name = "codEmp", type = JAXBElement.class, required = false)
    protected JAXBElement<Integer> codEmp;
    @XmlElementRef(name = "codMtv", type = JAXBElement.class, required = false)
    protected JAXBElement<String> codMtv;
    @XmlElementRef(name = "desAmp", type = JAXBElement.class, required = false)
    protected JAXBElement<String> desAmp;
    @XmlElementRef(name = "desMtv", type = JAXBElement.class, required = false)
    protected JAXBElement<String> desMtv;
    @XmlElementRef(name = "statusRegistro", type = JAXBElement.class, required = false)
    protected JAXBElement<String> statusRegistro;

    /**
     * Obtém o valor da propriedade codAmp.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Integer }{@code >}
     *     
     */
    public JAXBElement<Integer> getCodAmp() {
        return codAmp;
    }

    /**
     * Define o valor da propriedade codAmp.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Integer }{@code >}
     *     
     */
    public void setCodAmp(JAXBElement<Integer> value) {
        this.codAmp = value;
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
     * Obtém o valor da propriedade codMtv.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCodMtv() {
        return codMtv;
    }

    /**
     * Define o valor da propriedade codMtv.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCodMtv(JAXBElement<String> value) {
        this.codMtv = value;
    }

    /**
     * Obtém o valor da propriedade desAmp.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDesAmp() {
        return desAmp;
    }

    /**
     * Define o valor da propriedade desAmp.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDesAmp(JAXBElement<String> value) {
        this.desAmp = value;
    }

    /**
     * Obtém o valor da propriedade desMtv.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDesMtv() {
        return desMtv;
    }

    /**
     * Define o valor da propriedade desMtv.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDesMtv(JAXBElement<String> value) {
        this.desMtv = value;
    }

    /**
     * Obtém o valor da propriedade statusRegistro.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getStatusRegistro() {
        return statusRegistro;
    }

    /**
     * Define o valor da propriedade statusRegistro.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setStatusRegistro(JAXBElement<String> value) {
        this.statusRegistro = value;
    }

}
