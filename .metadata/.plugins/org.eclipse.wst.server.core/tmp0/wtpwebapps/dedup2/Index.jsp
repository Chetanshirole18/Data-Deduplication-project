<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Deduplication</title>
	<link rel="favicon" href="assets1/images/favicon.png">
	<link rel="stylesheet" media="screen" href="http://fonts.googleapis.com/css?family=Open+Sans:300,400,700">
	<link rel="stylesheet" href="assets1/css/bootstrap.min.css">
	<link rel="stylesheet" href="assets1/css/font-awesome.min.css">
	<link rel="stylesheet" href="assets1/css/bootstrap-theme.css" media="screen">
	<link rel="stylesheet" type="text/css" href="assets1/css/da-slider.css" />
	<link rel="stylesheet" href="assets1/css/style.css">
	<script src="assets1/js/html5shiv.js"></script>
	<script src="assets1/js/respond.min.js"></script>
</head>
<body>
	<div class="navbar navbar-inverse" style="background-color: black;">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse"><span class="icon-bar"></span><span class="icon-bar"></span><span class="icon-bar"></span></button>
				<a class="navbar-brand" href="Index.jsp">
				<h4>DEDUPLICATION</h4>
				</a>	
			</div>
			<div class="navbar-collapse collapse">
				<ul class="nav navbar-nav pull-right mainNav">
					<li><a href="Index.jsp">Home</a></li>
					<li><a href="user_signin.jsp">User</a></li>
					<li><a href="admin_signin.jsp">Admin</a></li>
					<li><a href="contact.jsp">Contact</a></li>

				</ul>
			</div>
			<!--/.nav-collapse -->
		</div>
	</div>
	<!-- /.navbar -->

	<!-- Header -->
	<header id="head">
		<div class="container">
			<div class="banner-content">
				<div id="da-slider" class="da-slider">
					<div class="da-slide">
						<h2>Data Storage Site</h2>
						<p>Store Data without Duplication</p>
						<div class="da-img"></div>
					</div>
					<div class="da-slide">
						<h2>Secured site</h2>
						<p>Data Storage is secured..</p>
						<div class="da-img"></div>
					</div>
				</div>
			</div>
		</div>
	</header>
	<!-- /Header -->


	<div id="courses">
		<div class="container">
			<h2>About Us</h2>
			<p align="justify">Data deduplication is a technique for eliminating duplicate copies of data, and has been widely used in cloud storage to
reduce storage space and upload bandwidth. However, there is only one copy for each file stored in cloud even if such a file is owned
by a huge number of users. As a result, deduplication system improves storage utilization while reducing reliability. Furthermore,
the challenge of privacy for sensitive data also arises when they are outsourced by users to cloud. Aiming to address the above
security challenges, we makes the first attempt to formalize the notion of distributed reliable deduplication system. We propose
new distributed deduplication systems with higher reliability in which the data chunks are distributed across multiple cloud servers.
The security requirements of data confidentiality and tag consistency are also achieved by introducing a deterministic secret sharing
scheme in distributed storage systems, instead of using convergent encryption as in previous deduplication systems.</p>
	</div>
	</div>
	<!-- container -->
	<section class="container">
		<div class="heading">
			<!-- Heading -->
			<h2>Our Features</h2>
		</div>
		<div class="row">
			<div class="col-md-4">
				<img src="assets1/images/1.png" alt="" class="img-responsive">
			</div>
			<div class="col-md-8">
				<p align="justify">New secure deduplication systems are proposed to provide efficient deduplication with high reliability for file-level and block-level deduplication.</p>
				<p align="justify">The secret splitting technique, instead
of traditional encryption methods, is utilized
to protect data confidentiality. Specifically, data are
split into fragments by using secure secret sharing
schemes and stored at different servers.</p>
				<p align="justify">In
more details, confidentiality, reliability and integrity
can be achieved in our proposed system.</p>
				<p align="justify">Two kinds
of collusion attacks are considered in our solutions.
These are the collusion attack on the data and the
collusion attack against servers.</p>

				<blockquote class="blockquote-1">
				<cite title="Source Title"><b>Problem Statement</b></cite>
					<p>To remove the duplicated copies of data in cloud storage considering security parameters.</p>
				</blockquote>
			</div>
		</div>
	</section>
	<footer id="footer">
		<div class="container">
			<div class="social text-center">
				<a href="#"><i class="fa fa-twitter"></i></a>
				<a href="#"><i class="fa fa-facebook"></i></a>
				<a href="#"><i class="fa fa-dribbble"></i></a>
				<a href="#"><i class="fa fa-flickr"></i></a>
				<a href="#"><i class="fa fa-github"></i></a>
			</div>

			<div class="clear"></div>
			<!--CLEAR FLOATS-->
		</div>
		<div class="footer2">
			<div class="container">
					<div class="col-md-6 panel">
						<div class="panel-body">
							<p class="text-right">
								Copyright &copy; 2022-23.
							</p>
						</div>
					</div>

				</div>
				<!-- /row of panels -->
			</div>
	</footer>

	<!-- JavaScript libs are placed at the end of the document so the pages load faster -->
	<script src="assets1/js/modernizr-latest.js"></script>
	<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
	<script src="http://netdna.bootstrapcdn.com/bootstrap/3.0.0/js/bootstrap.min.js"></script>
	<script src="assets1/js/jquery.cslider.js"></script>
	<script src="assets1/js/custom.js"></script>
</body>
</html>
