<%@ tag trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="monopoly" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!doctype html>
<html>
<monopoly:htmlHeader/>

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
	<div class="scrollableBodyDiv">
		<jsp:doBody/>
	</div>

<monopoly:footer/>

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
