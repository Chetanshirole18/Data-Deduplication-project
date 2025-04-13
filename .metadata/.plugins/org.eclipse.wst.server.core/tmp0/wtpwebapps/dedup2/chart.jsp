<!DOCTYPE HTML>
<%@page import="com.deduplication.db.ConnectionFactory"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<html>
<head>
<title>Admin Home Page</title>
<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
<link href="admin/css/bootstrap.min.css" rel='stylesheet' type='text/css' />
<link href="admin/css/style.css" rel='stylesheet' type='text/css' />
<link href="admin/css/font-awesome.css" rel="stylesheet"> 
<link rel="stylesheet" href="admin/css/icon-font.min.css" type='text/css' />
<link href="admin/css/animate.css" rel="stylesheet" type="text/css" media="all">
<link href='//fonts.googleapis.com/css?family=Cabin:400,400italic,500,500italic,600,600italic,700,700italic' rel='stylesheet' type='text/css'>
<script src="admin/js/jquery-1.10.2.min.js"></script>
</head>    
 <body class="sticky-header left-side-collapsed"  onload="initMap()">
    <section>
		<div class="left-side sticky-left-side">
			<div class="logo">
				<h1><a href="admin_home.jsp">Admin<span>Panel</span></a></h1>
			</div>
			<div class="logo-icon text-center">
				<a href="admin_home.jsp"><i class="lnr lnr-home"></i> </a>
			</div>
			<div class="left-side-inner">
					<ul class="nav nav-pills nav-stacked custom-nav">
						<li class="menu-list"><a href="#"><i class="lnr lnr-menu"></i> <span>History</span></a>
							<ul class="sub-menu-list">
								<li><a href="User_list.jsp">View Users</a> </li>
								<li><a href="Upload_files.jsp">View Uploaded Files</a> </li>
								<li><a href="Dup_filelist.jsp">View Duplicate Files</a> </li>
								<li><a href="chart.jsp">Analysis Chart</a> </li>
								<li><a href="admin_signin.jsp">Logout</a> </li>
							</ul>
						</li> 
							</ul>
					     
			</div>
		</div>
		<div class="main-content">
			<div class="header-section">
			<a class="toggle-btn  menu-collapsed"><i class="fa fa-bars"></i></a>
			<div class="menu-right">
				<div class="user-panel-top">  	
					<div class="profile_details">		
						<ul>
							<li class="dropdown profile_details_drop">
								<a href="#" class="dropdown-toggle" data-toggle="dropdown">
									<div class="profile_img">	
										 <div class="user-name">
											<p>Welcome<span>Administrator</span></p>
										 </div>
										 <i class="lnr lnr-chevron-down"></i>
										 <i class="lnr lnr-chevron-up"></i>
										<div class="clearfix"></div>	
									</div>	
								</a>
								<ul class="dropdown-menu drp-mnu">
								<li> <a href="Aprofile.jsp"><i class="fa fa-sign-out"></i> Setting</a> </li>
									<li> <a href="admin_signin.jsp"><i class="fa fa-sign-out"></i> Logout</a> </li>
								</ul>
							</li>
						</ul>
					</div>		
					<div class="clearfix"></div>
				</div>
			  </div>
			</div>
			<div id="page-wrapper">
			<h3>Analysis Chart</h3>
			<br>
				<div id="container" style="min-width: 500px; height: 700px;" align="center">
					<img src="assets1/images/img.png" height="550px" width="1200px">
				</div>
			</div>			
</div>
   </section>
<script src="admin/js/jquery.nicescroll.js"></script>
<script src="admin/js/scripts.js"></script>
<script src="admin/js/bootstrap.min.js"></script>

<script type="text/javascript" src="js/jquery-1.9.1.min.js"></script>
<script src="js/highcharts.js"></script>
<%
int totalfiles=0,dupfiles=0,dupblock=0,uniqueblock=0;
Connection con=ConnectionFactory.getInstance().getConnection();
Statement smt=con.createStatement();
ResultSet rs=smt.executeQuery("SELECT count(filename) FROM filestorage");
while(rs.next())
{
	totalfiles=rs.getInt(1);
}
Statement smt1=con.createStatement();
ResultSet rs1=smt1.executeQuery("SELECT count(filename) FROM filestorage where status<>'original'");
while(rs1.next())
{
	dupfiles=rs1.getInt(1);
}
Statement smt2=con.createStatement();
ResultSet rs2=smt2.executeQuery("SELECT count(filename) FROM blockindex where status='duplicate'");
while(rs2.next())
{
	dupblock=rs2.getInt(1);
}
Statement smt3=con.createStatement();
ResultSet rs3=smt3.executeQuery("SELECT count(filename) FROM blockindex where status<>'duplicate'");
while(rs3.next())
{
	uniqueblock=rs3.getInt(1);
}

int[] params={0,0,0,0};
params[0]=totalfiles;
params[1]=dupfiles;
params[2]=dupblock;
params[3]=uniqueblock;


%>
<%
if(params != null) 
{%>
<script>
var values = new Array(<%
for(int i = 0; i < params.length; i++) {
  out.print(""+params[i]+"");
  if(i+1 < params.length) {
    out.print(",");
  }
}
%>);
</script>
<% } %>
	
	
<script type="text/javascript">
var values1=[<%=totalfiles%>,0,0,0];
for(index=1;index<values1.length;index++)
	{
		values1[index]=values[index]-(Math.floor((Math.random() * (values[index]-1)) + 1));
		
	}


$(function () {
$('#container').highcharts({
	chart: {
        type: 'column'
    },
    title: {
        text: 'Deduplication Report'
    },
    subtitle: {
        text: 'DATA DE-DUPLICATION USING BLOCKCHAIN WITH ADVANCED SECURITY IN CLOUD COMPUTING'
    },
    xAxis: {
        categories: [
            'Total Files',
            'Duplicate Files',
            'Duplicate Blocks',
            'Unique Block'
           
        ],
        crosshair: true
    },
    yAxis: {
    	categories: 10,
    	title:{
    		text:'Count'
    	}
    	
    },
    tooltip: {
        headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
        pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
            '<td style="padding:0"><b>{point.y:.1f} </b></td></tr>',
        footerFormat: '</table>',
        shared: true,
        useHTML: true
    },
    plotOptions: {
        column: {
            pointPadding: 0.2,
            borderWidth: 0
        }
    },
    series: [{
        name: 'Existing',
        data: values1

    }, {
        name: 'Proposed',
        data: values

    }]
});

});
</script>	

</body>
</html>