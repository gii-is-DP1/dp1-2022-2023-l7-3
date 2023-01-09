<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="monopoly" tagdir="/WEB-INF/tags"%>


<div style="display:block; width: 100%;">
	<button type="button" onclick="setFormInput('true')" class="cancelButton" style="position:absolute; right: 5px;"><span class="glyphicon glyphicon-remove"></span></button>
	<div style="display:flex;flex-direction: column;margin: auto;">
		<h1 class="propertyTxt">Oh no...! It's "${property.owner.user.username}'s" property, you have to pay... </h1>
	</div>
</div>
<img class="propertyImg2" id="cardImg" alt="Card image" src="${property.badgeImage}">