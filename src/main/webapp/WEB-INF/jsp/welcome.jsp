<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<!-- %@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %-->  

<petclinic:layout screenTittle="${screenTittle}">
    <div id="mainBody">
        <h2><fmt:message key="welcome"/></h2>
        <div class="row">
        <h2> Project: ${title}</h2>
        <p>
        	<h2>Group ${group}</h2>
        </p>
        <p>
        	<ul>
		        <li><a href="/monopolyUsers/list?username=">View Players</a></li>
	        </ul>
        </p>
        </div>
        
        <div class="row">
            <div class="col-md-12">
                <spring:url value="/resources/images/00.png" htmlEscape="true" var="petsImage"/>
                <spring:url value="/resources/images/welcome.png" htmlEscape="true" var="usLogo"/>
                <img class="img-responsive" src="${petsImage}"/>
                <img class="img-responsive" src="${usLogo}"/>
            </div>
        </div>
    </div>
</petclinic:layout>
