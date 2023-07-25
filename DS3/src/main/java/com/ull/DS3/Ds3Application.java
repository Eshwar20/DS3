package com.ull.DS3;

import com.ull.DS3.sender.MQSender;
import org.json.JSONException;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.handler.MappedInterceptor;

import java.util.Hashtable;
import java.util.concurrent.atomic.AtomicInteger;

@SpringBootApplication
@RestController
@EnableRabbit
public class Ds3Application {

	static final String topicExchangeName = "spring-boot-exchange";
 /*
	static final String queueName = "SearchQueue";

	@Bean
	Queue queue() {
		return new Queue(queueName, false);
	}
*/
	@Bean
	TopicExchange exchange() {
		return new TopicExchange(topicExchangeName);
	}
/*
	@Bean
	Binding binding(Queue queue, TopicExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with("foo.bar.#");
	}

	@Bean
	SimpleMessageListenerContainer container(ConnectionFactory connectionFactory,
											 MessageListenerAdapter listenerAdapter) {
		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
		container.setConnectionFactory(connectionFactory);
		container.setQueueNames(queueName);
		container.setMessageListener(listenerAdapter);
		return container;
	}

	@Bean
	MessageListenerAdapter listenerAdapter(Receiver receiver) {
		return new MessageListenerAdapter(receiver, "receiveMessage");
	}
*/
	@Autowired
	MQSender mqSender;
	@Autowired
	HashTable hashTable;

	@GetMapping("/search")
	public String sendMessage(@RequestParam(defaultValue = "Hi") String key) throws JSONException, InterruptedException {
		AtomicInteger id = mqSender.sendMessage(key);

		return id.toString();
	}

	@GetMapping("/id")
	public String getId(int Id)
	{
		if(!hashTable.keySet().contains(Id))
			return "Wait and try again";
		return hashTable.get(Id);
	}
	public static void main(String[] args) {
		SpringApplication.run(Ds3Application.class, args);
	}

}
