package com.ess.framework.boot.asynctask;

import java.lang.reflect.Method;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
/**
 *  重新自定义线程池配置
 * @author Administrator
 *
 */
@Configuration
public class AsyncTaskExecutePool implements AsyncConfigurer {

	/** Logger used by this class. Available to subclasses. */
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private AsyncTaskThreadPoolConfig config;
	
	@Override
	public Executor getAsyncExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(config.getCorePoolSize());
		executor.setMaxPoolSize(config.getMaxPoolSize());
		executor.setQueueCapacity(config.getQueueCapacity());
		executor.setKeepAliveSeconds(config.getMaxPoolSize());
		executor.setThreadNamePrefix(config.getThreadNamePrefix());
		executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
		executor.initialize();
		return executor;
	}

	@Override
	public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
		return new AsyncUncaughtExceptionHandler() {
			
			@Override
			public void handleUncaughtException(Throwable ex, Method method, Object... params) {
				logger.error("exception  method:{}",method.getName());
				logger.error("exception message:{}",ex.getMessage());
			}
		};
	}

}
