<%@ attribute name="popUpId" required="true" rtexprvalue="true" description="Name of the active screen"%>

<div class="popUpOverlay1" id="popUpOverlay${popUpId}">
	<div class="gamePopUp1">
		<jsp:doBody/>
	</div>
</div>

<script>
function showPopUp(id) {
	var overlay = document.getElementById("popUpOverlay" + id);
	overlay.style.visibility = "visible";
	overlay.style.opacity = 1;
	
	return false;
}

function hidePopUp(id) {
	var overlay = document.getElementById("popUpOverlay" + id);
	overlay.style.visibility = "hidden";
	overlay.style.opacity = 0;
	
	return false;
}
</script>