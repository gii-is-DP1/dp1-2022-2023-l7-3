<%@ taglib prefix="monopoly" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<spring:url value="/resources/css/game.css" var="gameCss" />
<link href="${gameCss}" rel="stylesheet" />

<monopoly:layout screenTittle="Playing game">

	<div class="mainGameDiv">
		
		<div class="firstColumn">
		
<!-- 		1, 5, 3 -->
			<div class="playerDiv" id="Player1">
				<div class="propertiesAndColors">
					
				</div>
				
				<div class="hasExitGate">
				
				</div>
				
				<div class="playerAndMoney">
				
				</div>
			</div>
			
			<div class="playerDiv" id="Player5">
			
			</div>
			
			<div class="playerDiv" id="Player3">
			
			</div>
		
		</div>
		
		<div class="secondColumn">
			
			<canvas id="Board">
			
			</canvas>
			
		</div>
		
		<div class="thirdColumn">
		
<!-- 		2, 6, 4 -->
			<div class="playerDiv" id="Player2">
			
			</div>
			
			<div class="playerDiv" id="Player6">
			
			</div>
			
			<div class="playerDiv" id="Player4">
			
			</div>
		
		</div>
		
	</div>

</monopoly:layout>