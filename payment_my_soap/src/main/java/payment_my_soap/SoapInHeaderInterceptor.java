package payment_my_soap;

import java.util.List;

import javax.xml.bind.JAXBException;

import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.binding.soap.interceptor.AbstractSoapInterceptor;
import org.apache.cxf.headers.Header;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.phase.Phase;

public class SoapInHeaderInterceptor extends AbstractSoapInterceptor {
	public SoapInHeaderInterceptor() {
		super(Phase.PRE_INVOKE);
	}
	@Override
	public void handleMessage(SoapMessage message) throws Fault {
		System.out.println("in: " + Thread.currentThread().getName());
		message.getExchange().put("soapHeaderList", message.getHeaders());
	}
}