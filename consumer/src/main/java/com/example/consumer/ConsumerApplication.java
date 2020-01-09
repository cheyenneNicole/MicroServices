package com.example.consumer;

import com.example.consumer.controller.ConsumerController;
import com.example.consumer.model.Consumer;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.List;

@EnableDiscoveryClient
@SpringBootApplication
public class ConsumerApplication implements ExitCodeGenerator {

	private static ConfigurableApplicationContext context;

	private static ConsumerController consumerController;

	public static void main(String[] args) {
		context = SpringApplication.run(ConsumerApplication.class, args);

		testApp();
	}
	public static void testApp(){
		System.out.println();
		consumerController = (ConsumerController) context.getBean("consumerController");

		Consumer customer1 = Consumer.builder().withFirstName("Homer").withLastName("Simpson").withEmail("homer@gmail.com").build();
		Consumer customer2 = Consumer.builder().withFirstName("Lisa").withLastName("Millhouse").withEmail("lisa@gmail.com").build();
		Consumer customer3 = Consumer.builder().withFirstName("Marge").withLastName("Simpson").withEmail("marge@gmail.com").build();
		Consumer customer4 = Consumer.builder().withFirstName("Bart").withLastName("Simpson").withEmail("bart@gmail.com").build();

		consumerController.add(customer1);
		consumerController.add(customer2);
		consumerController.add(customer3);
		consumerController.add(customer4);

		display(consumerController.getAll());
		display(consumerController.getByEmail("lisa@gmail.com"));
	}

	private static void display(Object obj) {

		if (obj instanceof List) {

			List<?> list = (List) obj;
			for (Object o : list) {
				System.out.println("\t" + o);
			}

		} else {

			System.out.println(obj);

		}

		System.out.println();
	}

	@Override
	public int getExitCode() {
		return 42;
	}

}
