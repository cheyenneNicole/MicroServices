package com.example.item;

import com.example.item.controller.ItemController;
import com.example.item.model.Item;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.List;


@EnableDiscoveryClient
@SpringBootApplication
public class ItemApplication implements ExitCodeGenerator {

	private static ConfigurableApplicationContext context;

	private static ItemController itemController;

	public static void main(String[] args) {
		context = SpringApplication.run(ItemApplication.class, args);
		testApp();
	}

	private static void testApp() {
		System.out.println();
		itemController = (ItemController) context.getBean("itemController");


		Item item1 = Item.builder().withName("Coffee").withDescription("Starbucks").withPrice(10.99).build();
		Item item2 = Item.builder().withName("Chicken Salad").withDescription("HEB BRAND").withPrice(5.49).build();
		Item item3 = Item.builder().withName("Cellphone").withDescription("iPhone").withPrice(1099.99).build();
		Item item4 = Item.builder().withName("Turkey").withDescription("1 pound").withPrice(12.34).build();

		itemController.add(item1);
		itemController.add(item2);
		itemController.add(item3);
		itemController.add(item4);

		display(itemController.getAll());
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

	public int getExitCode() {
		return 42;
	}

}