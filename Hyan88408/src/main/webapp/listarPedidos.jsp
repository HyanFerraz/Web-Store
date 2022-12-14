<%@page import="java.util.List"%>
<%@page import="modelo.dao.DetalheDAO"%>
<%@page import="modelo.entidades.Detalhe"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="CSS/listar.css">
</head>
<body>
		
	<%
	DetalheDAO dao = new DetalheDAO();
	List <Detalhe> lista = dao.listar();
	%>
	
	<div class="lista">
		<%
		for (Detalhe detalhe : lista) {
		%>
			
		<div class="item">
			<form action="removerPedido" >
				<p><%= detalhe.getPedido().getNome() %></p>
				<p><%= detalhe.getProduto().getNome() %></p>
				<p><%= detalhe.getPedido().getData() %></p>
				<p><%= detalhe.getQuantidade() %></p>
				<p><%= detalhe.getTotal() %></p>
				
				<input type="hidden" value="<%= detalhe.getPedido().getId() %>" name="id">
				
				<input type="submit" value="Remover pedido">				
			</form>
		</div>
		<% } %>
	</div>
	
</body>
</html>