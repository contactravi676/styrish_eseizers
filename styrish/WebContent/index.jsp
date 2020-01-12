<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="s" uri="/struts-tags" %>

<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="css/bootstrap.min.css"><link rel="stylesheet" href="css/animate.css">

 <link href="css/font-awesome.css" rel="stylesheet">
 <link rel="stylesheet" href="css/style.css">
 <link rel="stylesheet" href="css/explore.css">
 <script src="js/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/tooltip.js"></script>
<script src="js/modal_validation.js"></script>
 <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
 <style>
 </style>

</head>
<body onload="k()">

<div class="top container jumbotron"><br style="clear:both">
<nav class=""  style="margin:0 0 1px 0;padding:0;border:none;" id="firstmenu1" >
  <div class="container">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>                        
      </button>
 
    </div>
    <div class="collapse navbar-collapse" id="myNavbar" >
      <ul class="nav navbar-nav" style="background" >
		<li class="dropdown"><span class="webname"><img src="img/logop.png" style="width:110px;height:auto"></span></li>
		 <li class="dropdown">
          <a class="dropdown-toggle" data-toggle="dropdown" href="" onmouseover="stop();" onmouseout="k1();">ALL EXAMS <span class="caret"></span></a>
			<ul class="dropdown-menu" style="margin-left:-100px;"  onmouseover="stop();" onmouseout="k1();">
				 <li  >
<section class="container sub" >
 
<div class="row "> 
  <div class="col-md-3">
	<h4 class="w3-bar-item " style="background:white" onclick="openCity('railways')">Railways</h4>
	<h4 class="w3-bar-item" onclick="openCity('ssc')">SSC</h4>
  </div>
	<div class="col-md-9"  >
		<div id="railways" class="w3-container city" >
			<h4 align="center">Railways</h4>
			<div class="row" align="center">
				<div class="col-md-3" >
					<h4>Head 1</h4>
					<a href="">1.1</a><br>
					<a href="">1.2</a><br>
							
				</div>
				<div class="col-md-3">
					<h4>Head 2</h4>
					<a href="">2.1</a><br>
					<a href="">2.2</a><br>
						
				</div>
				
				<div class="col-md-3">
					<h4>Head 3</h4>
					<a href="">3.1</a><br>
					<a href="">3.2</a><br>
							
				</div>
			</div>
		</div>
		
	</div>
	<div class="col-md-9" >
		<div id="ssc" class="w3-container city" style="display:none">
			<h4 align="center">SSC</h4>
			<div class="row" align="center">
				<div class="col-md-3" >
					<h4>Head 1</h4>
					<a href="">1.1</a><br>
					<a href="">1.2</a><br>
							
				</div>
				<div class="col-md-3">
					<h4>Head 2</h4>
					<a href="">2.1</a><br>
					<a href="">2.2</a><br>
							
				</div>
				
				<div class="col-md-3">
					<h4>Head 3</h4>
					<a href="">3.1</a><br>
					<a href="">3.2</a><br>
						
				</div>
			</div>
		</div>
	</div>
</div>
</section>
<script>
function openCity(cityName) {
  var i;
  var x = document.getElementsByClassName("city");
  for (i = 0; i < x.length; i++) {
    x[i].style.display = "none";  
  }
  document.getElementById(cityName).style.display = "block";  
}
</script>			 
				</li>
			</ul>
        </li>
		
		<li class="dropdown">
          <a class="dropdown-toggle" data-toggle="dropdown" href="">STUDY MATERIAL<span class="caret"></span></a>
			<ul class="dropdown-menu">
				  <li><a href="#">demo1</a></li>
				<li><a href="##">demo2</a></li>
			</ul>
        </li>
		
      </ul>
<ul class="nav navbar-nav navbar-right">
<li class="dropdown">
          <a  class="dropdown-toggle btn" data-toggle="dropdown" href="" style="background:#eee">All<span class="caret"></span></a>
			<ul class="dropdown-menu" style="margin-right:-100px">
				  <li><a href="#">demo1</a></li>
				<li><a href="##">demo2</a></li>
			</ul>
</li>
<li class="dropdown" >
	
