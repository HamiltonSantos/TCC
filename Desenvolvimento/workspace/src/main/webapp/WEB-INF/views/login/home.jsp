<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page session="false"%>
<html>
<head>
<title>Login</title>
<link href="/faddvm/webjars/bootstrap/3.0.0/css/bootstrap.min.css"
	rel="stylesheet" media="screen">
<script type="text/javascript"
	src="/faddvm/webjars/jquery/1.9.1/jquery.js"></script>
<script type="text/javascript"
	src="/faddvm/webjars/bootstrap/3.0.0/js/bootstrap.js"></script>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-md-4"></div>
			<div class="col-md-4">
				<form:form action="/faddvm/login" commandName="fisioterapeuta">
					<h2>Favor fazer o Login</h2>
					<form:input path="login" class="form-control" placeholder="Login"/>
					<form:input  path="senha" class="form-control" placeholder="Senha"/>
					<input type="submit" value="Login" class="btn btn-primary">
				</form:form>
			</div>
			<div class="col-md-4"></div>
		</div>
	</div>
</body>
</html>