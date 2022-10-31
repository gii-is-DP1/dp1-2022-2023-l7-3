<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!doctype html>
<html>
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
    <spring:url value="resources/css/layoutStyles.css" var="layoutStylesCss"/>
    <link href="${layoutStylesCss}" rel="stylesheet"/>
    
    <spring:url value="resources/css/SignUpStyles.css" var="signUpStylesCss"/>
    <link href="${signUpStylesCss}" rel="stylesheet"/>
    
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

<body>
	<div id="Header">
		<div id="LogoTittle">
			<img src="resources/images/logo.png" id="Logo"/>
			<p id="ScreenTittle">${screenTittle}</p>
		</div>
		
        <div id="HeaderButtons">
            <p id="AlreadySignedUp">Already have an account?</p>
            <a href="/login">
                <button id="SignInOutButton">Sign in</button>
            </a>
        </div>
    </div>

    <div id="MainBodySignUp">
        <h1 id="signInText"> Sign up to begin the fun!</h1>

        <div id="formDiv">
            <h2 id="formHeader">Enter your details below</h2>

            <form id="form">
                <label for="username" class="formLabel">Username</label>
                <input type="text" id="username" class="inputText" name="username" placeholder="Your username..">

                <label for="password" class="formLabel">Password</label>
                <input type="password" id="password" class="inputText" name="password" placeholder="Your password..">
                <i class="far fa-eye" id="togglePassword"></i>
                
                <label for="passwordConfirm" class="formLabel">Confirm your password</label>
                <input type="password" id="passwordConfirm" class="inputText" name="passwordConfirm" placeholder="..">
                <i class="far fa-eye" id="togglePasswordConfirm"></i>

                <input type="submit" value="Sign in">
            </form>
        </div>

        <h2 id="goBack"><a id="backLink" href="/">Go back</a></h2>
    </div>


<petclinic:footer/>

</body>

<script>
    const togglePassword = document.querySelector('#togglePassword');
    const password = document.querySelector('#password');

    togglePassword.addEventListener('click', function (e) {
        // toggle the type attribute
        const type = password.getAttribute('type') === 'password' ? 'text' : 'password';
        password.setAttribute('type', type);
        // toggle the eye slash icon
        this.classList.toggle('fa-eye-slash');
    });

    const togglePasswordConfirm = document.querySelector('#togglePasswordConfirm');
    const passwordConfirm = document.querySelector('#passwordConfirm');

    togglePasswordConfirm.addEventListener('click', function (e) {
        // toggle the type attribute
        const type = passwordConfirm.getAttribute('type') === 'password' ? 'text' : 'password';
        passwordConfirm.setAttribute('type', type);
        // toggle the eye slash icon
        this.classList.toggle('fa-eye-slash');
    });
</script>

</html>
