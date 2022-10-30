<%@page import="modelo.entidades.Pedido"%>
<%@page import="modelo.dao.ProdutoDAO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="modelo.entidades.Produto"%>
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
	ProdutoDAO dao = new ProdutoDAO();
	List <Produto> lista = dao.listar();
	%>
	
		<div class="container">
			<div class="lista">
				<% for (Produto produto : lista) { %>
					<form action="adicionarPedido">
					<div class="item">
						<p><%= produto.getNome() %></p>
						<p><%= produto.getDescricao()%></p>
						<p><%= produto.getPreco() %></p>
						<p><%= produto.getCategoria().getCategoria() %></p>
						<label>Quantidade</label>
						<input type="hidden" name="produto" value="<%= produto.getId() %>">
						<input type="hidden" name="nomeContato" value="<%= request.getParameter("nomeContato") %>">
						<input type="hidden" name="endereco" value="<%= request.getParameter("endereco") %>">
						<input type="hidden" name="data" value="<%= request.getParameter("data") %>">
						<input type="number"  name="quantidade">
						<input type="submit" value="Adicionar ao pedido" class="button">
					</div>
					</form>
				<% } %>
			</div>	
	</div>
	
</body>
</html>