<div class="right-inner-addon" >
	<i class="fa fa-search"></i>
	<input type="text" class="form-control" placeholder="Search Video Course, Test Series and More.." style="width:400px">
</div>

</li>
	  <li>&nbsp;&nbsp;&nbsp;<button type="button" class="btn loginbtn" data-toggle="modal" data-target="#myModal" style="margin-top:4%" onclick="stop();">Login/Signup</button></li>
	  
      </ul>
    </div>
  </div>
</nav>
<!-- /Menu -->
 
<!--Menu For Mobile -->
<section class="container" id="firstmenu2">
<div class="row " >
	<div class="col-xs-6 col-md-6 col-sm-6" align="left">
		<span class="webname"><img src="img/logop.png" style="width:110px;height:auto"></span>
	</div>
	<div class="col-xs-6 col-md-6 col-sm-6" >
		<button type="button" class="btn loginbtn" data-toggle="modal" data-target="#myModal" onclick="stop();" style="float:right;margin-top:2%">Login/Signup</button>
	  
	</div>
	
</div>
</section>
<!--<span class="webname">

<img src="img/logop.png" style="width:110px;height:auto"></span>
<button type="button" class="btn loginbtn" data-toggle="modal" data-target="#myModal">Login/Signup</button>-->
<%@ include file = "mainMenu.jsp"%>
<br style="clear:both">

     
	<div class="row jumbotron" id="content-desktop">
	
		<div class="col-md-5 ">
			<p style="color:#ff7700">Bright Future with Esiezers</p>
			<h1 style="font-size: 48px">40000+ Job Selections</h1>
			<p>India's No one E-learning platform</p>
			<button class="btn  vidbtn" class="exp" onclick="location.href='homePage.jsp'">
			Explore Courses</button>
			<button class="btn  vidbtn" class="exp" onclick="smoothScroll(document.getElementById('second'))">
			Know More</button>
			
		</div>
	
		<div class="col-md-7" >
			<div class="row" >
			<div class="col-md-2 col-sm-0"></div><div class="col-md-2 col-sm-0"></div>
		<div class="col-md-2 col-sm-2"><img class="a" src="img/stu1.png" height="300px" title="I became c!" data-toggle="t1"></div>	
		<div class="col-md-2 col-sm-2"><img class="b" src="img/stu2.png" height="300px" title="I became g!" data-toggle="t2"></div>
		<div class="col-md-2 col-sm-2"><img class="c" src="img/stu3.png" height="300px" title="I became c!" data-toggle="t3"></div>
		<div class="col-md-2 col-sm-2"><img class="d" src="img/stu4.png" height="300px" title="I became c!" data-toggle="t4"></div>
			</div>
		</div>
	</div>
<!-- Content Mobile -->	

<div class="row jumbotron" id="content-mobile">
	
	<div class="col-md-5 ">
		<p style="color:#ff7700;font-size:25px">Bright Future with Esiezers</p>
		<h1 style="font-size: 30px">40000+ Job Selections</h1>
		<p>India's No one E-learning platform</p>
		<button class="btn  vidbtn" class="exp" onclick="location.href='explore.html'">
		Explore Courses</button>
		<button class="btn  vidbtn" class="exp" onclick="smoothScroll(document.getElementById('second'))">
		Know More</button>
	</div>	
	
	<div class="col-md-7">
		<div class="row">
			<div class="col-xs-3 col-sm-3"><img class="a" src="img/stu2.png" height="300px" title="I became c!" data-toggle="t1"></div>	
			<div class="col-xs-3 col-sm-3"><img class="b" src="img/stu4.png" height="300px" title="I became g!" data-toggle="t2"></div>
			<div class="col-xs-3 col-sm-3"><img class="c" src="img/stu1.png" height="300px" title="I became c!" data-toggle="t3"></div>
			<div class="col-xs-3 col-sm-3 "><img class="d" src="img/stu3.png" height="300px" title="I became c!" data-toggle="t4"></div>
		</div>
	</div>
	
</div>
<!-- /Content Mobile -->	
	
	
	


<h1 align="center" class=" animated bounce comprehessive">E-Learning Platform</h1>
<div  id="second" ></div><!-- For Scrolling-->
<br>
<center><img src="img/360degree6.png" style="width:80%;height:40%; border:1px solid grey"></center>

