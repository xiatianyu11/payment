
package com.dbs.schemas.my.common._1_1;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.dbs.schemas.my.common._1_1 package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _MyId_QNAME = new QName("http://schemas.dbs.com/my/common/1_1", "MyId");
    private final static QName _RequestoerMyId_QNAME = new QName("http://schemas.dbs.com/my/common/1_1", "RequestoerMyId");
    private final static QName _MyUserId_QNAME = new QName("http://schemas.dbs.com/my/common/1_1", "MyUserId");
    private final static QName _MsgUID_QNAME = new QName("http://schemas.dbs.com/my/common/1_1", "MsgUID");
    private final static QName _MsgVersion_QNAME = new QName("http://schemas.dbs.com/my/common/1_1", "MsgVersion");
    private final static QName _SvcVersion_QNAME = new QName("http://schemas.dbs.com/my/common/1_1", "SvcVersion");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.dbs.schemas.my.common._1_1
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link DetailInfo }
     * 
     */
    public DetailInfo createDetailInfo() {
        return new DetailInfo();
    }

    /**
     * Create an instance of {@link MsgDetl }
     * 
     */
    public MsgDetl createMsgDetl() {
        return new MsgDetl();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.dbs.com/my/common/1_1", name = "MyId")
    public JAXBElement<String> createMyId(String value) {
        return new JAXBElement<String>(_MyId_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.dbs.com/my/common/1_1", name = "RequestoerMyId")
    public JAXBElement<String> createRequestoerMyId(String value) {
        return new JAXBElement<String>(_RequestoerMyId_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.dbs.com/my/common/1_1", name = "MyUserId")
    public JAXBElement<String> createMyUserId(String value) {
        return new JAXBElement<String>(_MyUserId_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.dbs.com/my/common/1_1", name = "MsgUID")
    public JAXBElement<String> createMsgUID(String value) {
        return new JAXBElement<String>(_MsgUID_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.dbs.com/my/common/1_1", name = "MsgVersion")
    public JAXBElement<String> createMsgVersion(String value) {
        return new JAXBElement<String>(_MsgVersion_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.dbs.com/my/common/1_1", name = "SvcVersion")
    public JAXBElement<String> createSvcVersion(String value) {
        return new JAXBElement<String>(_SvcVersion_QNAME, String.class, null, value);
    }

}
