package com.alinesno.infra.ops.scheduler;

import com.alinesno.infra.common.core.context.SpringContext;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * 集成一个Java开发示例工具
 * @author luoxiaodong
 * @version 1.0.0
 */
@EnableInfraSsoApi
@MapperScan("com.alinesno.infra.ops.scheduler.mapper")
@SpringBootApplication
public class OpsSchedulerApplication {

	public static void main(String[] args) {
		SpringApplication.run(OpsSchedulerApplication.class, args);
	}


	@Bean
	public SpringContext getSpringContext(){
		return new SpringContext() ;
	}

}