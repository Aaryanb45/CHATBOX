package com.project.chatbox;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;
import java.io.IOException;
import java.util.List;

@WebServlet("/chat")
public class ChatServlet extends HttpServlet {
    private ChatDAO chatDAO;

    @Override
    public void init() throws ServletException {
        chatDAO = new ChatDAO(); // Initialize DAO for database access
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve messages for chatroom
        String teamId = request.getParameter("teamId");
        List<Message> messages = chatDAO.getMessages(teamId);

        request.setAttribute("messages", messages);
        RequestDispatcher dispatcher = request.getRequestDispatcher("chat.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Send message
        String teamId = request.getParameter("teamId");
        String userId = request.getParameter("userId");
        String messageText = request.getParameter("message");

        Message message = new Message(userId, teamId, messageText);
        chatDAO.saveMessage(message);

        response.sendRedirect("chat?teamId=" + teamId); // Reload chat page
    }
}
