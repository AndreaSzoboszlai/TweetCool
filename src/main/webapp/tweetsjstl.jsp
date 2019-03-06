<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link href="../index.css" rel="stylesheet" type="text/css">
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Greeting</title>
</head>
<body>
<h1>Tweets: </h1>
<c:forEach var="t" items="${tweets}">
    <p><c:out value="${t.posterName}"/>  :  <c:out value="${t.content}"/><p>
</c:forEach>
<a href="index.html">Back to main page.</a>
<br>
</body>
</html>
