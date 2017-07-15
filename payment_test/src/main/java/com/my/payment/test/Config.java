package com.my.payment.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.my.payment.base.factory.SpringContextUtil;

public class Config {
	
	private String fileName = "a.properties";
	private static Config instance = null;
	
	public Config(){
		
	}

	
	 public void init(){
		 System.out.println(this.fileName);
	 }
	 
	 public static Config getInstance(){
		 return instance;
	 }


	public String getFileName() {
		return fileName;
	}


	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	 
	 public static void main(String[] args){
		 ApplicationContext context = new ClassPathXmlApplicationContext("conf/spring*.xml");
		 Config c = (Config) SpringContextUtil.getBean("config");
		 c.setFileName("gggvsdf");
		 System.out.println(c.getFileName() + "gggg");
		 Config c1 = (Config) SpringContextUtil.getBean("config");
		 System.out.println(c1.getFileName() + "gggg");
	 }
}
