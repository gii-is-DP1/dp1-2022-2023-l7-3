<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="monopoly" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<spring:url value="/resources/css/mortgage.css" var="mortgageCss"/>
<link href="${mortgageCss}" rel="stylesheet"/>

<form:form modelAttribute="MortgagePropertyForm" id="mortgageForm" action="/game/${Game.id}/mortgage">
	<input id="formInput" type="hidden" name="propertyId" value="-1">
	
	<div>
		<form:errors path="propertyId"/>
	</div>
</form:form>

<div style="display:block; width: 100%;">
	<button type="button" onclick="hidePopUp('mortgage')" class="cancelButton" style="position:absolute; right: 5px;"><span class="glyphicon glyphicon-remove"></span></button>
	<div style="display:flex;flex-direction: column;margin: auto;">
		<h1 class="propertyTxt">Select something to mortgage it, it's buildings, or to cancel it's existing mortgage</h1>
	</div>
</div>

<table>
	<thead>
		<tr>
			<th class="mgTableCell">Property</th>
			<th class="mgTableCell">Type</th>
			<th class="mgTableCell">Color</th>
			<th class="mgTableCell">Mortgage price</th>
			<th class="mgTableCell">Mortgage</th>
			<th class="mgTableCell">Cancel mortgage</th>
		</tr>
	</thead>
	<tbody class="mgTableBody">
		<c:forEach items="${playerStreets}" var="street">
		<fmt:parseNumber var="streetValue" integerOnly="true" type="number" value="${street.mortagePrice * 1.1}"/>
		<tr>
			<th> <c:out value="${street.name}"/> </th>
			<th class="mgTableCell"> Street </th>
			<th class="mgTableCell"> <div class="color<c:out value = "${street.color}" />"> </div> </th>
			<c:choose>
				<c:when test="${street.isMortage}">
					<td class="mgTableCell"> <c:out value="${streetValue}"/> <img style='height: 24px' src='/resources/images/Monodolar.png'/></td>	
				</c:when>
				<c:otherwise>
					<td class="mgTableCell"> <c:out value="${street.mortagePrice}"/> <img style='height: 24px' src='/resources/images/Monodolar.png'/></td>	
				</c:otherwise>
			</c:choose>
			<td class="mgTableCell"> <c:if test="${!street.isMortage}"><input name="selectProperty" type="checkbox" onclick="selectPropertyWithId(${street.id}, this)"></c:if> </td>
			<td class="mgTableCell"> <c:if test="${street.isMortage}"><input name="selectCancelProperty" type="checkbox" onclick="selectCancelPropertyWithId(${street.id}, this)"></c:if> </td>			
		</tr>
		</c:forEach>
		
		<c:forEach items="${playerStations}" var="station">
		<fmt:parseNumber var="stationValue" integerOnly="true" type="number" value="${station.mortagePrice * 1.1}"/>
		<tr>
			<th> <c:out value="${station.name}"/> </th>
			<th class="mgTableCell"> Station </th>
			<th class="mgTableCell"> </th>
			<c:choose>
				<c:when test="${station.isMortage}">
					<td class="mgTableCell"> <c:out value="stationValue"/> <img style='height: 24px' src='/resources/images/Monodolar.png'/></td>	
				</c:when>
				<c:otherwise>
					<td class="mgTableCell"> <c:out value="${station.mortagePrice}"/> <img style='height: 24px' src='/resources/images/Monodolar.png'/></td>	
				</c:otherwise>
			</c:choose>	
			<td class="mgTableCell"> <c:if test="${!station.isMortage}"><input name="selectProperty" type="checkbox" onclick="selectPropertyWithId(${station.id}, this)"></c:if> </td>
			<td class="mgTableCell"> <c:if test="${station.isMortage}"><input name="selectCancelProperty" type="checkbox" onclick="selectCancelPropertyWithId(${station.id}, this)"></c:if> </td>				
		</tr>
		</c:forEach>
		
		<c:forEach items="${playerCompanies}" var="company">
		<fmt:parseNumber var="companyValue" integerOnly="true" type="number" value="${company.mortagePrice * 1.1}"/>
		<tr>
			<th> <c:out value="${company.name}"/> </th>
			<th class="mgTableCell"> Company </th>
			<th class="mgTableCell"> </th>
			<c:choose>
				<c:when test="${company.isMortage}">
					<td class="mgTableCell"> <c:out value="companyValue"/> <img style='height: 24px' src='/resources/images/Monodolar.png'/></td>	
				</c:when>
				<c:otherwise>
					<td class="mgTableCell"> <c:out value="${company.mortagePrice}"/> <img style='height: 24px' src='/resources/images/Monodolar.png'/></td>	
				</c:otherwise>
			</c:choose>	
			<td class="mgTableCell"> <c:if test="${!company.isMortage}"><input name="selectProperty" type="checkbox" onclick="selectPropertyWithId(${company.id}, this)"></c:if> </td>
			<td class="mgTableCell"> <c:if test="${company.isMortage}"><input name="selectCancelProperty" type="checkbox" onclick="selectCancelPropertyWithId(${company.id}, this)"></c:if> </td>				
		</tr>
		</c:forEach>
	</tbody>
</table>
			
<button class="mainButtonStyle" type="button" onclick="document.getElementById('mortgageForm').submit()"> Mortgage or cancel mortgage on last selected property </button>

<script>
	function selectPropertyWithId(propertyId, checkbox) {
		let form = document.getElementById("mortgageForm");
		form.action = "/game/${Game.id}/mortgage";
		
		unsetCheckboxes(checkbox);
		
		let input = document.getElementById('formInput');
		input.value = propertyId;
	}
	
	function selectCancelPropertyWithId(propertyId, checkbox) {
		let form = document.getElementById("mortgageForm");
		form.action = "/game/${Game.id}/cancelMortgage";
		
		unsetCheckboxes(checkbox);
		
		let input = document.getElementById('formInput');
		input.value = propertyId;
	}
	
	function unsetCheckboxes(checkbox) {
		let checkboxes = document.getElementsByName('selectProperty');
		checkboxes.forEach((item) => {
			if(item !== checkbox) item.checked = false;
		});
		
		let cancelCheckboxes = document.getElementsByName('selectCancelProperty');
		cancelCheckboxes.forEach((item) => {
			if(item !== checkbox) item.checked = false;
		});
	}
</script>
