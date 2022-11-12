<%@ taglib prefix="monopoly" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<link href="${gameCss}" rel="stylesheet"/>

<monopoly:layout screenTittle="Blank Game">
	
	<div class="gamePopUp" id="buyPopUp">
		<monopoly:buyBuildings/>
	</div>
	<button onclick="showPopUp(&quot;buyPopUp&quot)">Sample comprar Propiedad</button>

	<div class="gamePopUp" id="wantToBuildPopUp">
		<monopoly:wantToBuild/>
	</div>
	<button onclick="showPopUp(&quot;wantToBuildPopUp&quot)">Sample construir</button>

	<div class="gamePopUp" id="buildBuildingsPopUp">
		<monopoly:buildBuildings/>
	</div>

	<div class="gamePopUp" id="haveToPay">
		<monopoly:haveToPay/>
	</div>
	<button onclick="showPopUp(&quot;haveToPay&quot)">Sample tener que pagar</button>

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


