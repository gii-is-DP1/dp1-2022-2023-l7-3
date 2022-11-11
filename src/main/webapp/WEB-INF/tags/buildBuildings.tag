<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="monopoly" tagdir="/WEB-INF/tags"%>

<spring:url value="/resources/css/game.css" var="gameCss"/>
<link href="${gameCss}" rel="stylesheet"/>
<div class= "parent">
	<div class="gamePopUp">
		<h1>Do you want to buy this property?</h1>
		
		<a href="${cardUrl}">
					<img class="propertyImg" alt="Card image" src="/resources/images/00.png">
		</a>
		
		<a href="/login">
         <button class="buildYesButton">Yes</button>
         <button class="buildNoButton">No</button>
        
        </a>
		
	</div>
	
</div>

