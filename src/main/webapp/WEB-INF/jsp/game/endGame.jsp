<%@ taglib prefix="monopoly" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<monopoly:layout screenTittle="Game Won!">
	
	<div class="gameWinningDiv">
		<span class="gameWinningSpan"><c:out value="${player.user.username}"/> has won game number <c:out value="${gameId}"/></span>
		<span class="gameWinningSpan">Congratulations!!</span>
	</div>
	
</monopoly:layout>