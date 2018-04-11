package app.example.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import app.example.dao.TestMapper;
import app.example.domain.Test;
import app.example.service.TestService;

@Service
public class TestServiceImpl implements TestService{

	@Autowired
	private TestMapper testMapper;
	
	
	
	/**
	 * redis测试
	 */
	@Override
	@Cacheable(value="thisredis", key="'test_'+#name")
	public List<Test> queryTest(String name){
		System.out.println("实时");
		return testMapper.findByName(name);
	}
    

    @CacheEvict(value="thisredis", key="'test_'+#name",condition="#name！=1")
    public void delUser(String name) {
        // 删除user
        System.out.println("user删除");
    }
}
