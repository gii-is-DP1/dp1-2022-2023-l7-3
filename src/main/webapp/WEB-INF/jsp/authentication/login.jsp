<%@ taglib prefix="monopoly" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!doctype html>
<html>
<monopoly:htmlHeader/>

<body>
	<div id="Header">
		<div id="LogoTittle">
			<img src="resources/images/logo.png" id="Logo"/>
			<p id="ScreenTittle">Login</p>
		</div>
    </div>
    
    <div class="scrollableBodyDiv">
		<div id="MainBodySignUp">
	        <h1 id="signInText"> Sign in to begin the fun!</h1>
	
	        <div id="formDiv">
	            <h2 id="formHeader">Enter your details below</h2>
				
	            <form:form id="form" name="newUser" action="/login" method="post">
	            
	            	<c:if test="${param.error != null}">
	            		<div style="margin-bottom: 15px;">
							Failed to login.
							<c:if test="${SPRING_SECURITY_LAST_EXCEPTION != null}">
								Reason: <c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}"/>
							</c:if>            		
	            		</div>
	            	</c:if>
	            
	                <label for="username" class="formLabel">Username</label>
	                <input type="text" id="username" class="inputText" name="username" placeholder="Your username.." required>
	
	                <label for="password" class="formLabel">Password</label>
	                <input type="password" id="password" class="inputText" name="password" placeholder="Your password.." required>
	                <i class="far fa-eye" id="togglePassword"></i>
	
	                <input type="submit" value="Sign in">
	            </form:form>
	        </div>
	
	    </div>
    </div>

<monopoly:footer/>

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
</script>

</html>