<%@page contentType="text/html;charset=UTF-8"%>
<%@page pageEncoding="UTF-8"%>
<%@ page session="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<title><fmt:message key="welcome.title" /></title>
<jsp:include page="includes/head.jsp" />
</head>
<body>
    <!-- nav bar -->
    <jsp:include page="includes/navbar.jsp"/>

	<div class="container" data-spy="scroll" data-target="#topnavbar" data-twttr-rendered="true" style="padding-top: 40px;">
		<!--  HEADER -->
		<div class="header">
			<div class="header-left">
				<ul class="nav">
					<li class="nav"><fmt:message key="welcome.title" /></li>
				</ul>
			</div>
		</div>
		<hr>
        
		<table id="beerstable" class="table table-condensed">
              <thead>
                <tr>
                  <th>Name</th>
                  <th>Brewery</th>
                  <th>Country</th>
                  <th>Alcohol</th>        
                </tr>
              </thead>
              <tbody>
   
				<c:forEach items="${beers}" var="beer">
	                <tr id="beer_${beer.id}">
	                  <td>${beer.name}</td>
	                  <td>${beer.brewery}</td>
	                  <td>${beer.country}</td>
	                  <td>${beer.alcohol}</td>
	                </tr>
				</c:forEach>
              </tbody>
         </table>
         <hr>
	</div>
	
	<jsp:include page="includes/footer.jsp" />
</body>


</html>