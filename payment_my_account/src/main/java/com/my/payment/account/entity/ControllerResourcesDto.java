package com.my.payment.account.entity;


import com.my.payment.base.annotations.EntityPK;
import com.my.payment.base.dto.impl.BaseDtoImpl;

@EntityPK(Pk = "id", tableName = "t_ctrl_res")
public class ControllerResourcesDto  extends BaseDtoImpl{
	private static final long serialVersionUID = 1L;
	private String name;
	private String sn;
	private String psn;
	private String className;
	private int orderNum;
	private ControllerResourcesDto parent;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSn() {
		return sn;
	}
	public void setSn(String sn) {
		this.sn = sn;
	}
	public String getPsn() {
		return psn;
	}
	public void setPsn(String psn) {
		this.psn = psn;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public int getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(int orderNum) {
		this.orderNum = orderNum;
	}
	public ControllerResourcesDto getParent() {
		return parent;
	}
	public void setParent(ControllerResourcesDto parent) {
		this.parent = parent;
	}
	
	
}
