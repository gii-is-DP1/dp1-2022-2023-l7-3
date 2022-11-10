<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<petclinic:signUpLayout>
	<jsp:body>
	    <div id="MainBodySignUp">
	        <h1 id="signInText"> Sign up to begin the fun!</h1>
	
	        <div id="formDiv">
	            <h2 id="formHeader">Enter your details below</h2>
	
	            <form:form modelAttribute="MonopolyUser" id="form" name="newUser" onsubmit="return validateForm()">
	                <label for="username" class="formLabel">Username</label>
	                <input type="text" id="username" class="inputText" name="username" placeholder="Your username.." required>
	
	                <label for="password" class="formLabel">Password</label>
	                <input type="password" id="password" class="inputText" name="password" placeholder="Your password.." required>
	                <i class="far fa-eye" id="togglePassword"></i>
	                
	                <label for="passwordConfirm" class="formLabel">Confirm your password</label>
	                <input type="password" id="passwordConfirm" class="inputText" name="passwordConfirm" placeholder=".." required>
	                <i class="far fa-eye" id="togglePasswordConfirm"></i>
	
	                <input type="submit" value="Sign up">
	            </form:form>
	        </div>
	
	        <h2 id="goBack"><a id="backLink" href="/">Go back</a></h2>
	    </div>
	</jsp:body>

</petclinic:signUpLayout>
