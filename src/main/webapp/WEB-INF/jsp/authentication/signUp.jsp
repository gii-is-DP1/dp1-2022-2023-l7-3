<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!doctype html>
<html>
<petclinic:htmlHeader/>

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

            <form:form modelAttribute="MonopolyUser" id="form" name="newUser" onsubmit="return validateForm()">
                <label for="username" class="formLabel">Username</label>
                <input type="text" id="username" class="inputText" name="username" placeholder="Your username.." required>

                <label for="password" class="formLabel">Password</label>
                <input type="password" id="password" class="inputText" name="password" placeholder="Your password.." required>
                <i class="far fa-eye" id="togglePassword"></i>
                
                <label for="passwordConfirm" class="formLabel">Confirm your password</label>
                <input type="password" id="passwordConfirm" class="inputText" name="passwordConfirm" placeholder=".." required>
                <i class="far fa-eye" id="togglePasswordConfirm"></i>

                <input type="submit" value="Sign in">
            </form:form>
        </div>

        <h2 id="goBack"><a id="backLink" href="/">Go back</a></h2>
    </div>


<petclinic:footer/>

</body>

<script>
	function validateForm() {
		var password = document.forms['newUser']['password'].value;
		var repeat = document.forms['newUser']['passwordConfirm'].value;
		if(password !== repeat) {
			alert("The password fields are not equal")
			return false;
		}
	}
</script>

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
