<%@ page session="false" %>
<%@ page import="org.springframework.validation.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
	<head>
		<title>Create new Account</title>
		<jsp:include page="../includes/head.jsp" />
	</head>	
	<body>
		<!-- nav bar -->
		<jsp:include page="../includes/navbar.jsp" />
	
		<div class="container" data-spy="scroll" data-target="#topnavbar" data-twttr-rendered="true" style="padding-top: 40px;">
			<h1>
				Create new Account
			</h1>
			
		<c:if test="${not empty message}">
			<div class="alert alert-error">
				${message}
			</div>
		</c:if>			
			
			<form:form modelAttribute="account" action="signup" method="post" class="form-horizontal">
			  <div id="pseudoControlGroup" class="control-group">
		        <label class="control-label" id="pseudoLabel" for="pseudo" >Pseudo</label>
		    	<div class="controls">
		          <form:input path="pseudo" type="text" id="pseudo" placeholder="Pseudo"/>
		 	    </div>
		      </div>

			  <div id="firstnameControlGroup" class="control-group">   
		        <label class="control-label" id="firstnameLabel" for="firstname" >First name</label>
		    	<div class="controls">
		          <form:input path="firstname" type="text" id="firstname" placeholder="First name"/><form:errors path="firstname" cssClass="help-inline" />
		 	    </div>
		      </div>
		      
			  <div id="lastnameControlGroup" class="control-group">
		        <label class="control-label" id="lastnameLabel" for="lastname" >Last name</label>
		    	<div class="controls">
		          <form:input path="lastname" type="text" id="lastname" placeholder="Last name"/>
		 	    </div>
		      </div>

			  <div id="passwordControlGroup" class="control-group">
		        <label class="control-label" id="passwordLabel" for="password" >Password</label>
		    	<div class="controls">
		          <form:input path="password" type="password" autocomplete="off" id="password" placeholder="Password"/>
		 	    </div>
		      </div>
		      
	 		  <div class="control-group">
	    			<div class="controls">
	      				<button id="create" type="submit" class="btn">Create</button>
	    			</div>
	  		  </div>		            
			</form:form>
			<hr>
		</div>
		<jsp:include page="../includes/footer.jsp" />		
	</body>

	<script type="text/javascript">	
		$(document).ready(function() {
			// check name availability on focus lost
			$('#pseudo').blur(function() {
				if ($('#pseudo').val()) {	
					checkAvailability();
				}
			});
			
		});

		
		function checkAvailability() {
			$.postJSON("signup-ajax/availability", { pseudo: $('#pseudo').val() }, function(availability) {
				if (availability.available && availability.failures.pseudo == null) {
					fieldValidated("pseudo", { valid : true });
				} else if (availability.available && availability.failures.pseudo != null) {
					fieldValidated("pseudo", { valid : false, message : availability.failures.pseudo });
				} else {
					fieldValidated("pseudo", { valid : false, message : $('#pseudo').val() + " is not available, try " + availability.suggestions });
				}
			});
		}	
	</script>
	
</html>
