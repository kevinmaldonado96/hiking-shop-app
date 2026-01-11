import org.apache.kafka.common.protocol.types.Field;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseService {

    Connection connection;

    public DatabaseService(String dbUrl, String dbUser, String dbPass) {
        try {
            connection = DriverManager.getConnection(dbUrl, dbUser, dbPass);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } 
    }

    public void updateOrderStatus(Long orderId) throws SQLException {
        String query = "UPDATE orders SET status = 'DELIVERED' WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setLong(1, orderId);
            int rows = stmt.executeUpdate();
            System.out.println("✅ Updated " + rows + " rows for order " + orderId);
        }
    }
}
