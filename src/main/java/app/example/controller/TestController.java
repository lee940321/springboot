package app.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import app.example.domain.Test;
import app.example.service.TestService;

@RestController
@RequestMapping("/test")
public class TestController {

	@Autowired 
	private TestService testService;
	
	@RequestMapping(value="/query",method=RequestMethod.GET)
	public int getTest(){
		List<Test> list = testService.queryTest("lee");
		return list.get(0).getId();
	}
}
