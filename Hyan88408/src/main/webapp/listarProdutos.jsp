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
</head>
<body>
		
	<%
	ProdutoDAO dao = new ProdutoDAO();
	List <Produto> lista = dao.listar();
	%>
	
	
	<label>Nome</label><p>
	<input type="text" name="nomeContato"><p>
	<label>Endereco</label><p>
	<input type="text" name="endereco"><p>
	<input type="date" name="data">
	
	<%
	for (Produto produto : lista) {
	%>
			
		<div class="Lista Produtos">
			<form action="pedido" >
				<p><%= produto.getNome() %></p>
				<p><%= produto.getDescricao()%></p>
				<p><%= produto.getPreco() %></p>
				<p><%= produto.getCategoria().getCategoria() %></p>
				
				<%
				request.setAttribute("produto", produto);
				%>
				
				<label>Quantidade</label>
				<input type="number" name="quantidade">
				<input type="submit" value="Adicionar ao pedido">
				
				
			</form>
		</div>
	<% } %>
		
</body>
</html>