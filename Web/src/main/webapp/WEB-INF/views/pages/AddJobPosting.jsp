<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<title>DashBoard</title>
<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
<link href="../css/bootstrap.min.css" rel="stylesheet">
<link href="../css/main.css" rel="stylesheet">
</head>

<body>
	<div id="wrapper">
		<div class="navbar-default sidebar" role="navigation">
			<div class="sidebar-nav navbar-collapse">

				<div class="navbar-header">
					<a class="navbar-brand" href="dashboard.php">TAMAS</a>
				</div>
				<h6 class="welcome">Welcome</h6>
				<h6 class="welcome">
					<a href="logout.php">Sign Out</a>
				</h6>
				<ul class="nav" id="side-menu">
					<li><a href="Dashboard.do"><i
							class="fa fa-dashboard fa-fw"></i> Dashboard</a></li>
					<li><a href="ViewAllJobPosting.jsp"><i
							class="fa fa-dashboard fa-fw"></i> View Job Postings</a></li>
					<li><a href="EvalTa.jsp"><i class="fa fa-dashboard fa-fw"></i>
							TA Evaluaion</a></li>
					<li><a href="viewAllApplication.jsp"><i
							class="fa fa-dashboard fa-fw"></i> View Application</a></li>
				</ul>
			</div>
		</div>
		<div id="page-wrapper">
			<div class="row">
				<div class="col-lg-12">
					<h1 class="page-header">Add Job Postings</h1>
				</div>
			</div>
			<div id="mydiv" class="row">
				<div class="box box-info col-lg-12">
					<div class="box-header with-border"></div>
					<div class="box-body">

						<form id="addjob">
							<label>Select a course:</label> <span class="error"> </span> <select
								name="course" class="form-control" id=""> ${courses}
							</select> <br>

							<p>
								<label>Job Title: </label> <span class="error"> </span> <select
									name="jobTitle" class="form-control" id="">
									<option value="TA">TA</option>
									<option value="Grader">Grader</option>
								</select>
							</p>

							<p>
								<label>Dead Line: </label> <input class="form-control"
									type="date" name="deadLine" value="" /> <span class="error">

								</span>
							</p>
							<div>
								<label>Perferred Experience: </label>
								<textarea class="form-control" name="perferredExperience"></textarea>
								<span class="error"> </span>
							</div>

							<p>
								<label>Hourly Rate: </label> <input class="form-control"
									type="text" name="hourlyRate" value="" /> <span class="error">

								</span>
							</p>
							<p>
								<input class="btn btn-sm btn-info btn-flat pull-left"
									type="submit" value='Publish' />
						</form>

						<br> <br> <br>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script>
		$("#addjob").submit(function(e) {
			var url = "AddJobPosting.do";
			$.ajax({
				type : "POST",
				url : url,
				data : $("#addjob").serialize(),
				success : function(data) {
					if (data.search("success") != -1) {
						window.location.replace('viewAllJobPostings.php');
					}
				}
			});
			$("#mydiv").load(location.href + " #mydiv");
		});
	</script>
</body>
</html>