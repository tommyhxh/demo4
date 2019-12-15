package com.hisense.hitest2;
    /*
    * hxh
    * today
    */

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import com.didispace.swagger.EnableSwagger2Doc;
import com.hisense.saas.monitor.EnableHiMonitor;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

@SpringBootApplication
@EnableSwagger2Doc
@EnableTransactionManagement
@EnableHiMonitor
@EnableFeignClients

public class App {
    public static void main(String[] args) {
        new SpringApplicationBuilder(App.class).web(true).run(args);
    }
}
