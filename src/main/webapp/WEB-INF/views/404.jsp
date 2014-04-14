<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
	<head>
		<title>Page non trouvée</title>
		<jsp:include page="includes/head.jsp" />
	</head>	
	<body>
		<!-- nav bar -->
		<jsp:include page="includes/navbar.jsp" />
	
		<div class="container" data-spy="scroll" data-target="#topnavbar" data-twttr-rendered="true" style="padding-top: 40px;">
		Page non trouvée
		Désolé,
		la page que vous demandez est introuvable.
		</div>
		
		<jsp:include page="includes/footer.jsp" />
	</body>
</html>