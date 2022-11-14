<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>


<petclinic:layout screenTittle="Games History">
   
	<div class="gameListMainBody">
		
		<div class="pagDiv">
			<div class="HeaderButtons">
				<nav aria-label= "Pagination">
					<ul class = "pagination justify-content-center">
						<c:forEach items="${pages}" var="page">
						<li><a href="/games/list?page=${page}">${page + 1}</a></li>
						</c:forEach>						
					</ul>
				</nav>
			</div>
		</div>
		
		<c:forEach items="${games}" var="game">
			<div class="gameListItem"> 
				<p class="gameInfoLine"> <c:out value="Game ${game.date}"/> <br> </p>
				
				<c:choose>
					<c:when test="${not empty game.duration}">
						<p class="gameDurationAndPlayers"> <c:out value="${game.duration} Hours"/> </p>
					</c:when>
				</c:choose>
				
				<p class="gameDurationAndPlayers"> <c:out value= "${game.players.size()}/6 Players" /> </p>
				
				<div class = "pagDiv2">  
					<c:forEach items="${game.players}" var="player">
					
						<c:choose>
							<c:when test="${player.getIsWinner()}">
								<p class="nameWinners"> <c:out value="${player.user.username} WINNER!"/> </p>
							</c:when>
							<c:otherwise>
								<p class="namePlayers"> <c:out value="${player.user.username}"/> </p>
							</c:otherwise>
					</c:choose>				
					</c:forEach>
				</div>		
					
			</div>
		</c:forEach>
	</div>
	  
</petclinic:layout>