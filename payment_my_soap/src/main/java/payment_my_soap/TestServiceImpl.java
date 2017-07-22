package payment_my_soap;

import java.util.Date;
import java.util.GregorianCalendar;

import javax.jws.WebService;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.springframework.stereotype.Service;

import com.dbs.schemas.my.common._1_1.DetailInfo;
import com.dbs.schemas.my.test._1_1.TestInq;
import com.dbs.schemas.my.test._1_1.TestResponse;
import com.dbs.schemas.my.wsdl.test._1_1.Fault;


@Service
@WebService(endpointInterface="payment_my_soap.TestService",
			targetNamespace="http://schemas.dbs.com/my/wsdl/Test/1_1",
			serviceName="Test_v1_1_0",
			wsdlLocation="/WEB-INF/wsdl/Test/Test_v1_1_0.wsdl")
public class TestServiceImpl implements TestService {
	
	public TestResponse Test(TestInq inq)throws Fault{
		TestResponse res = new TestResponse();
		res.setMyUserId("1234556");
		res.setMyId("122222");
		res.setRequestoerMyId("323423");
	
		return res;
	}

}
