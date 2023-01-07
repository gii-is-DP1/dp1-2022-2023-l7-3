<%@ taglib prefix="monopoly" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<monopoly:layout screenTittle="Playing on game ${Game.id}">

	<div class="mainGameDiv">
		 
		<div class="firstColumn">
		
<!-- 		1, 5, 3; Player 1 -->
			<div class="playerDiv" id="Player1">
				<div class="propertiesAndColors">
					<div class="properties">
						<p> Properties </p>
						<c:if test="${!Properties.get(0).isEmpty()}">
							<c:forEach items="${Properties.get(0)}" var="property">
								<span><c:out value = "${property}" /></span>
							</c:forEach>
						</c:if>
					</div>
					
					<p> Colors </p>
					<div class="colors">
						<c:if test="${Colors[0] != null && !Colors[0].isEmpty()}">
							<c:forEach items="${Colors[0]}" var="color">
								<div class="color<c:out value = "${color}" />">   </div>
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
					<p><c:out value = "${Players[0].user.username}" /></p>
					<p><c:out value = "${Players[0].money}" /> <img class="monodolarEmote" src="/resources/images/Monodolar.png"/> </p>
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
									<span><c:out value = "${property}" /></span>
								</c:forEach>
							</c:if>
						</div>
						
						<p> Colors </p>
						<div class="colors">
							<c:if test="${Colors[4] != null && !Colors[4].isEmpty()}">
								<c:forEach items="${Colors[4]}" var="color">
									<div class="color<c:out value = "${color}" />">   </div>
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
						<p><c:out value = "${Players[4].user.username}" /></p>
						<p><c:out value = "${Players[4].money}" /> <img class="monodolarEmote" src="/resources/images/Monodolar.png"/> </p>
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
									<span><c:out value = "${property}" /></span>
								</c:forEach>
							</c:if>
						</div>
						
						<p> Colors </p>
						<div class="colors">
							<c:if test="${Colors[2] != null && !Colors[2].isEmpty()}">
								<c:forEach items="${Colors[2]}" var="color">
									<div class="color<c:out value = "${color}" />">   </div>
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
						<p><c:out value = "${Players[2].user.username}" /></p>
						<p><c:out value = "${Players[2].money}" /> <img class="monodolarEmote" src="/resources/images/Monodolar.png"/> </p>
					</div>
				</c:if>
			</div>
		
		</div>
		
		<div class="secondColumn">
			<div class="boardTextDiv">
				<p class="normalText"> It's <c:out value = "${CurrentPlayer}" />'s turn. <c:if test="${Turn.isDoubles}">It was doubles!</c:if></p>
				<p class="normalText" id="boardText"></p>
			</div>
			<canvas id="Board" width="600" height="600"></canvas>
			<div class="boardButtons">
				<c:if test="${isPlaying}">
					<a href="/game/${Game.id}/endTurn" class="endTurnContainer">
						<button id="endTurnButton" class="boardActionButtons" type="button" onclick="JavaScript:void(0)" disabled="disabled">End turn</button>
					</a>
				</c:if>
				<c:if test="${isPlaying}">
					<button id="showMortgageButton" class="boardActionButtons" type="button" onclick="showPopUp('mortgage')" disabled="disabled">Show mortgage menu</button>
				</c:if>
				<button id="showActionButton" class="boardActionButtons" type="button" onclick="parsePopUp(true, '<c:out value = "${Turn.action}" />')" disabled="disabled"> Show turn action<br/>Show building menu </button>
			</div>
			
		</div>
		
		<div class="thirdColumn">
		
