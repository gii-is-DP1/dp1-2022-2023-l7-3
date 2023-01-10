<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="monopoly" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html xmlns:th="http://www.thymeleaf.org">

<monopoly:layout screenTittle="My profile">

	<div class="mainEditProfileDiv">
		<div class="editProfileData">
			<div class="avatarDiv">
				<img src="/resources/images/Verde.png" class = "avatarImage"></img>
				<button type="button" onClick="" class = "changeAvatarButton"> Change avatar</button>
			</div>
			<div class="currentProfileDiv">
				<p class="underline">Current profile</p>
				<p class="normalText">Username: ${user.username}</p>
			</div>
			<div class="statisticsDiv">
				<p class="underline">Statistics</p>
				<p class="normalText">Games won: ${gamesWon}</p>
				<p class="normalText">Games played: ${gamesPlayed}</p>
				<p class="normalText">Hours played: ${hoursPlayed}</p>
			</div>
		</div>
		<div class="editProfileMainFormDiv">
			<div class="editProfileFormTitle">
				<h1 class = "textCenter">Update your profile</h1>
			</div>
			<div class="profileFormDiv">
				<form:form modelAttribute="User" class ="editProfileForm" name="newUser"
					onsubmit="return validateForm()" action = "/users/${userId}">
					
					<c:if test="${param.error != null}">
                        <div style="margin-bottom: 15px;">
                            Failed to edit.
                            <c:if test="${SPRING_SECURITY_LAST_EXCEPTION != null}">
                                Reason: <c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}"/>
                            </c:if>
                        </div>
                    </c:if>
                    
					<label for="username" class="formLabel">New username (*)</label>
					<input type="text" id="username" class="inputText" name="username"
						placeholder="Your username.." required/>

					<label for="password" class="formLabel">New password (*)</label>
					<input type="password" id="password" class="inputText"
						name="password" placeholder="Your password.." required/>
					<i class="far fa-eye" id="togglePassword"></i>

					<label for="passwordConfirm" class="formLabel">Confirm new
						password (*)</label>
					<input type="password" id="passwordConfirm" class="inputText"
						name="passwordConfirm" placeholder=".." required>
					<i class="far fa-eye" id="togglePasswordConfirm"></i>
					
					<h5 class = "textCenter">(*) fields are mandatory</h5>
					
					<input type="submit" value="Save changes">
				</form:form>
			</div>
		</div>

	</div>
	<script>
		function validateForm() {
			var password = document.forms['newUser']['password'].value;
			var repeat = document.forms['newUser']['passwordConfirm'].value;
			if (password !== repeat) {
				alert("The password fields are not equal")
				return false;
			}
		}
	</script>

	<script>
		const togglePassword = document.querySelector('#togglePassword');
		const password = document.querySelector('#password');

		togglePassword.addEventListener('click', function(e) {
			// toggle the type attribute
			const type = password.getAttribute('type') === 'password' ? 'text'
					: 'password';
			password.setAttribute('type', type);
			// toggle the eye slash icon
			this.classList.toggle('fa-eye-slash');
		});

		const togglePasswordConfirm = document
				.querySelector('#togglePasswordConfirm');
		const passwordConfirm = document.querySelector('#passwordConfirm');

		togglePasswordConfirm
				.addEventListener(
						'click',
						function(e) {
							// toggle the type attribute
							const type = passwordConfirm.getAttribute('type') === 'password' ? 'text'
									: 'password';
							passwordConfirm.setAttribute('type', type);
							// toggle the eye slash icon
							this.classList.toggle('fa-eye-slash');
						});
	</script>
</monopoly:layout>