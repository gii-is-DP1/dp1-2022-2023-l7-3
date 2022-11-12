<%@ taglib prefix="monopoly" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<spring:url value="/resources/css/game.css" var="gameCss" />
<link href="${gameCss}" rel="stylesheet" />

<monopoly:layout screenTittle="Playing game">

	<div class="mainGameDiv">
		
		<div class="firstColumn">
		
<!-- 		1, 5, 3; Player 1 -->
			<div class="playerDiv" id="Player1">
				<div class="propertiesAndColors">
					<div class="properties">
						<p> Properties </p>
						<c:if test="${!Properties.get(0).isEmpty()}">
							<c:forEach items="${Properties.get(0)}" var="property">
								<span>${property.name}</span>
							</c:forEach>
						</c:if>
					</div>
					
					<p> Colors </p>
					<div class="colors">
						<c:if test="${Colors[0] != null && !Colors[0].isEmpty()}">
							<c:forEach items="${Colors[0]}" var="color">
								<div class="color${color}">   </div>
							</c:forEach>
						</c:if> 
					</div>
				</div>
				
				<div class="hasExitGate">
					<p>Exit Gate</p>
					<div class="exitGateIconDiv">
						<c:if test="${Players[0].hasExitGate}">
							<img class="exitGateIcon" src="/resources/images/exit_gate_icon.jpg"></img>
						</c:if>
					</div>
				</div>
				
				<div class="playerAndMoney">
					<img class="playerImg" src="/resources/images/Verde.png" />
					<p>${Players[0].user.username}</p>
					<p>${Players[0].money}  M </p>
				</div>
			</div>
			
			<!-- Player 5 -->
			
			<div class="playerDiv" id="Player5">
				<c:if test="${Players[4] != null}">
					<div class="propertiesAndColors">
						<div class="properties">
							<p> Properties </p>
							<c:if test="${!Properties.get(4).isEmpty()}">
								<c:forEach items="${Properties.get(4)}" var="property">
									<span>${property.name}</span>
								</c:forEach>
							</c:if>
						</div>
						
						<p> Colors </p>
						<div class="colors">
							<c:if test="${Colors[4] != null && !Colors[4].isEmpty()}">
								<c:forEach items="${Colors[4]}" var="color">
									<div class="color${color}">   </div>
								</c:forEach>
							</c:if> 
						</div>
					</div>
					
					<div class="hasExitGate">
						<p>Exit Gate</p>
						<div class="exitGateIconDiv">
							<c:if test="${Players[4].hasExitGate}">
								<img class="exitGateIcon" src="/resources/images/exit_gate_icon.jpg"></img>
							</c:if>
						</div>	
					</div>
					
					<div class="playerAndMoney">
						<img class="playerImg" src="/resources/images/Amarillo.png" />
						<p>${Players[4].user.username}</p>
						<p>${Players[4].money}  M </p>
					</div>
				</c:if>
			</div>
			
			<!-- Player 3 -->
			
			<div class="playerDiv" id="Player3">
				<c:if test="${Players[2] != null}">
					<div class="propertiesAndColors">
						<div class="properties">
							<p> Properties </p>
							<c:if test="${!Properties.get(2).isEmpty()}">
								<c:forEach items="${Properties.get(2)}" var="property">
									<span>${property.name}</span>
								</c:forEach>
							</c:if>
						</div>
						
						<p> Colors </p>
						<div class="colors">
							<c:if test="${Colors[2] != null && !Colors[2].isEmpty()}">
								<c:forEach items="${Colors[2]}" var="color">
									<div class="color${color}">   </div>
								</c:forEach>
							</c:if> 
						</div>
					</div>
					
					<div class="hasExitGate">
						<p>Exit Gate</p>
						<div class="exitGateIconDiv">
							<c:if test="${Players[2].hasExitGate}">
								<img class="exitGateIcon" src="/resources/images/exit_gate_icon.jpg"></img>
							</c:if>
						</div>
					</div>
					
					<div class="playerAndMoney">
						<img class="playerImg" src="/resources/images/Rosa.png" />
						<p>${Players[2].user.username}</p>
						<p>${Players[2].money}  M </p>
					</div>
				</c:if>
			</div>
		
		</div>
		
		<div class="secondColumn">
			
			<canvas id="Board">
			
			</canvas>
			
		</div>
		
		<div class="thirdColumn">
		
