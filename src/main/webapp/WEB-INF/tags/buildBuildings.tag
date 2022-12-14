<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="monopoly" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<spring:url value="/resources/css/game.css" var="gameCss"/>
<link href="${gameCss}" rel="stylesheet"/>

	<div style="display:flex;flex-direction: row;margin: auto;">
		<h1 class="propertyTxt">What do you want to build?</h1>
		<button onclick="closePopUp(&quot;buildBuildingsPopUp&quot)" class="cancelButton" ><span class="glyphicon glyphicon-remove"></span></button>
	</div>
    <div class="tableButtoms">
        <table id="propertiesTable" class="table table-stripped">
            <thead>
            <tr>
                <th><div class="tableCell">COLOR</div> </th>
                <th style="width: 30vw;"><div class="tableCell">NAME</div> </th>
                <th><div class="tableCell">HOUSES</div> </th>
                <th><div class="tableCell">HOTEL</div> </th>
				<th><div class="tableCell"></div> </th>
                
            </tr>
            </thead>
            <tbody>
            
            <c:forEach  items="${streets}" var="street">
            	<form:form object="streetForm" id="StreetForm" action="/blankGame/build">
            		<input type="hidden" name=streetId id = "streetId" class="tableCell" value="${street.id}">
            		<input type="hidden" name=color id = "color" class="tableCell" value="${street.color}">
            		
                	<tr>
                    	<td>
                        	<div  class="color2${street.color}">   </div>                    
                    	</td>
                    	<td style="width: 30vw;">
                        	<div class="tableCell">${street.name}</div>  
                    	</td>
                    	<td>
	                		<input type="number" name="house" id="house" class="tableCell2" placeholder="${street.houseNum}">  
                   		</td>
                   		<c:if test="${street.haveHotel==false}">
                    	<td class="tableCell">
	                		<input type="checkbox" name="hotel" id="hotel" class="tableCell" style= "margin-top: 15px;" placeholder="${street.haveHotel}">  
                   		</td>
                   		</c:if>
                   		<c:if test="${street.haveHotel==true}">
                   			<td class= "tableCell" style= "padding-top: 2vh;">Already has a hotel</td>
                   		</c:if>	                
                    	<td class="tableCell">
                    		<button type = "submit"  class="buildButtons"><span class="glyphicon glyphicon-ok"></span></button>
                    	</td>
             
                	</tr>
                </form:form>
            </c:forEach>
            </tbody>
        </table>       
    </div>
    
