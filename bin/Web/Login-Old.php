<!-- This is a log in page -->
<html>

<head>
	<title>TAMAS Login</title>
	<link rel="stylesheet" href="css/main.css" />
	<link rel="stylesheet" href="css/bootstrap.min.css" />
</head>

<body>
	<?php
	session_start ();
    ?>
	<section id="login">
		<div class="container">
			<div class="row">
				<div class="col-xs-12">
					<div class="form-wrap">
						<h1>Log in with your name</h1>
						<form role="form" action="pages/login.php" method="post" id="login-form" autocomplete="off">
							<div class="form-group">
								<label for="name" class="sr-only">Name</label>
								<input type="text" name="username" class="form-control" placeholder="Default name: test" />
							</div>
							<div class="form-group">
								<label for="key" class="sr-only">Password</label>
								<input type="password" name="password" id="password" class="form-control" placeholder="Default Pasword: password" />
							</div>
							<div class="checkbox">
								<span class="character-checkbox" onclick="showPassword()"></span>
								<span class="label">Show password</span>
							</div>
							<input type="submit" id="btn-login" class="btn btn-custom btn-lg btn-block" value="Log in" />
						</form>
						<p class="error">
							<span class="error">
								<?php
								if (isset ( $_SESSION ['loginError'] ) && !empty ( $_SESSION ['loginError'] )) {//display error messages.
									echo "*" . $_SESSION ['loginError'];
								}
                                ?>
							</span>
						</p>
					</div>
					<hr />
				</div>
			</div>
		</div>
	</section>
	<script type="text/javascript" src="js/showPassword.js"></script>
	<script type="text/javascript" src="js/jquery-3.1.1.js"></script>
</body>
</html>