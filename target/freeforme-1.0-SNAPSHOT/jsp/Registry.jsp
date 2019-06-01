<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: ny3an6
  Date: 30.05.19
  Time: 19:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link href="/css/styles.css" rel="stylesheet" type="text/css">
</head>
<body>
<div class="form-style-2">
    <div class="form-style-2-heading">
        Please add user
    </div>
    <form method="post" action="/registry">
        <%--@declare id="password"--%><label for="first-name">First Name
            <input class="input-field" type="text" id="first-name" name="first-name">
        </label>
        <label for="last-name">Last Name
            <input class="input-field" type="text" id="last-name" name="last-name">
        </label>
        <label for="password">Password
            <input class="input-field" type="password" id="password" name="password">
        </label>
        <input type="submit" value="Add user">
            <c:forEach items="${message}" var="mess">
                <div>
                    ${mess}
                </div>
            </c:forEach>
    </form>
</div>
</body>
</html>