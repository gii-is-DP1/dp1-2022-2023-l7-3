<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="owners">
    <h2>
        <c:if test="${disease['new']}">New </c:if> Disease
    </h2>
    <form:form modelAttribute="disease" class="form-horizontal" id="add-owner-form">
        <div class="form-group has-feedback">
            <petclinic:inputField label="Name" name="name"/>
            <petclinic:richTextArea id="description" label="Description" name="description"/>
            <div class="formGroup">            
            	<div class="col-sm-10">
            	<label>Pet types with known prevalence:</label>
            		<form:checkboxes items="${petTypes}" path="petTypeswithPrevalence" delimiter="&nbsp;&nbsp;&nbsp;"/>
            	</div>
            </div>         
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <c:choose>
                    <c:when test="${disease['new']}">
                        <button class="btn btn-default" type="submit">Add Disease</button>
                    </c:when>
                    <c:otherwise>
                        <button class="btn btn-default" type="submit">Update Disease</button>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </form:form>
</petclinic:layout>