<br>
<h4 align="center">Crack Exams with Video Courses</h4>
<br><br>

<section class="container" ><!--  for desktop-->
	<div class="row animated boumceIn" >		
		<div class="col-md-6  col-xs-12" >
			<div class="box">
				<br>
				<img src="img/icon6.png">
				<br><br>
				<p>Video Courses</p>
				<p class="box_description">Study Made Simple With The Help Of Video Courses.</p>
				<br>
				<p class="show">View</p>
				<br>
			</div>
			<div class="box">
				<br>
				<img src="img/icon8.png">
				<br><br>
				<p>Online Mock Tests</p>
				<p class="box_description">
					A Large Series Of Mock Tests Available.
				</p>
				<br><p class="show">View</p><br>
			</div>
		</div>
		<div class="col-md-6  col-xs-12">
			<div class="box">
				<br>
				<img src="img/icon7.png">
				<br><br>
				<p>E-books & Practice<br>Material</p>
				<p class="box_description">Study With digital books.</p>
				<br><p class="show">View</p><br>
			</div>
		
			<div class="box">
				<br>
				<img src="img/icon9.png">
				<br><br>
				<p>Doubt Solving</p>
				<p class="box_description">Highly Qualified Staff Available To Solve Your Doubts.</p>
				<br><p class="show">View</p><br>
			</div>
		</div>	
	</div>
</section><!-- / for desktop-->

<br style="clear:both"><br style="clear:both">
<section class="container jumbotron" id="why-desktop"><!-- Last Section For Desktop -->
<h1 align="center" class="animated bounce whychoose">Why Choose Esiezers?<h1>

<div class="row">
	<div class="col-md-4 col-sm-4" align="center">
		<img src="img/icon1.png">
		<h1 class="sec2" align="center">5 Million+</h1>
		<p>Online Students</p>
	</div>
	<div class="col-md-4 col-sm-4" align="center">
		<img src="img/icon2.png">
		<h1 class="sec2" align="center"> 10 Thousand+</h1>
		<p> Job Selections</p>
	</div>
	
	<div class="col-md-4 col-sm-4" align="center">
		<img src="img/icon3.png">
		<h1 class="sec2" align="center">No. 1</h1>
		<p>Elearning</p>
	</div>
</div>
<div class="row">
	<div class="col-md-4 col-sm-4" align="center">
		<img src="img/icon4.png">
		<h1 class="sec2" align="center"> No.1</h1>
		<p>Elearning</p>
	</div>
	<div class="col-md-4 col-sm-4" align="center">
		<img src="img/icon5.png">
		<h1 class="sec2" align="center">No.1</h1>
		<p>Elearning</p>
	</div>
	<div class="col-md-4 col-sm-4" align="center">
		<img src="img/icon6.png">
		<h1 class="sec2" align="center">No.1</h1>
		<p>Elearning</p>
	</div>
</div>	
</section>

<section  id="why-mobile" class="container jumbotron"><!-- Last Section For MObile -->
<h1 align="center" class="animated bounce whychoose">Why Choose Esiezers?<h1>

<div class="row">
	<div class="col-xs-6" align="center">
		<img src="img/icon1.png">
		<h1 class="sec2" align="center">5 Million+</h1>
		<p>Online Students</p>
	</div>
	<div class="col-xs-6" align="center">
		<img src="img/icon2.png">
		<h1 class="sec2" align="center"> 25 Thousand+</h1>
		<p>Job Selections</p>
	</div>
</div>
<div class="row">
	<div class="col-xs-6" align="center">
		<img src="img/icon3.png">
		<h1 class="sec2" align="center">No.1</h1>
		<p>Elearning</p>
	</div>


	<div class="col-xs-6" align="center">
		<img src="img/icon4.png">
		<h1 class="sec2" align="center"> No.1</h1>
		<p>Elearning</p>
	</div>
</div>
<div class="row">
	<div class="col-xs-6" align="center">
		<img src="img/icon5.png">
		<h1 class="sec2" align="center">No.1</h1>
		<p>Elearning</p>
	</div>
	<div class="col-xs-6" align="center">
		<img src="img/icon6.png">
		<h1 class="sec2" align="center">No.1</h1>
		<p>Elearning</p>
	</div>
