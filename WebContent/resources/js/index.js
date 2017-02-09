var isRegister = false;
var user = undefined;

function navToHome() {
	if (this.readyState === 4 && this.status === 200) {
		window.location.href += ("home.jsp?id=" + this.responseText);
	}
}
function statusChangeCallback(response) {
	if (isRegister) {
		isRegister = false;
		return;
	}
	console.log(response);
	if (response.status === 'connected') {
		console.log("User is logged and has granted access to the application.");
		var xhr = new XMLHttpRequest();
		xhr.open('POST', 'login', true);
		xhr.setRequestHeader("Content-Type", "application/json");
		xhr.onreadystatechange = navToHome;
		xhr.send(JSON.stringify({
			facebookUserId : response.authResponse.userID,
			accessToken : response.authResponse.accessToken
		}));
	} else if (response.status === 'not_authorized') {
		console.log("User is logged in facebook but has not authorized the application");
	} else {
		console.log("User is not logged in facebook. Needs to log to get the information");
	}
}

function facebookLogin() {
	isRegister = true;
	FB.login(function(response) {
		console.log(response);
		if (response.authResponse) {
			console.log('User is authenticated');
			var accessToken = response.authResponse.accessToken;
			FB.api('/me', {
				fields : 'first_name, last_name, email'
			}, function(response) {
				console.log(response);
				var xhr = new XMLHttpRequest();
				xhr.open('POST', 'register', true);
				xhr.setRequestHeader("Content-Type", "application/json; charset=utf-8");

				xhr.onreadystatechange = navToHome;
				xhr.send(JSON.stringify({
					firstName : response.first_name,
					lastName : response.last_name,
					email : response.email,
					facebookUserId : response.id,
					accessToken : accessToken
				}));
			});
		} else {
			console.log('User cancelled login or did not fully authorize.');
		}
	}, {
		scope : 'public_profile, email, user_managed_groups',
		return_scopes : true
	});
}

function changeActive(id, isActivate) {
	var element = document.getElementById(id);
	if (isActivate) {
		element.className += ' active';
	} else {
		element.className = element.className.replace(' active', '');
	}
}

function goToRegistration() {
	document.getElementById('register').style.display = 'block';
	document.getElementById('login').style.display = 'none';
	changeActive('loginTab', false);
	changeActive('registerTab', true);
}

function goToLogin() {
	document.getElementById('login').style.display = 'block';
	document.getElementById('register').style.display = 'none';
	changeActive('loginTab', true);
	changeActive('registerTab', false);
}