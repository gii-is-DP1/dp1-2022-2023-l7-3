<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="monopoly" tagdir="/WEB-INF/tags" %>
<!-- %@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %-->  

<monopoly:layout screenTittle="${screenTittle}">
    <div id="mainBody">

		<spring:url value="/resources/images/UnclePennybagsHoldingTittle.png" htmlEscape="true" var="monopolyImage"/>
		<div class="monopolyMan">
			<a href="/newGame" style="width: 80%;">
				<button class="playButton"> Play </button>
			</a>
			<img class="monopolyManImg" src="${monopolyImage}"/>
		</div>
		
		<spring:url value="/resources/images/welcome.png" htmlEscape="true" var="usLogo"/>
		<div class="usLogo">
			<img class="usLogoImg" src="${usLogo}"/>
		</div>

        <div class="welcomeFooter">
	        <h2> Project: ${title}</h2>
        	<h2> Group ${group}</h2>
        	
        	<ul>
		        <c:forEach items= "${authors}" var = "author"> 
		        <li>${author}</li>
		        </c:forEach>
	        </ul>
        </div>
    </div>
</monopoly:layout>
