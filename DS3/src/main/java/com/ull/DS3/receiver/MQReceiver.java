package com.ull.DS3.receiver;

import com.fasterxml.jackson.core.JsonParser;
import com.ull.DS3.HashTable;
import com.ull.DS3.Search;
import org.json.*;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.JsonObjectDeserializer;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.Map;

import static com.ull.DS3._webServiceSearch.searchTermInIndex;
import static com.ull.DS3._webServiceSearch.template;
import java.lang.Thread;


@Component
public class MQReceiver {
    @Autowired
    private HashTable hp;
    private static final String template = "%s!";
    @RabbitListener(queues = {"SearchQueue"})
    public void receiveManage(@Payload String Message){
        System.out.println("Received message:  "+Message);
        try {
            JSONObject jsonObject = new JSONObject(Message);
            String v = jsonObject.get("key").toString();
            String value = searchTermInIndex(v);
            Thread.sleep(2000);
            hp.put(jsonObject.getInt("id"),value);
            System.out.println(hp.keySet());
        }catch (JSONException | InterruptedException err){
            System.out.println("in catch ");
        }
    }
}
