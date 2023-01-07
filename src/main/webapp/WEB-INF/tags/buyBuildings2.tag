<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="monopoly" tagdir="/WEB-INF/tags"%>

<spring:url value="/resources/css/game.css" var="gameCss"/>
<link href="${gameCss}" rel="stylesheet"/>

	<div style="display:block; width: 100%;">
		<div style="display:flex;flex-direction: row;margin: auto;">
			<h1 class="propertyTxt">Do you want to buy this property for <c:out value = "${property.price}"></c:out> <img style="height: 22px" src="/resources/images/Monodolar.png"/> ?</h1>
			<button type="button" onclick="hidePopUp('buyPopUp')" class="cancelButton" ><span class="glyphicon glyphicon-remove"></span></button>
		</div>
	</div>
	
	<img class="propertyImg" id="cardImg" alt="Card image" src="${property.badgeImage}">

	<div class="popUpButtons">
		<button type="button" class="popUpButton" onclick="setFormInput('true')">Yes</button>
		<button type="button" class="popUpButton" onclick="setFormInput('false')">No</button>
	</div>
