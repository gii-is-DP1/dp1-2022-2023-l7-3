<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="monopoly" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<spring:url value="/resources/css/game.css" var="gameCss"/>
<link href="${gameCss}" rel="stylesheet"/>

	<h1 class="propertyTxt">Choose how to get out of jail</h1>
	
	<a  class="JailImg" href="${cardUrl}">
		<img  alt="Jail image" src="/resources/images/Jail.png">	
	</a>
	
	<form:form modelAttribute="exitGate" id = "ExitGateForm" action = "/exitGate">
		<input type="hidden" name="option" value="${0}"/>
	
		<a class="popUpButtons">
			<button class="popUpButton" type = "button" onclick="exitJailOption(1)">Pay 50M</button>
			<c:if test="${player.hasExitGate}">
				<button class="popUpButton" type = "button" onclick="exitJailOption(2)">Use Exit Gate Card</button>
			</c:if>
			<button class="popUpButton" type = "button" onclick="exitJailOption(3)">Roll dice</button>
		</a>
	</form:form>
	
<script>
function exitJailOption(quantity){
	 let option = document.getElementById("option");
	 document.getElementById("ExitGateForm").submit();
}
</script>


