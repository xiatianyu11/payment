package payment_my_soap;

import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.binding.soap.interceptor.AbstractSoapInterceptor;
import org.apache.cxf.headers.Header;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.phase.Phase;

public class SoapOutHeaderInterceptor extends AbstractSoapInterceptor {
	public SoapOutHeaderInterceptor() {
		super(Phase.PRE_STREAM);
	}
	@Override
	public void handleMessage(SoapMessage message) throws Fault {
		System.out.println("out: " + Thread.currentThread().getName());
		message.put(Header.HEADER_LIST, message.getExchange().get("soapHeaderList"));
	}
	
}