<!-- 		2, 6, 4; Player 2-->
			<div class="playerDiv" id="Player2">
				<div class="propertiesAndColors">
					<div class="properties">
						<p> Properties </p>
						<c:if test="${!Properties.get(1).isEmpty()}">
							<c:forEach items="${Properties.get(2)}" var="property">
								<span>${property.name}</span>
							</c:forEach>
						</c:if>
					</div>
					
					<p> Colors </p>
					<div class="colors">
						<c:if test="${Colors[1] != null && !Colors[1].isEmpty()}">
							<c:forEach items="${Colors[1]}" var="color">
								<div class="color${color}">   </div>
							</c:forEach>
						</c:if> 
					</div>
				</div>
				
				<div class="hasExitGate">
					<p>Exit Gate</p>
					<div class="exitGateIconDiv">
						<c:if test="${Players[1].hasExitGate}">
							<img class="exitGateIcon" src="/resources/images/exit_gate_icon.jpg"></img>
						</c:if>
					</div>
				</div>
				
				<div class="playerAndMoney">
					<img class="playerImg" src="/resources/images/Azul.png" />
					<p>${Players[1].user.username}</p>
					<p>${Players[1].money}  M </p>
				</div>
			</div>
			
			<!-- Player 6 -->
			
			<div class="playerDiv" id="Player6">
				<c:if test="${Players[5] != null}">
					<div class="propertiesAndColors">
						<div class="properties">
							<p> Properties </p>
							<c:if test="${!Properties.get(5).isEmpty()}">
								<c:forEach items="${Properties.get(5)}" var="property">
									<span>${property.name}</span>
								</c:forEach>
							</c:if>
						</div>
						
						<p> Colors </p>
						<div class="colors">
							<c:if test="${Colors[5] != null && !Colors[5].isEmpty()}">
								<c:forEach items="${Colors[5]}" var="color">
									<div class="color${color}">   </div>
								</c:forEach>
							</c:if> 
						</div>
					</div>
					
					<div class="hasExitGate">
						<p>Exit Gate</p>
						<div class="exitGateIconDiv">
							<c:if test="${Players[5].hasExitGate}">
								<img class="exitGateIcon" src="/resources/images/exit_gate_icon.jpg"></img>
							</c:if>
						</div>
					</div>
					
					<div class="playerAndMoney">
						<img class="playerImg" src="/resources/images/Cyan.png" />
						<p>${Players[5].user.username}</p>
						<p>${Players[5].money}  M </p>
					</div>
				</c:if>
			</div>
			
			<div class="playerDiv" id="Player4">
				<c:if test="${Players[3] != null}">
					<div class="propertiesAndColors">
						<div class="properties">
							<p> Properties </p>
							<c:if test="${!Properties.get(3).isEmpty()}">
								<c:forEach items="${Properties.get(3)}" var="property">
									<span>${property.name}</span>
								</c:forEach>
							</c:if>
						</div>
						
						<p> Colors </p>
						<div class="colors">
							<c:if test="${Colors[3] != null && !Colors[3].isEmpty()}">
								<c:forEach items="${Colors[3]}" var="color">
									<div class="color${color}">   </div>
								</c:forEach>
							</c:if> 
						</div>
					</div>
					
					<div class="hasExitGate">
						<p>Exit Gate</p>
						<div class="exitGateIconDiv">
							<c:if test="${Players[3].hasExitGate}">
								<img class="exitGateIcon" src="/resources/images/exit_gate_icon.jpg"></img>
							</c:if>
						</div>
					</div>
					
					<div class="playerAndMoney">
						<img class="playerImg" src="/resources/images/Naranja.png" />
						<p>${Players[3].user.username}</p>
						<p>${Players[3].money}  M </p>
					</div>
				</c:if>
			</div>
		
		</div>
		
	</div>

</monopoly:layout>