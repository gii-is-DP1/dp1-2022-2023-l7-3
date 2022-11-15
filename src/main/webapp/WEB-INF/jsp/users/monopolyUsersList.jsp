<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<html xmlns:th="http://www.thymeleaf.org">

<petclinic:layout screenTittle="Monopoly Users List">
	
	<div class="playersListMainBody">
		
		<div class="pagDiv">
		
			<h1> List of all players</h1>
			
			<form class ="form-inline" action = "/monopolyUsers/list" method = "get">
				<div class= "form-group mx-sm-3 mb-2">
					<input type = "text" name="username" class="form-control" id="username" value="${username}" placeholder="Search a player"> 

				<input type="submit" id = "SubmitButton" value = "Search">
				</div>
			</form> 

			<div class="HeaderButtons">
				<nav aria-label= "Pagination">
					<ul class = "pagination justify-content-center">
						<c:forEach items="${pages}" var="page">
						<li><a href="/monopolyUsers/list?page=${page}&username=${username}">${page + 1}</a></li>
						</c:forEach>						
					</ul>
				</nav>
			</div>
			
		</div>
		
		<c:forEach items="${monopolyUsers}" var="monopolyUser">
			<div class="playerListItem"> 
				
				<p class="playerInfoLine"> <c:out value="${monopolyUser.username}"/> </p>
            	<a href="/login">
                <button id="EditButton">Edit <span class="glyphicon glyphicon-pencil"></span></button>
            	</a>
            	<a href="/monopolyUsers/delete" >
                <button id="DeleteButton">Delete <span class="glyphicon glyphicon-trash"></span></button>
            	</a>
				
			</div>
		</c:forEach>
		
	</div>

</petclinic:layout>