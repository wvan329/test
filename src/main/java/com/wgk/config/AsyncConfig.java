package com.wgk.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;


/**
 * 当你在方法上使用 @Async 注解时，Spring 会查找一个 Executor 来执行它。
 * 如果你实现了 AsyncConfigurer 并重写了 getAsyncExecutor() 方法，
 * Spring 就会使用你提供的线程池来执行所有的异步任务（包括事件监听器中标注了 @Async 的方法）。
 * 你不需要手动去调用 shutdown()，Spring 会在应用程序上下文关闭时自动管理线程池的销毁。
 */
@Configuration
public class AsyncConfig implements AsyncConfigurer {
    @Override
    public Executor getAsyncExecutor() {
        // 自定义线程池，替代默认的 SimpleAsyncTaskExecutor
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(5); // 核心线程数
        executor.setMaxPoolSize(10); // 最大线程数
        executor.setQueueCapacity(20); // 队列容量
        executor.setThreadNamePrefix("AsyncEvent-"); // 线程名前缀
        // 设置拒绝策略
        /**
         * AbortPolicy：会抛出 RejectedExecutionException 异常，告诉调用者任务提交失败。
         *
         * CallerRunsPolicy：会让主线程执行任务，而不是让线程池执行。
         *
         * DiscardPolicy：会丢弃任务，不抛出异常。
         *
         * DiscardOldestPolicy：会丢弃队列中最旧的任务，然后尝试提交当前任务。
         */
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy()); // 设置拒绝策略为 CallerRunsPolicy
        executor.initialize();
        return executor;
    }
}