<!-- 		2, 6, 4; Player 2-->
			<div class="playerDiv" id="Player2">
				<div class="propertiesAndColors">
					<div class="properties">
						<p> Properties </p>
						<c:if test="${!Properties.get(1).isEmpty()}">
							<c:forEach items="${Properties.get(1)}" var="property">
								<span><c:out value = "${property}" /></span>
							</c:forEach>
						</c:if>
					</div>
					
					<p> Colors </p>
					<div class="colors">
						<c:if test="${Colors[1] != null && !Colors[1].isEmpty()}">
							<c:forEach items="${Colors[1]}" var="color">
								<div class="color<c:out value = "${color}" />">   </div>
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
					<p><c:out value = "${Players[1].user.username}" /></p>
					<p><c:out value = "${Players[1].money}" /> <img class="monodolarEmote" src="/resources/images/Monodolar.png"/> </p>
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
									<span><c:out value = "${property}" /></span>
								</c:forEach>
							</c:if>
						</div>
						
						<p> Colors </p>
						<div class="colors">
							<c:if test="${Colors[5] != null && !Colors[5].isEmpty()}">
								<c:forEach items="${Colors[5]}" var="color">
									<div class="color<c:out value = "${color}" />">   </div>
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
						<p><c:out value = "${Players[5].user.username}" /></p>
						<p><c:out value = "${Players[5].money}" /> <img class="monodolarEmote" src="/resources/images/Monodolar.png"/> </p>
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
									<span><c:out value = "${property}" /></span>
								</c:forEach>
							</c:if>
						</div>
						
						<p> Colors </p>
						<div class="colors">
							<c:if test="${Colors[3] != null && !Colors[3].isEmpty()}">
								<c:forEach items="${Colors[3]}" var="color">
									<div class="color<c:out value = "${color}" />">   </div>
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
						<p><c:out value = "${Players[3].user.username}" /></p>
						<p><c:out value = "${Players[3].money}" /> <img class="monodolarEmote" src="/resources/images/Monodolar.png"/> </p> 
					</div>
				</c:if>
			</div>
		
		</div>
		 
	</div>
	
	
	 <c:if test="${Turn.action == 'PAY'}">
		 <monopoly:popup popUpId="haveToPay" gameId="${Game.id}" popUpPostFormAction="tileAction">
		 	<monopoly:haveToPay/>
		 </monopoly:popup>
	 </c:if>

	 <c:if test="${Turn.action == 'BUY'}">
		 <monopoly:popup popUpId="buyPopUp" gameId="${Game.id}" popUpPostFormAction="tileAction">
		 	<monopoly:buyBuildings/>
		 </monopoly:popup>
	 </c:if>
	 
	 <c:if test="${isPlaying}">
		 <monopoly:popup popUpId="mortgage">
		 	<monopoly:mortgage/>
		 </monopoly:popup>
	 </c:if>
	 
	 <c:if test="${isPlaying && Turn.isActionEvaluated}">
		 <monopoly:popup popUpId="wantToBuild">
		 	<monopoly:wantToBuild2/>
		 </monopoly:popup>
	 </c:if>
	 
	 <c:if test="${isPlaying && Turn.isActionEvaluated}">
		 <monopoly:popup popUpId="buildBuildings" gameId="${Game.id}" popUpPostFormAction="build">
		 	<monopoly:buildBuildings2/>
		 </monopoly:popup>
	 </c:if>
	 
	 <c:if test="${Turn.action == 'AUCTION'}">
		 <monopoly:popup popUpId="auctionBuilding" gameId="${Game.id}" popUpPostFormAction="auction">
		 	<monopoly:auctionBuilding2/>
		 </monopoly:popup>
	 </c:if>
	 
	 <c:if test="${Turn.action == 'DRAW_CARD'}">
		 <monopoly:popup popUpId="drawCard" gameId="${Game.id}" popUpPostFormAction="tileAction">
		 	<monopoly:showCard/>
		 </monopoly:popup>
	 </c:if>
	 
	 <c:if test="${Turn.action == 'FREE'}">
		 <monopoly:popup popUpId="free">
		 	<monopoly:exitJail2/>
		 </monopoly:popup>
	 </c:if>
	 
	 <!-- Popup showing related scripts -->
	 <script>
	 	function parsePopUp(showOrHide = true, inputAction) {
	 		let monodolarEmote = "<img style='height: 24px' src='/resources/images/Monodolar.png'/>";
	 		
	 		let action = null;
	 		if(inputAction == null || inputAction == undefined) {
	 			action = "${Turn.action}";
	 		} else {
	 			action = inputAction;
	 		}
	 		
	 		const result = showOrHide ? showPopUp : hidePopUp; 
	 		
	 		switch(action){
	 		// Property actions
	 		case "PAY":
	 			setCardZoomListener();
	 			result("haveToPay");
	 			break;
	 			
	 		case "BUY":
	 			setCardZoomListener();
	 			result("buyPopUp");
	 			break;
	 			
	 		case "BUILD":
	 			result("wantToBuild");
	 			break;
	 			
	 		case "AUCTION":
	 			result("auctionBuilding");
	 			break;
	 			
	 		// Tile actions
	 		case "PAY_TAX":
	 			setBoardText("You landed on a taxes tile so you pay ${taxes.price} " + monodolarEmote + " .");
	 			break;
	 			
	 		case "DRAW_CARD":
	 			setCardZoomListener();
	 			result("drawCard");
	 			break;
	 			
	 		case "FREE":
	 			result("free");
	 			break;
	 			
	 		case "NOTHING_HAPPENS":
	 			break;
	 			
	 		default:
	 			console.error("Could not parse turn action: " + action);
	 		}
	 	}
	 
		function showPopUp(id) {
			let overlay = document.getElementById(id);
			
			if(overlay != null) {
				overlay.style.visibility = "visible";
				overlay.style.opacity = 1;
			} else {
				console.error("Could not find popup with id: " + id);
			}
		}
		
		function hidePopUp(id) {
			let overlay = document.getElementById(id);
			
			if(overlay != null) {
				overlay.style.visibility = "hidden";
				overlay.style.opacity = 0;
			} else {
				console.error("Could not find popup with id: " + id);
			}
		}
		
		function closeOpenPopUp(id1, id2) {
			hidePopUp(id1);
			showPopUp(id2);
		}
		
		function setBoardText(text) {
			let boardText = document.getElementById("boardText");
			boardText.innerHTML = text;
		}
	 </script>
	 
	
	<!-- Piece moving on board scripts --> 
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
			if(moves >= 0) {
				ctx.clearRect(0, 0, ctx.canvas.width, ctx.canvas.height);
				
				ctx.drawImage(background, 0, 0);
				
				for(let i = 0; i < pieces.length; i++) {
					let piece = pieces[i];
					ctx.drawImage(piece.img, piece.x - piece.offsetX, piece.y - piece.offsetY);
					
					if(i == movingPiece) {
						if('${Turn.isActionEvaluated}' == 'true') {
							for(let j = moves; j > 0; j--) {
								setMovement(piece);
								moves--;
							}
							
							ctx.drawImage(piece.img, piece.x - piece.offsetX, piece.y - piece.offsetY);
							moves--;
							
						} else {
								setMovement(piece);
								moves--;
						}
					}
				}
				
				if(play) {
					setTimeout(function() {
						window.requestAnimationFrame(movePiece);
					}, velocity);
				}
			} else {
				if("${hasToMortgage}" == "true") {
	 				setBoardText("You have to pay ${needToPay}  <img style='height: 24px' src='/resources/images/Monodolar.png'/> , but you don't have enough money, so you have to mortgage one of your properties.");
				}
	 				
				if((('${Turn.action}' == 'AUCTION' || '${Turn.action}' == 'DRAW_CARD') && '${Turn.isActionEvaluated}' == 'false') || 
						('${isPlaying}' == 'true' && '${Turn.isActionEvaluated}' == 'false')) {
					parsePopUp(true);
					
					let showActionButton = document.getElementById('showActionButton');
					
					if(showActionButton != null) {
						showActionButton.disabled = "";
					}
				}
				
				if("${isPlaying && Turn.isActionEvaluated}" == "true") {
					showPopUp("wantToBuild");
					
					let showActionButton = document.getElementById('showActionButton');
					if(showActionButton != null) {
						showActionButton.setAttribute('onclick',"showPopUp('buildBuildings')");
						showActionButton.disabled = "";
					}
				}
				
				if("${isPlaying}" == "true") {
					let showMortgageButton = document.getElementById('showMortgageButton');
					if(showMortgageButton != null) {
						showMortgageButton.disabled = "";
					}
				}
				
				let endTurnButton = document.getElementById('endTurnButton');
				
				if(endTurnButton != null) {
					endTurnButton.disabled = "";
				}
				
				// ajaxStartScanningForChanges();
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
	
	<!-- Auxiliary scripts to control piece position on board -->
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
				coords.x = 35
				coords.y = 550
			} else if(pos < 20) {
				coords.x = 35
				coords.y = 550 - ((pos-10)*50)
			} else if(pos == 20) {
				coords.x = 50
				coords.y = 35
			} else if(pos < 30) {
				coords.x = 50 + ((pos-20)*50)
				coords.y = 35
			} else if(pos == 30) {
				coords.x = 550
				coords.y = 35
			} else if(pos < 40) {
				coords.x = 550
				coords.y = 50 + ((pos-30)*50)
			}
			
			return coords;
		}
		
		function coordsToPosition(coords) {
			position = 0;
			
			if(coords.y == 555) {
				position = 0 + (550-coords.x)/50;
			} else if(coords.x == 50 && coords.y == 550) {
				position = 10;
			} else if(coords.x == 35) {
				position = 10 + (550 - coords.y) / 50;
			} else if(coords.x == 50 && coords.y == 35) {
				position = 20;
			} else if(coords.y == 35) {
				position = 30 + (coords.x - 50) / 50; 
			} else if(coords.x == 550 && coords.y == 35) {
				position = 30;
			} else if(coords.x = 550) {
				position = 30 + (coords.y - 50) / 50;
			}
			
			return position;
		}
	</script>
	
	<!-- Script in charge of loading and starting piece animation -->
	<script defer>
		let play = true;
	
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
	
		let canvas = document.getElementById('Board');
		let ctx = canvas.getContext('2d');
	</script>
	
	<!-- Scripts for setting up every player's piece on the board -->
