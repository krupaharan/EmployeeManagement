//package com.org.employeemgmt.config;
//
//import java.util.concurrent.Executor;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
//
//@Configuration
//public class ThreadConfiguration {
//
//	@Bean(name = "asyncExecutor")
//    public Executor asyncExecutor() 
//    {
//        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
//        executor.setCorePoolSize(3);
//        executor.setMaxPoolSize(3);
//        executor.setQueueCapacity(100);
//        executor.setThreadNamePrefix("AsynchThread-");
//        executor.initialize();
//        return executor;
//    }
//}
