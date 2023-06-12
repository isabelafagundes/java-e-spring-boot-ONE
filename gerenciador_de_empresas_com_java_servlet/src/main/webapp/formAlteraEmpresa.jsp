<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:url value="/atualizaEmpresa" var="caminhoNovaEmpresa"/>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Alterar empresa!</title>
</head>
	<body>
	
		<form action="${caminhoNovaEmpresa}" method="post">
			Nome: <input type="text" name="nome" value="${empresa.nome}"/>	
			Data de Abertura: <input type="text" name="data" value="<fmt:formatDate value="${empresa.dataAbertura}" pattern="dd/MM/yyyy"/>"/>
			<input type="hidden" name="id" value="${empresa.id}"/>	
			<input type="submit" />
		</form>
	
	</body>
</html>