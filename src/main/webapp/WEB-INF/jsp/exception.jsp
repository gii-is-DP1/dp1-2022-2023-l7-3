<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="monopoly" tagdir="/WEB-INF/tags" %>

<monopoly:layout screenTittle="error">

    <spring:url value="/resources/images/monopoly.png" var="monopolyImage"/>
    <img src="${monopolyImage}"/>

    <h2>Something happened...</h2>

    <p>${exception.message}</p>

</monopoly:layout>
