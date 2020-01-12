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