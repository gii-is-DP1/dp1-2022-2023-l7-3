<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!--  >%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%-->
<%@ attribute name="screenTittle" required="true" rtexprvalue="true"
	description="Name of the active screen"%>

<div id="Header">
	<div id="LogoTittle">
		<img src="resources/images/logo.png" id="Logo"/>
		<p id="ScreenTittle">${screenTittle}</p>
	</div>
	
	<div id="HeaderButtons">
		<sec:authorize access="!isAuthenticated()">
			<a href="/signup">
				<button id="SignInOutButton">Sign up</button>
			</a>
		</sec:authorize>
		
		<sec:authorize access="isAuthenticated()">
			<a href="/logout">
				<button id="SignInOutButton">Logout</button>
			</a>
		</sec:authorize>
		

		<a href="">
			<button id="BackButton">  Back  </button>
		</a>

		<a href="">
			<img href="" src="resources/images/settings.png" id="Settings"/>
		</a>
	</div>
</div>
