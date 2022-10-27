<%@page import="java.util.Set"%>
<%@page import="modelo.dao.CategoriaDAO"%>
<%@page import="modelo.entidades.Categoria"%>
<%@page import="java.util.Map"%>
<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="CSS/cadastro.css">
</head>
<body>

	<%	Map<Integer, Categoria> lista = new CategoriaDAO().listar();
		Set<Integer> listaID = lista.keySet();%>

	<div class="cadastro_div">
		<form method="post" action="produto">
			<label>Nome do Produto</label><p/>
			<input type="text" name="nomeProduto" required><p/>
			
			<label>Descrição do Produto</label><p/>
			<input type="text" name="descricaoProduto" required><p/>
			
			<label>Preco do Produto</label><p/>
			
			<input type="number" name="precoProduto" required><p/>
			
			<label>Categoria</label><p/>
			<select name="categoria" required>
				<option value="" disabled="disabled">-- Selecione uma Opção --</option>
				
				<% for(Integer id : listaID) { %>
				<option value="<%= id %>"> <%= lista.get(id).getCategoria()%></option>
				<% } %>
				
			</select><p>
			
			<input type="submit" value="Adicionar" class="button" ><p/>
		</form>
	</div>
</body>
</html>