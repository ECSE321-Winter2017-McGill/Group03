
<html lang="en">

<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<meta name="description" content="" />
<meta name="author" content="" />
<title>View All Job Postings</title>
<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
<link rel="stylesheet"
	href="http://cdn.datatables.net/1.10.2/css/jquery.dataTables.min.css" />
<script type="text/javascript"
	src="http://cdn.datatables.net/1.10.2/js/jquery.dataTables.min.js"></script>
<link href="../css/bootstrap.min.css" rel="stylesheet" />
<link href="../css/main.css" rel="stylesheet" />
</head>

<body>
	<div id="wrapper">
		<div class="navbar-default sidebar" role="navigation">
			<div class="sidebar-nav navbar-collapse">

				<div class="navbar-header">
					<a class="navbar-brand" href="dashboard.php">TAMAS</a>
				</div>
				<h6 class="welcome">
					Welcome
					<?php echo $login_session; ?>
				</h6>
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
				</ul>
			</div>
		</div>
		<div id="page-wrapper">
			<div class="row">
				<div class="col-lg-12">
					<h1 class="page-header">View All Job Postings</h1>
				</div>
			</div>
			<div class="row">
				<div class="box box-info col-lg-12">
					<div class="box-header with-border"></div>
					<div class="box-body">
						<div class="table-responsive">

							<table id="myTable" class="table no-margin">
								<thead>
									<tr>
										<th data-field="fruit" data-sortable="true">Job Title</th>
										<th>Course ID</th>
										<th>Hour Required TA</th>
										<th>Hourly Rate</th>
										<th>Perferred Experience</th>
										<th>Submission Dead Line</th>
									</tr>
								</thead>
								<tbody>${result}

								</tbody>
							</table>
							<script>
$(document).ready(function(){
    $('#myTable').dataTable();
});
                            </script>
						</div>

						<span class="error"> ${viewAllJobPostingsError } </span>
					</div>
					<a class="btn btn-info" href="AddJobPosting.do">Add a Job
						Posting</a> <br />
					<p>
						<br />
					</p>
				</div>
			</div>
		</div>
	</div>
</body>
</html>