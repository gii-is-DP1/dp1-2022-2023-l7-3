<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html xmlns:th="http://www.thymeleaf.org">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <%-- The above 4 meta tags *must* come first in the head; any other head content must come *after* these tags --%>

    <spring:url value="/resources/images/favicon.png" var="favicon"/>
    <link rel="shortcut icon" type="image/x-icon" href="${favicon}">

    <title>Monopoly</title>

    <%-- CSS --%>
    
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
    
    <spring:url value="/resources/css/font.css" var="fontCss"/>
    <link href="${fontCss}" rel="stylesheet"/>
    
    <spring:url value="/resources/css/layoutStyles.css" var="layoutStylesCss"/>
    <link href="${layoutStylesCss}" rel="stylesheet"/>
    
    <spring:url value="/resources/css/SignUpStyles.css" var="signUpStylesCss"/>
    <link href="${signUpStylesCss}" rel="stylesheet"/>
    
    <spring:url value="/resources/css/players.css" var="playersCss"/>
    <link href="${playersCss}" rel="stylesheet"/>
    
    <spring:url value="/resources/css/game.css" var="gameCss" />
	<link href="${gameCss}" rel="stylesheet" />
	
	<spring:url value="/resources/css/mainGame.css" var="mainGameCss" />
	<link href="${mainGameCss}" rel="stylesheet" />
	
	<spring:url value="/resources/css/editPlayer.css" var="editPlayerCss" />
	<link href="${editPlayerCss}" rel="stylesheet" />
    
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/css/all.min.css">


    <%-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries --%>
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->

    <!-- Only datepicker is used -->
    <spring:url value="/webjars/jquery-ui/1.11.4/jquery-ui.min.css" var="jQueryUiCss"/>
    <link href="${jQueryUiCss}" rel="stylesheet"/>
    <spring:url value="/webjars/jquery-ui/1.11.4/jquery-ui.theme.min.css" var="jQueryUiThemeCss"/>
    <link href="${jQueryUiThemeCss}" rel="stylesheet"/>
</head>
