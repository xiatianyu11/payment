package com.my.payment.test;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.my.payment.base.dto.PageDto;
import com.my.payment.base.dto.Pagination;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:conf/spring*.xml")
public class MainApp {
	
	@Autowired
	private StudentDaoImpl studentDaoImpl;
	
	@Test
	public void testAdd(){
		Student s = new Student();
		s.setName("test");
		s.setAge(45);
		studentDaoImpl.save(s);
	}
	
	@Test
	public void testExist(){
		Student s = new Student();
		s.setName("test");
		Assert.assertTrue(studentDaoImpl.exists(s));
	}
	
	@Test
	public void testUpdate(){
		Student s = new Student();
		s.setAge(70);
		studentDaoImpl.updatePK(s);
	}
	
	@Test
	public void testPg(){
		Student s = new Student();
		s.setAge(66);
		PageDto<Student> pg = new PageDto<Student>();
		pg.setDto(s);
		Pagination p =studentDaoImpl.findObjectsWithPg(pg);
		Assert.assertTrue(p.getResultList().size() > 0);
	}
	
	@Test
	public void testDelete(){
		Student s = studentDaoImpl.getRow(new Student(70));
		studentDaoImpl.delete(s);
	}
	

}
