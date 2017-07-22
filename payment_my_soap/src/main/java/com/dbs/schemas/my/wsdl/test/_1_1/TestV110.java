package com.dbs.schemas.my.wsdl.test._1_1;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.Service;

/**
 * This class was generated by Apache CXF 3.1.11
 * 2017-07-22T11:27:28.229+08:00
 * Generated source version: 3.1.11
 * 
 */
@WebServiceClient(name = "Test_v1_1_0", 
                  wsdlLocation = "file:/Users/yuxia/Documents/mycode/workspace_sg_jasmine/payment_my_soap/src/main/resources/wsdl/Test/Test_v1_1_0.wsdl",
                  targetNamespace = "http://schemas.dbs.com/my/wsdl/Test/1_1") 
public class TestV110 extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("http://schemas.dbs.com/my/wsdl/Test/1_1", "Test_v1_1_0");
    public final static QName HTTP = new QName("http://schemas.dbs.com/my/wsdl/Test/1_1", "HTTP");
    static {
        URL url = null;
        try {
            url = new URL("file:/Users/yuxia/Documents/mycode/workspace_sg_jasmine/payment_my_soap/src/main/resources/wsdl/Test/Test_v1_1_0.wsdl");
        } catch (MalformedURLException e) {
            java.util.logging.Logger.getLogger(TestV110.class.getName())
                .log(java.util.logging.Level.INFO, 
                     "Can not initialize the default wsdl from {0}", "file:/Users/yuxia/Documents/mycode/workspace_sg_jasmine/payment_my_soap/src/main/resources/wsdl/Test/Test_v1_1_0.wsdl");
        }
        WSDL_LOCATION = url;
    }

    public TestV110(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public TestV110(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public TestV110() {
        super(WSDL_LOCATION, SERVICE);
    }
    
    public TestV110(WebServiceFeature ... features) {
        super(WSDL_LOCATION, SERVICE, features);
    }

    public TestV110(URL wsdlLocation, WebServiceFeature ... features) {
        super(wsdlLocation, SERVICE, features);
    }

    public TestV110(URL wsdlLocation, QName serviceName, WebServiceFeature ... features) {
        super(wsdlLocation, serviceName, features);
    }    




    /**
     * Endpoint for Customer Retrieve
     *
     * @return
     *     returns PortType
     */
    @WebEndpoint(name = "HTTP")
    public PortType getHTTP() {
        return super.getPort(HTTP, PortType.class);
    }

    /**
     * Endpoint for Customer Retrieve
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns PortType
     */
    @WebEndpoint(name = "HTTP")
    public PortType getHTTP(WebServiceFeature... features) {
        return super.getPort(HTTP, PortType.class, features);
    }

}
