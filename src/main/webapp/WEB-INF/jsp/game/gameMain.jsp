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
					<img class="playerAvatar" src="/resources/images/Verde.png" />
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
						<img class="playerAvatar" src="/resources/images/Amarillo.png" />
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
						<img class="playerAvatar" src="/resources/images/Rosa.png" />
						<p>${Players[2].user.username}</p>
						<p>${Players[2].money}  M </p>
					</div>
				</c:if>
			</div>
		
		</div>
		
		<div class="secondColumn">
			
<!-- 			<img src="/resources/images/board.png" class="canvasBackground"></img> -->
			<canvas id="Board" width="600" height="600"></canvas>
			
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
					<img class="playerAvatar" src="/resources/images/Azul.png" />
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
						<img class="playerAvatar" src="/resources/images/Cyan.png" />
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
						<img class="playerAvatar" src="/resources/images/Naranja.png" />
						<p>${Players[3].user.username}</p>
						<p>${Players[3].money}  M </p>
					</div>
				</c:if>
			</div>
		
		</div>
		
	</div>
	
	<!-- 
	
	Aqui es donde van los layouts de los popups, con c:if para cargarlos solo cuando vayan a hacer falta
	o ponerlos todos y que salgan solo los que se usen
	
	 -->
	 
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
	
	<script defer>
		background = new Image();
		background.src = "/resources/images/board2.png";
		background.onload=start;
		
		p1Piece = new Image();
		p1Piece.src = "/resources/images/PieceMockups/VerdePiece.png";
		p1Piece.width = 30;
		p1Piece.height = 30;
		p1Piece.onload=start;
		
		var canvas = document.getElementById('Board'),
		ctx = canvas.getContext('2d');
		
		var imgCount = 2;
		function start() {
			if(--imgCount > 0) {
				return;
			}
			
			ctx.drawImage(background, 0, 0);
			ctx.drawImage(p1Piece, 550, 550);
			
			await new Promise(r => setTimeout(r, 2000));
			piece = {
				x: 550,
				y: 550,
				pos: 0,
				tiles: 60
			}
			getMovement(piece);
		}
		
		function movePiece(pieceMov) {
			ctx.clearRect(0, 0, ctx.canvas.width, ctx.canvas.height);
			
			ctx.drawImage(background, 0, 0);
			ctx.drawImage(p1Piece, pieceMov.x, pieceMov.y)
		}
	</script>
	
	<script>
		function getMovement(pieceMov) {
			while(pieceMov.tiles > 0) {
				if(pieceMov.pos < 10) {
					pieceMov.x = pieceMov.x - 50;
					ctx.drawImage(background, 0, 0);
				}
				
				pieceMov.pos = (pieceMov.pos + 1) % 40;
				pieceMov.tiles = pieceMov.tiles - 1;
			}
			
			return pieceMov.pos;
		}
	
	
		function getPosByTile(tile) {
// 			0  -> 550, 550
// 			1  -> 495, 555
// 			2  -> 445, 555
// 			3  -> 395, 555
// 			4  -> 345, 555
// 			5  -> 295, 555
// 			6  -> 255, 555
// 			7  -> 205, 555
// 			8  -> 150, 555
// 			9  -> 105, 555
// 			10 Carcel -> 50, 545
// 			10 Visitas -> 5, 545
// 			11 -> 35, 495
// 			12 -> 35, 445
// 			13 -> 35, 395
// 			14 -> 35, 345
// 			15 -> 35, 300
// 			16 -> 35, 250
// 			17 -> 35, 200
// 			18 -> 35, 150
// 			19 -> 35, 100
// 			20 -> 40, 40          Salida
// 			21 -> 105, 35
// 			22 -> 150, 35
// 			23 -> 205, 35
// 			24 -> 255, 35
// 			25 -> 295, 35
// 			26 -> 345, 35
// 			27 -> 395, 35
// 			28 -> 445, 35
// 			29 -> 495, 35
// 			30 -> 550, 35        Go to jail
// 			31 -> 550, 100
// 			32 -> 550, 150
// 			33 -> 550, 200
// 			34 -> 550, 250
// 			35 -> 550, 300
// 			36 -> 550, 345
// 			37 -> 550, 395
// 			38 -> 550, 445
// 			39 -> 550, 495
		} 
	
	</script>	

</monopoly:layout>