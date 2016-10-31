package org.nlejeune;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import static org.springframework.amqp.core.BindingBuilder.bind;

@SpringBootApplication
@EnableRabbit
@EnableTransactionManagement(proxyTargetClass=true)
public class TxIssueApplication {

	public static final String NOTIFICATION_EXCHANGE = "com.emasphere.event";
	public static final String FORECAST_DEFINITION_CHANGED = "forecast.definition.changed";
	public static final String FORECAST_DEFINITION_CHANGED_QUEUE = FORECAST_DEFINITION_CHANGED;

	@Bean
	Queue forecastDefinitionChangedQueue() {
		return new Queue(FORECAST_DEFINITION_CHANGED_QUEUE);
	}

	@Bean
	Binding forecastDefinitionChangedBinding(Queue forecastDefinitionChangedQueue, TopicExchange notificationExchange) {
		return bind(forecastDefinitionChangedQueue).to(notificationExchange).with(FORECAST_DEFINITION_CHANGED);
	}

	@Bean
	public TopicExchange notificationExchange() {
		return new TopicExchange(NOTIFICATION_EXCHANGE);
	}

	public static void main(String... args) {
		SpringApplication.run(TxIssueApplication.class, args);
	}
}
