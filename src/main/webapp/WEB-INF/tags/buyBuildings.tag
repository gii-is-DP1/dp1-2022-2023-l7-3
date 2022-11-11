<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="monopoly" tagdir="/WEB-INF/tags"%>

<spring:url value="/resources/css/game.css" var="gameCss"/>
<link href="${gameCss}" rel="stylesheet"/>

	<h1 class="propertyTxt">Do you want to buy this property?</h1>

	<a  class="propertyImg" href="${cardUrl}">
		<img  alt="Card image" src="/resources/images/00.png">
	</a>

	<a class="buyButtons">
		<button class="buyYesButton">Yes</button>
		<button onclick="closePopUp()" class="buyNoButton">No</button>
	</a>
