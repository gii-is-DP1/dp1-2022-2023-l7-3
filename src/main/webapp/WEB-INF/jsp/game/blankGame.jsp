<%@ taglib prefix="monopoly" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<link href="${gameCss}" rel="stylesheet"/>

<monopoly:layout screenTittle="Blank Game">
	
	<div class="gamePopUp" id="buyPopUp">
		<monopoly:buyBuildings/>
	</div>

	<div class="gamePopUp" id="wantToBuildPopUp">
		<monopoly:wantToBuild/>
	</div>

	<div class="gamePopUp" id="buildBuildingsPopUp">
		<monopoly:buildBuildings/>
	</div>

	<div class="gamePopUp" id="haveToPay">
		<monopoly:haveToPay/>
	</div>

	<div class="gamePopUp" id="auctionBuilding">
		<monopoly:auctionBuilding/>
	</div>

	<div class="gamePopUp" id="mergeBuilding">
		<monopoly:mergeBuilding/>
	</div>
	<a class="popUpButtons">
		<button class="popUpSampleButton" onclick="showPopUp(&quot;buyPopUp&quot)">Sample comprar Propiedad</button>
		<button class="popUpSampleButton" onclick="showPopUp(&quot;wantToBuildPopUp&quot)">Sample construir</button>
		<button class="popUpSampleButton" onclick="showPopUp(&quot;haveToPay&quot)">Sample tener que pagar</button>
		<button class="popUpSampleButton" onclick="showPopUp(&quot;auctionBuilding&quot)">Sample subasta</button>
		<button class="popUpSampleButton" onclick="showPopUp(&quot;mergeBuilding&quot)">Sample hipoteca</button>
	</a>
</monopoly:layout>

<script>
	function showPopUp(id) {
		var overlay = document.getElementById(id);
		overlay.style.visibility = "visible";
		return;
	}
	function closePopUp(id) {
		var overlay = document.getElementById(id)
		overlay.style.visibility = "hidden";
		return;
	}

	function buildBuildingsPopUp() {
		closePopUp("wantToBuildPopUp")
		showPopUp("buildBuildingsPopUp");
		return;
	}
	</script>


