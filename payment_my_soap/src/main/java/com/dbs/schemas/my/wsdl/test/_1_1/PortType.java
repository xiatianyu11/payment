package com.dbs.schemas.my.wsdl.test._1_1;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;

/**
 * This class was generated by Apache CXF 3.1.11
 * 2017-07-22T11:27:28.214+08:00
 * Generated source version: 3.1.11
 * 
 */
@WebService(targetNamespace = "http://schemas.dbs.com/my/wsdl/Test/1_1", name = "PortType")
@XmlSeeAlso({com.dbs.schemas.my.common._1_1.ObjectFactory.class, com.dbs.schemas.my.test._1_1.ObjectFactory.class})
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
public interface PortType {

    @WebMethod(operationName = "Test", action = "/RIB/TW/TestOp_v1_1_0/OpImpl/Test")
    @WebResult(name = "TestResponse", targetNamespace = "http://schemas.dbs.com/my/Test/1_1", partName = "TestRs")
    public com.dbs.schemas.my.test._1_1.TestResponse test(
        @WebParam(partName = "TestRq", name = "TestInq", targetNamespace = "http://schemas.dbs.com/my/Test/1_1")
        com.dbs.schemas.my.test._1_1.TestInq testRq
    ) throws Fault;
}
