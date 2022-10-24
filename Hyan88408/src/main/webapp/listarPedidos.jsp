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
</head>
<body>
		
	<%
	DetalheDAO dao = new DetalheDAO();
	List <Detalhe> lista = dao.listar();
	%>
	
	<%
	for (Detalhe detalhe : lista) {
	%>
			
		<div class="Lista Produtos">
			<form action="pedido" >
				<p><%= detalhe.getPedido().getNome() %></p>
				<p><%= detalhe.getProduto().getNome() %></p>
				<p><%= detalhe.getPedido().getData() %></p>
				<p><%= detalhe.getQuantidade() %></p>
				<p><%= detalhe.getTotal() %></p>
				
				<a href=""><button value="Remover pedido"></button></a>
				
			</form>
		</div>
	<% } %>
	
</body>
</html>