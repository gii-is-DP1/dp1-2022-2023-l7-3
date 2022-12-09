<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="monopoly" tagdir="/WEB-INF/tags"%>

<spring:url value="/resources/css/game.css" var="gameCss"/>
<link href="${gameCss}" rel="stylesheet"/>

	<h1 class="propertyTxt">What do you want to build?</h1>

	    <table id="propertiesTable" class="table table-stripped">
        <thead>
        <tr>
            <th style="font-size: 20px; width: 25vw; text-align: center;">COLOR</th>
            <th style="font-size: 20px; width: 25vw; text-align: center;">NAME</th>
            <th style="font-size: 20px; width: 25vw; text-align: center;">NUM_HOUSE</th>
            <th style="font-size: 20px; width: 25vw; text-align: center;">HOTEL?</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${properties}" var="property">
            <tr>
                <td>
                    <div  class="color2${property.color}">   </div>                    
                </td>
                <td>
                    <div class="tableCell">${property.name}</div>  
                </td>
                <td>
                    <div class="tableCell">${property.houseNum}</div>   
                </td>
                <td>
                    <div class="tableCell">${property.haveHotel}</div>  
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>