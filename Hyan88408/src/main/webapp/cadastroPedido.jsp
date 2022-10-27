<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<link rel="stylesheet" type="text/css" href="CSS/cadastro.css">
<body>

	<div class="cadastro_div">
		<form action="listarProdutos.jsp" method="post">
			<div class="container">
				<div class="cliente">
					<label>Nome</label><p>
					<input type="text" name="nomeContato" required><p>
					<label>Endereco</label><p>
					<input type="text" name="endereco" required><p>
					<input type="date" name="data" required>
					<input type="submit" value="Próximo">
				</div>
			</div>
		</form>
	</div>

</body>
</html>