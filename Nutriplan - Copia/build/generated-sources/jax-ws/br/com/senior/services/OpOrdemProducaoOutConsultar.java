
package br.com.senior.services;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java de opOrdemProducaoOutConsultar complex type.
 * 
 * <p>O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.
 * 
 * <pre>
 * &lt;complexType name="opOrdemProducaoOutConsultar">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="capSmt" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="cicPad" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="codDer" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="codEmp" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="codEtg" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="codOri" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="codPro" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="desDer" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="desEtg" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="desPro" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="desPrxDer" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="desPrxPro" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="numOrp" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="numPri" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="pesPad" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="prxDer" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="prxPro" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="qtdMax" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="qtdPrv" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="qtdRfg" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="seqRot" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="sfxEtr" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
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
@XmlType(name = "opOrdemProducaoOutConsultar", propOrder = {
    "capSmt",
    "cicPad",
    "codDer",
    "codEmp",
    "codEtg",
    "codOri",
    "codPro",
    "desDer",
    "desEtg",
    "desPro",
    "desPrxDer",
    "desPrxPro",
    "numOrp",
    "numPri",
    "pesPad",
    "prxDer",
    "prxPro",
    "qtdMax",
    "qtdPrv",
    "qtdRfg",
    "seqRot",
    "sfxEtr",
    "statusRegistro"
})
public class OpOrdemProducaoOutConsultar {

    @XmlElementRef(name = "capSmt", type = JAXBElement.class, required = false)
    protected JAXBElement<Double> capSmt;
    @XmlElementRef(name = "cicPad", type = JAXBElement.class, required = false)
    protected JAXBElement<Double> cicPad;
    @XmlElementRef(name = "codDer", type = JAXBElement.class, required = false)
    protected JAXBElement<String> codDer;
    @XmlElementRef(name = "codEmp", type = JAXBElement.class, required = false)
    protected JAXBElement<Integer> codEmp;
    @XmlElementRef(name = "codEtg", type = JAXBElement.class, required = false)
    protected JAXBElement<Integer> codEtg;
    @XmlElementRef(name = "codOri", type = JAXBElement.class, required = false)
    protected JAXBElement<String> codOri;
    @XmlElementRef(name = "codPro", type = JAXBElement.class, required = false)
    protected JAXBElement<String> codPro;
    @XmlElementRef(name = "desDer", type = JAXBElement.class, required = false)
    protected JAXBElement<String> desDer;
    @XmlElementRef(name = "desEtg", type = JAXBElement.class, required = false)
    protected JAXBElement<String> desEtg;
    @XmlElementRef(name = "desPro", type = JAXBElement.class, required = false)
    protected JAXBElement<String> desPro;
    @XmlElementRef(name = "desPrxDer", type = JAXBElement.class, required = false)
    protected JAXBElement<String> desPrxDer;
    @XmlElementRef(name = "desPrxPro", type = JAXBElement.class, required = false)
    protected JAXBElement<String> desPrxPro;
    @XmlElementRef(name = "numOrp", type = JAXBElement.class, required = false)
    protected JAXBElement<Integer> numOrp;
    @XmlElementRef(name = "numPri", type = JAXBElement.class, required = false)
    protected JAXBElement<Integer> numPri;
    @XmlElementRef(name = "pesPad", type = JAXBElement.class, required = false)
    protected JAXBElement<Double> pesPad;
    @XmlElementRef(name = "prxDer", type = JAXBElement.class, required = false)
    protected JAXBElement<String> prxDer;
    @XmlElementRef(name = "prxPro", type = JAXBElement.class, required = false)
    protected JAXBElement<String> prxPro;
    @XmlElementRef(name = "qtdMax", type = JAXBElement.class, required = false)
    protected JAXBElement<Double> qtdMax;
    @XmlElementRef(name = "qtdPrv", type = JAXBElement.class, required = false)
    protected JAXBElement<Double> qtdPrv;
    @XmlElementRef(name = "qtdRfg", type = JAXBElement.class, required = false)
    protected JAXBElement<Double> qtdRfg;
    @XmlElementRef(name = "seqRot", type = JAXBElement.class, required = false)
    protected JAXBElement<Integer> seqRot;
    @XmlElementRef(name = "sfxEtr", type = JAXBElement.class, required = false)
    protected JAXBElement<Integer> sfxEtr;
    @XmlElementRef(name = "statusRegistro", type = JAXBElement.class, required = false)
    protected JAXBElement<String> statusRegistro;

    /**
     * Obtém o valor da propriedade capSmt.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Double }{@code >}
     *     
     */
    public JAXBElement<Double> getCapSmt() {
        return capSmt;
    }

    /**
     * Define o valor da propriedade capSmt.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Double }{@code >}
     *     
     */
    public void setCapSmt(JAXBElement<Double> value) {
        this.capSmt = value;
    }

    /**
     * Obtém o valor da propriedade cicPad.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Double }{@code >}
     *     
     */
    public JAXBElement<Double> getCicPad() {
        return cicPad;
    }

    /**
     * Define o valor da propriedade cicPad.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Double }{@code >}
     *     
     */
    public void setCicPad(JAXBElement<Double> value) {
        this.cicPad = value;
    }

    /**
     * Obtém o valor da propriedade codDer.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCodDer() {
        return codDer;
    }

    /**
     * Define o valor da propriedade codDer.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCodDer(JAXBElement<String> value) {
        this.codDer = value;
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
     * Obtém o valor da propriedade codEtg.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Integer }{@code >}
     *     
     */
    public JAXBElement<Integer> getCodEtg() {
        return codEtg;
    }

