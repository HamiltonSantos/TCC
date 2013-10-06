<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Nova Categoria</title>
</head>
<body>
	<h3>Adicionar Categoria</h3>
	
	<form:form action="/faddvm/categoria" commandName="categoria">
		<div class="form-group">
			<form:label path="descricao">Descrição:</form:label> 
			<form:input path="descricao" class="form-control" placeholder="Descrição da Categoria"/>
			<form:hidden path="status" />
		</div>
		<input type="submit" value="Salvar" />
	</form:form>
	
</body>
</html>