<%@ taglib prefix="monopoly" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<monopoly:layout screenTittle="New Game">

	<div class="NewGameMainBody">
		<div class="players">

			<form:form modelAttribute="GameForm" class="GameForm" id="GameForm" action="/newGame">
				<%-- <spring:url value="/newGame/creating/add/{userId}" var="addUserUrl">
					<spring:param id="urlParam" name="userId" value="0" />
				</spring:url> --%>
				
				<c:forEach items="${players}" var="player" varStatus="status">
					<input type="hidden" name="users[${status.index}]" value="${player.id}"/>
				</c:forEach>
				
				<div class="formDiv">
				
<%-- 					<input type="hidden" name="players[0].id" value="${player[0].id}"/> --%>
<%-- 					<input type="hidden" name="players[0].user" value="${player[0].user}"/> --%>
					<div class="player" id="player1">
						<img class="playerImg" src="/resources/images/Verde.png" />
						<p class="playerName"><c:out value = "${players[0].username}"/></p>

					</div>
					
					<div class="player" id="player2">
						<c:choose>
							<c:when test="${players[1] != null}">
								<img class="playerImg" src="/resources/images/Azul.png" />

								<%-- <spring:url value="/newGame/creating/remove/{playerNum}"
									var="playerUrl1">
									<spring:param name="playerNum" value="1" />
								</spring:url> --%>

								<a onClick="removePlayer(1)" href="JavaScript:void(0)" class="playerName"><c:out value="${players[1].username}"/></a>
							</c:when>
							<c:otherwise>
								<img class="playerImg" src="/resources/images/Add.png" />

								<a onclick="showPopUp()" href="JavaScript:void(0)"
									class="playerName">Add player</a>
							</c:otherwise>
						</c:choose>
					</div>
					
					
					<div class="player" id="player3">
						<c:choose>
							<c:when test="${players[2] != null}">
								<img class="playerImg" src="/resources/images/Rosa.png" />

								<%-- <spring:url value="/newGame/creating/remove/{playerNum}"
									var="playerUrl2">
									<spring:param name="playerNum" value="2" />
								</spring:url> --%>

								<a onClick="removePlayer(2)" href="JavaScript:void(0)" class="playerName"><c:out value="${players[2].username}"/></a>
							</c:when>
							<c:otherwise>
								<img class="playerImg" src="/resources/images/Add.png" />

								<a onclick="showPopUp()" href="JavaScript:void(0)"
									class="playerName">Add player</a>
							</c:otherwise>
						</c:choose>
					</div>

					
					<div class="player" id="player4">
						<c:choose>
							<c:when test="${players[3] != null}">
								<img class="playerImg" src="/resources/images/Naranja.png" />

								<%-- <spring:url value="/newGame/creating/remove/{playerNum}"
									var="playerUrl3">
									<spring:param name="playerNum" value="3" />
								</spring:url> --%>

								<a onClick="removePlayer(3)" href="JavaScript:void(0)" class="playerName"><c:out value="${players[3].username}"/></a>
							</c:when>
							<c:otherwise>
								<img class="playerImg" src="/resources/images/Add.png" />

								<a onclick="showPopUp()" href="JavaScript:void(0)"
									class="playerName">Add player</a>
							</c:otherwise>
						</c:choose>
					</div>

					
					<div class="player" id="player5">
						<c:choose>
							<c:when test="${players[4] != null}">
								<img class="playerImg" src="/resources/images/Amarillo.png" />

								<%-- <spring:url value="/newGame/creating/remove/{playerNum}"
									var="playerUrl4">
									<spring:param name="playerNum" value="4" />
								</spring:url> --%>

								<a onClick="removePlayer(4)" href="JavaScript:void(0)" class="playerName"><c:out value="${players[4].username}"/></a>
							</c:when>
							<c:otherwise>
								<img class="playerImg" src="/resources/images/Add.png" />

								<a onclick="showPopUp()" href="JavaScript:void(0)"
									class="playerName">Add player</a>
							</c:otherwise>
						</c:choose>
					</div>
					
					
					<div class="player" id="player6">
						<c:choose>
							<c:when test="${players[5] != null}">
								<img class="playerImg" src="/resources/images/Cyan.png" />

								<%-- <spring:url value="/newGame/creating/remove/{playerNum}"
									var="playerUrl5">
									<spring:param name="playerNum" value="5" />
								</spring:url> --%>

								<a onClick="removePlayer(5)" href="JavaScript:void(0)" class="playerName"><c:out value="${players[5].username}"/></a>
							</c:when>
							<c:otherwise>
								<img class="playerImg" src="/resources/images/Add.png" />

								<a onclick="showPopUp()" href="JavaScript:void(0)"
									class="playerName">Add player</a>
							</c:otherwise>
						</c:choose>
					</div>
				</div>


				<div class="popUpOverlay1" id="popUpOverlayId">
					<div class="addPlayerPopUp1" id="addPlayerPopUpId">
						<c:forEach items="${users}" var="user" varStatus="status">
							<div class="userToAdd">
								<a onClick="addNewPlayer(${user.id})" href="JavaScript:void(0)" class="playerName"><c:out value="${user.username}"/></a>
							</div>
						</c:forEach>
						
						<button class="mainButtonStyle" type="button" onclick="hidePopUp()"> Close </button>
					</div>
				</div> 
			</form:form>

		</div>
		
		<div style = "display: flex; justify-content: center;">
			<span style = "font-size: 20px"><strong><c:out value = "${error}"/></strong></span>
		</div>
		
		<div class="bottomButtons">
			<input onClick="JavaScript:document.getElementById('GameForm').submit()" type="submit" value="Create game">
		</div>
	</div>



<script>
	function addNewPlayer(index) {
		var url = "/newGame/creating/add/" + index;
		
		var form = document.getElementById("GameForm");
		form.action = url;
		form.submit();
	}

	function removePlayer(index) {
		var url = "/newGame/creating/remove/" + index;
		
		var form = document.getElementById("GameForm");
		form.action = url;
		form.submit();
	}
</script>

<script>
	function showPopUp() {
		var overlay = document.getElementById("popUpOverlayId");
		overlay.style.visibility = "visible";
		overlay.style.opacity = 1;
	}
	
	function hidePopUp() {
		let overlay = document.getElementById("popUpOverlayId");
		
		overlay.style.visibility = "hidden";
		overlay.style.opacity = 0;
	}
</script>

</monopoly:layout>
