<%@ page import="com.project.chatbox.Message" %>
<html>
<head>
    <title>Chatroom</title>
</head>
<body>
    <h1>Team Chat</h1>

    <div id="chat-box">
        <c:forEach var="message" items="${messages}">
            <p><strong>${message.userId}:</strong> ${message.message}</p>
        </c:forEach>
    </div>

    <form action="chat" method="POST">
        <input type="hidden" name="teamId" value="${teamId}">
        <input type="text" name="message" placeholder="Type a message...">
        <button type="submit">Send</button>
    </form>
</body>
</html>
