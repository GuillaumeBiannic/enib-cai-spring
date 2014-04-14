<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
<head>
<title>Login Page</title>
<jsp:include page="includes/head.jsp" />
</head>


<body onload='document.f.j_username.focus();'>
	<!-- nav bar -->
	<jsp:include page="includes/navbar.jsp" />

	<div class="container" data-spy="scroll" data-target="#topnavbar"
		data-twttr-rendered="true" style="padding-top: 40px;">

		<c:if test="${not empty error}">
			<div class="alert alert-error">
				<fmt:message key="signin.error" />
				<br />
				<fmt:message key="signin.caused" />
				${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}
			</div>
		</c:if>

		<form class="form-horizontal" name='f'
			action="<c:url value='j_spring_security_check' />" method='POST'>
			<legend>
				<fmt:message key="signin.title" />
			</legend>
			<div class="control-group">
				<label class="control-label" for="inputUsername"><fmt:message
						key="signin.username" /></label>
				<div class="controls">
					<input type="text" id="inputUsername" name='j_username'
						placeholder=<fmt:message key="signin.username"/>>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label" for="inputPassword"><fmt:message
						key="signin.password" /></label>
				<div class="controls">
					<input type="password" id="inputPassword" name='j_password'
						placeholder=<fmt:message key="signin.password"/>>
				</div>
			</div>
			<div class="control-group">
				<div class="controls">
					<label class="checkbox"> <input type="checkbox">
					<fmt:message key="signin.rememberme" />
					</label>
					<button type="submit" name="submit" class="btn">
						<fmt:message key="signin.submit" />
					</button>
				</div>
			</div>
		</form>
	</div>

	<jsp:include page="includes/footer.jsp" />
</body>
</html>