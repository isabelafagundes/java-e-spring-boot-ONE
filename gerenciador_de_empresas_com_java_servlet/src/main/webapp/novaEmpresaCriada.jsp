<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<body>
		<c:if test="${not empty empresa}">
			Empresa ${ empresa } foi cadastrada com sucesso!
		</c:if>
		
		<c:if test="${empty empresa}">
			Nenhuma empresa cadastrada!
		</c:if>
	</body>
</html>
