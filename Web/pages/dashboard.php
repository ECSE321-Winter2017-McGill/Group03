<?php
include ('session.php');
?>
<html lang="en">

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<title>DashBoard</title>
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
				<h6 class="welcome">Welcome
                            <?php echo $login_session; //say welcome to logged in user?> 
                        </h6>
				<h6 class="welcome">
					<a href="logout.php">Sign Out</a>
				</h6>
				<ul class="nav" id="side-menu">
					<li><a href="dashboard.php"><i class="fa fa-dashboard fa-fw"></i>
							Dashboard</a></li>
					<li><a href="jobPostings.php"><i class="fa fa-dashboard fa-fw"></i>
							Add Job Postings</a></li>
					<!-- 					<li><a href="courses.php"><i class="fa fa-dashboard fa-fw"></i> Job -->
					<!-- <!-- 							Postings</a></li> -->
				</ul>
			</div>
		</div>
		<div id="page-wrapper">
			<div class="row">
				<div class="col-lg-12">
					<h1 class="page-header">Dashboard</h1>
				</div>
			</div>
			<div class="row">
				<div class="box box-info col-lg-12">
					<div class="box-header with-border">
						<h3 class="box-title">Overview</h3>
					</div>
					<div class="box-body">
						<div class="table-responsive">
							<table class="table no-margin">
								<thead>
									<tr>
										<th>Course ID</th>
										<th>Course Name</th>
										<th>TA (Current/Needed)</th>
										<th>Grader (Current/Needed)</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td><a href="jobPostings.php">COMP 250</a></td>
										<td>Intro to Computer Science</td>
										<td><span class="label label-success">2/5</span></td>
										<!--<td><p>2/5</p></td>-->
										<td><span class="label label-success">2/5</span> <!--<div class="sparkbar" data-color="#00a65a" data-height="20">90,80,90,-70,61,-83,63</div>-->
										</td>
									</tr>
									<tr>
										<td><a href="jobPostings.php">ECSE 200</a></td>
										<td>Electrical Circuits 1</td>
										<td><span class="label label-warning">0/3</span></td>
										<!--<td><p>2/5</p></td>-->
										<td><span class="label label-info">1/3</span> <!--<p>2/5</p>-->
											<!--<div class="sparkbar" data-color="#f39c12" data-height="20"> <td><p>2/5</p></td></div>-->
										</td>
									</tr>
									<tr>
										<td><a href="jobPostings.php">ECSE 321</a></td>
										<td>Intro. to Soft.Eng</td>
										<td><span class="label label-danger">5/5</span></td>
										<td>
											<!--<div class="sparkbar" data-color="#f56954" data-height="20">90,-80,90,70,-61,83,63</div>-->
											<span class="label label-info">1/3</span>
										</td>
									</tr>
									<tr>
										<td><a href="jobPostings.php">ECSE 221</a></td>
										<td>Intro. to Comp.Eng</td>
										<td><span class="label label-info">1/3</span></td>
										<td>
											<!--<div class="sparkbar" data-color="#00c0ef" data-height="20">90,80,-90,70,-61,83,63</div>-->
											<span class="label label-info">1/3</span>
										</td>
									</tr>

								</tbody>
							</table>
						</div>
					</div>
				</div>


			</div>
			<div class="box-footer clearfix">
				<a href="javascript:void(0)"
					class="btn btn-sm btn-info btn-flat pull-left">Add New Courses</a>
				<a href="javascript:void(0)"
					class="btn btn-sm btn-default btn-flat pull-right">View All Courses</a>
			</div>
		</div>
	</div>


</body>

</html>