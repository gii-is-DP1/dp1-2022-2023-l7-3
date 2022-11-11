<spring:url value="/resources/css/game.css" var="gameCss"/>
<link href="${gameCss}" rel="stylesheet"/>
	<div class="gamePopUp" id="gamePopUp">
		<jsp:doBody/>
	</div>

<script>
	function showPopUp() {
		var overlay = document.getElementById("gamePopUp");
		overlay.style.visibility = "visible";
		return;
	}
	function closePopUp() {
		var overlay = document.getElementById("gamePopUp")
		overlay.style.visibility = "hidden";
		return;
	}
</script>