package app.example.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.apache.solr.common.params.MapSolrParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import app.example.domain.SolrTest;
import app.example.domain.Test;
import app.example.service.TestService;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@RestController
@RequestMapping("/test")
public class TestController {

	@Autowired 
	private TestService testService;
	
	@Autowired 
	private HttpSolrClient solrServer;
	
	@RequestMapping(value="/query",method=RequestMethod.GET)
	public Test getTest(){
		List<Test> list = testService.queryTest("lll");
		return list.get(0);
	}
	
	@RequestMapping(value="/redis",method=RequestMethod.GET)
	public String redis(){
		
		JedisPoolConfig poolConfig = new JedisPoolConfig();
		poolConfig.setMaxIdle(30);
		poolConfig.setMinIdle(10);
		poolConfig.setMaxTotal(50);
		//new JedisPool(poolConfig, host, port, timeout, password)
		JedisPool pool = new JedisPool(poolConfig,"59.110.155.204",6379,5000,"123456");
		
		Jedis jedis = pool.getResource();
		
		jedis.set("lee", "大帅哥");
		System.out.println(jedis.get("lee"));
		jedis.close();
		return jedis.get("lee");
	}

	@RequestMapping(value="/solr")
	public void Solr() throws SolrServerException, IOException{
		 //[2]封装查询参数
        SolrQuery query = new SolrQuery("*:*");
        //[3]添加需要回显得内容
        query.addField("id");
        query.addField("tittle");
        query.setRows(20);//设置每页显示多少条
		
		//SolrTest result = new SolrTest();
		QueryResponse queryResponse = solrServer.query(query);
		SolrDocumentList solrDocumentList = queryResponse.getResults();
		System.out.println("查询总数"+solrDocumentList.getNumFound());
		Map<String, Map<String, List<String>>> highlighting = queryResponse.getHighlighting();
		
	}
	
	@RequestMapping(value="/addsolr")
	public void AddSolr() throws SolrServerException, IOException{
        //[2]创建文档doc
        SolrInputDocument doc = new SolrInputDocument();
        //[3]添加内容
        String str = UUID.randomUUID().toString();
        System.out.println(str);
        doc.addField("id", str);
        doc.addField("name", "Amazon Kindle Paperwhite");
        //[4]添加到client
        UpdateResponse updateResponse = solrServer.add(doc);
        System.out.println(updateResponse.getElapsedTime());
        //[5] 索引文档必须commit
        solrServer.commit();
		System.out.println("索引提交成功");
	}
	
}
