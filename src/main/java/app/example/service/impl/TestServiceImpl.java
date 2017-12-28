package app.example.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.example.dao.TestMapper;
import app.example.domain.Test;
import app.example.service.TestService;

@Service
public class TestServiceImpl implements TestService{

	@Autowired
	private TestMapper testMapper;
	
	@Override
	public List<Test> queryTest(String name){
		System.out.println("执行到service");
		return testMapper.findByName(name);
	}
}
