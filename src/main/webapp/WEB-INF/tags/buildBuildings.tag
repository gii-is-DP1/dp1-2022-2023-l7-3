<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="monopoly" tagdir="/WEB-INF/tags"%>

<spring:url value="/resources/css/game.css" var="gameCss"/>
<link href="${gameCss}" rel="stylesheet"/>

	<h1 class="propertyTxt">What do you want to build?</h1>

	    <table id="propertiesTable" class="table table-striped">
        <thead>
        <tr>
            <th>Color</th>
            <th>Name</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${properties}" var="property">
            <tr>
                <td>
                    <c:out value="${property.color}"/>
                    
                </td>
                <td>
                <c:out value="${property.name}"/>  
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>