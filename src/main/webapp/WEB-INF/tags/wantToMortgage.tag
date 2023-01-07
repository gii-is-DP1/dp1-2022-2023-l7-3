<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="monopoly" tagdir="/WEB-INF/tags"%>

<spring:url value="/resources/css/game.css" var="gameCss"/>
<link href="${gameCss}" rel="stylesheet"/>

	<h1 class="propertyTxt">Do you want to mortgage a property? </h1>
	
	<img  class="propertyImg" alt="Card image" src="/resources/images/monopolyMortgage.png">

	<div class="popUpButtons">
		<button type="button" onclick="closeOpenPopUp(&quot;wantToMortgagePopUp&quot, &quot;mortgagePopUp&quot)" class="popUpButton">Yes</button>
		<button onclick="hidePopUp(&quot;wantToMortgagePopUp&quot)" class="popUpButton">No</button>
	</div>