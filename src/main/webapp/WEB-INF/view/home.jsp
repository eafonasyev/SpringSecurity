<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<html>

<head>
    <title>luv2code Company Home Page</title>
</head>

<body>
<h2>luv2code Company Home Page</h2>
<hr>

<p>
    Welcome to the luv2code company home page!
</p>

<p>

    User:<security:authentication property="principal.username"/>
    <br><br>
    Role:<security:authentication property="principal.authorities"/>
</p>
<security:authorize access="hasRole('MANAGER')">
<p>
    <a href="${pageContext.request.contextPath}/leader">LeaderShip</a>
     (Only for LeaderShip)
</p>
 </security:authorize>
<security:authorize access="hasRole('ADMIN')">
<p>
    <a href="${pageContext.request.contextPath}/admin">AdminShip</a>
    (Only for AdminShip)
</p>
 </security:authorize>

<!-- Add a logout button -->
<form:form action="${pageContext.request.contextPath}/logout"
           method="POST">

		<input type="submit" value="Logout" />

	</form:form>

</body>

</html>
