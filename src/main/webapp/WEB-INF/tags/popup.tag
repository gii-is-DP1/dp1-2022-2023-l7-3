
<div class="popUpOverlay" id="popUpOverlayId">
	<div class="popUp" id="popUpId">
		<jsp:doBody/>
	</div>
</div>

<script>
function showPopUp() {
	var overlay = document.getElementById("popUpOverlayId");
	overlay.style.visibility = "visible";
	overlay.style.opacity = 1;
	
	return false;
}
</script>