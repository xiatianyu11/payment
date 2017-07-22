
package com.dbs.schemas.my.common._1_1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element ref="{http://schemas.dbs.com/my/common/1_1}MsgVersion" minOccurs="0"/&gt;
 *         &lt;element ref="{http://schemas.dbs.com/my/common/1_1}MsgUID"/&gt;
 *         &lt;element ref="{http://schemas.dbs.com/my/common/1_1}SvcVersion" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="mustUnderstand" type="{http://www.w3.org/2001/XMLSchema}boolean" default="false" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "msgVersion",
    "msgUID",
    "svcVersion"
})
@XmlRootElement(name = "MsgDetl")
public class MsgDetl {

    @XmlElement(name = "MsgVersion")
    protected String msgVersion;
    @XmlElement(name = "MsgUID", required = true)
    protected String msgUID;
    @XmlElement(name = "SvcVersion")
    protected String svcVersion;
    @XmlAttribute(name = "mustUnderstand")
    protected Boolean mustUnderstand;

    /**
     * Gets the value of the msgVersion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMsgVersion() {
        return msgVersion;
    }

    /**
     * Sets the value of the msgVersion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMsgVersion(String value) {
        this.msgVersion = value;
    }

    /**
     * Gets the value of the msgUID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMsgUID() {
        return msgUID;
    }

    /**
     * Sets the value of the msgUID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMsgUID(String value) {
        this.msgUID = value;
    }

    /**
     * Gets the value of the svcVersion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSvcVersion() {
        return svcVersion;
    }

    /**
     * Sets the value of the svcVersion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSvcVersion(String value) {
        this.svcVersion = value;
    }

    /**
     * Gets the value of the mustUnderstand property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public boolean isMustUnderstand() {
        if (mustUnderstand == null) {
            return false;
        } else {
            return mustUnderstand;
        }
    }

    /**
     * Sets the value of the mustUnderstand property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setMustUnderstand(Boolean value) {
        this.mustUnderstand = value;
    }

}
