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