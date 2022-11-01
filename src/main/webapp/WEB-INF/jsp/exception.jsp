<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout screenTittle="error">

    <spring:url value="/resources/images/monopoly.png" var="monopolyImage"/>
    <img src="${monopolyImage}"/>

    <h2>Something happened...</h2>

    <p>${exception.message}</p>

</petclinic:layout>
