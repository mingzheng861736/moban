package com.mz.demo.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;

/**
 * 校验配置
 * @author weishilei
 * @date 2019.09.17
 */
@Configuration
@EnableAutoConfiguration
public class ValidatorConfig {

   @Bean
   public MethodValidationPostProcessor methodValidationPostProcessor(){
      return new MethodValidationPostProcessor();
   }
}