package com.my.payment.base.aop;

import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.Ordered;

public abstract class BaseAspect implements Ordered{

	private Map<String, Integer> aopOrderDefine;
	protected final Log log = LogFactory.getLog(getClass());

	public BaseAspect() {
		super();
	}

	public void setAopOrderDefine(Map<String, Integer> aopOrderDefine) {
		this.aopOrderDefine = aopOrderDefine;
	}

	public int getOrder() {		
		return aopOrderDefine.get(getClass().getName()).intValue();
	}

}