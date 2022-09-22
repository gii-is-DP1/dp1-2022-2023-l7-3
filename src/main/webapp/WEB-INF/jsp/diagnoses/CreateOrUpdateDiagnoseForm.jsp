<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="owners">
    <h2>
        <c:if test="${diagnose['new']}">New </c:if> Diagnose
    </h2>
    <p>
    	Diagnose for visit on <petclinic:localDate date="${visit.date}" pattern="yyyy-MM-dd"/> for <c:out value="${visit.pet.name}"/>, a ${visit.pet.type} born in <petclinic:localDate date="${visit.pet.birthDate}" pattern="yyyy-MM-dd"/>    </p>
    
    <form:form modelAttribute="diagnose" class="form-horizontal" id="add-owner-form">
        <div class="form-group has-feedback">      
        	<input type="hidden" name="visit" value="${visit.id}" />      
            <petclinic:richTextArea id="description" label="Description" name="description"/>            
            <div class="form-group">
            	<label class="col-sm-2 control-label">Vet:</label>
            	<div class="col-sm-10">
            	<form:select path="vet">
	            	<form:options itemValue="id" itemLabel="fullName" items="${vets}" />
    	        </form:select>
    	        </div>
            </div>                        
            <div class="form-group">
            	<label class="col-sm-2 control-label">Disease:</label>
            	<div class="col-sm-10">
				<form:select path="disease">
            		<form:options itemValue="id" itemLabel="name" items="${diseases}" />
            	</form:select>
            	<div class="has-error ">
            		<form:errors path="disease" class="help-block"/>            	
            	</div>                        
            </div>
        </div>
        <div class="form-group">
        	<div class="has-error ">
            		<form:errors class="help-block"/>            	
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <c:choose>
                    <c:when test="${diagnose['new']}">
                        <button class="btn btn-default" type="submit">Add Diagnose</button>
                    </c:when>
                    <c:otherwise>
                        <button class="btn btn-default" type="submit">Update Diagnose</button>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </form:form>
</petclinic:layout>