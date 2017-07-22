package payment_my_soap;

import javax.jws.WebService;

import com.dbs.schemas.my.test._1_1.TestInq;
import com.dbs.schemas.my.test._1_1.TestResponse;
import com.dbs.schemas.my.wsdl.test._1_1.Fault;


@WebService(targetNamespace = "http://schemas.dbs.com/my/wsdl/Test/1_1", name = "PortType")
public interface TestService {
	
	
	public TestResponse Test(TestInq inq)throws Fault;

}
