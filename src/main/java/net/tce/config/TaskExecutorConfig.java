package net.tce.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;

@Configuration
public class TaskExecutorConfig {

	@Bean
	public SimpleAsyncTaskExecutor simpleAsyncTaskExecutor(){
		return new SimpleAsyncTaskExecutor();
		
	}
	
}