<c:forEach items="${Players}" varStatus="status">
	<script defer>
		(function () {
			i = parseInt("${status.index}")
			let pieceI = new Image();
			let imgSrc = "";
			switch (i) {
			  case 1:
				  imgSrc = "/resources/images/PieceMockups/AzulPiece.png";
				  break;
			  case 2:
				  imgSrc = "/resources/images/PieceMockups/RosaPiece.png";
				  break;
			  case 3:
				  imgSrc = "/resources/images/PieceMockups/NaranjaPiece.png";
				  break;
			  case 4:
				  imgSrc = "/resources/images/PieceMockups/AmarilloPiece.png";
				  break;
			  case 5:
				  imgSrc = "/resources/images/PieceMockups/CyanPiece.png";
				  break;
			  default:
				  imgSrc = "/resources/images/PieceMockups/VerdePiece.png";
			}
			
			pieceI.src = imgSrc;
			pieceI.width = 30;
			pieceI.height = 30;
			pieceI.onload = start;
			if("${Players[status.index] == Turn.player}" == "true") {
				coords = positionToCoords(parseInt("${Turn.initial_tile}"))
			} else {
				coords = positionToCoords(parseInt("${Players[status.index].tile}"))
			}
				
			let piecePropI = {
				img: pieceI,
				x: coords.x,
				offsetX: pieceI.width/2,
				y: coords.y,
				offsetY: pieceI.height/2
			}
			
			pieces.push(piecePropI);
		})();
	</script>
