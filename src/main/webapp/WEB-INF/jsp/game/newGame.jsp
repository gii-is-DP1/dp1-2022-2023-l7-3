<%@ taglib prefix="monopoly" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<spring:url value="/resources/css/game.css" var="gameCss"/>
<link href="${gameCss}" rel="stylesheet"/>

<monopoly:layout screenTittle="New Game">
	
	<div class="NewGameMainBody">
		<div class="players">
			
			<form:form>
				<div class="formDiv">
					<div class="player" id="player1">
						<img class="playerImg" src="/resources/images/Verde.png"/>
						<p class="playerName">${playerNames[0]}</p>
							
					</div>
						
					<div class="player" id="player2">
						<c:choose>
							<c:when test="${players1] != null}">
								<img class="playerImg" src="/resources/images/Verde.png"/>
								
								<spring:url value="/newGame/creating/remove/{playerNum}" var="playerUrl1">
			                        <spring:param name="playerNum" value="1"/>
			                    </spring:url>
								
								<a href="${playerUrl1}" class="playerName">${playerNames[1]}</a>
							</c:when>
							<c:otherwise>
								<img class="playerImg" src="/resources/images/Add.png"/>
								<a href="/newGame/creating/add/">
									<button class="playerName">Add player</button>
								</a>
							</c:otherwise>
						</c:choose>
					</div>
					
					<div class="player" id="player3">
						<c:choose>
							<c:when test="${players[2] != null}">
								<img class="playerImg" src="/resources/images/Verde.png"/>
								
								<spring:url value="/newGame/creating/remove/{playerNum}" var="playerUrl2">
			                        <spring:param name="playerNum" value="2"/>
			                    </spring:url>
								
								<a href="${playerUrl2}" class="playerName">${playerNames[2]}</a>
							</c:when>
							<c:otherwise>
								<img class="playerImg" src="/resources/images/Add.png"/>
								<a href="/newGame/creating/add/">
									<button class="playerName">Add player</button>
								</a>
							</c:otherwise>
						</c:choose>
					</div>
					
					<div class="player" id="player4">
						<c:choose>
							<c:when test="${players[3] != null}">
								<img class="playerImg" src="/resources/images/Verde.png"/>
								
								<spring:url value="/newGame/creating/remove/{playerNum}" var="playerUrl3">
			                        <spring:param name="playerNum" value="3"/>
			                    </spring:url>
								
								<a href="${playerUrl3}" class="playerName">${playerNames[3]}</a>
							</c:when>
							<c:otherwise>
								<img class="playerImg" src="/resources/images/Add.png"/>
								<a href="/newGame/creating/add/">
									<button class="playerName">Add player</button>
								</a>
							</c:otherwise>
						</c:choose>
					</div>
					
					<div class="player" id="player5">
						<c:choose>
							<c:when test="${players[4] != null}">
								<img class="playerImg" src="/resources/images/Verde.png"/>
								
								<spring:url value="/newGame/creating/remove/{playerNum}" var="playerUrl4">
			                        <spring:param name="playerNum" value="4"/>
			                    </spring:url>
								
								<a href="${playerUrl4}" class="playerName">${playerNames[4]}</a>
							</c:when>
							<c:otherwise>
								<img class="playerImg" src="/resources/images/Add.png"/>
								<a href="/newGame/creating/add/">
									<button class="playerName">Add player</button>
								</a>
							</c:otherwise>
						</c:choose>
					</div>
					
					<div class="player" id="player6">
						<c:choose>
							<c:when test="${players[5] != null}">
								<img class="playerImg" src="/resources/images/Verde.png"/>
								
								<spring:url value="/newGame/creating/remove/{playerNum}" var="playerUrl5">
			                        <spring:param name="playerNum" value="5"/>
			                    </spring:url>
								
								<a href="${playerUrl5}" class="playerName">${playerNames[5]}</a>
							</c:when>
							<c:otherwise>
								<img class="playerImg" src="/resources/images/Add.png"/>
								<a href="/newGame/creating/add/">
									<button class="playerName">Add player</button>
								</a>
							</c:otherwise>
						</c:choose>
					</div>
				</div>
			</form:form>
		
		</div>
		
		<c:if test="${adding == true}">
			<div class="addPlayerPopUp">
				<c:forEach items="users">
					<div class="userToAdd">
						<spring:url value="/newGame/creating/add/{userId}" var="userUrl<c:out value="${user.id}"/>">
	                        <spring:param name="userId" value="${user.id}"/>
	                    </spring:url>
						
						<a href="${'userUrl' + 'user.id'}" class="playerName">${users}</a>
					</div>
				</c:forEach>
			</div>
		</c:if>
	
		<div class="bottomButtons">
		
		</div>
	</div>

</monopoly:layout>
