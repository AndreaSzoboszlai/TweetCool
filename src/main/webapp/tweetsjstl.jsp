<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<link href="index.css" rel="stylesheet" type="text/css">
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Tweets</title>
</head>
<body>
<script>
    onload = function() {
        setTimeout("location.reload(true)",5000);
    }
</script>
<h1>Tweets: </h1>
<c:forEach var="t" items="${filtered}">
    <div class="tweets">
    <c:out value="${t.posterName}"/>  :  <c:out value="${t.content}"/><br>
    <c:out value="${t.timestamp}"/><br>
    </div>
    <br>
</c:forEach>
<a href="index.html">Back to main page.</a>
<br>
</body>
</html>
