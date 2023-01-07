<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="monopoly" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<h1 class="propertyTxt">Choose how to get out of jail</h1>

<img class="propertyImg" id="JailImg" alt="Jail image" src="/resources/images/Jail.png">

<form:form modelAttribute="exitGate" id="ExitGateForm" action="/game/${Game.id}/exitGate">
	<input id="option" type="hidden" name="option" value="${0}"/>

	<div class="popUpButtons">
		<button class="popUpButton" type = "button" onclick="exitJailOption(1)">Pay 50 <img style='height: 24px' src='/resources/images/Monodolar.png'/> </button>
		<c:if test="${hasExitGate}">
			<button class="popUpButton" type = "button" onclick="exitJailOption(2)">Use Exit Gate Card</button>
		</c:if>
		<button class="popUpButton" type = "button" onclick="exitJailOption(3)">Roll dice</button>
	</div>
</form:form>
	
<script>
function exitJailOption(quantity){
	 let option = document.getElementById("option");
	 option.value = quantity;
	 document.getElementById("ExitGateForm").submit();
}
</script>