</div>	
</section>
<!-- Why choosee Section Over-->
  
   
<footer class="foot">
	<div class="row">
		<div class="col-md-2 col-sm-2 "></div>
			<div class="col-md-2 col-sm-2 "><a href="#" class="foot_menu">Home</a></div>
			<div class="col-md-2 col-sm-2 "><a href="#" class="foot_menu">About Us</a></div>
			<div class="col-md-2 col-sm-2 "><a href="#" class="foot_menu">Contact Us</a></div>
			<div class="col-md-2 col-sm-2 "><a href="#" class="foot_menu">News</a></div>	
		<div class="col-md-2 col-sm-2 "></div>
	</div>
	<br style="clear:both">
	
	<div class="row">
<div class="col-md-4 col-sm-4 col-xs-2"></div>
				
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

<div class='demopadding'>
<h5 align="center" style="color:white">Follw US On</h5>
  <div class='icon social fb' style="color: #4267b2;background:black;"><i class='fa fa-facebook'></i></div>
  <div class='icon social tw' style="color: #4099FF; background:black;"><i class='fa fa-twitter'></i></div>
  <div class='icon social in' style="color: #007bb6; background:black;"><i class='fa fa-linkedin'></i></div>
</div>  
<div class="col-md-4 col-sm-4 col-xs-2"></div>
</div>
		
<br>
	<div class="row">
		<div class="col-md-12 col-sm-12 col-xs-12" align="center" style="font-size:12px" >All Right Reserved Copy right</div>
	</div>
</footer>

	<script>
	var x;
	 x = setInterval(function(){k();}, 4000);
	function k1(){
	x = setInterval(function(){k();}, 4000);
	}	
	function stop() {
  clearInterval(x);
}
	</script>
	
<!-- Pop Up Form-->	
<div class="container">	  
  <!-- The Modal -->
  <div class="modal" id="myModal">
    <div class="modal-dialog">
      <div class="modal-content">
      
        <!-- Modal Header -->
        <div class="modal-header">
		 <!-- <button type="button" class="close" data-dismiss="modal" >&times;</button>-->
		  <i class="fa fa-times close" data-dismiss="modal" style="font-size: 40px"></i>
          <h4 class="modal-title">Login</h4>
        
        </div>
        
        <!-- Modal body -->
        <div class="modal-body " style="padding: 10%" >
          
			<div class="row">
				<div class="col-md-12 col-sm-12 col-xs-12">
					<button class="form-control btn btn-info " style="background:#4568B2;height:40px">
					<i class="fa fa-facebook"></i>&nbsp;Continue with Facebook
					</button>
				</div>
				<br><br><br>
				
				<div class="col-md-12 col-sm-12 col-xs-12">
					<button class="form-control btn btn-default" style="height:40px;border:2px solid black">
					<i class="fa fa-google"></i>&nbsp;Continue with Google
					</button>
				</div>
				<br><br><br>
				<div class="col-md-5 col-sm-5 col-xs-5" ></div>
				<div class="col-md-2 col-sm-2 col-xs-2" align="center"><b>OR</b></div>
				<div class="col-md-5 col-sm-5 col-xs-5"></div><br><br><br>
		<form id="login_form" >
				<div class="col-md-12 col-sm-12 col-xs-12">
<input type="email" name="log_email" id="log_email" class="form-control" style="height:40px;" placeholder="Email">					
				</div>
				<br><br><br>
				<div class="col-md-12 col-sm-12 col-xs-12 form-group has-feedback">
					<input type="password" name="pass" id="log_pass" class="form-control" style="height:40px;" placeholder="Password" required>
					<br>
<button type="button" class="btn btn-primary" data-dismiss="modal" data-toggle="modal" data-target="#forget">Forgot?</button>			
				</div>
				
				<br><br><br>
				<div class="col-md-12 col-sm-12 col-xs-12">
