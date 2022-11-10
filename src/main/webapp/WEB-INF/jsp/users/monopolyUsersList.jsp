<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>


<petclinic:layout screenTittle="Monopoly Users List">
	
	<div class="playersListMainBody">
		
		<div class="pagDiv">
			
			<h1> List of all players</h1>
			
			<div class="HeaderButtons">
				<a href="/monopolyUsers/list/previous">
					<button class="pageButton"> - </button>
				</a>
				<a href="/monopolyUsers/list/next">
					<button href="/monopolyUsers/list/previous" class="pageButton"> + </button>
				</a>
			</div>
		</div>
		
		<c:forEach items="${monopolyUsers}" var="monopolyUser">
			<div class="playerListItem"> 
				
				<p class="playerInfoLine"> <c:out value="${monopolyUser.username}"/> </p>
            	<a href="/login">
                <button id="EditButton">Edit <span class="glyphicon glyphicon-pencil"></span></button>
            	</a>
            	<a href="/login">
                <button id="DeleteButton">Delete <span class="glyphicon glyphicon-trash"></span></button>
            	</a>

				<spring:url value="/monopolyUsers/{monopolyUserId}" var="monopolyUserUrl">
					<spring:param name="monopolyUserId" value="${monopolyUser.id}"/>
				</spring:url>
				
			</div>
		</c:forEach>
		
		
		
	</div>

</petclinic:layout>