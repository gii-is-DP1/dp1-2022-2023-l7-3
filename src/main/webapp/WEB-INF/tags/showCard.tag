
<%-- <%@ attribute name="cardImg" required="true" rtexprvalue="true" description="Card image source" %> --%>

<div style="display:block; width: 100%;">
	<div style="display:flex;flex-direction: row;margin: auto;">
		<h1> This card has been drawn by '${CurrentPlayer}' ! </h1>
		<button type="button" onclick="hidePopUp('drawCard')" class="cancelButton" ><span class="glyphicon glyphicon-remove"></span></button>
	</div>
</div>

<img  alt="Card image" src="${drawCardSource}">