    /**
     * Define o valor da propriedade codEtg.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Integer }{@code >}
     *     
     */
    public void setCodEtg(JAXBElement<Integer> value) {
        this.codEtg = value;
    }

    /**
     * Obtém o valor da propriedade codOri.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCodOri() {
        return codOri;
    }

    /**
     * Define o valor da propriedade codOri.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCodOri(JAXBElement<String> value) {
        this.codOri = value;
    }

    /**
     * Obtém o valor da propriedade codPro.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCodPro() {
        return codPro;
    }

    /**
     * Define o valor da propriedade codPro.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCodPro(JAXBElement<String> value) {
        this.codPro = value;
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
     * Obtém o valor da propriedade desEtg.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDesEtg() {
        return desEtg;
    }

    /**
     * Define o valor da propriedade desEtg.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDesEtg(JAXBElement<String> value) {
        this.desEtg = value;
    }

    /**
     * Obtém o valor da propriedade desPro.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDesPro() {
        return desPro;
    }

    /**
     * Define o valor da propriedade desPro.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDesPro(JAXBElement<String> value) {
        this.desPro = value;
    }

    /**
     * Obtém o valor da propriedade desPrxDer.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDesPrxDer() {
        return desPrxDer;
    }

    /**
     * Define o valor da propriedade desPrxDer.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDesPrxDer(JAXBElement<String> value) {
        this.desPrxDer = value;
    }

    /**
     * Obtém o valor da propriedade desPrxPro.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDesPrxPro() {
        return desPrxPro;
    }

    /**
     * Define o valor da propriedade desPrxPro.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDesPrxPro(JAXBElement<String> value) {
        this.desPrxPro = value;
    }

    /**
     * Obtém o valor da propriedade numOrp.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Integer }{@code >}
     *     
     */
    public JAXBElement<Integer> getNumOrp() {
        return numOrp;
    }

    /**
     * Define o valor da propriedade numOrp.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Integer }{@code >}
     *     
     */
    public void setNumOrp(JAXBElement<Integer> value) {
        this.numOrp = value;
    }

    /**
     * Obtém o valor da propriedade numPri.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Integer }{@code >}
     *     
     */
    public JAXBElement<Integer> getNumPri() {
        return numPri;
    }

    /**
     * Define o valor da propriedade numPri.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Integer }{@code >}
     *     
     */
    public void setNumPri(JAXBElement<Integer> value) {
        this.numPri = value;
    }

    /**
     * Obtém o valor da propriedade pesPad.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Double }{@code >}
     *     
     */
    public JAXBElement<Double> getPesPad() {
        return pesPad;
    }

    /**
     * Define o valor da propriedade pesPad.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Double }{@code >}
     *     
     */
    public void setPesPad(JAXBElement<Double> value) {
        this.pesPad = value;
    }

    /**
     * Obtém o valor da propriedade prxDer.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getPrxDer() {
        return prxDer;
    }

    /**
     * Define o valor da propriedade prxDer.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setPrxDer(JAXBElement<String> value) {
        this.prxDer = value;
    }

    /**
     * Obtém o valor da propriedade prxPro.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getPrxPro() {
        return prxPro;
    }

    /**
     * Define o valor da propriedade prxPro.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setPrxPro(JAXBElement<String> value) {
        this.prxPro = value;
    }

    /**
     * Obtém o valor da propriedade qtdMax.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Double }{@code >}
     *     
     */
    public JAXBElement<Double> getQtdMax() {
        return qtdMax;
    }

    /**
     * Define o valor da propriedade qtdMax.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Double }{@code >}
     *     
     */
    public void setQtdMax(JAXBElement<Double> value) {
        this.qtdMax = value;
    }

    /**
     * Obtém o valor da propriedade qtdPrv.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Double }{@code >}
     *     
     */
    public JAXBElement<Double> getQtdPrv() {
        return qtdPrv;
    }

    /**
     * Define o valor da propriedade qtdPrv.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Double }{@code >}
     *     
     */
    public void setQtdPrv(JAXBElement<Double> value) {
        this.qtdPrv = value;
    }

    /**
     * Obtém o valor da propriedade qtdRfg.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Double }{@code >}
     *     
     */
    public JAXBElement<Double> getQtdRfg() {
        return qtdRfg;
    }

    /**
     * Define o valor da propriedade qtdRfg.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Double }{@code >}
     *     
     */
    public void setQtdRfg(JAXBElement<Double> value) {
        this.qtdRfg = value;
    }

    /**
     * Obtém o valor da propriedade seqRot.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Integer }{@code >}
     *     
     */
    public JAXBElement<Integer> getSeqRot() {
        return seqRot;
    }

    /**
     * Define o valor da propriedade seqRot.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Integer }{@code >}
     *     
     */
    public void setSeqRot(JAXBElement<Integer> value) {
        this.seqRot = value;
    }

    /**
     * Obtém o valor da propriedade sfxEtr.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Integer }{@code >}
     *     
     */
    public JAXBElement<Integer> getSfxEtr() {
        return sfxEtr;
    }

    /**
     * Define o valor da propriedade sfxEtr.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Integer }{@code >}
     *     
     */
    public void setSfxEtr(JAXBElement<Integer> value) {
        this.sfxEtr = value;
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
