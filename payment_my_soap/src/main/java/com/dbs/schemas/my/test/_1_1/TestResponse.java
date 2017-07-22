
package com.dbs.schemas.my.test._1_1;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
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
 *         &lt;element ref="{http://schemas.dbs.com/my/common/1_1}MyId"/&gt;
 *         &lt;element ref="{http://schemas.dbs.com/my/common/1_1}RequestoerMyId"/&gt;
 *         &lt;element ref="{http://schemas.dbs.com/my/common/1_1}MyUserId"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "myId",
    "requestoerMyId",
    "myUserId"
})
@XmlRootElement(name = "TestResponse")
public class TestResponse {

    @XmlElement(name = "MyId", namespace = "http://schemas.dbs.com/my/common/1_1", required = true)
    protected String myId;
    @XmlElement(name = "RequestoerMyId", namespace = "http://schemas.dbs.com/my/common/1_1", required = true)
    protected String requestoerMyId;
    @XmlElement(name = "MyUserId", namespace = "http://schemas.dbs.com/my/common/1_1", required = true)
    protected String myUserId;

    /**
     * Gets the value of the myId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMyId() {
        return myId;
    }

    /**
     * Sets the value of the myId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMyId(String value) {
        this.myId = value;
    }

    /**
     * Gets the value of the requestoerMyId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRequestoerMyId() {
        return requestoerMyId;
    }

    /**
     * Sets the value of the requestoerMyId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRequestoerMyId(String value) {
        this.requestoerMyId = value;
    }

    /**
     * Gets the value of the myUserId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMyUserId() {
        return myUserId;
    }

    /**
     * Sets the value of the myUserId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMyUserId(String value) {
        this.myUserId = value;
    }

}
