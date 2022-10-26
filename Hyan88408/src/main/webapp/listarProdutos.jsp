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
<link rel="stylesheet" href="CSS/produto.css">
</head>
<body>
		
	<%
	ProdutoDAO dao = new ProdutoDAO();
	List <Produto> lista = dao.listar();
	%>
	
	<div>

		<form action="adicionarPedido">
			<div class="cliente">
				<label>Nome</label><p>
				<input type="text" name="nomeContato"><p>
				<label>Endereco</label><p>
				<input type="text" name="endereco"><p>
				<input type="date" name="data">
			</div>
	
			<% for (Produto produto : lista) { %>
				<div class="lista_produto">
					<p><%= produto.getNome() %></p>
					<p><%= produto.getDescricao()%></p>
					<p><%= produto.getPreco() %></p>
					<p><%= produto.getCategoria().getCategoria() %></p>
					<label>Quantidade</label>
					<input type="hidden" name="produto" value="<%= produto.getId() %>">
					<input type="number" name="quantidade">
					<input type="submit" value="Adicionar ao pedido" class="button">
				</div>
			<% } %>
		</form>
	</div>
	
</body>
</html>