package com.yuanian.dmp.base;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.openfeign.EcsEnableFeignClients;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import java.util.TimeZone;
/**
 * @author 访客
 * @since Wed Dec 30 14:46:58 CST 2020
 */
@SpringBootApplication(scanBasePackages = {"com.yuanian"})
@EnableTransactionManagement(proxyTargetClass = true)
@MapperScan(basePackages = {"com.yuanian.**.dao"})
@EnableCaching
@EnableAsync
@EcsEnableFeignClients(currentServiceName = "dmp-base", basePackages = {"com.yuanian"})
@EnableAspectJAutoProxy(proxyTargetClass = true, exposeProxy = true)
@EnableScheduling
public class App {
    public static void main(String[] args) {
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Shanghai"));
        SpringApplication.run(App.class, args);
    }

}
