<%@page import="br.com.alura.gerenciador.servlet.Empresa"%>
<%@page import="java.util.List"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<html>
	<body>
		<c:if test="${not empty empresa}">
			Empresa ${ empresa } foi cadastrada com sucesso!
		</c:if>
	
		<h3>Lista de empresas:</h3>
		<ul>
			<c:forEach items="${empresas}" var="empresa">
				<li>
					${empresa.nome} - <fmt:formatDate value="${empresa.dataAbertura}" pattern="dd/MM/yyyy"/>
					<a href="/gerenciador/removeEmpresa?id=${empresa.id}">Remover</a>
					<a href="/gerenciador/exibeEmpresa?id=${empresa.id}">Atualizar</a>
				</li>
			</c:forEach>
		</ul>
	
	</body>
</html>