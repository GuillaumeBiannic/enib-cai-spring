<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!-- Very simple menu manager -->
<c:set var="pathinfo" value="${fn:split(pageContext.request.requestURI, '/')}" />
<c:set var="jspname" value="${pathinfo[fn:length(pathinfo)-1]}" />

<!--  first extract the local from uri parameters -->
<c:choose>
    <c:when test="${empty param.locale}">
		<!-- No url attribut - Read the cookie for the locale -->
		<c:forEach items="${cookie}" var="currentCookie">
			<c:choose>
				<c:when test="${currentCookie.value.name=='org.springframework.web.servlet.i18n.CookieLocaleResolver.LOCALE'}"> 
					<c:set var="locale" value="${fn:split(currentCookie.value.value, '_')}" />
					<c:set var="langage" value="${locale[0]}" />
					<c:set var="country" value="${locale[1]}" />
				</c:when>
				<c:otherwise>
					<!-- Default locale -->
					<c:set var="langage" value="fr" />
					<c:set var="country" value="FR" />
				</c:otherwise>
			</c:choose>
		</c:forEach>          
    </c:when>
    <c:otherwise>
    	<!-- set the locale by url attribut -->
		<c:set var="locale" value="${fn:split(param.locale, '_')}" />
		<c:set var="langage" value="${locale[0]}" />
		<c:set var="country" value="${locale[1]}" />
    </c:otherwise>
</c:choose>





<div id="topnavbar" class="navbar navbar-inverse navbar-fixed-top">
	<div class="navbar-inner">
		<div class="container">
			<button type="button" class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
				<span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span>
			</button>
			<a class="brand" href="#"><fmt:message key="application.title" /></a>
			<div class="nav-collapse collapse">
				<ul class="nav">
					<sec:authorize access="isAnonymous()">
						<li class="${(jspname=='welcome.jsp') ? 'active' : ''}" ><a href="."><fmt:message key="menu.homepage"/></a></li>
						<li class="${(jspname=='createAccountForm.jsp') ? 'active' : ''}"><a href="signup"><fmt:message key="menu.signup"/></a></li>
						<li class="${(jspname=='createAccountFormAjax.jsp') ? 'active' : ''}"><a href="signup-ajax"><fmt:message key="menu.signup"/> (Ajax)</a></li>
					</sec:authorize>
					<!-- only for authenticated -->
					<sec:authorize access="isAuthenticated()">
						<li class="${(jspname=='home.jsp') ? 'active' : ''}"><a href="home"><fmt:message key="menu.home"/></a></li>
						<li class="${(jspname=='bar.jsp') ? 'active' : ''}"><a href="bar"><fmt:message key="menu.bar"/></a></li>
						<li><a data-toggle="lightbox" href="#addbeer">Add Beer</a></li>
					</sec:authorize>
					
				</ul>
				<ul class="nav pull-right">
					<sec:authorize access="isAuthenticated()">
						<li class=""><a href="<c:url value="/j_spring_security_logout" />" > Logout</a></li>
					</sec:authorize>	
					<sec:authorize access="isAnonymous()">
						<li class="${(jspname=='login.jsp') ? 'active' : ''}"><a href="login"><fmt:message key="menu.signin"/></a></li>
					</sec:authorize>
					<li class="divider-vertical"></li>
					<li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown">
						<fmt:message key="internationalization.langage" />
						<fmt:message key="internationalization.langage.${langage}"/><b class="caret"></b></a>
						<ul class="dropdown-menu">
							<li><a href="?locale=en_us"><fmt:message key="internationalization.langage.en" /></a></li>
							<li><a href="?locale=fr_FR"><fmt:message key="internationalization.langage.fr" /></a></li>
						</ul>
					</li>
				</ul>
			</div>
		</div>
	</div>
</div>
