<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="monopoly" tagdir="/WEB-INF/tags"%>

<spring:url value="/resources/css/game.css" var="gameCss"/>
<link href="${gameCss}" rel="stylesheet"/>

	<h1 class="propertyTxt">Do you want to buy this property for <c:out value = "${property.price}"></c:out> <img style="height: 24px" src="/resources/images/Monodolar.png"/> ?</h1>

	
	<a  class="propertyImg">
		<img id="cardImg" alt="Card image" src="${property.badgeImage}">
	</a>

	<a class="popUpButtons">
		<button class="popUpButton">Yes</button>
		<button onclick="closePopUp(&quot;buyPopUp&quot)" class="popUpButton">No</button>
	</a>
