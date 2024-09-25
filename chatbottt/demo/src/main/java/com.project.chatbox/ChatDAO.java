package com.project.chatbox;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ChatDAO {
    private String jdbcURL = "jdbc:mysql://localhost:3306/management_portal";
    private String jdbcUsername = "root";
    private String jdbcPassword = "aryan123";

    public List<Message> getMessages(String teamId) {
        List<Message> messages = new ArrayList<>();
        String sql = "SELECT * FROM messages WHERE team_id = ?";

        try (Connection connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, teamId);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                String userId = rs.getString("user_id");
                String message = rs.getString("message");
                messages.add(new Message(userId, teamId, message));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return messages;
    }

    public void saveMessage(Message message) {
        String sql = "INSERT INTO messages (user_id, team_id, message) VALUES (?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, message.getUserId());
            statement.setString(2, message.getTeamId());
            statement.setString(3, message.getMessage());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
