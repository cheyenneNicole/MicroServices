package com.example.restclient.business;


import com.example.restclient.model.Consumer;
import com.example.restclient.model.Item;
import com.example.restclient.service.ConsumerService;
import com.example.restclient.service.ItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class StoreBusinessFlow {

    private static final Logger log = LoggerFactory.getLogger(StoreBusinessFlow.class.getName());

    private ConsumerService consumerService;
    private ItemService itemService;


    public StoreBusinessFlow(ConsumerService consumerService, ItemService itemService) {
        this.consumerService = consumerService;
        this.itemService = itemService;
    }

    public void runBusiness() {


        // Create Customers
        Consumer consumer1 = Consumer.builder().withFirstName("Homer").withLastName("Simpson").withEmail("homer@gmail.com").build();
        Consumer consumer2 = Consumer.builder().withFirstName("Marge").withLastName("Simpson").withEmail("marge@gmail.com").build();
        Consumer consumer3 = Consumer.builder().withFirstName("Bart").withLastName("Simpson").withEmail("bart@gmail.com").build();
        Consumer consumer4 = Consumer.builder().withFirstName("Lisa").withLastName("Simpson").withEmail("lisa@gmail.com").build();

        // saveCustomer
        consumerService.saveConsumer(consumer1);
        consumerService.saveConsumer(consumer2);
        consumerService.saveConsumer(consumer3);
        consumerService.saveConsumer(consumer4);

        // getAllCustomers
        List<Consumer> customers = consumerService.getAllConsumers();
        customers.forEach(consumer -> log.info(consumer.toString()));

        // getCustomerByName
        final String searchFor = "homer@gmail.com";
        List<Consumer> homers = consumerService.getConsumerByEmail(searchFor);

        final AtomicInteger counter = new AtomicInteger(1);
        homers.forEach(homer -> {

            StringBuilder sb = new StringBuilder();
            sb.append(searchFor);
            sb.append(" ");
            sb.append(counter.getAndIncrement());
            sb.append(": ");
            sb.append(homer);

            log.info(sb.toString());
        });

        /*
        Items
         */

        Consumer homer = null;
        if (!homers.isEmpty()) {
            homer = homers.get(0);
        }

        String target = "marge@gmail";
        List<Consumer> marges = consumerService.getConsumerByEmail(target);
        Consumer marge = null;
        if (!marges.isEmpty()) {
            marge = marges.get(0);
        }

        target = "bart@gmail.com";
        List<Consumer> barts = consumerService.getConsumerByEmail(target);
        Consumer bart = null;
        if (!barts.isEmpty()) {
            bart = barts.get(0);
        }

        target = "lisa@gmail.com";
        List<Consumer> lisas = consumerService.getConsumerByEmail(target);
        Consumer lisa = null;
        if (!lisas.isEmpty()) {
            lisa = lisas.get(0);
        }

        // Items for Homer
        Item item1 = Item.builder().withName("Belt").withDescription("Brown, and black").withConsumerId(homer.getId()).build();
        Item item2 = Item.builder().withName("iPad").withDescription("Cherry flavored").withConsumerId(homer.getId()).build();
        Item item3 = Item.builder().withName("Bottle Water").withDescription("***").withConsumerId(homer.getId()).build();
        Item item4 = Item.builder().withName("Mouse").withDescription("***").withConsumerId(homer.getId()).build();

        // Items for Marge
        Item item5 = Item.builder().withName("Greyhound").withDescription("****").withConsumerId(marge.getId()).build();

        // Items for Bart
        Item item6 = Item.builder().withName("Laddie").withDescription("****").withConsumerId(bart.getId()).build();
        Item item7 = Item.builder().withName("Santa's Little Helper").withDescription("****").withConsumerId(bart.getId()).build();
        Item item8 = Item.builder().withName("Stampy").withDescription("****").withConsumerId(bart.getId()).build();
        Item item9 = Item.builder().withName("Duncan").withDescription("****").withConsumerId(bart.getId()).build();


        // Items for Lisa
        Item item10 = Item.builder().withName("Nibbles").withDescription("***").withConsumerId(lisa.getId()).build();
        Item item11 = Item.builder().withName("Chirpy Boy").withDescription("***").withConsumerId(lisa.getId()).build();
        Item item12 = Item.builder().withName("Bart Junior").withDescription("***").withConsumerId(lisa.getId()).build();
        Item item13 = Item.builder().withName("Snowball IV").withDescription("***").withConsumerId(lisa.getId()).build();
        Item item14 = Item.builder().withName("Princess").withDescription("***").withConsumerId(lisa.getId()).build();

        itemService.saveItem(item1);
        itemService.saveItem(item2);
        itemService.saveItem(item3);
        itemService.saveItem(item4);
        itemService.saveItem(item5);
        itemService.saveItem(item6);
        itemService.saveItem(item7);
        itemService.saveItem(item8);
        itemService.saveItem(item9);
        itemService.saveItem(item10);
        itemService.saveItem(item11);
        itemService.saveItem(item12);
        itemService.saveItem(item13);
        itemService.saveItem(item14);

        List<Item> items = itemService.getAllItems();

        items.forEach(item -> log.info(item.getName()));

        List<Item> laddie = itemService.getItemByName("Laddie");

        laddie.forEach(l -> log.info(l.toString()));


    }
}
