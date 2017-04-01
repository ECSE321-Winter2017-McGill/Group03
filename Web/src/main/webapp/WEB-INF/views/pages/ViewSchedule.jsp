<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
<link rel="stylesheet"
	href="http://cdn.datatables.net/1.10.2/css/jquery.dataTables.min.css" />
<script type="text/javascript"
	src="http://cdn.datatables.net/1.10.2/js/jquery.dataTables.min.js"></script>
<link href="../css/bootstrap.min.css" rel="stylesheet" />
<link href="../css/main.css" rel="stylesheet" />
<link href='../css/fullcalendar.min.css' rel='stylesheet' />
<link href='../css/fullcalendar.print.min.css' rel='stylesheet'
	media='print' />
<script src='../lib/moment.min.js'></script>
<script src='../lib/jquery-ui.min.js'></script>
<script src='../js/fullcalendar.min.js'></script>

<script>
	var eventlist = [ {
		id : 1,
		title : 'ECSE321 TA: Tim',
		start : '2017-04-06'
	}, {
		id : 1,
		title : 'ECSE321 TA: Tim',
		start : '2017-04-13'
	}, {
		id : 1,
		title : 'ECSE321 TA: Tim',
		start : '2017-04-20'
	}, {
		id : 1,
		title : 'ECSE321 TA: Tim',
		start : '2017-04-27'
	}, {
		id : 2,
		title : 'ECSE321 TA: Jack',
		start : '2017-04-04'
	}, {
		id : 2,
		title : 'ECSE321 TA: Jack',
		start : '2017-04-11'
	}, {
		id : 2,
		title : 'ECSE321 TA: Jack',
		start : '2017-04-18'
	}, {
		id : 2,
		title : 'ECSE321 TA: Jack',
		start : '2017-04-25'
	}

	]; //array of events in json format
	$(document).ready(function() {

		$('#calendar').fullCalendar({
			defaultDate : '2017-04-12',
			editable : true,
			eventLimit : true, // allow "more" link when too many events
			events : eventlist
		});

	});
</script>
<!-- function saveEvent(event) {
		window.alert(event);
		$.ajax({
			url : 'Schedule.jsp',
			type : 'post',
			data : {
				event : event
			},
			dataType : 'json',
			success : function(response) {
				console.log('response');
			}
		}); -->
<style>
body {
	padding: 0;
	font-family: "Lucida Grande", Helvetica, Arial, Verdana, sans-serif;
	font-size: 14px;
}

.btn {
	margin: 10px 0 0 10px;
}

#calendar {
	max-width: 900px;
	margin: 0 auto;
}
</style>
</head>
<body>
<body>
	<div id="wrapper">
		<div class="navbar-default sidebar" role="navigation">
			<div class="sidebar-nav navbar-collapse">

				<div class="navbar-header">
					<a class="navbar-brand" href="Dashboard.do">TAMAS</a>
				</div>
				<h6 class="welcome">Welcome ${name}</h6>
				<h6 class="welcome">
					<a href="logout.do">Sign Out</a>
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
					<li><a href="Schedule.jsp"><i
							class="fa fa-dashboard fa-fw"></i> TA Schedule</a></li>



				</ul>
			</div>
		</div>
		<div id="page-wrapper">
			<div class="row">
				<div class="col-lg-12">
					<h1 class="page-header">TA Evaluaion</h1>
				</div>
			</div>
			<div class="row">
				<div class="box box-info col-lg-12">
					<div class="box-header with-border"></div>
					<div class="box-body">
						<div class="mydiv">
							<div id='calendar' name='js'></div>
							<br> <a> </a><input
								class="btn btn-sm btn-info btn-flat pull-left" type="submit"
								value='Submit Changes' /> <br />
						</div>

						<p>
							<br />
						</p>
					</div>
				</div>
			</div>
		</div>
	</div>




	</form>
</body>
</html>
