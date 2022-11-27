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
								<span>${property}</span>
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
									<span>${property}</span>
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
									<span>${property}</span>
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
<!-- 		Delete this on completion -->
			<button onclick="setTimeout(function() {window.requestAnimationFrame(movePiece);}, velocity)">Next frame</button>
			<button onclick="if(!play){setTimeout(function() {play = true; window.requestAnimationFrame(movePiece);}, velocity)}else{play = false}">Play/Stop</button>
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
								<span>${property}</span>
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
									<span>${property}</span>
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
			
			<!-- Player 4 -->
			
			<div class="playerDiv" id="Player4">
				<c:if test="${Players[3] != null}">
					<div class="propertiesAndColors">
						<div class="properties">
							<p> Properties </p>
							<c:if test="${!Properties.get(3).isEmpty()}">
								<c:forEach items="${Properties.get(3)}" var="property">
									<span>${property}</span>
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
	
	Insert pop up layouts in this space here
	
	 -->
	 
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
	
	<script>
		function start() {
			if(--imgCount > 0) {
				return;
			}
			
			ctx.drawImage(background, 0, 0);
			for(let i = 0; i < pieces.length; i++) {
				let piece = pieces[i];
				ctx.drawImage(piece.img, piece.x - piece.offsetX, piece.y - piece.offsetY);
			}
			
			const animationDelay = setTimeout(function() {
				window.requestAnimationFrame(movePiece);
			}, 1500);
			
		}
		
		function movePiece() {
			if(moves > 0) {
				ctx.clearRect(0, 0, ctx.canvas.width, ctx.canvas.height);
				
				ctx.drawImage(background, 0, 0);
				
				for(let i = 0; i < pieces.length; i++) {
					let piece = pieces[i];
					ctx.drawImage(piece.img, piece.x - piece.offsetX, piece.y - piece.offsetY);
					
					if(i == movingPiece) {
						moves--;
						setMovement(piece);
					}
				}
				
				if(play) {
					setTimeout(function() {
						window.requestAnimationFrame(movePiece);
					}, velocity);
				}
			}
			
		}
		
		function setMovement(piece) {
			if(piece.y == 555) {
				piece.x -= 50;
				
				if(piece.x < 100) {
					piece.x = 35;
					piece.y = 550;
				}
					
			} else if(piece.x == 35) {
				piece.y -= 50;
					
				if(piece.y < 100) {
					piece.y = 35;
					piece.x = 50;
				}
				
			} else if(piece.y == 35) {
				piece.x += 50;
				
				if(piece.x >= 550) {
					piece.y = 50;
					piece.x = 550;
				}
				
			} else if(piece.x == 550) {
				piece.y += 50;
				
				if(piece.y >= 550) {
					piece.y = 555;
					piece.x = 550;
				}
				
			}
		}

	</script>
	
	<script defer>
		let play = false;
	
		let background = new Image();
		background.src = "/resources/images/board2.png";
		background.width = 600;
		background.height = 600;
		background.onload=start;
		
		let imgCount = 1 + parseInt("${Players.size()}");
		let pieces = [];
		const movingPiece = parseInt("${Turn.player.turnOrder}"); // Player of the turn that is being played
		const velocity = 300; // miliseconds between jumps
		let moves = parseInt("${Turn.roll}");
		
		let piece = new Image();
		piece.src = "/resources/images/PieceMockups/VerdePiece.png";
		piece.width = 30;
		piece.height = 30;
		piece.onload = start;
		
		let pieceProp = {
			img: piece,
			x: 550,
			offsetX: piece.width/2,
			y: 555,
			offsetY: piece.height/2
		}
		
		pieces[0] = pieceProp;
		
		for(let i = 1; i < parseInt("${Players.size()}"); i++) {
			let pieceI = new Image();
			let imgSrc = "";
			switch (i) {
			  case 1:
				  imgSrc = "/resources/images/PieceMockups/AzulPiece.png";
			  case 2:
				  imgSrc = "/resources/images/PieceMockups/RosaPiece.png";
			  case 3:
				  imgSrc = "/resources/images/PieceMockups/NaranjaPiece.png";
			  case 4:
				  imgSrc = "/resources/images/PieceMockups/AmarilloPiece.png";
			  case 5:
				  imgSrc = "/resources/images/PieceMockups/CyanPiece.png";
			  default:
				  imgSrc = "/resources/images/PieceMockups/VerdePiece.png";
			}
			
			pieceI.src = imgSrc;
			pieceI.width = 30;
			pieceI.height = 30;
			pieceI.onload = start;
			
			let piecePropI = {
				img: pieceI,
				x: 550,
				offsetX: pieceI.width/2,
				y: 555,
				offsetY: pieceI.height/2
			}
			
			pieces.push(piecePropI);
		}
	
		let canvas = document.getElementById('Board');
		let ctx = canvas.getContext('2d');
	</script>
	
	<script>
		function positionToCoords(pos) {
			coords = {
				x: 0,
				y: 0
			}
			
			if(pos < 10) {
				coords.x = 550 - (pos*50)
				coords.y = 555
			} else if(pos == 10) {
				coords.x = 50
				coords.y = 550
			} else if(pos < 20) {
				coords.x = 35
				coords.y = 550 - (pos*50)
			} else if(pos == 20) {
				coords.x = 50
				coords.y = 35
			} else if(pos < 30) {
				coords.x = 50 + (pos*50)
				coords.y = 35
			} else if(pos == 30) {
				coords.x = 550
				coords.y = 35
			} else if(pos < 40) {
				coords.x = 550
				coords.y = 50 + (pos*50)
			}
			
			return coords;
		}
		
// 			0  -> 550, 555			Salida
// 			1  -> 500, 555
// 			2  -> 450, 555
// 			3  -> 400, 555
// 			4  -> 350, 555
// 			5  -> 300, 555
// 			6  -> 250, 555
// 			7  -> 200, 555
// 			8  -> 150, 555
// 			9  -> 100, 555
// 			10 Carcel -> 50, 545
// 			10 Visitas -> 5, 545
//				35, 550
// 			11 -> 35, 500
// 			12 -> 35, 450
// 			13 -> 35, 400
// 			14 -> 35, 350
// 			15 -> 35, 300
// 			16 -> 35, 250
// 			17 -> 35, 200
// 			18 -> 35, 150
// 			19 -> 35, 100
// 			20 -> 35, 35          Parking
//				50, 35
// 			21 -> 100, 35
// 			22 -> 150, 35
// 			23 -> 200, 35
// 			24 -> 250, 35
// 			25 -> 300, 35
// 			26 -> 350, 35
// 			27 -> 400, 35
// 			28 -> 450, 35
// 			29 -> 500, 35
// 			30 -> 550, 35        Go to jail
// 			31 -> 550, 100
// 			32 -> 550, 150
// 			33 -> 550, 200
// 			34 -> 550, 250
// 			35 -> 550, 300
// 			36 -> 550, 350
// 			37 -> 550, 400
// 			38 -> 550, 450
// 			39 -> 550, 500
	</script>	

</monopoly:layout>