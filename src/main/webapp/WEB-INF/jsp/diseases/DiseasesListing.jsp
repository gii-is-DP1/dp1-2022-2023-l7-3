<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="vademecum">
	<h2>Diseases</h2>
	    <table id="diseasesTable" class="table table-striped">
        <thead>
        <tr>
            <th style="width: 15%;">Name</th>
            <th style="width: 70%'%;">Description</th>
            <th style="width: 15%;">Pet Types</th>
            <th></th>
            <th></th>            
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${diseases}" var="disease">
            <tr>
                <td>                    
                    <c:out value="${disease.name}"/>
                </td>
                <td>
                    ${disease.description}"
                </td>                
                <td>
                	<ul>
                	 <c:forEach items="${disease.petTypeswithPrevalence}" var="petType">
                	 	<li>${petType}</li>
                	 </c:forEach>
                	</ul>
                </td>
                <td>
                	<a href="/diseases/${disease.id}/edit">
                	<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
                	</a>
                </td>
                <td>
                	<a href="/diseases/${disease.id}/delete">
                		<span class="glyphicon glyphicon-trash" aria-hidden="true"></span>
                	</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <p>
    	<a href="/diseases/new" class="btn  btn-success"><span class="glyphicon glyphicon-plus" aria-hidden="true"></span>Add disease</a>
    </p>
	
</petclinic:layout>