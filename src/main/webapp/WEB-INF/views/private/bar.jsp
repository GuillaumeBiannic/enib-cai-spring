<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
	<title><fmt:message key="home.title" /></title>
	<jsp:include page="../includes/head.jsp" />
	<script type="text/javascript">

		// Check if the beer id in the stock
		function checkin( beerId ) {
			var inputAmount = document.getElementById('amount_' + beerId).value;
			$.postJSON("bar/checkin", { id: beerId, amount: inputAmount },
				function(beerStock) {
				if( eval(beerStock.added) ) {
					updateRow(beerStock.beerId,beerStock.amount);
				} else {
					displayError();
				}
				}).error(function() { 
					displayError();
				});
		}

		// Check if the beer id in the stock
		function checkout( beerId ) {
			var inputAmount = document.getElementById('amount_' + beerId).value;
			$.postJSON("bar/checkout", { id: beerId, amount: inputAmount },
				function(beerStock) {
				if( eval(beerStock.added) ) {
					updateRow(beerStock.beerId,beerStock.amount);
				} else {
					displayError();
				}
				}).error(function() { 
					displayError();
				});
		}
		
		function displayError() {
			$("#error-message").html('<div class="alert alert-error"><button type="button" class="close" data-dismiss="alert">×</button><strong>Oh snap!</strong> Something bad has happened, please <a href="#">refresh</a> the page !</div>');
		}
		
		function updateRow( beerId, beerStock) {	
			var row = document.getElementById("beer_" + beerId);
			row.cells[4].innerHTML =  beerStock;
		}

	</script>
</head>
<body>	
	    <!-- nav bar -->
    <jsp:include page="../includes/navbar.jsp"/>

	<div class="container" data-spy="scroll" data-target="#topnavbar" data-twttr-rendered="true" style="padding-top: 40px;">
		<!--  HEADER -->
		<div class="header">
			<div class="header-left">
				<ul class="nav">
					<li class="nav"><fmt:message key="bar.title" /></li>
				</ul>
			</div>
		</div>
		<hr>
      	<h3>Message : ${message}</h3>	
		<h3>Username : ${username}</h3>	
        
		<table id="beerstable" class="table table-condensed">
              <thead>
                <tr>
                  <th>Name</th>
                  <th>Brewery</th>
                  <th>Country</th>
                  <th>Alcohol</th>
                  <th>Stock</th>      
                </tr>
              </thead>
              <tbody>
   
				<c:forEach items="${bar}" var="bar">
	                <tr id="beer_${bar.beer.id}">
	                  <td>${bar.beer.name}</td>
	                  <td>${bar.beer.brewery}</td>
	                  <td>${bar.beer.country}</td>
	                  <td>${bar.beer.alcohol}</td>
	                  <td>${bar.stock}</td>
	                  <td>
	                  <a href="javascript:checkin(${bar.beer.id})"><i class="icon-plus"></i></a>
	                  <input type="number" class="input-mini" maxlength="3" id="amount_${bar.beer.id}" value="1" />
	                  <a href="javascript:checkout(${bar.beer.id})"><i class="icon-minus"></i></a>
	                  </td>
	                </tr>
				</c:forEach>
								
              </tbody>
         </table>
         <hr>
	</div>
	
	
	<jsp:include page="../includes/footer.jsp" />
</body>
</html>