<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<html xmlns:th="http://www.thymeleaf.org">

<petclinic:layout screenTittle="Monopoly Users List">
	
	<div class="playersListMainBody">
		

		<div class="headerButtons">
		
			<h1 style="align-items: center; margin-top: 15px; margin-bottom: 15px;"> List of all players</h1>
			
			<form class = "searchPagination" action = "/monopolyUsers/list" method = "get">
				<input type = "text" name="username" class="form-control" id="username" value="${username}" placeholder="Search a player"> 
				<input type="submit" id = "SearchButton" value = "Search" >
			</form> 

			<div class="searchPagination">
				<c:if test="${currentPage > 0}">
				<a href = "/monopolyUsers/list?page=${currentPage - 1}&username=${username}">
				<button class="paginationButton">&lt;</button>
				</a>
				</c:if>
				<nav class = "navClass"aria-label= "Pagination">
					<ul class = "pagination justify-content-center">
						<c:forEach items="${pages}" var="page">
						<li><a href="/monopolyUsers/list?page=${page}&username=${username}">${page + 1}</a></li>
						</c:forEach>						
					</ul>
				</nav>
								<c:if test="${currentPage < totalPages - 1}">
				<a href="/monopolyUsers/list?page=${currentPage + 1}&username=${username}">
				<button class="paginationButton">&gt;</button>
				</a>
				</c:if>

			</div>

		</div>
		
		<c:forEach items="${monopolyUsers}" var="monopolyUser">
			<div class="playerListItem"> 
				
				<p class="playerInfoLine"> <c:out value="${monopolyUser.username}"/> </p>
            	<a href="/users/${monopolyUser.id}">
                <button id="EditButton">Edit <span class="glyphicon glyphicon-pencil"></span></button>
            	</a>
            	<c:if test="${monopolyUser.id!=0}">
            	<a href="/monopolyUsers/delete/${monopolyUser.id }" >
                <button id="DeleteButton">Delete <span class="glyphicon glyphicon-trash"></span></button>
            	</a>
            	</c:if>
				
			</div>
		</c:forEach>
		
	</div>

</petclinic:layout>