<button type="submit" name="login" class="btn btn-warning form-control" style="height:40px;font-size:20px" onclick="return validate_login();" value="x">Login</button>
				</div>
		</form>
				<br><br><br>
				<div class="col-md-12 col-sm-12 col-xs-12">
					<p align="center" style="font-size:16px">Don't Have an Account?
						<span ><a href="#" style="color: orange" data-dismiss="modal" data-toggle="modal" data-target="#sign">SIgn Up</a></span>
					</p>
				</div>			
			</div>		  
        </div>        
        <!-- Modal footer -->
        <!--<div class="modal-footer">
          <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
        </div>-->        
      </div>
    </div>
  </div> 
</div>
<!-- Modal 2 forgot pass-->
<div class="modal" id="forget" style="margin: auto">
    <div class="modal-dialog">
      <div class="modal-content">
			<div class="modal-header">
		<!--<button type="button" class="close" data-dismiss="modal">&times;</button>-->
		 <i class="fa fa-times close" data-dismiss="modal" style="font-size: 40px"></i>
          <h4 class="modal-title"><i class="fa fa-arrow-left" data-dismiss="modal" data-toggle="modal" data-target="#myModal"></i>&nbsp;&nbsp;Forget Password?</h4>         
        </div>       
        <!-- Modal body -->
<div class="modal-body " style="padding: 10%" >
    <form>
		<div class="row">
			<div class="col-md-12 col-sm-12 col-xs-12">
				<p>	Enter the email address associated with your account, and we'll email you an OTP to verify it's you.</p>
			</div>
			<br><br><br>
			<div class="col-md-12 col-sm-12 col-xs-12">
<input type="email" id="for_email"name="name" class="form-control" style="height:40px;" placeholder="Email" required>					
			</div>				
			<div class="col-md-12 col-sm-12 col-xs-12">
			<br>
<button name="login" type="submmit" class="btn btn-warning form-control" style="height:40px;font-size:20px" onclick="return validate_forgot();">Send OTP</button>
			</div>
		</div>
	</form>
</div>
	</div>
	</div>
</div>
<!-- Modal 3 Sign Up-->
<div class="modal" id="sign" style="margin: auto">
    <div class="modal-dialog">
      <div class="modal-content">
			<div class="modal-header">
		 <i class="fa fa-times close" data-dismiss="modal" style="font-size: 40px"></i>
		 <h4 class="modal-title"><i class="fa fa-arrow-left" data-dismiss="modal" data-toggle="modal" data-target="#myModal"></i>&nbsp;&nbsp;Sing Up</h4>
         
        </div>
        
        <!-- Modal body -->
<div class="modal-body " style="padding: 10%" >
        
			<div class="row">
				<div class="col-md-12 col-sm-12 col-xs-12">
					<button class="form-control btn btn-info " style="background:#4568B2;height:40px">
					<i class="fa fa-facebook"></i>&nbsp;Continue with Facebook
					</button>
				</div>
				<br><br><br>
				
				<div class="col-md-12 col-sm-12 col-xs-12">
					<button class="form-control btn btn-default" style="height:40px;border:2px solid black">
					<i class="fa fa-google"></i>&nbsp;Continue with Google
					</button>
				</div>
				<br><br><br>
				<div class="col-md-5 col-sm-5 col-xs-5" ></div>
				<div class="col-md-2 col-sm-2 col-xs-2" align="center"><b>OR</b></div>
				<div class="col-md-5 col-sm-5 col-xs-5"></div><br><br><br>
				
				<div class="col-md-12 col-sm-12 col-xs-12">
				<button class="form-control btn btn-default" style="height:40px;border:2px solid orange;color:orange">
					<i class="fa fa-google"></i>&nbsp;SignUp with Email
					</button>
				</div>
				<br><br><br>
				<div class="col-md-12 col-sm-12 col-xs-12">
					<button name="login" class="btn btn-warning form-control" style="height:40px;font-size:20px">Login</button>
				</div>
				
				<br><br><br>
				<div class="col-md-12 col-sm-12 col-xs-12">
					<p align="center" style="font-size:16px">Already Have an Account?
						<span ><a href="#" style="color: orange" data-dismiss="modal" data-toggle="modal" data-target="#myModal">Login</a></span>
					</p>
				</div>
			
			</div>
		
        </div>
  </div>
    </div>
  </div>
  </div>
  
</div>
</div>	
</body>
</html>