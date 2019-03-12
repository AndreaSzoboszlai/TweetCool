<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.codecool.web.model.Tweet" %>
<link href="index.css" rel="stylesheet" type="text/css">
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Greeting</title>
</head>
<body>
<h1>Got your Tweet!</h1>
<% List<Tweet> tweets1 = (List<Tweet>) request..getAttribute("tweets"); %>
<% for (Tweet t : tweets1) { %>
    <%= t.getPosterName() %> : <%= t.getContent() %> <br>

    <%=  t.getTimestamp()  %> <br>
    -----------------------<br>
    <br>
<% } %>
<a href="index.html">Go back to the <em>index</em> page.</a>
<br>
</body>
</html>
