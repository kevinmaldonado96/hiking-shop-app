package org.example.hickingshop.services.iservices;

public interface IKafkaService {
    void sendMessage(Long orderId, Long userId);
}
