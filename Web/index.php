<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<meta http-equiv="X-UA-Compatible" content="IE=10.000" />
	<title>TAMAS Log In</title>

	<link rel="stylesheet" type="text/css" href="./css/style.css" />
	<style>
        .illustrationClass {
            background-image: url(./img/mcimg.jpg);
        }
    </style>
</head>

<body dir="ltr" class="body">
	<?php
	session_start ();
    ?>
	<div id="fullPage">
		<div id="brandingWrapper" class="float">
			<div id="branding" class="illustrationClass"></div>
		</div>
		<div id="contentWrapper" class="float">
			<p>
				<br />
			</p>
			<div id="content">
				<div id="header">
					<img class="logoImage" src="./img/mclogo.png" alt="McGill University" />
				</div>
				<div id="workArea">
					<div id="authArea" class="groupMargin">
						<div id="loginArea">
							<p>
								<br />
								<br />
							</p>
							<div id="loginMessage" class="groupMargin">Log in:</div>
							<form role="form" action="pages/login.php" method="post" id="loginForm" autocomplete="off">
								<div id="error" class="fieldMargin error smallText" style="display: none;">
									<label id="errorText" for=""></label>
								</div>
								<div id="formsAuthenticationArea">
									<div id="userNameArea">
										<input id="userNameInput" name="username" type="text" value="" tabindex="1" class="text fullWidth" spellcheck="false" placeholder="Default name: test"
											autocomplete="off" />
									</div>

									<div id="passwordArea">
										<input id="passwordInput" name="password" type="password" tabindex="2" class="text fullWidth" placeholder="Default Pasword: password"
											autocomplete="off" />
									</div>
									<input type="submit" id="submitButton" class="submit" value="Log in" />
								</div>
							</form>
							<span class="error">
								<?php
								if (isset ( $_SESSION ['loginError'] ) && !empty ( $_SESSION ['loginError'] )) {//display error messages.
									echo "*" . $_SESSION ['loginError'];
								}
                                ?>
							</span>
						</div>

						<div id="introduction" class="groupMargin">
							<p>
								Please sign in with your Username and Password.
								<br />
								<br />
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>