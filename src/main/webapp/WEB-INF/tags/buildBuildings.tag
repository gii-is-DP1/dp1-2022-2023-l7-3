<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="monopoly" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<spring:url value="/resources/css/game.css" var="gameCss"/>
<link href="${gameCss}" rel="stylesheet"/>

	<h1 class="propertyTxt">What do you want to build?</h1>
    <div class="tableButtoms">
    	
        <table id="propertiesTable" class="table table-stripped">
            <thead>
            <tr>
                <th style="font-size: 20px; width: 25vw; text-align: center;">COLOR</th>
                <th style="font-size: 20px; width: 25vw; text-align: center;">NAME</th>
                <th style="font-size: 20px; width: 25vw; text-align: center;">HOUSES</th>
                <th style="font-size: 20px; width: 25vw; text-align: center;">HOTEL</th>
                
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${streets}" var="street">
            	<form:form object="streetForm" action="/blankGame/build">
            		<input type="hidden" name=streetId class="tableCell" value="${street.id}">
                	<tr>
                    	<td>
                        	<div  class="color2${street.color}">   </div>                    
                    	</td>
                    	<td>
                        	<div class="tableCell">${street.name}</div>  
                    	</td>
                    	<td>
	                		<input type="number" name="house" class="tableCell" placeholder="${street.houseNum}">  
                   		</td>
                   		<c:if test="${street.haveHotel==false}">
                    	<td>
	                		<input type="checkbox" name="hotel" class="tableCell" placeholder="${street.haveHotel}">  
                   		</td>
                   		</c:if>
                    	<td>
                    		<button type="submit" class="popUpButton">Confirm</button>
                    	</td>
                	</tr>
                </form:form>
            </c:forEach>
            </tbody>
        </table>
        
        <div class="popUpButtons">
           
            <button onclick="closePopUp(&quot;buildBuildingsPopUp&quot)" class="popUpDangerButton">Cancel</button>
        </div>
       
    </div>
