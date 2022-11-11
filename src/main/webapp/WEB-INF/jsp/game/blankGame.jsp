<%@ taglib prefix="monopoly" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<link href="${gameCss}" rel="stylesheet"/>

<monopoly:layout screenTittle="Blank Game">
	
	<monopoly:popup>
		<monopoly:buyBuildings/>
	</monopoly:popup>
	<button onclick="showPopUp()">Sample comprar Propiedad</button>

</monopoly:layout>


