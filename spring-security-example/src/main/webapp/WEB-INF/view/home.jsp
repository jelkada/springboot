<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<html>

	<head>
		<title>Home page</title>
	</head>
		
	<body>
		<h2>Welcome to Home page</h2>

		<hr/><br/>
		
		<div>User: <security:authentication property="principal.username" /></div>
		<div>Roles: <security:authentication property="principal.authorities" /></div>
		
		<br/>
		<hr/><br/>

		<security:authorize access="hasRole('MANAGER')">
			<div><a href="${pageContext.request.contextPath}/leaders">Leadership Meeting (only for leaders)</a></div>
			<hr/>
		</security:authorize>
		
		<security:authorize access="hasRole('ADMIN')">
			<div><a href="${pageContext.request.contextPath}/admin">Admin (only for admins)</a></div>
			<hr/>
		</security:authorize>
		
		<br/><br/>
		
		<form:form action="${pageContext.request.contextPath}/logout" 
				   method="POST" class="form-horizontal">
			<input type="Submit" value="Logout">
		</form:form>
	</body>

</html>