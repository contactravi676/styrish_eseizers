<!DOCTYPE html>
<html lang="en">
<head>
  <title>Styrish.....</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
 
  <style>
  .carousel-inner > .item > img,
  .carousel-inner > .item > a > img {
    width: 70%;
    margin: auto;
  }
  </style>
</head>
<body>

<div class="container">
  <br>
  <div id="myCarousel" class="carousel slide" data-ride="carousel">
    <!-- Indicators -->
    <ol class="carousel-indicators">
      <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
      <li data-target="#myCarousel" data-slide-to="1"></li>
      <li data-target="#myCarousel" data-slide-to="2"></li>
      <li data-target="#myCarousel" data-slide-to="3"></li>
    </ol>

    <!-- Wrapper for slides -->
    <div class="carousel-inner" role="listbox">

      <div class="item active">
         <a href="course.jsp"><img src="image\banner\bankTopBanner.jpg" alt="Banking" width="460" height="345"></a>
        <div class="carousel-caption">
          <h3>Banking Course</h3>
          <p>Best Online Banking Course</p>
        </div>
      </div>

      <div class="item">
       <img src="image\banner\railwayTopBanner.jpg" alt="Railways" width="460" height="345">
        <div class="carousel-caption">
          <h3>Railway Course</h3>
          <p>Best Online Railway Course</p>
        </div>
      </div>
    
      <div class="item">
       <img src="image\banner\schoolTopBanner.jpg" alt="Teaching" width="460" height="345">
        <div class="carousel-caption">
          <h3>Teaching Course</h3>
          <p>Best Online Teaching Course</p>
        </div>
      </div>

     
  
    </div>

    <!-- Left and right controls -->
    <a class="left carousel-control" href="#myCarousel" role="button" data-slide="prev">
      <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
      <span class="sr-only">Previous</span>
    </a>
    <a class="right carousel-control" href="#myCarousel" role="button" data-slide="next">
      <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
      <span class="sr-only">Next</span>
    </a>
  </div>
</div>

</body>
</html>
