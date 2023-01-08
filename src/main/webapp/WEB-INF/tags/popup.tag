<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@ attribute name="popUpId" required="true" rtexprvalue="true" description="Id of the popup"%>
<%@ attribute name="popUpPostFormAction" rtexprvalue="true" description="Action of the form sent by the popup"%>
<%@ attribute name="gameId" rtexprvalue="true" description="Current game id"%>
<%-- <%@ attribute name="formModelAttribute" rtexprvalue="true" description="Model attribute of the form sent"%> --%>

<div class="popUpOverlay1" id="${popUpId}">
	<div class="gamePopUp1" id="gamePopUp1">
	
		<c:choose>
			<c:when test="${popUpId != 'free' && popUpId != 'mortgage' && popUpId != 'wantToBuild' && 
							popUpId != 'newGame' && popUpId != 'buildBuildings' && popUpId != 'auctionBuilding'}">
				<form:form id="popUpForm" modelAttribute="Boolean" action="/game/${gameId}/${popUpPostFormAction}">
					<div class="popUpFormDiv">
						<input id="popUpFormInput" hidden="true" name="decisionResult"></input>
						<jsp:doBody/>
					</div>
					
					<script>
						function setFormInput(setValue) {
							let input = document.getElementById("popUpFormInput");
							input.value = setValue;
							
							let form = document.getElementById("popUpForm");
							form.submit();
						}					
					</script>
				</form:form>
			</c:when>
			
			<c:otherwise>
				<jsp:doBody/>
			</c:otherwise>
		</c:choose>
		
	</div>
</div>
