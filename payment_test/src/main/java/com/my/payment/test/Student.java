package com.my.payment.test;

import com.my.payment.base.annotations.EntityPK;
import com.my.payment.base.dto.BaseDto;



@EntityPK(Pk = "id", tableName = "Student")
public class Student implements BaseDto{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;
	
	private String name;
	
	private Integer age;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Student(Integer age) {
		super();
		this.age = age;
	}

	public Student() {
		super();
	}

	
	
	

}
