package com.ull.DS3.mqconfig;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



@Configuration
public class MQConfig {

    @Bean
    public Queue queue(){
        return new Queue("SearchQueue");

    }
}
