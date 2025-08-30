package com.payetonkawa.order;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import java.io.Serializable;
@SpringBootApplication
public class OrderApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class, args);
    }
}
@Component
class OrderMessageListener {
    @RabbitListener(queues = "produit-change-queue")
    public void receiveProductChange(Serializable produit) {
        // Logique pour traiter le message
        System.out.println("Message reçu de l'API Produits : " + produit);
    }

    @RabbitListener(queues = "client-change-queue")
    public void receiveClientChange(Serializable client) {
        // Logique pour traiter le message
        System.out.println("Message reçu de l'API Clients : " + client);
    }
}
