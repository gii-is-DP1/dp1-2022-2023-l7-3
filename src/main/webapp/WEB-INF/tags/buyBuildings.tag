<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="monopoly" tagdir="/WEB-INF/tags"%>

<spring:url value="/resources/css/game.css" var="gameCss"/>
<link href="${gameCss}" rel="stylesheet"/>

	<h1 class="propertyTxt">Do you want to buy this property for <c:out value = "${property.price}"></c:out> <img style="height: 22px" src="/resources/images/Monodolar.png"/> ?</h1>

		<img class="propertyImg" id="cardImg" alt="Card image" src="${property.badgeImage}">

	<a class="popUpButtons">
		<button type="button" class="popUpButton" onclick="setFormInput('true')">Yes</button>
		<button type="button" class="popUpButton" onclick="setFormInput('false')">No</button>
	</a>
