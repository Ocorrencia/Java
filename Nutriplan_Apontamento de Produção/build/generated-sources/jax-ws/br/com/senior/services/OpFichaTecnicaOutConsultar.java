
package br.com.senior.services;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java de opFichaTecnicaOutConsultar complex type.
 * 
 * <p>O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.
 * 
 * <pre>
 * &lt;complexType name="opFichaTecnicaOutConsultar">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="codCmp" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="derCmp" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="desCmp" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="desDer" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="mensagemRetorno" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="prdQtd" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="qtdTot" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="qtdUti" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="seqMod" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="statusRegistro" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tipPro" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="uniMe2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "opFichaTecnicaOutConsultar", propOrder = {
    "codCmp",
    "derCmp",
    "desCmp",
    "desDer",
    "mensagemRetorno",
    "prdQtd",
    "qtdTot",
    "qtdUti",
    "seqMod",
    "statusRegistro",
    "tipPro",
    "uniMe2"
})
public class OpFichaTecnicaOutConsultar {

    @XmlElementRef(name = "codCmp", type = JAXBElement.class, required = false)
    protected JAXBElement<String> codCmp;
    @XmlElementRef(name = "derCmp", type = JAXBElement.class, required = false)
    protected JAXBElement<String> derCmp;
    @XmlElementRef(name = "desCmp", type = JAXBElement.class, required = false)
    protected JAXBElement<String> desCmp;
    @XmlElementRef(name = "desDer", type = JAXBElement.class, required = false)
    protected JAXBElement<String> desDer;
    @XmlElementRef(name = "mensagemRetorno", type = JAXBElement.class, required = false)
    protected JAXBElement<String> mensagemRetorno;
    @XmlElementRef(name = "prdQtd", type = JAXBElement.class, required = false)
    protected JAXBElement<Double> prdQtd;
    @XmlElementRef(name = "qtdTot", type = JAXBElement.class, required = false)
    protected JAXBElement<Double> qtdTot;
    @XmlElementRef(name = "qtdUti", type = JAXBElement.class, required = false)
    protected JAXBElement<Double> qtdUti;
    @XmlElementRef(name = "seqMod", type = JAXBElement.class, required = false)
    protected JAXBElement<Integer> seqMod;
    @XmlElementRef(name = "statusRegistro", type = JAXBElement.class, required = false)
    protected JAXBElement<String> statusRegistro;
    @XmlElementRef(name = "tipPro", type = JAXBElement.class, required = false)
    protected JAXBElement<String> tipPro;
    @XmlElementRef(name = "uniMe2", type = JAXBElement.class, required = false)
    protected JAXBElement<String> uniMe2;

    /**
     * Obtém o valor da propriedade codCmp.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCodCmp() {
        return codCmp;
    }

    /**
     * Define o valor da propriedade codCmp.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCodCmp(JAXBElement<String> value) {
        this.codCmp = value;
    }

    /**
     * Obtém o valor da propriedade derCmp.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDerCmp() {
        return derCmp;
    }

    /**
     * Define o valor da propriedade derCmp.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDerCmp(JAXBElement<String> value) {
        this.derCmp = value;
    }

    /**
     * Obtém o valor da propriedade desCmp.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDesCmp() {
        return desCmp;
    }

    /**
     * Define o valor da propriedade desCmp.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDesCmp(JAXBElement<String> value) {
        this.desCmp = value;
    }

    /**
     * Obtém o valor da propriedade desDer.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDesDer() {
        return desDer;
    }

    /**
     * Define o valor da propriedade desDer.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDesDer(JAXBElement<String> value) {
        this.desDer = value;
    }

    /**
     * Obtém o valor da propriedade mensagemRetorno.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getMensagemRetorno() {
        return mensagemRetorno;
    }

    /**
     * Define o valor da propriedade mensagemRetorno.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setMensagemRetorno(JAXBElement<String> value) {
        this.mensagemRetorno = value;
    }

    /**
     * Obtém o valor da propriedade prdQtd.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Double }{@code >}
     *     
     */
    public JAXBElement<Double> getPrdQtd() {
        return prdQtd;
    }

    /**
     * Define o valor da propriedade prdQtd.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Double }{@code >}
     *     
     */
    public void setPrdQtd(JAXBElement<Double> value) {
        this.prdQtd = value;
    }

    /**
     * Obtém o valor da propriedade qtdTot.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Double }{@code >}
     *     
     */
    public JAXBElement<Double> getQtdTot() {
        return qtdTot;
    }

    /**
     * Define o valor da propriedade qtdTot.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Double }{@code >}
     *     
     */
    public void setQtdTot(JAXBElement<Double> value) {
        this.qtdTot = value;
    }

    /**
     * Obtém o valor da propriedade qtdUti.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Double }{@code >}
     *     
     */
    public JAXBElement<Double> getQtdUti() {
        return qtdUti;
    }

    /**
     * Define o valor da propriedade qtdUti.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Double }{@code >}
     *     
     */
    public void setQtdUti(JAXBElement<Double> value) {
        this.qtdUti = value;
    }

    /**
     * Obtém o valor da propriedade seqMod.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Integer }{@code >}
     *     
     */
    public JAXBElement<Integer> getSeqMod() {
        return seqMod;
    }

    /**
     * Define o valor da propriedade seqMod.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Integer }{@code >}
     *     
     */
    public void setSeqMod(JAXBElement<Integer> value) {
        this.seqMod = value;
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
     * Obtém o valor da propriedade tipPro.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getTipPro() {
        return tipPro;
    }

    /**
     * Define o valor da propriedade tipPro.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setTipPro(JAXBElement<String> value) {
        this.tipPro = value;
    }

    /**
     * Obtém o valor da propriedade uniMe2.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getUniMe2() {
        return uniMe2;
    }

    /**
     * Define o valor da propriedade uniMe2.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setUniMe2(JAXBElement<String> value) {
        this.uniMe2 = value;
    }

}
