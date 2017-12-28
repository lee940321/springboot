package app;

import java.util.logging.Logger;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

/**
 * springboot 启动类
 * @author lidanfeng
 *
 */
@SpringBootApplication
//@ServletComponentScan
//@ComponentScan  			  //开启bean扫描 
//@EnableAutoConfiguration	  //开启自动配置
public class Application {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SpringApplication.run(Application.class, args);
	}

}
