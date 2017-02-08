<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>FacebookApp - Login</title>
	<link rel="stylesheet" href="resources/css/style.css" type="text/css">
</head>
<body>
	<script>
		window.fbAsyncInit = function() {
			FB.init({
				appId : '267142873716474',
				cookie : true,
				xfbml : true,
				version : 'v2.8'
			});
			FB.getLoginStatus(function(response) {
				statusChangeCallback(response);
			});
		};
		(function(d, s, id) {
			var js, fjs = d.getElementsByTagName(s)[0];
			if (d.getElementById(id))
				return;
			js = d.createElement(s);
			js.id = id;
			js.src = "//connect.facebook.net/en_US/sdk.js";
			fjs.parentNode.insertBefore(js, fjs);
		}(document, 'script', 'facebook-jssdk'));
	</script>
	<div class="form">

		<ul class="tab-group">
			<li class="tab active" id="registerTab"><a onclick="goToRegistration();return false;">Register</a></li>
			<li class="tab" id="loginTab"><a onclick="goToLogin();return false;">Log In</a></li>
		</ul>

		<div class="tab-content">
			<div id="register">
				<h1>Sign Up for Free</h1>
				<h2><font color="red"><%=request.getAttribute("errorMessage") == null ? "" : request.getAttribute("errorMessage")%></font></h2>
				<form action="/FacebookApp/register" method="post">
					<div class="top-row">
						<div class="field-wrap">
							<input type="text" name="firstName" required placeholder="First Name*"/>
						</div>
						<div class="field-wrap">
							<input type="text" name="lastName" required placeholder="Last Name*"/>
						</div>
					</div>
					<div class="field-wrap">
						<input type="email" name="email" required placeholder="Email Address*" />
					</div>
					<div class="field-wrap">
						<input type="password" name="password" required placeholder="Enter password*"/>
					</div>
					<div class="field-wrap">
						<input type="password" name="facebookToken" required placeholder="Facebook Token*"/>
					</div>
					<button type="submit" class="button button-block">Get Started</button>
				</form>
			</div>
			<div id="login" style="display:none">
				<h1>Welcome Back!</h1>
				<form action="/login" method="post">
					<div class="field-wrap">
						<input type="email" required placeholder="Email Address*" />
					</div>
					<div class="field-wrap">
						<input type="password" required placeholder="Enter password*"/>
					</div>
					<button type="submit" class="button button-block login-button" >Log In</button>
					<button class="button button-block login-button" onclick="facebookLogin();return false;">Log In with Facebook</button>
					<button class="button button-block" >Forgot Password?</button>
				</form>
			</div>
		</div>
	</div>
	<script src="resources/js/index.js"></script>
</body>
</html>