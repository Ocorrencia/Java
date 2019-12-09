
package br.com.senior.services;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java de opOperadorOutConsultar complex type.
 * 
 * <p>O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.
 * 
 * <pre>
 * &lt;complexType name="opOperadorOutConsultar">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="codEmp" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="nomOpe" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="numCad" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="statusRegistro" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="turTrb" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "opOperadorOutConsultar", propOrder = {
    "codEmp",
    "nomOpe",
    "numCad",
    "statusRegistro",
    "turTrb"
})
public class OpOperadorOutConsultar {

    @XmlElementRef(name = "codEmp", type = JAXBElement.class, required = false)
    protected JAXBElement<Integer> codEmp;
    @XmlElementRef(name = "nomOpe", type = JAXBElement.class, required = false)
    protected JAXBElement<String> nomOpe;
    @XmlElementRef(name = "numCad", type = JAXBElement.class, required = false)
    protected JAXBElement<Integer> numCad;
    @XmlElementRef(name = "statusRegistro", type = JAXBElement.class, required = false)
    protected JAXBElement<String> statusRegistro;
    @XmlElementRef(name = "turTrb", type = JAXBElement.class, required = false)
    protected JAXBElement<Integer> turTrb;

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
     * Obtém o valor da propriedade nomOpe.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getNomOpe() {
        return nomOpe;
    }

    /**
     * Define o valor da propriedade nomOpe.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setNomOpe(JAXBElement<String> value) {
        this.nomOpe = value;
    }

    /**
     * Obtém o valor da propriedade numCad.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Integer }{@code >}
     *     
     */
    public JAXBElement<Integer> getNumCad() {
        return numCad;
    }

    /**
     * Define o valor da propriedade numCad.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Integer }{@code >}
     *     
     */
    public void setNumCad(JAXBElement<Integer> value) {
        this.numCad = value;
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

    /**
     * Obtém o valor da propriedade turTrb.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Integer }{@code >}
     *     
     */
    public JAXBElement<Integer> getTurTrb() {
        return turTrb;
    }

    /**
     * Define o valor da propriedade turTrb.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Integer }{@code >}
     *     
     */
    public void setTurTrb(JAXBElement<Integer> value) {
        this.turTrb = value;
    }

}
