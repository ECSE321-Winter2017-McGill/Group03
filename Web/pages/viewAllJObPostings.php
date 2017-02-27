<?php
include ('session.php');
require_once __DIR__ . '\..\model\ManagementSystem.php';
require_once __DIR__ . '\..\model\JobPosting.php';
require_once __DIR__ . '\..\model\Course.php';
require_once __DIR__ . '\..\persistence\PersistenceTAMAS.php';
$pm = new PersistenceTAMAS ();
$rm = $pm->loadDataFromStore ();
?>
<html lang="en">

<head>


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
                            <?php echo $login_session; ?>
                        </h6>
				<h6 class="welcome">
					<a href="logout.php">Sign Out</a>
				</h6>
				<ul class="nav" id="side-menu">
					<li><a href="dashboard.php"><i class="fa fa-dashboard fa-fw"></i>
							Dashboard</a></li>
					<li><a href="jobPostings.php"><i class="fa fa-dashboard fa-fw"></i>
							All Job Postings</a></li>
					<!-- 					<li><a href="courses.php"><i class="fa fa-dashboard fa-fw"></i> Job -->
					<!-- 							Postings</a></li> -->
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


						<!-- HourRate () . " " . $jobPostings->getPerferredExperience () . " " . $jobPostings->getSubmissionDeadline () . "</p>"; -->

						<div class="table-responsive">
							<table class="table no-margin">
								<thead>
									<tr>
										<th>Job Title</th>
										<th>Course ID</th>
										<th>Hour Required TA</th>
										<th>Hourly Rate</th>
										<th>Perferred Experience</th>
										<th>Submission Dead Line</th>
									</tr>
								</thead>
								<tbody>
								<?php
							
							foreach ( $rm->getJobPostings () as $jobPostings ) {
								// <thead>
								// <tr>
								// <td>Job Title</th>
								// <td>Course ID</th>
								// <td>Hour Required TA</th>
								// <td>Hourly Rate</th>
								// <td>Perferred Experience</th>
								// <td>Submission Dead Line</th>
								// </tr>
								// </thead>
								echo "<tr>";
								echo "<td>" . $jobPostings->getJobTitle () . " </td>";
								echo "<td>" . $jobPostings->getCourse ()->getCourseCoude () . " </td>";
								echo "<td>" . $jobPostings->getCourse ()->getHourRequiredTa () . " </td>";
								echo "<td>" . $jobPostings->getHourRate () . " </td>";
								echo "<td>" . $jobPostings->getPerferredExperience () . " </td>";
								echo "<td>" . $jobPostings->getSubmissionDeadline () . " </td>";
								echo "</tr>";
							}
							?> 

								</tbody>
							</table>
						</div>
					
					
					
					
					
					
					
							<?php
							
// 							foreach ( $rm->getJobPostings () as $jobPostings ) {
// 								// <thead>
// 								// <tr>
// 								// <td>Job Title</th>
// 								// <td>Course ID</th>
// 								// <td>Hour Required TA</th>
// 								// <td>Hourly Rate</th>
// 								// <td>Perferred Experience</th>
// 								// <td>Submission Dead Line</th>
// 								// </tr>
// 								// </thead>
// 								echo "<tbody>";
// 								echo "<td>" . $jobPostings->getJobTitle () . " </td>";
// 								echo "<td>" . $jobPostings->getCourse ()->getCourseCoude () . " </td>";
// 								echo "<td>" . $jobPostings->getCourse ()->getHourRequiredTa () . " </td>";
// 								echo "<td>" . $jobPostings->getHourRate () . " </td>";
// 								echo "<td>" . $jobPostings->getPerferredExperience () . " </td>";
// 								echo "<td>" . $jobPostings->getSubmissionDeadline () . " </td>";
// 								echo "</tbody>";
// 							}
							?> <span class="error">
			<?php
			if (isset ( $_SESSION ['error'] ) && ! empty ( $_SESSION ['error'] )) {
				echo "*" . $_SESSION ["error"];
			}
			?>
			</span>
					</div>
					<a class="btn btn-info" href="jobPostings.php">Add a Job Posting</a>
				</div>


			</div>

		</div>
	</div>
</body>

</html>