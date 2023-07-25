package com.ull.DS3.sender;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.amqp.core.Queue;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;
import java.lang.Thread;

@Component
public class MQSender {
    @Autowired
    private Queue queue;
    @Autowired
    private RabbitTemplate rabbitTemplate;


    private static AtomicInteger id = new AtomicInteger(0);
    public AtomicInteger sendMessage(String Message) throws JSONException, InterruptedException {
        JSONObject json = new JSONObject();
        json.put("key",Message);
        json.put("id",id.incrementAndGet());
        Thread.sleep(3000);
        rabbitTemplate.convertAndSend(this.queue.getName(),json.toString());
        return id;
    }
}
