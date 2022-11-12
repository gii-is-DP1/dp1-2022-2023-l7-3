<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>


<petclinic:layout screenTittle="Games History">
	
	<div class="playersListMainBody">
		
		<div class="pagDiv">
		
			<div class="HeaderButtons">
				<a href="/games/list/previous">
					<button class="pageButton"> - </button>
				</a>
				<a href="/games/list/next">
					<button href="/games/list/previous" class="pageButton"> + </button>
				</a>
			</div>
			
		</div>
		
		<c:forEach items="${games}" var="game">
			<div class="playerListItem"> 
				<p class="playerInfoLine"> <c:out value="${game.date}"/> </p>
				<p class="playerInfoLine"> <c:out value="${game.duration}"/> </p>
				
				<c:forEach items="${game.players}" var="player">
					<p class="playerInfoLine"> <c:out value="${player.user.username}"/> </p>
					<p><br></br></p>
				</c:forEach>		
			</div>
		</c:forEach>
	</div>

</petclinic:layout>