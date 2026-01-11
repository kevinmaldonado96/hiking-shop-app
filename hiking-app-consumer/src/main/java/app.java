import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.sql.SQLException;
import java.time.Duration;
import java.util.Properties;

public class app {

    public static void main(String[] args) {

        String bootstrapServers = System.getenv("KAFKA_BOOTSTRAP_SERVERS");
        String topic = System.getenv("KAFKA_TOPIC");
        String dbUrl = System.getenv("DB_URL");
        String dbUser = System.getenv("DB_USER");
        String dbPass = System.getenv("DB_PASSWORD");


        DatabaseService dbService = new DatabaseService(dbUrl, dbUser, dbPass);
        KafkaConfig kafkaConfig = new KafkaConfig();

        Properties props = kafkaConfig.getProperties(bootstrapServers, topic);
        KafkaConsumer<String, Long> consumer = kafkaConfig.getConsumer(props, topic);

        while (true) {
            ConsumerRecords<String, Long> records = consumer.poll(Duration.ofMillis(1000));
            for (ConsumerRecord<String, Long> record : records) {
                System.out.println("📩 Received: " + record.value());
                try {
                    dbService.updateOrderStatus(record.value());
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }

        }
    }


}