</c:forEach>

	<!-- Bankrupt player sign -->
<c:if test="${bankruptPlayer != null}">
	<script defer>
		setBoardText("The player ${bankruptPlayer.user.username} has gone bankrupt and is out of the game");
	</script>
</c:if>

	<!-- Card images zoom setter and controller -->
	<script>
		let cardImg = null;
		let gamePopUpDiv = null;
		function cardZoomListener() {
			if(cardImg.zoom) { // Remove zoom
				gamePopUpDiv.setAttribute("style", '');
				
				cardImg.style.position = "";
				cardImg.style.height = "";
				cardImg.style.cursor = "zoom-in";
			
			} else { // Add zoom
				gamePopUpDiv.setAttribute("style", "padding-top: 15%; top: 0px; bottom: 0px; max-height: 100vh; height: 100vh")
				
				cardImg.style.position = "relative";
				cardImg.style.height = "95vh";
				cardImg.style.cursor = "zoom-out";
			}
			cardImg.zoom = !cardImg.zoom;
		}
		
		function setCardZoomListener() {
			cardImg = document.getElementById("cardImg");
			gamePopUpDiv = document.getElementById("gamePopUp1");
			
			if(cardImg != null && cardImg != undefined) {
				cardImg.style.cursor = "zoom-in";
				cardImg.zoom = false;
				
				cardImg.addEventListener("click", cardZoomListener);
			}
		}
	</script>
	
	<!-- Script in charge of testing if game state has changed and reloads the page if needed -->
	<script>
		function ajaxStartScanningForChanges() {
			var auto_refresh = setInterval(
				  function() {
				    $.get('/game/${Game.id}/version').done(function(reply) {
				    	console.log(reply);
				        if (reply <= parseInt("${Version}")) {
				            return;
				        }
				        location.reload();
				    });
				  }, 2000);
		}
	</script>
	
	<!-- Ajax related jquery -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>

</monopoly:layout>