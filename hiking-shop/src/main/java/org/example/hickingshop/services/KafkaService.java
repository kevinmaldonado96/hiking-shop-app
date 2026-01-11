package org.example.hickingshop.services;

import org.example.hickingshop.repository.OrderRepository;
import org.example.hickingshop.services.iservices.IKafkaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaService implements IKafkaService {

    @Value(value = "${kafka.topic.name}")
    private String topicName;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private KafkaTemplate<String, Long> kafkaTemplate;

    @Override
    public void sendMessage(Long orderId, Long userId) {
        orderRepository.findByIdAndUserId(orderId, userId).orElseThrow();
        kafkaTemplate.send(topicName, orderId);
    }
}
